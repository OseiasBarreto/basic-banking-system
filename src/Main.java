public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        UserService userService = new UserService();

        // Contas e usuários exemplo
        ContaBancaria c1 = new ContaBancaria("Mariana", "00000000001", "22/02/2001", "000");
        ContaBancaria c2 = new ContaBancaria("Rodolfo", "00000000002", "08/10/2000","000");

        banco.adicionarConta(c1);
        banco.adicionarConta(c2);

        // Usuários precisam ter o login igual ao CPF da conta para bater
        userService.cadastrarUsuario(new User("Mariana", "00000000001", "000", TypeUser.TipoUsuario.COMUM));
        userService.cadastrarUsuario(new User("ADM", "admin", "admin123", TypeUser.TipoUsuario.ADMIN));

        // Iniciar menu
        Menu menu = new Menu(userService, banco);
        menu.iniciar();
    }
}
