package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amanda on 21/07/2015.
 */
public class MemoryClientRepository implements ClientRepository{

    private List<Client> clients;
    private static MemoryClientRepository singletonInstance;
    private MemoryClientRepository(){
        super();
        clients = new ArrayList<Client>();
    }

    //a vantagem é que caso queiramos fazer tratamento ou atribuir comportamento antes de instanciar a classe, conseguiríamos. Se fosse o construtor, pelo menos teriamos que ter chamado o super. Super sempre tem que vir na primeira linha.
    //perguntar sobre a vantagem do get Instance depois / ultima parte n entendi;

    public static ClientRepository getInstance(){
        if (MemoryClientRepository.singletonInstance == null) {
            MemoryClientRepository.singletonInstance = new MemoryClientRepository();
        }
        return singletonInstance;
    }

    @Override
    public void save(Client client) {
        clients.add(client);
    }

    @Override
    public List<Client> getAll() {
        return clients;
    }

    @Override
    public void delete(Client client) {
        clients.remove(client);
    }

}
