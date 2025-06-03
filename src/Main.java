public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        ContaBancaria joao = new ContaBancaria("João", "12345678904", "02/06/1990");
        ContaBancaria maria = new ContaBancaria("Maria", "98765432102", "05/06/1995");

        banco.adicionarConta(joao);
        banco.adicionarConta(maria);

        joao.depositar(500);
        joao.transferir(maria, 235);

        banco.listarContas();
    }
}
