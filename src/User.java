public class User {
    private String cpf;
    private String senha;

    public User(String cpf, String senha){
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getCpf(){
        return cpf;
    }
    public String getSenha(){
        return senha;
    }
}
