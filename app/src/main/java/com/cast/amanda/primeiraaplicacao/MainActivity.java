package com.cast.amanda.primeiraaplicacao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

//pertence a hierarquia de activity e tem que referenciar a view -> parou de compilar a classe R, erro no layout ou XML
//para criar uma ACTIVITY criar layout, classe java, mapear no androidManifest
//WrappContent -> ocupe o menos de espaço possivel / MatchParent -> ocupe o maximo de espaço possivel
public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Client> clients = getClient();

        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);

        ClientListAdapter clientAdapter = new ClientListAdapter(MainActivity.this, clients);

        listViewClients.setAdapter(clientAdapter);
    }

    private List<Client> getClient() {
        List<Client> clients = new ArrayList<>();

        Client renan = new Client();
        renan.setAge(23);
        renan.setName("Renan Jhonson&Jhonson");
        clients.add(renan);


        Client valdeco = new Client();
        valdeco.setAge(26);
        valdeco.setName("ARRUDA");
        clients.add(valdeco);

        return clients;
    }
}