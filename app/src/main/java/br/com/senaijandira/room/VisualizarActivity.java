package br.com.senaijandira.room;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import br.com.senaijandira.room.api.ExcluirProducaoAPI;

public class VisualizarActivity extends AppCompatActivity {

    ImageView img_producao;
    RatingBar rt_avaliacao;
    TextView txt_sinopse;

    Integer idProducao;
    Context ctx = this;
    String avaliacao, link, sinopse, titulo;


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

//        URL de Quarta-Feira
//        final String url = "http://10.0.2.2/inf3T20181/TurmaA/Arthur%20Ferreira/APIFilmes/selecionarUm.php?id="+idProducao;
//        URL de Terça-Feira
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
                    titulo = objeto.getString("titulo");
                    sinopse = objeto.getString("sinopse");
                    link = objeto.getString("link");
                    avaliacao = String.valueOf(objeto.getDouble("avaliacao"));
                    String foto = objeto.getString("imagem");

                    String linkimagem = "http://10.0.2.2/INF3T20181/APIFilmes/"+foto;
                    Picasso.with(ctx).load(linkimagem).into(img_producao);

                    rt_avaliacao.setRating(Float.parseFloat(avaliacao));
                    txt_sinopse.setText(sinopse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    public void assistirProducao(View view) {

        Uri url = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, url);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_excluir){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Excluir");
            builder.setMessage("Tem certeza que deseja Excluir?");
            final Activity activity = this;
            builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    URL de Quarta-Feira
//                    final String url = "http://10.0.2.2/inf3T20181/TurmaA/Arthur%20Ferreira/APIFilmes/delete.php?id="+idProducao;
//                    URL de Terça-Feira
                    final String url = "http://10.0.2.2/INF3T20181/APIFilmes/delete.php?id="+idProducao;
                    new ExcluirProducaoAPI(url, activity).execute();

//                    Toast.makeText(activity, "Excluido com sucesso!", Toast.LENGTH_SHORT).show();
//                    finish();
                }
            });

            builder.setNegativeButton("NÃO", null);
            builder.create().show();

            return true;
        } else if(id == R.id.action_editar){
            Intent intent = new Intent(this, CadastroActivity.class);
            intent.putExtra("id",idProducao);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
