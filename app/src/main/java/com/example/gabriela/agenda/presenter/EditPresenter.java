package com.example.gabriela.agenda.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.gabriela.agenda.model.Classmate;
import com.example.gabriela.agenda.model.service.ClassmateDAO;
import com.example.gabriela.agenda.view.contact.EditInterface;

public class EditPresenter {
    EditInterface view;
    Activity activity;
    Classmate item;

    public EditPresenter(EditInterface view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    public void reciveBundle(Bundle bundle){
        try{
            item = (Classmate) bundle.get("item");
            Log.e("TESTE2", item.toString());
        }catch (Exception e){
            Log.e("Item", e.getMessage());
        }
    }

    public void setEditTexts(EditText editName, EditText editAddress, EditText editPhone, EditText editSite, RatingBar starsBar){
        editName.setText(item.getName());
        editAddress.setText(item.getAddress());
        editPhone.setText(item.getPhone());
        editSite.setText(item.getSite());
        starsBar.setRating(item.getStars());
    }

    public void updateItem(Context context, EditText editName, EditText editAddress, EditText editPhone, EditText editSite, RatingBar starsBar){

        item.setName(editName.getText().toString());
        item.setAddress(editAddress.getText().toString());
        item.setPhone(editPhone.getText().toString());
        item.setSite(editSite.getText().toString());
        item.setStars(starsBar.getNumStars());
        ClassmateDAO classmateDAO = new ClassmateDAO(context);
        classmateDAO.update(item);
        classmateDAO.close();
    }
}
