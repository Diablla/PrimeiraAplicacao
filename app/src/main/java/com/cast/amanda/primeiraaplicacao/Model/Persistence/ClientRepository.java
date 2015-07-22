package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Client;

import java.util.List;

/**
 * Created by Amanda on 21/07/2015.
 */
//basicamente essa interface ser� o DAO
    //PROGRAME PARA A INTERFACE E N�O PARA A IMPLEMENTA��O
public interface ClientRepository {

    void save(Client client);
    List<Client> getAll();
    void delete(Client client);

}
