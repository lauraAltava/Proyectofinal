import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class usuarioLogin extends JFrame {

    JTextField usernameField;
    private JTextField passwordField;
    public usuarioLogin() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        // Crear componente de título
        JLabel titleLabel = new JLabel("INICIAR SESIÓN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.LEFT);

        Color backgroundColor = new Color(85, 211, 83);
        Color buttonColor = new Color(255, 255, 255);
        Color textColor = new Color(85, 211, 83);


        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(backgroundColor);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,0,20,10));

        JButton backButton = new JButton("Atrás");
        backButton.setBackground(buttonColor);
        backButton.setForeground(textColor);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));



        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        usernameLabel.setForeground(buttonColor);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordLabel.setForeground(buttonColor);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBackground(buttonColor);
        registerButton.setForeground(textColor);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));

        titleLabel.setForeground(buttonColor);

        JButton loginButton = new JButton("Entrar");
        loginButton.setBackground(buttonColor);
        loginButton.setForeground(textColor);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(backButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(loginButton);

        loginPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(backgroundColor);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 70, 0, 50));


        JPanel fieldPanel = new JPanel(new GridLayout(2, 2, 10, 20));
        fieldPanel.setBackground(backgroundColor);
        fieldPanel.add(usernameLabel);
        fieldPanel.add(usernameField);
        fieldPanel.add(passwordLabel);
        fieldPanel.add(passwordField);
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        loginPanel.add(fieldPanel, BorderLayout.CENTER);

        add(loginPanel);
        add(titlePanel,BorderLayout.NORTH);

        loginButton.addActionListener(e -> openPanelUsuario());
        registerButton.addActionListener(e -> openRegistrationScreen());
        backButton.addActionListener(e -> openTipoUsuarioScreen());
    }

    public void openPanelUsuario() {
        SwingUtilities.invokeLater(() -> {
            String resultado="";
            if((usernameField.getText().isEmpty() || passwordField.getText().isEmpty())) {
                JOptionPane.showMessageDialog(usuarioLogin.this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);

            }
            else{
            try {
                //C:\Users\Alejandro\IdeaProjects\dropin2-main\Dropin\src\main\resources\baseDropin

                Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/baseDropin ", "username", "password");

                String Nombre=usernameField.getText();
                String consulta="SELECT contraseña from Usuarios where nombre= ?" ;


                PreparedStatement statement = connection.prepareStatement(consulta);
                statement.setString(1, Nombre);

                ResultSet resultSet = statement.executeQuery();

// Recorrer los resultados
                while (resultSet.next()) {
                    // Obtener el valor del conteo
                    resultado = resultSet.getString("contraseña");
                     // Realizar las operaciones necesarias con el valor del conteo
                    // Por ejemplo, puedes asignarlo a una variable o utilizarlo para establecer el texto en un JLabel
                }
                // Pasamos por el array
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (resultado.equals(passwordField.getText())){
            usuarioOpciones panelUsuario = new usuarioOpciones();
            panelUsuario.setNombre(usernameField.getText());
            panelUsuario.setVisible(true);
            dispose(); // Cierra la ventana de inicio de sesión al abrir la pantalla de usuario
        }

        else{
                JOptionPane.showMessageDialog(usuarioLogin.this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }});
    }

    public void openRegistrationScreen() {
        SwingUtilities.invokeLater(() -> {
            usuarioRegister registration = new usuarioRegister();
            registration.setVisible(true);
            dispose(); // Cierra la ventana de inicio de sesión al abrir la ventana de registro
        });
    }

    public void openTipoUsuarioScreen() {
        SwingUtilities.invokeLater(() -> {
            tipoUsuario tipoUsuario = new tipoUsuario();
            tipoUsuario.setVisible(true);
            dispose(); // Cierra la ventana de inicio de sesión al volver a la pantalla de TipoUsuario
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            usuarioLogin login = new usuarioLogin();
            login.setVisible(true);
        });
    }
}
