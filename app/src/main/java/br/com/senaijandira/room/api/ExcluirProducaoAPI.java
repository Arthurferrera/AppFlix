package br.com.senaijandira.room.api;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.senaijandira.room.HttpConnection;
import br.com.senaijandira.room.VisualizarActivity;

/**
 * Created by 17170075 on 29/05/2018.
 */

public class ExcluirProducaoAPI extends AsyncTask<Void, Void, String>{
    private String url;

    private Activity activity;

    public ExcluirProducaoAPI(String url, Activity activity) {
        this.url = url;
        this.activity =  activity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return HttpConnection.get(url);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(s!=null){
            try {
                JSONObject jsonObjeto = new JSONObject(s);
                boolean sucesso = jsonObjeto.getBoolean("Sucesso");

                if(sucesso){
                    Toast.makeText(activity, "Excluido com sucesso!", Toast.LENGTH_SHORT).show();
                    activity.finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
