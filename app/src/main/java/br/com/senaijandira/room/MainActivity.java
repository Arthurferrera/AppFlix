package br.com.senaijandira.room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProducaoAdapter adapter;

    GridView list_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list_view = findViewById(R.id.list_view);

        adapter = new ProducaoAdapter(this);

        list_view.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            startActivity(new Intent(view.getContext(), CadastroActivity.class));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.clear();

        new AsyncTask<Void, Void, String>(){


            @Override
            protected String doInBackground(Void... voids) {
                String json = "";

                String url = "http://10.0.2.2/inf3T20181/TurmaA/Arthur%20Ferreira/APIFilmes/selecionar.php";
                json = HttpConnection.get(url);

                return json;
            }


            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);

                if(json == null) json = "Sem Dados";
               Log.d("onPostExecute", json);

                ArrayList<Producao> lstProducoes = new ArrayList<>();
                if(json != null){
                    try{
                        JSONArray arrayProducoes = new JSONArray(json);
                        for(int i =0; i < arrayProducoes.length(); i++){
                            JSONObject producaoJson = arrayProducoes.getJSONObject(i);

                            Producao prod = new Producao();
                            prod.setId(producaoJson.getInt("id"));
                            prod.setTitulo(producaoJson.getString("titulo"));
                            prod.setSinopse(producaoJson.getString("sinopse"));
                            prod.setLink(producaoJson.getString("link"));
                            prod.setAvaliacao(producaoJson.getDouble("avaliacao"));

                            lstProducoes.add(prod);
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    adapter.addAll(lstProducoes);
                }
            }
        }.execute();
    }
}