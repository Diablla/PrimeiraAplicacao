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
        bindFab();
    }

    private void bindFab() {
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainActivity = new Intent(HerbsListActivity.this, HerbsPersistActivity.class);
                startActivity(goToMainActivity);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshClientList();
    }

    private void refreshClientList() {
        HerbsListAdapter adapter = (HerbsListAdapter) listViewClients.getAdapter();
        adapter.setClients(Herbs.getAll());
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
            // Create the text message with a string
            final Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Seu texto aqui...");
            sendIntent.setType(HTTP.PLAIN_TEXT_TYPE);

            // Create intent to show the chooser dialog
            final Intent chooser = Intent.createChooser(sendIntent, "Titulo Chooser");

            // Verify the original intent will resolve to at least one activity
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuEdit){
        Intent intent = new Intent(HerbsListActivity.this, HerbsPersistActivity.class);
            intent.putExtra(HerbsPersistActivity.CLIENT_PARAM, (Parcelable) herbs);
            startActivity(intent);
        }else if(item.getItemId() == R.id.menuDelete){
            new AlertDialog.Builder(HerbsListActivity.this).setMessage("Confirma exclusao?")
                    .setTitle("confirma").setPositiveButton("yes", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    herbs.delete();
                    Toast.makeText(HerbsListActivity.this, getString(R.string.delete), Toast.LENGTH_LONG).show();
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

        List<Herbs> herbses = Herbs.getAll();

        listViewClients = (ListView) findViewById(R.id.listViewClients);

        final HerbsListAdapter clientAdapter = new HerbsListAdapter(HerbsListActivity.this, herbses);

        listViewClients.setAdapter(clientAdapter);

        /*listViewClients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                herbs = (Herbs) parent.getItemAtPosition(position);
                return false;
            }
        });*/

        listViewClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listViewClients.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(),"Veio "+id,Toast.LENGTH_SHORT).show();
                //Intent goToMainActivity = new Intent(LoginActivity.this, HerbsListActivity.class);

                Intent intent = new Intent(HerbsListActivity.this, HerbsPersistActivity.class);
                intent.putExtra(HerbsPersistActivity.CLIENT_PARAM, (Parcelable) listItem);
                startActivity(intent);
            }
        });

        registerForContextMenu(listViewClients);
    }
}