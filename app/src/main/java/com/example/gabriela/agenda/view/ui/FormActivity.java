package com.example.gabriela.agenda.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.gabriela.agenda.helper.FormHelper;
import com.example.gabriela.agenda.R;
import com.example.gabriela.agenda.model.Classmate;
import com.example.gabriela.agenda.model.service.ClassmateDAO;

public class FormActivity extends AppCompatActivity {

    private FormHelper formHelper;
    EditText nameForm, addressForm, phoneForm, siteForm;
    RatingBar starsForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        formHelper = new FormHelper(this);

        nameForm = findViewById(R.id.edit_name);
        addressForm = findViewById(R.id.edit_address);
        phoneForm = findViewById(R.id.edit_phone);
        siteForm = findViewById(R.id.edit_site);
        starsForm = findViewById(R.id.stars_bar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_ok:

                Classmate classmate = formHelper.getClassmate();

                ClassmateDAO classmateDAO = new ClassmateDAO(this);
                classmateDAO.insertClassmate(classmate);
                classmateDAO.close();

                Toast.makeText(getApplicationContext(), "Item: " + classmate.getName() + " Salvo!", Toast.LENGTH_LONG).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
