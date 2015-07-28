package com.cast.amanda.primeiraaplicacao.Controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Client;
import com.cast.amanda.primeiraaplicacao.R;

import java.io.Serializable;
import java.util.List;

//pertence a hierarquia de activity e tem que referenciar a view -> parou de compilar a classe R, erro no layout ou XML
//para criar uma ACTIVITY criar layout, classe java, mapear no androidManifest
//WrappContent -> ocupe o menos de espaço possivel / MatchParent -> ocupe o maximo de espaço possivel
public class ClientListActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    private Client client;
    ListView listViewClients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindClientList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshClientList();
    }

    private void refreshClientList() {
        ClientListAdapter adapter = (ClientListAdapter) listViewClients.getAdapter();
        adapter.setClients(Client.getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_add_lista, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuAddLista){
            Intent goToMainActivity = new Intent(ClientListActivity.this, ClientPersistActivity.class);
            startActivity(goToMainActivity);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuEdit){
        Intent intent = new Intent(ClientListActivity.this, ClientPersistActivity.class);
            intent.putExtra(ClientPersistActivity.CLIENT_PARAM, (Parcelable) client);
            startActivity(intent);
        }else if(item.getItemId() == R.id.menuDelete){
            new AlertDialog.Builder(ClientListActivity.this).setMessage("Confirma exclusão?")
                    .setTitle("confirma").setPositiveButton("yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    client.delete();
                    Toast.makeText(ClientListActivity.this, getString(R.string.delete), Toast.LENGTH_LONG).show();
                    refreshClientList();
                }
            }).setNegativeButton("not", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create().show();

        }
            return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.getMenuInflater().inflate(R.menu.menu_client_list_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void bindClientList() {

        List<Client> clients = Client.getAll();

        listViewClients = (ListView) findViewById(R.id.listViewClients);

        final ClientListAdapter clientAdapter = new ClientListAdapter(ClientListActivity.this, clients);

        listViewClients.setAdapter(clientAdapter);

        listViewClients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                client = (Client) parent.getItemAtPosition(position);
                return false;
            }
        });

        registerForContextMenu(listViewClients);
    }
}