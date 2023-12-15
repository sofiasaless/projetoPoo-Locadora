package classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import interfaces.Serializacao;

public class EntrarCadastrar implements Serializacao {
    //listas de clientes e funcionarios que vao guardar os usuarios
    private List<Cliente> clientes;
    private List<Funcionario> funcionarios;
    //cliente ou usuario que vai ser logado 
    private Cliente clienteLogado = new Cliente(null, null, null);
    private Funcionario funcionarioLogado = new Funcionario(null, null, null);
    
    //construtor
    public EntrarCadastrar(){
        this.clientes = new ArrayList<Cliente>();
        this.funcionarios = new ArrayList<Funcionario>();
        desserializar();
    }

    //lendo clientes e funcionarios dos arquivos, e gravando nas listas
    //desserializando
    @Override
    public void desserializar() {
        //desserializando usuarios clientes
        Path pathClientes = Paths.get("clientes.ser");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(pathClientes))) {
            this.clientes = (List<Cliente>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //desserializando usuarios clientes
        Path pathFun = Paths.get("funcionarios.ser");
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(pathFun))) {
            this.funcionarios = (List<Funcionario>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //salvando os funcionarios e clientes cadastrados no arquivo .ser
    //serializando
    @Override
    public void serializar(){
        //serializando os CLIENTES
        Path pathCli = Paths.get("clientes.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(pathCli, StandardOpenOption.CREATE))) {
            oos.writeObject(this.clientes);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //serializando os FUNCIONARIOS
        Path pathFun = Paths.get("funcionarios.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(pathFun, StandardOpenOption.CREATE))) {
            oos.writeObject(this.funcionarios);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    //fazendo login do usuario, sendo cliente ou funcionario
    public void logar (Usuario usu) throws Exception{
        //se é cliente....
        Map<String, Cliente> cliente_map = new TreeMap<String, Cliente>();
        for(Cliente c:this.clientes){
            cliente_map.put(c.getId(), c);
        }
        if(usu instanceof Cliente){
            Cliente cliente = new Cliente(usu.getNome(), usu.getId(), usu.getSenha());
            String key = cliente.getId();
            if(cliente_map.get(key) == null){
                throw new Exception("\u001b[31mUsuário não encontrado no sistema! Necessário cadastrar.\u001b[37m");
            }
            if(!(cliente_map.get(key).getSenha().equals(cliente.getSenha()))){
                throw new Exception("\u001b[31mSenha incorreta! Tente novamente.\u001b[37m");
            }
            if(!(cliente_map.get(key).getNome().equals(cliente.getNome()))){
                throw new Exception("\\u001b[31mNome de usuário incorreto! Tente novamente.\u001b[37m");
            }
            setClienteLogado(cliente_map.get(key));
            System.out.print("\u001b[32mLogado com sucesso! Divirta-se alugando seus filmes\u001b[37m");
            throw new Exception("!");
        }

        //se é funcionario....
        Map<String, Funcionario> funcio_map = new TreeMap<String, Funcionario>();
        for(Funcionario f:this.funcionarios){
            funcio_map.put(f.getId(), f);
        }
        if(usu instanceof Funcionario){
            Funcionario funcionario = new Funcionario(usu.getNome(), usu.getId(), usu.getSenha());
            String key = funcionario.getId();
            if(funcio_map.get(key) == null){
                throw new Exception("\u001b[31mUsuário não encontrado no sistema! Necessário cadastrar.\u001b[37m");
            }
            if(!(funcio_map.get(key).getSenha().equals(funcionario.getSenha()))){
                throw new Exception("\u001b[31mSenha incorreta! Tente novamente.\u001b[37m");
            }
            if(!(funcio_map.get(key).getNome().equals(funcionario.getNome()))){
                throw new Exception("\\u001b[31mNome de usuário incorreto! Tente novamente.\u001b[37m");
            }
            setFuncionarioLogado(funcio_map.get(key));
            System.out.print("\u001b[32mLogado com sucesso! Bom trabalho na locadora\u001b[37m");
            throw new Exception("!");
        }
    }

    //fazendo cadastro de um novo usuario, sendo cliente ou funcionario
    public void cadastrar(Usuario usu) throws Exception {
        //se for cliente....
        if(usu instanceof Cliente){
            Cliente cliente = new Cliente(usu.getNome(), usu.getId(), usu.getSenha());
            cliente.setSaldo(0);
            String key = cliente.getId();

            //verificando se tem um cliente já cadastrado com o id colocado
            for(int i = 0; i < this.clientes.size(); i++){
                if(this.clientes.get(i).getId().equals(key)){
                    throw new Exception("\u001b[31mUsuário já tem cadastro no sistema! Tente fazer login.\u001b[37m");
                }
            }
            this.clientes.add(cliente);
            System.out.print("\u001b[32mUsuário cadastrado com sucesso! Agora faça login para prosseguir\u001b[37m");
            throw new Exception("!");
        }

        //se for funcionario
        if(usu instanceof Funcionario){
            Funcionario funcionario = new Funcionario(usu.getNome(), usu.getId(), usu.getSenha());
            funcionario.setSaldo(0);
            String key = funcionario.getId();

            //verificando se tem um funcionario já cadastrado com o id colocado
            for(int i = 0; i < this.funcionarios.size(); i++){
                if(this.funcionarios.get(i).getId().equals(key)){
                    throw new Exception("\u001b[31mUsuário já tem cadastro no sistema! Tente fazer login.\u001b[37m");
                }
            }
            this.funcionarios.add(funcionario);
            System.out.print("\u001b[32mUsuário cadastrado com sucesso! Agora faça login para prosseguir\u001b[37m");
            throw new Exception("!");
        }
    }

    //get e set do cliente ou funcionario que foi logado
    public void setClienteLogado(Cliente c){ this.clienteLogado = c; }
    public Cliente getClienteLogado(){ return this.clienteLogado; }
    public Funcionario getFuncionarioLogado() { return funcionarioLogado; }
    public void setFuncionarioLogado(Funcionario funcionarioLogado) { this.funcionarioLogado = funcionarioLogado; }

    //get das listas de clientes e funcionarios
    public List<Cliente> getClientes() { return clientes; }
    public List<Funcionario> getFuncionarios() { return funcionarios; }

}