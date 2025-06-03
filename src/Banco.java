import java.util.List;
import java.util.ArrayList;


public class Banco {
    //Coleção (lista) que guarda as contas criadas
    private List<ContaBancaria> contas;

    //metodo que cria a lista quando o banco inicializa
    public Banco() {
        this.contas = new ArrayList<>();
    }
    //mtd add nova conta
    public boolean adicionarConta(ContaBancaria novaConta) {
        if (buscarConta(novaConta.getCpf()) != null) {
            System.out.println("CPF já cadastrado.");
            return false;
        }
        contas.add(novaConta);
        return true;
    }
    //mtd listar contas existentes
    public void listarContas(){
        if (contas.isEmpty()){
            System.out.println("Nenhuma conta cadastrada.");
            return;
        }
        System.out.println("\n=== Contas Cadastradas ===");
        for (ContaBancaria conta : contas){
            System.out.println(
                    "Nome: " + conta.getNome() +
                    " | CPF: ***." + conta.getCpf().substring(9) +
                    " | Saldo: R$ " + conta.getSaldo()
            );
    }
        System.out.println("Total: " + contas.size() + "contas\n");
}
//Metodo para buscar conta por CPF usando o for-each para list
public ContaBancaria buscarConta(String cpf) {
    for (ContaBancaria conta : contas) {
        if (conta.getCpf().equals(cpf)) {
            return conta;
        }
    }
    return null; //se nao encontrar nada
    }

}



