package com.cast.amanda.primeiraaplicacao.Model.Persistence;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Herbs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amanda on 21/07/2015.
 */
public class MemoryHerbsRepository implements HerbsRepository {

    private List<Herbs> herbses;
    private static MemoryHerbsRepository singletonInstance;
    private MemoryHerbsRepository(){
        super();
        herbses = new ArrayList<Herbs>();
    }

    //a vantagem � que caso queiramos fazer tratamento ou atribuir comportamento antes de instanciar a classe, conseguir�amos. Se fosse o construtor, pelo menos teriamos que ter chamado o super. Super sempre tem que vir na primeira linha.
    //perguntar sobre a vantagem do get Instance depois / ultima parte n entendi;

    public static HerbsRepository getInstance(){
        if (MemoryHerbsRepository.singletonInstance == null) {
            MemoryHerbsRepository.singletonInstance = new MemoryHerbsRepository();
        }
        return singletonInstance;
    }

    @Override
    public void save(Herbs herbs) {
        herbses.add(herbs);
    }

    @Override
    public List<Herbs> getAll() {
        return herbses;
    }

}
