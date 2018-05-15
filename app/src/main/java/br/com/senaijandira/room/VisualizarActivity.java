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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class VisualizarActivity extends AppCompatActivity {

    ImageView img_producao;
    RatingBar rt_avaliacao;
    TextView txt_sinopse;

    Integer idProducao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        img_producao = findViewById(R.id.img_producao);
        rt_avaliacao = findViewById(R.id.rt_avaliacao);
        txt_sinopse = findViewById(R.id.txt_sinopse);

        idProducao = intent.getIntExtra("idProducao", 0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final String url = "http://10.0.2.2/INF3T20181/APIFilmes/selecionarUm.php?id="+idProducao;

        new AsyncTask<Void, Void, Void>(){

            String retorno = "";

            @Override
            protected Void doInBackground(Void... voids) {
//                tudo que estiver aqui será executado em segundo plano

                retorno = HttpConnection.get(url);


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                try {
                    JSONObject objeto = new JSONObject(retorno);
                    String titulo = objeto.getString("titulo");
                    String sinopse = objeto.getString("sinopse");
                    String link = objeto.getString("link");
                    String avaliacao = String.valueOf(objeto.getString("avaliacao"));

                    rt_avaliacao.setRating(Float.parseFloat(avaliacao));
                    txt_sinopse.setText(sinopse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute();


    }

    public void assistirProducao(View view) {
    }
}
