package com.cast.amanda.primeiraaplicacao.Controller;

import android.app.AlertDialog;
import android.content.Context;
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

import com.cast.amanda.primeiraaplicacao.Model.Entities.Herbs;
import com.cast.amanda.primeiraaplicacao.R;
import com.melnykov.fab.FloatingActionButton;

import org.apache.http.protocol.HTTP;

import java.util.List;

//pertence a hierarquia de activity e tem que referenciar a view -> parou de compilar a classe R, erro no layout ou XML
//para criar uma ACTIVITY criar layout, classe java, mapear no androidManifest
//WrappContent -> ocupe o menos de espa�o possivel / MatchParent -> ocupe o maximo de espa�o possivel
public class HerbsListActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    private Herbs herbs;
    ListView listViewClients;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list_menu);

        bindClientList();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void bindClientList() {

        List<Herbs> herbses = Herbs.getAll();

        listViewClients = (ListView) findViewById(R.id.listViewClients);

        final HerbsListAdapter clientAdapter = new HerbsListAdapter(HerbsListActivity.this, herbses);

        listViewClients.setAdapter(clientAdapter);


        listViewClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listViewClients.getItemAtPosition(position);
                Intent intent = new Intent(HerbsListActivity.this, HerbsPersistActivity.class);
                intent.putExtra(HerbsPersistActivity.CLIENT_PARAM, (Parcelable) listItem);
                startActivity(intent);
            }
        });

        registerForContextMenu(listViewClients);
    }
}