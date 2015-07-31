package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Client;

import java.util.List;

/**
 * Created by Amanda on 21/07/2015.
 */
//basicamente essa interface serao DAO
    //PROGRAME PARA A INTERFACE E NAO PARA A IMPLEMENTACAO
public interface ClientRepository {

    void save(Client client);
    List<Client> getAll();
    void delete(Client client);

}
