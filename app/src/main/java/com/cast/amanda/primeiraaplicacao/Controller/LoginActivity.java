package com.cast.amanda.primeiraaplicacao.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cast.amanda.primeiraaplicacao.R;


/**
 * Created by Amanda on 20/07/2015.
 */
public class LoginActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindLoginButton();
    }

    private void bindLoginButton() {
        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainActivity = new Intent(LoginActivity.this, ClientListActivity.class);
                startActivity(goToMainActivity);
            }
        });
    }

}
