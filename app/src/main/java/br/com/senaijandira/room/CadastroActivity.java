package br.com.senaijandira.room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

import br.com.senaijandira.room.api.InserirProducaoAPI;

public class CadastroActivity extends AppCompatActivity {

    RatingBar rt_avaliacao;
    EditText txt_sinopse,txt_link,txt_titulo;
    ImageView img_producao;
    Bitmap foto;

    String titulo, sinopse, link;
    Double avaliacao;

    int COD_GALERIA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //findview by Ids
        rt_avaliacao = findViewById(R.id.rt_avaliacao);
        txt_sinopse=findViewById(R.id.txt_sinopse);
        txt_link=findViewById(R.id.txt_link);
        txt_titulo=findViewById(R.id.txt_titulo);
        img_producao = findViewById(R.id.img_producao);

    }


    public void salvarFilme(View view) {

        titulo = txt_titulo.getText().toString();
        sinopse = txt_sinopse.getText().toString();
        link = txt_link.getText().toString();
        avaliacao = Double.valueOf(rt_avaliacao.getRating());

//        URL de Quarta-Feira
        String url = "http://10.0.2.2/inf3T20181/TurmaA/Arthur%20Ferreira/APIFilmes/inserir.php?";
//        URL de Terça-Feira
//        String url = "http://10.0.2.2/INF3T20181/APIFilmes/inserir.php?";
        url += "titulo="+titulo+"&sinopse="+sinopse+"&link="+link+"&avaliacao="+avaliacao;
        new InserirProducaoAPI(url, this).execute();

        finish();

    }


    //abrir a galeria de imagens atraves
    //da imageView
    public void abrirGaleria(View v){

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent , COD_GALERIA);
    }

    //Aqui que volta o resultado da chamada a galeria
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COD_GALERIA ){

            if(resultCode == Activity.RESULT_OK){
                //selecionou alguma coisa

                try {
                    //pegando a img em binario
                    InputStream inp = getContentResolver()
                            .openInputStream(data.getData());

                    //transformando o stream em bitmap
                    foto = BitmapFactory.decodeStream(inp);

                    //colocando a fotinha
                    img_producao.setImageBitmap(foto);

                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }
    }
}
