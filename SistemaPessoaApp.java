import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SistemaPessoaApp {
    public static void main(String[] args) {
        SistemaPessoa sistemaPessoa = new SistemaPessoa();
        sistemaPessoa.iniciar();
    }
}

class SistemaPessoa {
    private final String versaoSistema = "12.1.2024";
    private final String nomeUsuario = "denys.silva";
    private final String dataAcesso;

    private JFrame principal;

    public SistemaPessoa() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        dataAcesso = dateFormat.format(new Date());
    }

    public void iniciar() {
        criarJanelaPrincipal();
        adicionarComponentes();
        principal.setVisible(true);
    }

    private void criarJanelaPrincipal() {
        principal = new JFrame("Sistema Pessoa");
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.setSize(800, 800);
        principal.setLocationRelativeTo(null);
    }

    private void adicionarComponentes() {
        MenuSistema menuSistema = new MenuSistema(principal);
        principal.setJMenuBar(menuSistema.criarMenu());

        JTextArea areaTrabalho = new JTextArea();
        principal.getContentPane().add(BorderLayout.CENTER, areaTrabalho);

        Rodape rodape = new Rodape(versaoSistema, nomeUsuario, dataAcesso);
        principal.getContentPane().add(BorderLayout.SOUTH, rodape.criarRodape());
    }
}

class MenuSistema {
    private final JFrame principal;

    public MenuSistema(JFrame principal) {
        this.principal = principal;
    }

    public JMenuBar criarMenu() {
        JMenuBar menuPrincipal = new JMenuBar();

        JMenu menuCadastro = new JMenu("Cadastro");
        JMenu menuVisualizacao = new JMenu("Visualização");
        JMenu menuSair = new JMenu("Sair");

        menuSair.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(javax.swing.event.MenuEvent e) {
                System.exit(0);
            }
            public void menuDeselected(javax.swing.event.MenuEvent e) {}
            public void menuCanceled(javax.swing.event.MenuEvent e) {}
        });

        menuPrincipal.add(menuCadastro);
        menuPrincipal.add(menuVisualizacao);
        menuPrincipal.add(menuSair);

        JMenuItem itemMenuCadastroUsuarios = new JMenuItem("Usuários");
        JMenuItem itemMenuCadastroPessoas = new JMenuItem("Pessoas");
        menuCadastro.add(itemMenuCadastroUsuarios);
        menuCadastro.add(itemMenuCadastroPessoas);

        JMenuItem itemMenuVisualizacaoListaUsuarios = new JMenuItem("Lista de usuários");
        JMenuItem itemMenuVisualizacaoListaPessoas = new JMenuItem("Lista de pessoas");
        menuVisualizacao.add(itemMenuVisualizacaoListaUsuarios);
        menuVisualizacao.add(itemMenuVisualizacaoListaPessoas);

        itemMenuCadastroUsuarios.addActionListener(e -> abrirCadastroUsuarios());
        itemMenuCadastroPessoas.addActionListener(e -> abrirCadastroPessoas());
        itemMenuVisualizacaoListaUsuarios.addActionListener(e -> abrirListaUsuarios());
        itemMenuVisualizacaoListaPessoas.addActionListener(e -> abrirListaPessoas());

        return menuPrincipal;
    }

    private void abrirCadastroUsuarios() {
        new DialogCadastroUsuarios(principal).mostrar();
    }

    private void abrirCadastroPessoas() {
        new DialogCadastroPessoas(principal).mostrar();
    }

    private void abrirListaUsuarios() {
        new DialogListaUsuarios(principal).mostrar();
    }

    private void abrirListaPessoas() {
        new DialogListaPessoas(principal).mostrar();
    }
}

class Rodape {
    private final String versaoSistema;
    private final String nomeUsuario;
    private final String dataAcesso;

    public Rodape(String versaoSistema, String nomeUsuario, String dataAcesso) {
        this.versaoSistema = versaoSistema;
        this.nomeUsuario = nomeUsuario;
        this.dataAcesso = dataAcesso;
    }

    public JPanel criarRodape() {
        JPanel painelRodape = new JPanel();
        JLabel labelRodape = new JLabel("Versão: " + versaoSistema + "    Usuário: " + nomeUsuario + "    Data de acesso: " + dataAcesso);
        painelRodape.add(labelRodape);
        return painelRodape;
    }
}

class DialogCadastroUsuarios {
    private final JDialog dialog;

    public DialogCadastroUsuarios(JFrame principal) {
        dialog = new JDialog(principal, "Cadastro de Usuários", true);
        dialog.setSize(600, 300);
        dialog.setLayout(new BorderLayout());
    }

    public void mostrar() {
        dialog.add(new JLabel("Cadastro de Usuários", SwingConstants.CENTER), BorderLayout.NORTH);

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
        dialog.add(criarPainelBotoes(), BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setVisible(true);
    }

    private JPanel criarPainelBotoes() {
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

class DialogCadastroPessoas {
    private final JDialog dialog;

    public DialogCadastroPessoas(JFrame principal) {
        dialog = new JDialog(principal, "Cadastro de Pessoa", true);
        dialog.setSize(600, 300);
        dialog.setLayout(new BorderLayout());
    }

    public void mostrar() {
        dialog.add(new JLabel("Cadastro de Pessoa", SwingConstants.CENTER), BorderLayout.NORTH);

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
        JComboBox<String> comboSexo = new JComboBox<>(new String[]{"Masculino", "Feminino"});
        painelCampos.add(comboSexo);

        dialog.add(painelCampos, BorderLayout.CENTER);
        dialog.add(criarPainelBotoes(), BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setVisible(true);
    }

    private JPanel criarPainelBotoes() {
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

// Classes para as listas de usuários e pessoas (apenas estrutura)
class DialogListaUsuarios {
    private final JDialog dialog;

    public DialogListaUsuarios(JFrame principal) {
        dialog = new JDialog(principal, "Lista de Usuários", true);
        dialog.setSize(750, 650);
        dialog.setLayout(new BorderLayout());
    }

    public void mostrar() {
        dialog.add(new JLabel("Lista de Usuários", SwingConstants.CENTER), BorderLayout.NORTH);
        dialog.add(new JTextArea("Exemplo de lista de usuários..."), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dialog.setVisible(false));
        dialog.add(btnFechar, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setVisible(true);
    }
}

class DialogListaPessoas {
    private final JDialog dialog;

    public DialogListaPessoas(JFrame principal) {
        dialog = new JDialog(principal, "Lista de Pessoas", true);
        dialog.setSize(750, 650);
        dialog.setLayout(new BorderLayout());
    }

    public void mostrar() {
        dialog.add(new JLabel("Lista de Pessoas", SwingConstants.CENTER), BorderLayout.NORTH);
        dialog.add(new JTextArea("Exemplo de lista de pessoas..."), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dialog.setVisible(false));
        dialog.add(btnFechar, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(dialog.getOwner());
        dialog.setVisible(true);
    }
}
