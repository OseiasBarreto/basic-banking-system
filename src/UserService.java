import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class UserService {

    private final List<User> usuarios = new ArrayList<>();

    private final String caminhoArquivo = "usuarios.json"; //caso necessário, altere o diretório para um específico.private

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
    private void salvarUsuarios(){
        try (Writer writer = new FileWriter(caminhoArquivo)){
            gson.toJson(usuarios, writer);
        } catch (JsonIOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

//add um novo usuario
public void cadastrarUsuario (User usuario){
    usuarios.add(usuario);
    salvarUsuarios(); // atualiza o arquvi JSON

    }
    public User autenticarUsuario(String login, String senha){
        for(User user : usuarios){
            if(user.getLogin().equals(login) && user.getSenha().equals(senha)){
                return user;
            }
        }
        return null;
    }
}





