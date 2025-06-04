import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

//Aqui eu criei os métodos da classe, onde há informações cruciais para o sistema.
public class ContaBancaria {
    private final String nome;
    private final LocalDate dataNascimento;
    private final String cpf;
    private double saldo;
    private boolean ativo;
    private boolean jaDepositou;

    //-------------------------------------------------------------------------------------------------------------------
    //Aqui é o construtor, onde irá criar e inicializar um objeto ao usar o "new..."
    public ContaBancaria(String nome, String cpf, String dataNascimentoStr) {
        this.nome = nome;
        this.cpf = cpf;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);

        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        this.ativo = calcularIdade() >= 18;
    }

    //--------------------------------------------------------------------------------------------------------------
    //Metodo getters para campos privados
    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public boolean isAtiva() {
        return ativo;
    }

    public String getDataNascimentoFormatada() {
        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("pt", "BR")));
    }

    //----------------------------------------------------------------------------------------------------------
    /*Aqui é o metodo (ações) do programa e sua lógica

    O metodo depositar adiciona saldo e permite saque ou transferencia apos o primeiro
    uso. Ele soma o valor ao saldo e faz com que o atributo JaDepositou seja true */
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            jaDepositou = true;
            System.out.println("Depósito de R$ " + String.format("%.2f",valor) + " realizado com sucesso.");
        }else{
            System.out.println("Valor inválido para depósito.");
        }
    }

    public int calcularIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    /*Aqui o metodo ira passar por três condições para poder ou não sacar o valor
    1- o valor tem que está acima de 0
    2- o saldo em conta tem que ser maior ou igual ao valor de saque
    3- a conta precisa já ter recebido algum depósito */
    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor && jaDepositou) {
            saldo -= valor;
            System.out.println("Saque de R$ " + String.format("%.2f",valor) + " realizado com sucesso.");
            return true;
        }
        System.out.println("Saque não autorizado. Verifique saldo ou depósito anterior.");
        return false;
    }


    /*O metodo transferir vai subtrair o valor da conta origem e somar na conta destino
    levando em consideração:
    1- o valor tem que ser maior que zero
    2- tem que está ativa
    3- o saldo tem que ser maior ou igual ao valor
     */
    public boolean transferir(ContaBancaria destino, double valor) {
        if (valor > 0 && jaDepositou && saldo >= valor) {
            saldo -= valor;
            destino.depositar(valor);
            System.out.println("Transferência de R$ " + String.format("%.2f",valor) + " realizado para " + destino.getCpf() + ".");
            return true;
        }
        System.out.println("Transferência falhou. Verifique saldo, valor ou se a conta já recebeu depósito.");
        return false;
    }

    /*O metodo encerra a conta se:
    1- o saldo for igual a zero
    2- se ela estiver ativa
     */
    public boolean encerrarConta() {
        if (saldo == 0 && jaDepositou) {
            ativo = false;
            System.out.println("Conta encerrada com sucesso.");
            return true;
        }
        System.out.println("Não foi possível encerrar a conta. Verifique saldo e histórico de depósitos.");
        return false;
    }

    private String formatarCpf() {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "***.***.***-$4");
    }


    //Sobrescreve o metodo toString e retorna as informações da conta bancaria
    @Override
    public String toString() {
        return "👤 Nome: " + nome +
                " | CPF: " + formatarCpf() +
                " | Saldo: R$ " + String.format("%.2f", saldo) +
                " | Ativa: " + (ativo ? "Sim" : "Não");


    }
}

