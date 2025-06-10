public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        ContaBancaria c1 = new ContaBancaria("Mariana", "00000000001", "22/02/2001", "000");
        ContaBancaria c2 = new ContaBancaria("Rodolfo", "00000000002", "08/10/2000","000");

        banco.adicionarConta(c1);
        banco.adicionarConta(c2);

        c1.depositar(2000);
        c1.transferir(c2,500);
        c2.transferir(c1,400);
        c1.sacar(1900);
        c1.encerrarConta();


        banco.listarContas();

        System.out.println(c1.toString());
        System.out.println(c2.toString());








    }
}
