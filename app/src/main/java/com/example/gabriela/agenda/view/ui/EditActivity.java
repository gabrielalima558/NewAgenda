package com.example.gabriela.agenda.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.gabriela.agenda.R;
import com.example.gabriela.agenda.model.Classmate;
import com.example.gabriela.agenda.presenter.EditPresenter;
import com.example.gabriela.agenda.view.contact.EditInterface;

public class EditActivity extends AppCompatActivity implements EditInterface {
    Bundle bundle;
    Classmate item;

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

        try{
            bundle = getIntent().getExtras();
            item = (Classmate) bundle.get("item");
            Log.e("TESTE2", item.toString());
        }catch (Exception e){
            Log.e("Item", e.getMessage());
        }

        editName = findViewById(R.id.edit_name);
        editAddress = findViewById(R.id.edit_address);
        editPhone = findViewById(R.id.edit_phone);
        editSite = findViewById(R.id.edit_site);
        starsBar = findViewById(R.id.stars_bar);

        editName.setText(item.getName());
        editAddress.setText(item.getAddress());
        editPhone.setText(item.getPhone());
        editSite.setText(item.getSite());
        starsBar.setRating(item.getStars());

    }


}
