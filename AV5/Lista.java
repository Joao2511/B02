import javax.swing.*;
import java.awt.*;

// Classe base
public abstract class Lista {
    protected final JFrame principal;

    public Lista(JFrame principal) {
        this.principal = principal;
    }

    // Método exibir a ser sobrescrito pelas subclasses
    public abstract void exibir();

    // Método comum para criar botão de fechar
    protected JButton criarBotaoFechar(JDialog dialog) {
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dialog.setVisible(false));
        return btnFechar;
    }
}

// Subclasse para Lista de Usuários
class ListaUsuarios extends Lista {
    public ListaUsuarios(JFrame principal) {
        super(principal);
    }

    @Override
    public void exibir() {
        JDialog dialog = new JDialog(principal, "Lista de Usuários", true);
        dialog.setSize(750, 650);
        dialog.setLayout(new BorderLayout());

        dialog.add(new JLabel("Lista de Usuários", SwingConstants.CENTER), BorderLayout.NORTH);
        dialog.add(new JTextArea("Exemplo de lista de usuários..."), BorderLayout.CENTER);
        dialog.add(criarBotaoFechar(dialog), BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(principal);
        dialog.setVisible(true);
    }
}

// Subclasse para Lista de Pessoas
class ListaPessoas extends Lista {
    public ListaPessoas(JFrame principal) {
        super(principal);
    }

    @Override
    public void exibir() {
        JDialog dialog = new JDialog(principal, "Lista de Pessoas", true);
        dialog.setSize(750, 650);
        dialog.setLayout(new BorderLayout());

        dialog.add(new JLabel("Lista de Pessoas", SwingConstants.CENTER), BorderLayout.NORTH);
        dialog.add(new JTextArea("Exemplo de lista de pessoas..."), BorderLayout.CENTER);
        dialog.add(criarBotaoFechar(dialog), BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(principal);
        dialog.setVisible(true);
    }
}
