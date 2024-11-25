/* Classe para executar o sistema utilizando polimorfismo com Method Overriding */
import javax.swing.*;
import java.awt.*;

public class SistemaPessoa {

    public static void main(String[] args) {
        new SistemaPessoa().iniciarSistema();
    }

    public void iniciarSistema() {
        JFrame principal = new JFrame("Sistema Pessoa");
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        principal.setSize(800, 800);

        // Menu principal
        JMenuBar menuPrincipal = new JMenuBar();
        JMenu menuCadastro = new JMenu("Cadastro");
        JMenu menuVisualizacao = new JMenu("Visualização");
        JMenu menuSair = new JMenu("Sair");

        // Ação do menu "Sair"
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

        // Itens do menu "Cadastro"
        JMenuItem itemMenuCadastroUsuarios = new JMenuItem("Usuários");
        JMenuItem itemMenuCadastroPessoas = new JMenuItem("Pessoas");
        menuCadastro.add(itemMenuCadastroUsuarios);
        menuCadastro.add(itemMenuCadastroPessoas);

        // Itens do menu "Visualização"
        JMenuItem itemMenuVisualizacaoListaUsuarios = new JMenuItem("Lista de usuários");
        JMenuItem itemMenuVisualizacaoListaPessoas = new JMenuItem("Lista de pessoas");
        menuVisualizacao.add(itemMenuVisualizacaoListaUsuarios);
        menuVisualizacao.add(itemMenuVisualizacaoListaPessoas);

        // Área de trabalho e rodapé
        JTextArea areaTrabalho = new JTextArea();
        JPanel painelRodape = new JPanel();
        JLabel labelRodape = new JLabel(
            "Versão: " + ConfiguracoesSistema.VERSAO_SISTEMA +
            "    Usuário: " + ConfiguracoesSistema.NOME_USUARIO +
            "    Data de acesso: " + ConfiguracoesSistema.DATA_ACESSO
        );
        painelRodape.add(labelRodape);

        principal.getContentPane().add(BorderLayout.NORTH, menuPrincipal);
        principal.getContentPane().add(BorderLayout.CENTER, areaTrabalho);
        principal.getContentPane().add(BorderLayout.SOUTH, painelRodape);

        // Ações dos itens de menu utilizando polimorfismo
        itemMenuCadastroUsuarios.addActionListener(e -> {
            Cadastro cadastroUsuarios = new CadastroUsuarios(principal);
            cadastroUsuarios.exibir();
        });

        itemMenuCadastroPessoas.addActionListener(e -> {
            Cadastro cadastroPessoas = new CadastroPessoasFisica(principal);
            cadastroPessoas.exibir();
        });

        itemMenuVisualizacaoListaUsuarios.addActionListener(e -> {
            Lista listaUsuarios = new ListaUsuarios(principal);
            listaUsuarios.exibir();
        });

        itemMenuVisualizacaoListaPessoas.addActionListener(e -> {
            Lista listaPessoas = new ListaPessoas(principal);
            listaPessoas.exibir();
        });

        // Configuração final da janela principal
        principal.setLocationRelativeTo(null);
        principal.setVisible(true);
    }
}