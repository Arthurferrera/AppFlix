package br.com.senaijandira.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 17170075 on 09/05/2018.
 */

public class ProducaoAdapter extends ArrayAdapter<Producao> {

    ProducaoAdapter adapter;

    public ProducaoAdapter (Context ctx) {
        super(ctx, 0, new ArrayList<Producao>());
        adapter = this;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, null);
        }

        Producao item = getItem(position);

        ImageView  img_item = v.findViewById(R.id.img_item);
        TextView txt_titulo_item = v.findViewById(R.id.txt_titulo_item);

//        TODO: salvar e setar foto
        txt_titulo_item.setText(item.getTitulo());
        String caminho_imagem = item.getImagem();


//        picasso
        String linkimagem = "http://10.0.2.2/INF3T20181/APIFilmes/"+caminho_imagem;
        Picasso.with(getContext()).load(linkimagem).into(img_item);
        return v;
    }
}
