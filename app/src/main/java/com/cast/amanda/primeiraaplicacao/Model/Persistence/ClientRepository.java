package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Herbs;

import java.util.List;

/**
 * Created by Amanda on 21/07/2015.
 */
//basicamente essa interface serao DAO
    //PROGRAME PARA A INTERFACE E NAO PARA A IMPLEMENTACAO
public interface ClientRepository {

    void save(Herbs herbs);
    List<Herbs> getAll();

}
