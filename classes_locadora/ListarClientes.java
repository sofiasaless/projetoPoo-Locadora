package classes_locadora;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import classes.Cliente;
import interfaces.Serializacao;

public class ListarClientes implements Serializacao {
    private List<Cliente> clientes = new ArrayList<Cliente>(); //vai guardar os clientes do arquivo .ser numa lista para ser mostrada ao funcionario
    
    public ListarClientes(){
        this.clientes = new ArrayList<Cliente>();
        //chamando o arquivo quando a classe iniciar
        desserializar();
    }

    //disserializando os clientes cadastrados
    @Override
    public void desserializar(){
        //desserializando usuarios clientes
        Path pathClientes = Paths.get("clientes.ser");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(pathClientes))) {
            this.clientes = (List<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //serializando os clientes que restaram após exclusão
    @Override
    public void serializar(){
        //setando as chaves do map clientes para levar ate a arraylist auxiliar que vai salvar no csv
        Path pathCli = Paths.get("clientes.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(pathCli, StandardOpenOption.CREATE))) {
            oos.writeObject(this.clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //removendo cliente pelo id dele na lista
    public boolean rmCliente(String id) {
        for(int i = 0; i < this.clientes.size(); i++){
            if(this.clientes.get(i).getId().equals(id)){
                System.out.println(this.clientes.get(i).toString());
                this.clientes.remove(i);
                return true;
            }
        }
        return false;
    }

    //imprimindo a lista de clientes
    @Override
    public String toString(){
        String s = "";
        for(Cliente c:this.clientes){
            s += c.toString() + "\n";
        }
        return s;
    }
    
}
