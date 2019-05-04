package com.example.gabriela.agenda.view.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.gabriela.agenda.R;
import com.example.gabriela.agenda.presenter.EditPresenter;
import com.example.gabriela.agenda.view.contact.EditInterface;

public class EditActivity extends AppCompatActivity implements EditInterface {
    Bundle bundle;
    EditText editName;
    EditText editAddress;
    EditText editPhone;
    EditText editSite;
    RatingBar starsBar;
    EditPresenter editPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editPresenter = new EditPresenter(this, EditActivity.this);

        bundle = getIntent().getExtras();
        editPresenter.reciveBundle(bundle);

        editName = findViewById(R.id.edit_name);
        editAddress = findViewById(R.id.edit_address);
        editPhone = findViewById(R.id.edit_phone);
        editSite = findViewById(R.id.edit_site);
        starsBar = findViewById(R.id.stars_bar);

        editPresenter.setEditTexts(editName, editAddress, editPhone, editSite, starsBar);

    }

    @Override
    public void redirectActivity() {
        finish();
        Intent intent = new Intent(this, ListaItensActivity.class);
        startActivity(intent);

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
                editPresenter.updateItem(this, editName, editAddress, editPhone, editSite, starsBar);
                redirectActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
