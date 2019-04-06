package com.example.gabriela.agenda.helper;

import android.widget.EditText;
import android.widget.RatingBar;

import com.example.gabriela.agenda.R;
import com.example.gabriela.agenda.model.Classmate;
import com.example.gabriela.agenda.view.FormActivity;

public class FormHelper {
    EditText nameForm,addressForm,phoneForm,siteForm ;
    RatingBar starsForm;


    public FormHelper(FormActivity formActivity) {
        nameForm = (EditText) formActivity.findViewById(R.id.edit_name);
        addressForm = (EditText) formActivity.findViewById(R.id.edit_address);
        phoneForm = (EditText) formActivity.findViewById(R.id.edit_phone);
        siteForm = (EditText) formActivity.findViewById(R.id.edit_site);
        starsForm = (RatingBar) formActivity.findViewById(R.id.stars_bar);
    }

    public Classmate getClassmate() {
        Classmate classmate = new Classmate();
        classmate.setName(nameForm.getText().toString());
        classmate.setAddress(addressForm.getText().toString());
        classmate.setPhone(phoneForm.getText().toString());
        classmate.setSite(siteForm.getText().toString());
        classmate.setStars(Double.valueOf(starsForm.getProgress()));
        return classmate;
    }
}
