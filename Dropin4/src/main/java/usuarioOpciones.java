import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class usuarioOpciones extends JFrame {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public usuarioOpciones() {


        // Configurar la pantalla siguiente
        setTitle("Pantalla siguiente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);
        setLocationRelativeTo(null);

        // Crear componente de título
        JLabel titleLabel = new JLabel("Perfil Usuario");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(JLabel.LEFT);


        // Crear componentes
        JButton saldoButton = new JButton("Saldo");
        JButton reciclarButton = new JButton("Reciclar");
        JButton backButton = new JButton("Atrás");

        // Establecer colores personalizados
        Color backgroundColor = new Color(85, 211, 83);
        Color buttonColor = new Color(255, 255, 255);
        Color textColor = new Color(85, 211, 83);

        // Configurar estilo de los botones
        Font buttonFont = new Font("Arial", Font.BOLD, 12);

        titleLabel.setForeground(buttonColor);
        saldoButton.setBackground(buttonColor);
        saldoButton.setForeground(textColor);
        saldoButton.setFont(buttonFont);

        reciclarButton.setBackground(buttonColor);
        reciclarButton.setForeground(textColor);
        reciclarButton.setFont(buttonFont);

        backButton.setBackground(buttonColor);
        backButton.setForeground(textColor);
        backButton.setFont(buttonFont);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(backgroundColor);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(50, 95, 0, 50));

        // Crear un panel para contener los botones
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 100, 50));

        // Agregar botones al panel
        buttonPanel.add(saldoButton);
        buttonPanel.add(reciclarButton);
        buttonPanel.add(backButton);

        // Agregar el panel al marco
        add(titlePanel,BorderLayout.NORTH);
        add(buttonPanel,BorderLayout.CENTER);

        // Acción del botón Saldo
        saldoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    Saldo saldo = new Saldo(getNombre());
                    saldo.setNombre(getNombre());
                    saldo.setVisible(true);
                    dispose(); // Cierra la ventana actual
                });
            }
        });

        // Acción del botón Reciclar
        reciclarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    Reciclar reciclar = new Reciclar();
                    reciclar.setNombre(getNombre());
                    reciclar.setVisible(true);
                    dispose(); // Cierra la ventana actual
                });
            }
        });

        // Acción del botón Atrás
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    usuarioLogin usuarioLogin = new usuarioLogin();
                    usuarioLogin.setVisible(true);
                    dispose(); // Cierra la ventana actual
                });
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                usuarioOpciones panelUsuario = new usuarioOpciones();
                panelUsuario.setVisible(true);
            }
        });
    }
}
