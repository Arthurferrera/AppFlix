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

//        ImageView  img_item = v.findViewById(R.id.img_item);
        TextView txt_titulo_item = v.findViewById(R.id.txt_titulo_item);

//        TODO: salvar e setar foto
//        img_item.setImageBitmap(item.getFoto);
        txt_titulo_item.setText(item.getTitulo());

        return v;
    }
}
