import java.util.List;
import java.util.ArrayList;

public class Banco {
    private List<ContaBancaria> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public boolean adicionarConta(ContaBancaria novaConta) {
        String cpf = novaConta.getCpf();
        if (!cpf.matches("\\d{11}")) {
            System.out.println("CPF inválido. Deve conter 11 números.");
            return false;
        }

        if (buscarConta(cpf) != null) {
            System.out.println("CPF já cadastrado.");
            return false;
        }
        contas.add(novaConta);
        System.out.println("Conta adicionada com sucesso.");
        return true;
    }

    public void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        System.out.println("\n=== Contas Cadastradas ===");
        for (ContaBancaria conta : contas) {
            String saldoStr = "R$ " + String.format("%.2f", conta.getSaldoSemAutenticacao());
            System.out.printf("Nome: %s | CPF: ***-%s | Saldo: %s%n",
                    conta.getNome(),
                    conta.getCpf().substring(9),
                    saldoStr);
        }
        System.out.println("Total: " + contas.size() + " contas\n");


    }

    public ContaBancaria buscarConta(String cpf) {
        for (ContaBancaria conta : contas) {
            if (conta.getCpf().equals(cpf)) {
                return conta;
            }
        }
        return null;
    }
}
