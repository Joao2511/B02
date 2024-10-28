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
        this.ativo = true;
    }

    @Override
    public String toString() {
        return codigo + " " + nome + " " + String.format("%.2f", preco);
    }
}

public class LanchoneteV1 {
    static Produto[] produtos = new Produto[100];
    static int countProdutos = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("1. Cadastro de Produtos");
            System.out.println("2. Exibir Cardápio");
            System.out.println("3. Cadastro de Clientes");
            System.out.println("4. Sair");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch(opcao) {
                case 1:
                    menuCadastroProdutos();
                    break;
                case 2:
                    exibirCardapio();
                    break;
                case 3:
                    // Implementar menu de cadastro de clientes
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

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

            switch(opcao) {
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
}