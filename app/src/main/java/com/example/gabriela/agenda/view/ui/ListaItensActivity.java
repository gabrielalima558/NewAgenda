package com.example.gabriela.agenda.view.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gabriela.agenda.R;
import com.example.gabriela.agenda.model.Classmate;
import com.example.gabriela.agenda.model.service.ClassmateDAO;
import com.example.gabriela.agenda.presenter.ListaInterfacePresenter;
import com.example.gabriela.agenda.view.contact.ListaItensInterface;

import java.util.List;

public class ListaItensActivity extends AppCompatActivity implements ListaItensInterface{

    private ListView listaAlunos;
    private Button btnNew;
    private ListaInterfacePresenter listaInterfacePresenter;

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

        listaInterfacePresenter = new ListaInterfacePresenter(this, ListaItensActivity.this);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Classmate item = (Classmate) listaAlunos.getItemAtPosition(i);
                Log.e("TESTE", String.valueOf(item));
                redirectActivityEdit(item);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaInterfacePresenter.loadList(listaAlunos);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("TESTE", String.valueOf(item.getItemId()));
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem delete = menu.add("Excluir");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Classmate classmate = (Classmate) listaAlunos.getItemAtPosition(info.position);

                listaInterfacePresenter.deleteItem(classmate);
                listaInterfacePresenter.loadList(listaAlunos);

                Toast.makeText(getApplicationContext(), "Item: " + classmate.getName() + "Excluído", Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

    @Override
    public void redirectActivityEdit(Classmate item) {
        Intent intent = new Intent(ListaItensActivity.this, EditActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
