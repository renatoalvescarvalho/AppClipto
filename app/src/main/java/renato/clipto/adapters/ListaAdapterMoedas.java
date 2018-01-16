package renato.clipto.adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.content.Context;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import renato.clipto.Models.Moeda;
import renato.clipto.R;

/**
 * Created by Renato on 13/01/2018.
 */

public class ListaAdapterMoedas extends ArrayAdapter<Moeda> {

    private Context context;
    private ArrayList<Moeda> lista;

    public ListaAdapterMoedas(Context context, ArrayList<Moeda>  lista ){
        super(context,0, lista);

        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Moeda item  = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.item, null );

        TextView tNome = (TextView)convertView.findViewById(R.id.tNome);
        tNome.setText(item.getName());

        TextView tValor = (TextView)convertView.findViewById(R.id.tValor);
        tValor.setText(item.getPrice_brl());

        TextView tVariacao = (TextView)convertView.findViewById(R.id.tVariacao);
        tVariacao.setText(item.getPercent_change_24h());

        if(item.getPercent_change_24h().contains("-") ){
             tVariacao.setTextColor(Color.RED);
         }
         else{
             tVariacao.setTextColor(Color.parseColor("#228B22"));
         }

        return  convertView;

    }
}
