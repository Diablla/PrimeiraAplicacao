package com.cast.amanda.primeiraaplicacao.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cast.amanda.primeiraaplicacao.Model.Entities.Herbs;
import com.cast.amanda.primeiraaplicacao.Model.Services.CepService;
import com.cast.amanda.primeiraaplicacao.Model.Services.ClientAdress;
import com.cast.amanda.primeiraaplicacao.R;
import com.cast.amanda.primeiraaplicacao.util.FormHelper;

public class HerbsPersistActivity extends AppCompatActivity {

    public static String CLIENT_PARAM = "CLIENT_PARAM";
    private EditText editTextName;
    private EditText editAbout;
    private EditText editMedicalUse;
    private Herbs herbs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_client);

        editAbout = (EditText) findViewById(R.id.persistAbout);
        editMedicalUse = (EditText) findViewById(R.id.persistMedicalUse);
        editTextName = (EditText) findViewById(R.id.persistTextName);
        editTextName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0);
        editTextName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextName.getRight() - editTextName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                        startActivityForResult(goToSOContacts, 999);
                    }
                }
                return false;
            }
        });

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
        this.getMenuInflater().inflate(R.menu.menu_client_persist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSave) {
            if (FormHelper.requireValidate(HerbsPersistActivity.this, editAbout, editTextName,
                    editMedicalUse)) {
                Herbs herbs = bindClient();
                herbs.save();
                Toast.makeText(HerbsPersistActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                HerbsPersistActivity.this.finish();
            }
        } else if (item.getItemId() == R.id.menuClear) {
            editTextName.setText("");
            editAbout.setText("");
            editMedicalUse.setText("");
        }
        return true;
    }

    //pega no cliente e seta no form
    private Herbs bindClient() {
        if (herbs == null) {
            herbs = new Herbs();
        }
        herbs.setName(editTextName.getText().toString());
        herbs.setAbout(editAbout.getText().toString());
        herbs.setMedicalUse(editMedicalUse.getText().toString());

        return herbs;
    }

    //pega no form e seta no cliente
    private void bindForm(Herbs herbs) {
        editTextName.setText(herbs.getName());
        editTextName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_edittext_client, 0);

        editAbout.setText(herbs.getAbout());
        editMedicalUse.setText(herbs.getMedicalUse());
    }

    /**
     * @see <a href="http://developer.android.com/training/basics/intents/result.html">Getting a Result from an Activity</a>
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    };
                    final Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    editTextName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    editMedicalUse.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

                    cursor.close();
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private ProgressDialog progressDialog;

    private class getAddressByCep extends AsyncTask<String, Void, ClientAdress> {
        @Override
        protected ClientAdress doInBackground(String... params) {
            try {
                return CepService.getAddressBy(params[0]);
            }
            catch (Exception e){
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(HerbsPersistActivity.this);
            progressDialog.setMessage(getString(R.string.Loading));
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ClientAdress clientAdress) {
            if(clientAdress != null){
                super.onPostExecute(clientAdress);
            }
            else{
                Toast.makeText(HerbsPersistActivity.this,"Erro de conexão.", Toast.LENGTH_LONG).show();
            }
            progressDialog.dismiss();
        }
    }
}

