import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaPessoa extends JFrame {

    public SistemaPessoa() {
        // Configurações da janela principal
        setTitle("Cadastro de Usuários");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));
        
        // Campos de entrada
        panel.add(new JLabel("Usuário:"));
        JTextField usuarioField = new JTextField();
        panel.add(usuarioField);

        panel.add(new JLabel("Senha:"));
        JPasswordField senhaField = new JPasswordField();
        panel.add(senhaField);

        panel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Ativo:"));
        JRadioButton ativoCheckBox = new JRadioButton();
        panel.add(ativoCheckBox);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Definição dos botões com ActionListener para mensagens
        String[] buttonLabels = {"Incluir", "Alterar", "Excluir", "Consultar", "Cancelar", "Sair"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener(label));
            buttonPanel.add(button);
        }

        // Adiciona os painéis ao frame
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ButtonClickListener implements ActionListener {
        private String buttonText;

        public ButtonClickListener(String buttonText) {
            this.buttonText = buttonText;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttonText.equals("Sair")) {
                dispose(); // Fecha a janela
            } else {
                JOptionPane.showMessageDialog(null, "Botão clicado: " + buttonText);
            }
        }
    }

    public static void main(String[] args) {
        // Cria e exibe a janela do sistema
        SwingUtilities.invokeLater(() -> {
            SistemaPessoa frame = new SistemaPessoa();
            frame.setVisible(true);
        });
    }
}
