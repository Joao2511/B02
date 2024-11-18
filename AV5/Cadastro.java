import javax.swing.*;
import java.awt.*;

// Classe base
public abstract class Cadastro {
    protected final JFrame principal;

    public Cadastro(JFrame principal) {
        this.principal = principal;
    }

    // Método exibir a ser sobrescrito pelas subclasses
    public abstract void exibir();

    // Método comum para criar os botões de cadastro
    protected JPanel criarBotoesCadastro(JDialog dialog) {
        JPanel painelBotoes = new JPanel(new FlowLayout());
        String[] labels = {"Incluir", "Alterar", "Excluir", "Consultar", "Cancelar", "Sair"};
        for (String label : labels) {
            JButton button = new JButton(label);
            if (label.equals("Sair")) {
                button.addActionListener(e -> dialog.setVisible(false));
            } else {
                button.addActionListener(e -> JOptionPane.showMessageDialog(dialog, "Botão " + label + " clicado!"));
            }
            painelBotoes.add(button);
        }
        return painelBotoes;
    }
}

// Subclasse para Cadastro de Usuários
class CadastroUsuarios extends Cadastro {
    public CadastroUsuarios(JFrame principal) {
        super(principal);
    }

    @Override
    public void exibir() {
        JDialog dialog = new JDialog(principal, "Cadastro de Usuários", true);
        dialog.setSize(600, 300);
        dialog.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Cadastro de Usuários", SwingConstants.CENTER);
        dialog.add(titulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
        painelCampos.add(new JLabel("Usuário:"));
        painelCampos.add(new JTextField(25));
        painelCampos.add(new JLabel("Senha:"));
        painelCampos.add(new JPasswordField(15));
        painelCampos.add(new JLabel("Email:"));
        painelCampos.add(new JTextField(30));
        painelCampos.add(new JLabel("Ativo:"));
        painelCampos.add(new JRadioButton());

        dialog.add(painelCampos, BorderLayout.CENTER);
        dialog.add(criarBotoesCadastro(dialog), BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(principal);
        dialog.setVisible(true);
    }
}

// Subclasse para Cadastro de Pessoas
class CadastroPessoas extends Cadastro {
    public CadastroPessoas(JFrame principal) {
        super(principal);
    }

    @Override
    public void exibir() {
        JDialog dialog = new JDialog(principal, "Cadastro de Pessoas", true);
        dialog.setSize(600, 300);
        dialog.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Cadastro de Pessoas", SwingConstants.CENTER);
        dialog.add(titulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel(new GridLayout(7, 2, 5, 5));
        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(new JTextField(40));
        painelCampos.add(new JLabel("Endereço:"));
        painelCampos.add(new JTextField(60));
        painelCampos.add(new JLabel("Cidade:"));
        painelCampos.add(new JTextField(40));
        painelCampos.add(new JLabel("UF:"));
        painelCampos.add(new JTextField(2));
        painelCampos.add(new JLabel("Email:"));
        painelCampos.add(new JTextField(30));
        painelCampos.add(new JLabel("Telefone:"));
        painelCampos.add(new JTextField(20));
        painelCampos.add(new JLabel("Sexo:"));
        painelCampos.add(new JComboBox<>(new String[]{"Masculino", "Feminino"}));

        dialog.add(painelCampos, BorderLayout.CENTER);
        dialog.add(criarBotoesCadastro(dialog), BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(principal);
        dialog.setVisible(true);
    }
}
