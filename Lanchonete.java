import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Produto {
    String codigo;
    String nome;
    boolean ativo;
    double preco;

    Produto(String codigo, String nome, double preco) {
        this.codigo = codigo.toUpperCase();
        this.nome = nome.toUpperCase();
        this.preco = preco;
        this.ativo = true; // Valor padrão
    }

    @Override
    public String toString() {
        return codigo + " " + nome + " R$ " + String.format("%.2f", preco);
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
        this.ativo = true; // O cliente começa como ativo
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nEndereço: " + logradouro + ", " + numero + " - " + complemento + "\n" +
               "Bairro: " + bairro + "\nCidade: " + cidade + "\nCEP: " + cep + "\nEstado: " + estado + "\n" +
               "Sexo: " + sexo + "\nTelefone: " + telefone + "\nEmail: " + email + "\nNascimento: " + nascimento;
    }
}

public class Lanchonete {
    static Produto[] produtos = new Produto[100]; // Vetor para armazenar até 100 produtos
    static int countProdutos = 0; // Contador de produtos cadastrados
    static Cliente[] clientes = new Cliente[100]; // Vetor para armazenar até 100 clientes
    static int countClientes = 0; // Contador de clientes cadastrados
    static Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("1. Cadastro de Produtos");
            System.out.println("2. Exibir Cardápio");
            System.out.println("3. Cadastro de Clientes");
            System.out.println("4. Exibir Clientes");
            System.out.println("5. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    menuCadastroProdutos();
                    break;
                case 2:
                    exibirCardapio();
                    break;
                case 3:
                    menuCadastroClientes();
                    break;
                case 4:
                    exibirClientes();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    // Funções de cadastro de produtos
    public static void menuCadastroProdutos() {
        int opcao;
        do {
            System.out.println("1. Incluir Produto");
            System.out.println("2. Alterar Produto");
            System.out.println("3. Excluir Produto");
            System.out.println("4. Consultar Produto");
            System.out.println("5. Voltar ao Menu Principal");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    incluirProduto();
                    break;
                case 2:
                    alterarProduto();
                    break;
                case 3:
                    excluirProduto();
                    break;
                case 4:
                    consultarProduto();
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    public static void incluirProduto() {
        System.out.print("Digite o código do produto (6 caracteres): ");
        String codigo = scanner.nextLine();
        if (codigo.length() != 6) {
            System.out.println("Código inválido. Deve conter 6 caracteres.");
            return;
        }

        System.out.print("Digite o nome do produto (3 a 60 caracteres): ");
        String nome = scanner.nextLine();
        if (nome.length() < 3 || nome.length() > 60) {
            System.out.println("Nome inválido. Deve conter entre 3 e 60 caracteres.");
            return;
        }

        System.out.print("Digite o preço do produto (positivo): ");
        double preco = scanner.nextDouble();
        if (preco <= 0) {
            System.out.println("Preço inválido. Deve ser um valor positivo.");
            return;
        }

        Produto p = new Produto(codigo, nome, preco);
        produtos[countProdutos] = p;
        countProdutos++;
        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void alterarProduto() {
        System.out.print("Digite o código do produto para alterar: ");
        String codigo = scanner.nextLine();
        Produto produto = buscarProduto(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Digite o novo nome do produto: ");
        String nome = scanner.nextLine();
        if (nome.length() >= 3 && nome.length() <= 60) {
            produto.nome = nome.toUpperCase();
        }

        System.out.print("Digite o novo preço do produto: ");
        double preco = scanner.nextDouble();
        if (preco > 0) {
            produto.preco = preco;
        }

        System.out.println("Produto alterado com sucesso!");
    }

    public static void excluirProduto() {
        System.out.print("Digite o código do produto para excluir: ");
        String codigo = scanner.nextLine();
        Produto produto = buscarProduto(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        produto.ativo = false;
        System.out.println("Produto desativado com sucesso!");
    }

    public static void consultarProduto() {
        System.out.print("Digite o código do produto para consultar: ");
        String codigo = scanner.nextLine();
        Produto produto = buscarProduto(codigo);

        if (produto != null) {
            System.out.println(produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public static Produto buscarProduto(String codigo) {
        for (int i = 0; i < countProdutos; i++) {
            if (produtos[i].codigo.equalsIgnoreCase(codigo)) {
                return produtos[i];
            }
        }
        return null;
    }

    public static void exibirCardapio() {
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("CÓDIGO PRODUTO                                                                              VALOR");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (int i = 0; i < countProdutos; i++) {
            if (produtos[i].ativo) {
                System.out.println(produtos[i]);
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
    }

    // Funções de cadastro de clientes
    public static void menuCadastroClientes() {
        int opcao;
        do {
            System.out.println("1. Incluir Cliente");
            System.out.println("2. Alterar Cliente");
            System.out.println("3. Excluir Cliente");
            System.out.println("4. Consultar Cliente");
            System.out.println("5. Voltar ao Menu Principal");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    incluirCliente();
                    break;
                case 2:
                    alterarCliente();
                    break;
                case 3:
                    excluirCliente();
                    break;
                case 4:
                    consultarCliente();
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    public static void incluirCliente() {
        System.out.print("Digite o nome do cliente (6 a 60 caracteres): ");
        String nome = scanner.nextLine();
        if (nome.length() < 6 || nome.length() > 60) {
            System.out.println("Nome inválido. Deve conter entre 6 e 60 caracteres.");
            return;
        }

        System.out.print("Digite o logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.print("Digite o número: ");
        String numero = scanner.nextLine();
        System.out.print("Digite o complemento (opcional): ");
        String complemento = scanner.nextLine();
        System.out.print("Digite o bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Digite a cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite o CEP: ");
        String cep = scanner.nextLine();
        System.out.print("Digite o estado: ");
        String estado = scanner.nextLine();
        System.out.print("Digite o sexo (M/F): ");
        String sexo = scanner.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
        String dataNascStr = scanner.nextLine();
        LocalDate nascimento = LocalDate.parse(dataNascStr, formatter);

        if (calcularIdade(nascimento) < 18) {
            System.out.println("Cadastro negado. Cliente deve ser maior de idade.");
            return;
        }

        Cliente c = new Cliente(nome, logradouro, numero, complemento, bairro, cidade, cep, estado, sexo, telefone, email, nascimento);
        clientes[countClientes] = c;
        countClientes++;
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static void alterarCliente() {
        System.out.print("Digite o nome do cliente para alterar: ");
        String nome = scanner.nextLine();
        Cliente cliente = buscarCliente(nome);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Digite o novo logradouro: ");
        cliente.logradouro = scanner.nextLine();
        System.out.print("Digite o novo número: ");
        cliente.numero = scanner.nextLine();
        System.out.print("Digite o novo complemento (opcional): ");
        cliente.complemento = scanner.nextLine();
        System.out.print("Digite o novo bairro: ");
        cliente.bairro = scanner.nextLine();
        System.out.print("Digite a nova cidade: ");
        cliente.cidade = scanner.nextLine();
        System.out.print("Digite o novo CEP: ");
        cliente.cep = scanner.nextLine();
        System.out.print("Digite o novo estado: ");
        cliente.estado = scanner.nextLine();
        System.out.print("Digite o novo sexo (M/F): ");
        cliente.sexo = scanner.nextLine();
        System.out.print("Digite o novo telefone: ");
        cliente.telefone = scanner.nextLine();
        System.out.print("Digite o novo email: ");
        cliente.email = scanner.nextLine();
        System.out.print("Digite a nova data de nascimento (dd/MM/yyyy): ");
        String dataNascStr = scanner.nextLine();
        cliente.nascimento = LocalDate.parse(dataNascStr, formatter);

        System.out.println("Cliente alterado com sucesso!");
    }

    public static void excluirCliente() {
        System.out.print("Digite o nome do cliente para excluir: ");
        String nome = scanner.nextLine();
        Cliente cliente = buscarCliente(nome);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        cliente.ativo = false;
        System.out.println("Cliente desativado com sucesso!");
    }

    public static void consultarCliente() {
        System.out.print("Digite o nome do cliente para consultar: ");
        String nome = scanner.nextLine();
        Cliente cliente = buscarCliente(nome);

        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static Cliente buscarCliente(String nome) {
        for (int i = 0; i < countClientes; i++) {
            if (clientes[i].nome.equalsIgnoreCase(nome)) {
                return clientes[i];
            }
        }
        return null;
    }

    public static void exibirClientes() {
        System.out.println("Lista de Clientes:");
        for (int i = 0; i < countClientes; i++) {
            if (clientes[i].ativo) {
                System.out.println(clientes[i]);
                System.out.println("-------------------------------------------------------------------------------------------------");
            }
        }
    }

    public static int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
