import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SistemaPessoa extends JFrame {

    public SistemaPessoa() {
        setTitle("Sistema de Pessoa");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criação da barra de menu
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem itemUsuarios = new JMenuItem("Usuários");
        JMenuItem itemPessoas = new JMenuItem("Pessoas");
        menuCadastro.add(itemUsuarios);
        menuCadastro.add(itemPessoas);
        menuBar.add(menuCadastro);

        JMenu menuVisualizacao = new JMenu("Visualização");
        JMenuItem itemListaUsuarios = new JMenuItem("Lista de usuário");
        JMenuItem itemListaPessoas = new JMenuItem("Lista de Pessoas");
        menuVisualizacao.add(itemListaUsuarios);
        menuVisualizacao.add(itemListaPessoas);
        menuBar.add(menuVisualizacao);

        JMenu menuSair = new JMenu("Sair");
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0)); // Sair do sistema
        menuSair.add(itemSair);
        menuBar.add(menuSair);

        // Adiciona a barra de menu ao frame
        setJMenuBar(menuBar);

        // Painel de entrada de dados
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2, 5, 5));

        panel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Endereço:"));
        JTextField enderecoField = new JTextField();
        panel.add(enderecoField);

        panel.add(new JLabel("Cidade:"));
        JTextField cidadeField = new JTextField();
        panel.add(cidadeField);

        panel.add(new JLabel("UF:"));
        JTextField ufField = new JTextField(2);
        panel.add(ufField);

        panel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Telefone:"));
        JTextField telefoneField = new JTextField();
        panel.add(telefoneField);

        panel.add(new JLabel("Sexo:"));
        JComboBox<String> sexoComboBox = new JComboBox<>(new String[]{"Masculino", "Feminino", "Outro"});
        panel.add(sexoComboBox);

        // Adiciona o painel de entrada de dados ao frame
        add(panel, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        String[] buttonLabels = {"Incluir", "Alterar", "Excluir", "Consultar", "Cancelar"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener(label));
            buttonPanel.add(button);
        }

        // Rodapé com informações
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(Color.LIGHT_GRAY);
        JLabel versionLabel = new JLabel("Versão: 12.1.2024 Usuário: denys.silva Data de acesso: 20/09/2024 10:58");
        footerPanel.add(versionLabel);

        // Painel para os botões e rodapé
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(footerPanel, BorderLayout.SOUTH);

        // Adiciona o painel de entrada de dados e o painel de rodapé ao frame
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private class ButtonClickListener implements ActionListener {
        private String buttonText;

        public ButtonClickListener(String buttonText) {
            this.buttonText = buttonText;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Botão clicado: " + buttonText);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaPessoa sistema = new SistemaPessoa();
            sistema.setVisible(true);
        });
    }
}
