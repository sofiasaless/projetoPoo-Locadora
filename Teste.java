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
import classes.Filme;
import classes.Funcionario;
import enums.Generos;

public class Teste {
    public static void main(String[] args) {
        /* List<Cliente> cli = new ArrayList<Cliente>();
        List<Funcionario> fun = new ArrayList<Funcionario>();

        Filme f = new Filme("ALice", Generos.TERROR, "18+", "4.5", 12);
        Cliente cli1 = new Cliente("Sofia", "2004", "sofia1234");
        cli1.adcFilmeIn(f);

        Funcionario fun1 = new Funcionario("widney", "admin", "2023");
        fun1.adcFilmeIn(f);

        //botando na lista
        cli.add(cli1);
        fun.add(fun1);
        serializar(cli, fun); */

        System.out.println("CLIENTES");

        List<Cliente> clientes = desserializandoCli();
        for(Cliente c:clientes){
            System.out.println(c);
        }

        System.out.println("FUNCIONARIOS");
        List<Funcionario> funcionarios = desserializandoFun();
        for(Funcionario fu:funcionarios){
            System.out.println(fu);
        }

    }

    public static List<Cliente> desserializandoCli(){
        List<Cliente> clientes = new ArrayList<Cliente>();
        Path pathClientes = Paths.get("clientes.ser");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(pathClientes))) {
            clientes = (List<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public static List<Funcionario> desserializandoFun(){
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        Path pathClientes = Paths.get("funcionarios.ser");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(pathClientes))) {
            funcionarios = (List<Funcionario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return funcionarios;
    }

    public static void serializar(List<Cliente> clientes, List<Funcionario> funcionarios){
        //levando os CLIENTES
        Path pathCli = Paths.get("clientes.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(pathCli, StandardOpenOption.CREATE))) {
            oos.writeObject(clientes);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //levando os FUNCIONARIOS
        Path pathFun = Paths.get("funcionarios.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(pathFun, StandardOpenOption.CREATE))) {
            oos.writeObject(funcionarios);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}