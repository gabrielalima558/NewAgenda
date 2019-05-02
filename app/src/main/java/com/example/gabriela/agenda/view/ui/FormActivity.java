package com.example.gabriela.agenda.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.gabriela.agenda.view.helper.FormHelper;
import com.example.gabriela.agenda.R;
import com.example.gabriela.agenda.presenter.FormActivityPresenter;
import com.example.gabriela.agenda.view.contact.FormInterface;

public class FormActivity extends AppCompatActivity implements FormInterface{

    private FormHelper formHelper;
    EditText nameForm, addressForm, phoneForm, siteForm;
    RatingBar starsForm;
    FormActivityPresenter formActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        formActivityPresenter = new FormActivityPresenter(this, FormActivity.this);

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

                formActivityPresenter.insertItem(formHelper, this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
