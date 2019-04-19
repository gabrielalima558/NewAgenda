package com.example.gabriela.agenda.presenter;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gabriela.agenda.model.Classmate;
import com.example.gabriela.agenda.model.service.ClassmateDAO;
import com.example.gabriela.agenda.view.contact.ListaItensInterface;

import java.util.List;

public class ListaInterfacePresenter {
    ListaItensInterface view;
    Activity activity;

    public ListaInterfacePresenter(ListaItensInterface view, Activity activity) {
        this.view = view;
        this.activity = activity;
    }


    public void loadList(ListView listaAlunos) {
        ClassmateDAO classmateDAO = new ClassmateDAO(activity);
        List<Classmate> classmates = classmateDAO.getClassmate();
        classmateDAO.close();

        ArrayAdapter<Classmate> adapter = new ArrayAdapter<Classmate>(activity, android.R.layout.simple_list_item_1, classmates);
        listaAlunos.setAdapter(adapter);
    }
}
