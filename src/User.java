public class User {
    private String nome;
    private final String login;
    private  String senha;
    private TypeUser.TipoUsuario tipo;

//construct
    public User(String nome, String login, String senha, TypeUser.TipoUsuario tipo){
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    //getters
    public String getNome(){
        return nome;
    }
    public String getLogin(){
        return login;
    }
    public String getSenha(){
        return senha;
    }
    public TypeUser.TipoUsuario getTipo(){
        return tipo;
    }
}
