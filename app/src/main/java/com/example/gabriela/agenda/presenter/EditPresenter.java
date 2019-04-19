package com.example.gabriela.agenda.presenter;

import android.app.Activity;

import com.example.gabriela.agenda.view.contact.EditInterface;

public class EditPresenter {
    EditInterface view;
    Activity activity;

    public EditPresenter(EditInterface view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }
}
