package com.cast.amanda.primeiraaplicacao.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Herbs;
import com.cast.amanda.primeiraaplicacao.R;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

//pertence a hierarquia de activity e tem que referenciar a view -> parou de compilar a classe R, erro no layout ou XML
//para criar uma ACTIVITY criar layout, classe java, mapear no androidManifest
//WrappContent -> ocupe o menos de espa�o possivel / MatchParent -> ocupe o maximo de espa�o possivel
public class HerbsListActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    private Herbs herbs;
    ListView listViewHerbs;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herbs_list_menu);

        bindHerbsList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_lista, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        //searchView.getQuery();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void bindHerbsList() {

        List<Herbs> herbses = Herbs.getAll();

        listViewHerbs = (ListView) findViewById(R.id.listViewHerbs);

        final HerbsListAdapter clientAdapter = new HerbsListAdapter(HerbsListActivity.this, herbses);

        listViewHerbs.setAdapter(clientAdapter);


        listViewHerbs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listViewHerbs.getItemAtPosition(position);
                Intent intent = new Intent(HerbsListActivity.this, HerbsDetailsActivity.class);
                intent.putExtra(HerbsDetailsActivity.CLIENT_PARAM, (Parcelable) listItem);
                startActivity(intent);
            }
        });

        registerForContextMenu(listViewHerbs);
    }
}