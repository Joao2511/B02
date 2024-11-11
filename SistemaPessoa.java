import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SistemaPessoa extends JPanel {
    private final String versaoSistema;
    private final String nomeUsuario;
    private final String dataAcesso;

    public SistemaPessoa(String nomeUsuario, String versaoSistema) {
        this.nomeUsuario = nomeUsuario;
        this.versaoSistema = versaoSistema;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        this.dataAcesso = dateFormat.format(new Date());
        
        configurarInterface();
    }

    private void configurarInterface() {
        setLayout(new BorderLayout());

        JMenuBar menuPrincipal = criarMenuPrincipal();

        JTextArea areaTrabalho = new JTextArea();

        JPanel painelRodape = criarPainelRodape();

        add(menuPrincipal, BorderLayout.NORTH);
        add(areaTrabalho, BorderLayout.CENTER);
        add(painelRodape, BorderLayout.SOUTH);
    }

    private JMenuBar criarMenuPrincipal() {
        JMenuBar menuPrincipal = new JMenuBar();

        JMenu menuCadastro = new JMenu("Cadastro");
        JMenu menuVisualizacao = new JMenu("Visualização");
        JMenu menuSair = new JMenu("Sair");

   
        menuSair.addMenuListener(new javax.swing.event.MenuListener() {
            @Override
            public void menuSelected(javax.swing.event.MenuEvent e) {
                System.exit(0);
            }

            @Override
            public void menuDeselected(javax.swing.event.MenuEvent e) { }

            @Override
            public void menuCanceled(javax.swing.event.MenuEvent e) { }
        });

        menuCadastro.add(new JMenuItem("Usuários"));
        menuCadastro.add(new JMenuItem("Pessoas"));

        menuVisualizacao.add(new JMenuItem("Lista de usuários"));
        menuVisualizacao.add(new JMenuItem("Lista de pessoas"));

        menuPrincipal.add(menuCadastro);
        menuPrincipal.add(menuVisualizacao);
        menuPrincipal.add(menuSair);

        return menuPrincipal;
    }

    private JPanel criarPainelRodape() {
        JPanel painelRodape = new JPanel();
        JLabel labelRodape = new JLabel("Versão: " + versaoSistema + "               Usuário: " + nomeUsuario + "               Data de acesso: " + dataAcesso);
        painelRodape.add(labelRodape);
        return painelRodape;
    }

    public static void main(String[] args) {
        JFrame principal = new JFrame("Sistema Pessoa");
        SistemaPessoa sistemaPessoa = new SistemaPessoa("denys.silva", "12.1.2024");

        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.setSize(800, 800);
        principal.setLocationRelativeTo(null);
        principal.add(sistemaPessoa);
        principal.setVisible(true);
    }
}
