import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcessoAplicativo extends JFrame {

    private JTextField usuarioField;
    private JPasswordField senhaField;

    public AcessoAplicativo() {
        setTitle("Acesso ao aplicativo");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioField = new JTextField(15);
        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField(15);
        usuarioField.setPreferredSize(new Dimension(200, 20));
        senhaField.setPreferredSize(new Dimension(200, 20));

        inputPanel.add(usuarioLabel);
        inputPanel.add(usuarioField);
        inputPanel.add(senhaLabel);
        inputPanel.add(senhaField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton confirmarButton = new JButton("Confirmar");
        JButton cancelarButton = new JButton("Cancelar");
        buttonPanel.add(cancelarButton);
        buttonPanel.add(confirmarButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String senha = new String(senhaField.getPassword());

                if (usuario.equals("denys.silva") && senha.equals("Teste@2024")) {
                    JOptionPane.showMessageDialog(null, "Acesso confirmado!");
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválido!");
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AcessoAplicativo app = new AcessoAplicativo();
            app.setVisible(true);
        });
    }
}
