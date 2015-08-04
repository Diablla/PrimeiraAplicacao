package com.cast.amanda.primeiraaplicacao.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Herbs;
import com.cast.amanda.primeiraaplicacao.R;

import org.apache.http.protocol.HTTP;

public class HerbsPersistActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "CLIENT_PARAM";
    private TextView txtName;
    private TextView txtAbout;
    private TextView txtMedicalUse;
    private Herbs herbs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_herbs);

        txtAbout = (TextView) findViewById(R.id.txtAbout);
        txtName = (TextView) findViewById(R.id.txtName);
        txtMedicalUse = (TextView) findViewById(R.id.txtMedicalUse);

        herbs = new Herbs();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            herbs = (Herbs) extras.getParcelable(CLIENT_PARAM);
            if (herbs == null) {
                throw new IllegalArgumentException();
            }
            bindForm(herbs);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_add_lista, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //pega no cliente e seta no cliente
    private void bindForm(Herbs herbs) {
        txtName.setText(herbs.getName());
        txtAbout.setText(herbs.getAbout());
        txtMedicalUse.setText(herbs.getMedicalUse());
    }

    //item de share
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuShare){

            // Create the text message with a string
            final Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, herbs.getName() + "\n" + herbs.getAbout());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

            return true;
        } else if(item.getItemId() == R.id.menuFavorite){
            boolean isFavorite = !herbs.isFavorite();
            herbs.setFavorite(isFavorite);
            Toast.makeText(this, isFavorite ? "Favoritado!" : "Desfavoritado", Toast.LENGTH_SHORT).show();
            herbs.save();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

