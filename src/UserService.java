import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.sql.SQLOutput;
import java.util.*;

public class UserService {

    private final List<User> usuarios = new ArrayList<>();

    private final String caminhoArquivo = "usuarios.json"; //caso necessário, altere o diretório para um específico.

    private final Gson gson = new Gson(); // converter objetos para JSON

    //ao criar uma instancia, carrega os usuarios do arquivo
    public UserService() {
        carregarUsuarios(); // chama o metodo aqui
    }

    //Construtor para carregar os dados JSON, se existir.
    private void carregarUsuarios() {
        try (Reader reader = new FileReader(caminhoArquivo)) {
            Type tipoLista = new TypeToken<List<User>>() {
            }.getType();
            List<User> carregados = gson.fromJson(reader, tipoLista);
            if (carregados != null) {
                usuarios.addAll(carregados);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de usuários não encontrado.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    //salva a lista atual de usuarios no arquivo JSON
    private void salvarUsuarios() {
        try {
            File file = new File(caminhoArquivo);

            File parent = file.getParentFile();//cria a pasta
            if (parent != null) {
                parent.mkdirs();
            }

            try (Writer writer = new FileWriter(file)) {
                gson.toJson(usuarios, writer);
            }
        } catch (IOException | JsonIOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public boolean loginJaExiste(String login) {
        for (User u : usuarios) {
            if (u.getLogin().equalsIgnoreCase(login)) {
                return true;
            }
        }
        return false;
    }


    //add um novo usuario
    public boolean cadastrarUsuario(User usuario) {
        if (loginJaExiste(usuario.getLogin())) {
            System.out.println("Erro: Login j´cadastrado.");
            return true;
        }
        String senhaCriptografada = CriptografiaUtil.criptografarSenha(usuario.getSenha());

        User usuarioSeguro = new User(
                usuario.getNome(),
                usuario.getLogin(),
                senhaCriptografada,
                usuario.getTipo()
        );

        usuarios.add(usuarioSeguro);
        salvarUsuarios();
        System.out.println("Usuário cadastrado com sucesso.");
        return true;
    }

    // autentica o login e senha fornecidos
    public User autenticarUsuario(String login, String senha) {
        String senhaCriptografada = CriptografiaUtil.criptografarSenha(senha);

        for (User user : usuarios) {
            if (user.getLogin().equals(login) && user.getSenha().equals(senhaCriptografada)) {
                System.out.println("Autenticação bem-sucedida!");
                return user;
            }
        }

        System.out.println("Login ou senha incorretos.");
        return null;
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        System.out.println("\n=== Lista de Usuários ===");
        for (User user : usuarios) {
            System.out.println("Nome: " + user.getNome());
            System.out.println("Login: " + user.getLogin());
            System.out.println("Tipo: " + user.getTipo());
            System.out.println("----------------------");
        }
        System.out.println("Total de usuários: " + usuarios.size());
    }
        public boolean removerUsuario (String login) {
            Iterator<User> iterator = usuarios.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user.getLogin().equals(login)) {
                    iterator.remove();
                    salvarUsuarios();
                    System.out.println("Usuário removido com sucesso.");
                    return true;
                }
            }
            System.out.println("Usuário não encontrado.");
            return false;
        }
    }







