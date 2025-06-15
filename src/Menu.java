import java.util.Scanner;

public class Menu {
    private UserService userService;
    private Banco banco;

    public Menu(UserService userService, Banco banco) {
        this.userService = userService;
        this.banco = banco;
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Bem vindo(a) ao Caixa Eletrônico ===");
        System.out.println("Login: ");
        String login = sc.nextLine();

        System.out.println("Senha: ");
        String senha = sc.nextLine();

        User user = userService.autenticarUsuario(login, senha);

        if (user != null) {
            System.out.println("\nLogin bem-sucedido! Bem-vindo, " + user.getNome());

            if (user.getTipo() == TypeUser.TipoUsuario.ADMIN) {
                menuAdmin(sc);
            } else {
                menuComum(sc, user);
            }
        } else {
            System.out.println("Login ou senha inválidos.");
        }
    }

    private void menuAdmin(Scanner sc) {
        System.out.println("=== MENU ADMIN ===");
        banco.listarContas();
    }

    private void menuComum(Scanner sc, User user) {
        System.out.println("=== MENU USUÁRIO COMUM ===");
        ContaBancaria conta = banco.buscarConta(user.getLogin());
        if (conta == null) {
            System.out.println("Conta não encontrada para o CPF/Login informado.");
            return;
        }
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n1 - Ver Saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Sair");
            System.out.println("Escolha: ");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    try {
                        if (conta.autenticar(user.getSenha()))
                            System.out.println("Saldo atual: R$ " + conta.getSaldo());
                    } catch (SecurityException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Valor: ");
                    double valor = sc.nextDouble();
                    conta.depositar(valor);
                    break;
                case 3:
                    System.out.println("Valor: ");
                    double saque = sc.nextDouble();
                    conta.sacar(saque);
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        conta.sair();
    }
}





