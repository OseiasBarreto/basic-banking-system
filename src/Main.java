import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Fazer login");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    // Cadastro de novo usuário
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o login: ");
                    String login = scanner.nextLine();

                    System.out.print("Digite a senha: ");
                    String senha = scanner.nextLine();

                    System.out.print("Tipo de usuário (1 para ADMIN, 2 para COMUM): ");
                    int tipoOpcao = scanner.nextInt();
                    scanner.nextLine();

                    TypeUser.TipoUsuario tipo = (tipoOpcao == 1) ? TypeUser.TipoUsuario.ADMIN : TypeUser.TipoUsuario.COMUM;

                    User novoUser = new User(nome, login, senha, tipo);
                    userService.cadastrarUsuario(novoUser);
                    break;

                case 2:
                    // Login
                    System.out.print("Login: ");
                    String loginTentativa = scanner.nextLine();

                    System.out.print("Senha: ");
                    String senhaTentativa = scanner.nextLine();

                    User usuario = userService.autenticarUsuario(loginTentativa, senhaTentativa);

                    if (usuario != null) {
                        System.out.println("✅ Login bem-sucedido! Bem-vindo(a), " + usuario.getNome());

                        // Chama menu de acordo com tipo
                        if (usuario.getTipo() == TypeUser.TipoUsuario.ADMIN) {
                            menuAdmin(userService, scanner);
                        } else {
                            menuComum(usuario, scanner);
                        }
                    } else {
                        System.out.println("❌ Login ou senha inválidos.");
                    }
                    break;

                case 3:
                    continuar = false;
                    System.out.println("Saindo do sistema... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    /**
     * Menu para usuários ADMINISTRADORES
     */
    public static void menuAdmin(UserService userService, Scanner scanner) {
        boolean ativo = true;

        while (ativo) {
            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1 - Listar todos os usuários");
            System.out.println("2 - Remover usuário");
            System.out.println("3 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    userService.listarUsuarios(); // Mostra todos os cadastrados
                    break;
                case 2:
                    System.out.print("Digite o login do usuário a ser removido: ");
                    String loginRemover = scanner.nextLine();
                    boolean removido = userService.removerUsuario(loginRemover);
                    if (!removido) {
                        System.out.println("Usuário não encontrado para remoção.");
                    }
                    break;
                case 3:
                    ativo = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    /**
     * Menu para usuários COMUNS
     */
    public static void menuComum(User usuario, Scanner scanner) {
        boolean ativo = true;

        while (ativo) {
            System.out.println("\n=== MENU USUÁRIO COMUM ===");
            System.out.println("1 - Ver meus dados");
            System.out.println("2 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    // Mostra as informações do próprio usuário logado
                    System.out.println("\n👤 Nome: " + usuario.getNome());
                    System.out.println("🔑 Login: " + usuario.getLogin());
                    System.out.println("👥 Tipo: " + usuario.getTipo());
                    break;
                case 2:
                    // Voltar para o menu principal
                    ativo = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
