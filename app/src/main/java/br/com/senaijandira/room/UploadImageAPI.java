package br.com.senaijandira.room;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by 17170075 on 29/05/2018.
 */

public class UploadImageAPI extends AsyncTask<Bitmap, Void, String> {

    final String url = "http://10.0.2.2/INF3T20181/APIFilmes/upload_imagem.php";

    ProgressDialog progress;
    Context ctx;
    StringBuffer nomeImage;

    public UploadImageAPI(Context ctx, StringBuffer nomeImage){
        this.ctx = ctx;
        this.nomeImage = nomeImage;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = ProgressDialog.show(ctx, "Upload", "Aguarde...", false, false);
    }

    @Override
    protected String doInBackground(Bitmap... bitmaps) {

        if(bitmaps.length > 0){
            Bitmap img  = bitmaps[0];
            byte[] arrBytes = ImageHelper.toByteArray(img);
            String img_str = Base64.encodeToString(arrBytes, Base64.DEFAULT);
            HashMap<String, String> values = new HashMap<>();
            values.put("image", img_str);
            String retorno = HttpConnection.post(url, values);
            return retorno;
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progress.dismiss();
        nomeImage.append(s);
        Log.d("doInBackground", s);
    }
}
