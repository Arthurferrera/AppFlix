package br.com.senaijandira.room.api;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.senaijandira.room.HttpConnection;

/**
 * Created by 17170075 on 09/05/2018.
 */

public class InserirProducaoAPI extends AsyncTask<Void, Void, String>{
    private String url;

    private Activity activity;

    public InserirProducaoAPI(String url, Activity activity) {
        this.url = url;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return HttpConnection.get(url);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s!=null){
            try {
                JSONObject jsonObjeto = new JSONObject(s);

                boolean sucesso = jsonObjeto.getBoolean("Sucesso");

                if(sucesso){
                    Toast.makeText(activity,"Inserido com sucesso",Toast.LENGTH_SHORT).show();

                    activity.finish();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
