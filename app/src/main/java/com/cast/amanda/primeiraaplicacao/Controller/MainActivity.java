package com.cast.amanda.primeiraaplicacao.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Client;
import com.cast.amanda.primeiraaplicacao.R;

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

        listaClientes();
    }

    @Override
    protected void onResume() {
        super.onResume();

        listaClientes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_add_lista, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuAddLista){
            Intent goToMainActivity = new Intent(MainActivity.this, ClientPersistActivity.class);
            startActivity(goToMainActivity);
        }
        return true;
    }

    private void listaClientes() {
        List<Client> clients = Client.getAll();

        ListView listViewClients = (ListView) findViewById(R.id.listViewClients);

        ClientListAdapter clientAdapter = new ClientListAdapter(MainActivity.this, clients);

        listViewClients.setAdapter(clientAdapter);
    }
}