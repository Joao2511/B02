import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.*;

class Produto {
    String codigo;
    String nome;
    boolean ativo;
    double preco;

    Produto(String codigo, String nome, double preco) {
        this.codigo = codigo.toUpperCase();
        this.nome = nome.toUpperCase();
        this.preco = preco;
        this.ativo = true;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " - R$ " + String.format("%.2f", preco);
    }
}

class Cliente {
    String nome;
    String logradouro;
    String numero;
    String complemento;
    String bairro;
    String cidade;
    String cep;
    String estado;
    String sexo;
    String telefone;
    String email;
    LocalDate nascimento;
    boolean ativo;

    Cliente(String nome, String logradouro, String numero, String complemento, String bairro, String cidade,
            String cep, String estado, String sexo, String telefone, String email, LocalDate nascimento) {
        this.nome = nome.toUpperCase();
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.estado = estado;
        this.sexo = sexo;
        this.telefone = telefone;
        this.email = email;
        this.nascimento = nascimento;
        this.ativo = true;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nEndereço: " + logradouro + ", " + numero + " - " + complemento + "\n" +
               "Bairro: " + bairro + "\nCidade: " + cidade + "\nCEP: " + cep + "\nEstado: " + estado + "\n" +
               "Sexo: " + sexo + "\nTelefone: " + telefone + "\nEmail: " + email + "\nNascimento: " + nascimento;
    }
}

public class LanchoneteV3 {
    static Produto[] produtos = new Produto[100];
    static int countProdutos = 0;
    static Cliente[] clientes = new Cliente[100];
    static int countClientes = 0;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createGUI());
    }

    public static void createGUI() {
        JFrame frame = new JFrame("Sistema de Lanchonete");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza a janela
        frame.setBackground(new Color(255, 255, 255));

        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.setBackground(new Color(240, 240, 240));

        JButton btnCadastroProdutos = new JButton("Cadastro de Produtos");
        btnCadastroProdutos.setBackground(new Color(76, 175, 80));
        btnCadastroProdutos.setForeground(Color.WHITE);
        btnCadastroProdutos.setFont(buttonFont);

        JButton btnExibirCardapio = new JButton("Exibir Cardápio");
        btnExibirCardapio.setBackground(new Color(33, 150, 243));
        btnExibirCardapio.setForeground(Color.WHITE);
        btnExibirCardapio.setFont(buttonFont);

        JButton btnCadastroClientes = new JButton("Cadastro de Clientes");
        btnCadastroClientes.setBackground(new Color(255, 193, 7)); 
        btnCadastroClientes.setForeground(Color.BLACK);
        btnCadastroClientes.setFont(buttonFont);

        JButton btnExibirClientes = new JButton("Exibir Clientes");
        btnExibirClientes.setBackground(new Color(255, 87, 34));
        btnExibirClientes.setForeground(Color.WHITE);
        btnExibirClientes.setFont(buttonFont);

        JButton btnSair = new JButton("Sair");
        btnSair.setBackground(new Color(244, 67, 54));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFont(buttonFont);

        panel.add(btnCadastroProdutos);
        panel.add(btnExibirCardapio);
        panel.add(btnCadastroClientes);
        panel.add(btnExibirClientes);
        panel.add(btnSair);

        // Adicionando ActionListeners aos botões
        btnCadastroProdutos.addActionListener(e -> menuCadastroProdutos());
        btnExibirCardapio.addActionListener(e -> exibirCardapio());
        btnCadastroClientes.addActionListener(e -> menuCadastroClientes());
        btnExibirClientes.addActionListener(e -> exibirClientes());
        btnSair.addActionListener(e -> System.exit(0));

        // Adicionando o painel à janela
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void menuCadastroProdutos() {
        JFrame frame = new JFrame("Cadastro de Produtos");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.setBackground(new Color(240, 240, 240));

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(labelFont);
        JTextField txtCodigo = new JTextField();
        
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(labelFont);
        JTextField txtNome = new JTextField();
        
        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setFont(labelFont);
        JTextField txtPreco = new JTextField();

        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.setBackground(new Color(76, 175, 80));
        btnIncluir.setForeground(Color.WHITE);
        btnIncluir.setFont(buttonFont);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBackground(new Color(33, 150, 243));
        btnAlterar.setForeground(Color.WHITE);
        btnAlterar.setFont(buttonFont);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(255, 87, 34));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFont(buttonFont);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(244, 67, 54));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(buttonFont);

        // Adicionando componentes ao painel
        panel.add(lblCodigo);
        panel.add(txtCodigo);
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblPreco);
        panel.add(txtPreco);
        panel.add(btnIncluir);
        panel.add(btnAlterar);
        panel.add(btnExcluir);
        panel.add(btnVoltar);

        // Ações dos botões
        btnIncluir.addActionListener(e -> {
            String codigo = txtCodigo.getText();
            String nome = txtNome.getText();
            double preco;
            try {
                preco = Double.parseDouble(txtPreco.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Preço inválido.");
                return; // Adiciona um retorno aqui para evitar exceções de null
            }

            if (codigo.length() == 6 && nome.length() >= 3 && nome.length() <= 60 && preco > 0) {
                Produto p = new Produto(codigo, nome, preco);
                produtos[countProdutos++] = p;
                JOptionPane.showMessageDialog(frame, "Produto cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, "Dados inválidos.");
            }
        });

        btnAlterar.addActionListener(e -> {
            String codigo = txtCodigo.getText();
            for (int i = 0; i < countProdutos; i++) {
                if (produtos[i].codigo.equals(codigo)) {
                    String nome = txtNome.getText();
                    double preco;
                    try {
                        preco = Double.parseDouble(txtPreco.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Preço inválido.");
                        return; // Adiciona um retorno aqui para evitar exceções de null
                    }

                    if (nome.length() >= 3 && nome.length() <= 60 && preco > 0) {
                        produtos[i].nome = nome;
                        produtos[i].preco = preco;
                        JOptionPane.showMessageDialog(frame, "Produto alterado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Dados inválidos.");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Produto não encontrado.");
        });

        btnExcluir.addActionListener(e -> {
            String codigo = txtCodigo.getText();
            for (int i = 0; i < countProdutos; i++) {
                if (produtos[i].codigo.equals(codigo)) {
                    produtos[i].ativo = false;
                    JOptionPane.showMessageDialog(frame, "Produto excluído com sucesso!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Produto não encontrado.");
        });

        btnVoltar.addActionListener(e -> frame.dispose());

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void exibirCardapio() {
        StringBuilder cardapio = new StringBuilder("Cardápio:\n");
        for (int i = 0; i < countProdutos; i++) {
            if (produtos[i].ativo) {
                cardapio.append(produtos[i].toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, cardapio.toString());
    }

    public static void menuCadastroClientes() {
        JFrame frame = new JFrame("Cadastro de Clientes");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel(new GridLayout(24, 1));
        panel.setBackground(new Color(240, 240, 240));

        // Definindo uma nova fonte para os rótulos e botões
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(labelFont);
        JTextField txtNome = new JTextField();
        
        JLabel lblLogradouro = new JLabel("Logradouro:");
        lblLogradouro.setFont(labelFont);
        JTextField txtLogradouro = new JTextField();
        
        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setFont(labelFont);
        JTextField txtNumero = new JTextField();
        
        JLabel lblComplemento = new JLabel("Complemento:");
        lblComplemento.setFont(labelFont);
        JTextField txtComplemento = new JTextField();
        
        JLabel lblBairro = new JLabel("Bairro:");
        lblBairro.setFont(labelFont);
        JTextField txtBairro = new JTextField();
        
        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setFont(labelFont);
        JTextField txtCidade = new JTextField();
        
        JLabel lblCep = new JLabel("CEP:");
        lblCep.setFont(labelFont);
        JTextField txtCep = new JTextField();
        
        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setFont(labelFont);
        JTextField txtEstado = new JTextField();
        
        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(labelFont);
        JTextField txtSexo = new JTextField();
        
        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(labelFont);
        JTextField txtTelefone = new JTextField();
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(labelFont);
        JTextField txtEmail = new JTextField();
        
        JLabel lblNascimento = new JLabel("Nascimento (dd/MM/yyyy):");
        lblNascimento.setFont(labelFont);
        JTextField txtNascimento = new JTextField();

        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.setBackground(new Color(76, 175, 80));
        btnIncluir.setForeground(Color.WHITE);
        btnIncluir.setFont(buttonFont);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBackground(new Color(33, 150, 243));
        btnAlterar.setForeground(Color.WHITE);
        btnAlterar.setFont(buttonFont);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(255, 87, 34));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFont(buttonFont);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(new Color(244, 67, 54));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(buttonFont);

        // Adicionando componentes ao painel
        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(lblLogradouro);
        panel.add(txtLogradouro);
        panel.add(lblNumero);
        panel.add(txtNumero);
        panel.add(lblComplemento);
        panel.add(txtComplemento);
        panel.add(lblBairro);
        panel.add(txtBairro);
        panel.add(lblCidade);
        panel.add(txtCidade);
        panel.add(lblCep);
        panel.add(txtCep);
        panel.add(lblEstado);
        panel.add(txtEstado);
        panel.add(lblSexo);
        panel.add(txtSexo);
        panel.add(lblTelefone);
        panel.add(txtTelefone);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblNascimento);
        panel.add(txtNascimento);
        panel.add(btnIncluir);
        panel.add(btnAlterar);
        panel.add(btnExcluir);
        panel.add(btnVoltar);

        // Ações dos botões
        btnIncluir.addActionListener(e -> {
            String nome = txtNome.getText();
            String logradouro = txtLogradouro.getText();
            String numero = txtNumero.getText();
            String complemento = txtComplemento.getText();
            String bairro = txtBairro.getText();
            String cidade = txtCidade.getText();
            String cep = txtCep.getText();
            String estado = txtEstado.getText();
            String sexo = txtSexo.getText();
            String telefone = txtTelefone.getText();
            String email = txtEmail.getText();
            LocalDate nascimento;
            try {
                nascimento = LocalDate.parse(txtNascimento.getText(), formatter);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Data de nascimento inválida.");
                return; // Adiciona um retorno aqui para evitar exceções de null
            }

            if (calcularIdade(nascimento) >= 18) {
                Cliente c = new Cliente(nome, logradouro, numero, complemento, bairro, cidade, cep, estado, sexo, telefone, email, nascimento);
                clientes[countClientes++] = c;
                JOptionPane.showMessageDialog(frame, "Cliente cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, "Cliente deve ser maior de idade.");
            }
        });

        btnAlterar.addActionListener(e -> {
            String nome = txtNome.getText();
            for (int i = 0; i < countClientes; i++) {
                if (clientes[i].nome.equals(nome)) {
                    String logradouro = txtLogradouro.getText();
                    String numero = txtNumero.getText();
                    String complemento = txtComplemento.getText();
                    String bairro = txtBairro.getText();
                    String cidade = txtCidade.getText();
                    String cep = txtCep.getText();
                    String estado = txtEstado.getText();
                    String sexo = txtSexo.getText();
                    String telefone = txtTelefone.getText();
                    String email = txtEmail.getText();
                    LocalDate nascimento;
                    try {
                        nascimento = LocalDate.parse(txtNascimento.getText(), formatter);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Data de nascimento inválida.");
                        return; // Adiciona um retorno aqui para evitar exceções de null
                    }

                    if (calcularIdade(nascimento) >= 18) {
                        clientes[i].logradouro = logradouro;
                        clientes[i].numero = numero;
                        clientes[i].complemento = complemento;
                        clientes[i].bairro = bairro;
                        clientes[i].cidade = cidade;
                        clientes[i].cep = cep;
                        clientes[i].estado = estado;
                        clientes[i].sexo = sexo;
                        clientes[i].telefone = telefone;
                        clientes[i].email = email;
                        clientes[i].nascimento = nascimento;
                        JOptionPane.showMessageDialog(frame, "Cliente alterado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Cliente deve ser maior de idade.");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Cliente não encontrado.");
        });

        btnExcluir.addActionListener(e -> {
            String nome = txtNome.getText();
            for (int i = 0; i < countClientes; i++) {
                if (clientes[i].nome.equals(nome)) {
                    clientes[i].ativo = false;
                    JOptionPane.showMessageDialog(frame, "Cliente excluído com sucesso!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Cliente não encontrado.");
        });

        btnVoltar.addActionListener(e -> frame.dispose());

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public static void exibirClientes() {
        StringBuilder listaClientes = new StringBuilder("Clientes:\n");
        for (int i = 0; i < countClientes; i++) {
            if (clientes[i].ativo) {
                listaClientes.append(clientes[i].toString()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, listaClientes.toString());
    }

    public static int calcularIdade(LocalDate nascimento) {
        return Period.between(nascimento, LocalDate.now()).getYears();
    }
}
