package com.example.gabriela.agenda.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gabriela.agenda.R;
import com.example.gabriela.agenda.model.Classmate;
import com.example.gabriela.agenda.model.service.ClassmateDAO;
import com.example.gabriela.agenda.view.FormActivity;

import java.io.Serializable;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;
    private Button btnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        btnNew = findViewById(R.id.btn_new);
        listaAlunos = findViewById(R.id.lista_alunos);


        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    private void loadList() {
        ClassmateDAO classmateDAO = new ClassmateDAO(this);
        List<Classmate> classmates = classmateDAO.getClassmate();
        classmateDAO.close();

        ArrayAdapter<Classmate> adapter = new ArrayAdapter<Classmate>(this, android.R.layout.simple_list_item_1, classmates);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("TESTE", String.valueOf(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem delete = menu.add("Excluir");
        MenuItem editar = menu.add("Editar");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Classmate classmate = (Classmate) listaAlunos.getItemAtPosition(info.position);

                ClassmateDAO classmateDAO = new ClassmateDAO(ListaAlunosActivity.this);
                classmateDAO.delete(classmate);
                classmateDAO.close();
                loadList();

                Toast.makeText(getApplicationContext(), "Excluir aluno: " + classmate.getName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem Item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Classmate item = (Classmate) listaAlunos.getItemAtPosition(info.position);

                Intent intent = new Intent(ListaAlunosActivity.this, FormActivity.class);
                intent.putExtra("item", item);
                startActivity(intent);
                Log.e("TESTE", item.toString());

                return false;
            }
        });

    }

}
