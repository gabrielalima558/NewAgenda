package com.example.gabriela.agenda.presenter;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.gabriela.agenda.view.helper.FormHelper;
import com.example.gabriela.agenda.model.Classmate;
import com.example.gabriela.agenda.model.service.ClassmateDAO;
import com.example.gabriela.agenda.view.contact.FormInterface;

public class FormActivityPresenter {

    FormInterface view;
    Activity activity;

    public FormActivityPresenter(FormInterface view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }

    public void insertItem(FormHelper formHelper, Context context){

        Classmate classmate = formHelper.getClassmate();

        ClassmateDAO classmateDAO = new ClassmateDAO(context);
        classmateDAO.insertClassmate(classmate);
        classmateDAO.close();

        Toast.makeText(context, "Item: " + classmate.getName() + " Salvo!", Toast.LENGTH_LONG).show();
        activity.finish();
    }
}
