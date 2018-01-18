package renato.clipto.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

import renato.clipto.ListCoinActivity;
import renato.clipto.MainActivity;
import renato.clipto.MercadoService;
import renato.clipto.Models.Coinmarketcap;
import renato.clipto.Models.Moeda;
import renato.clipto.R;
import renato.clipto.adapters.ListaAdapterMoedas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Renato on 18/01/2018.
 */

public class ListFragment extends Fragment {

    private static final String TAG = "";
    private View view;
    Context thisContext;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thisContext = container.getContext();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_list_coin, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MercadoService.BASE_URLCOINMARKETCAP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MercadoService service =  retrofit.create(MercadoService.class);

        Call<Moeda[]> requestMoedas =  service.getListAll();

        requestMoedas.enqueue(new Callback<Moeda[]>() {
            @Override
            public void onResponse(Call<Moeda[]> call, Response<Moeda[]> response) {

                if(!response.isSuccessful()){
                    Log.i("TAG","Erro:" + response.code());
                }
                else{
                    ArrayList<Moeda> list =  new ArrayList<Moeda>();

                    Moeda[] moedas =  response.body();

                    BigDecimal _last =  new BigDecimal(0);


                    for (Moeda m : moedas) {

                        _last = new BigDecimal(m.getPrice_brl());

                        NumberFormat nf = NumberFormat.getCurrencyInstance();

                        list.add(new Moeda ("",m.getName(), nf.format(_last),m.getPercent_change_24h() + "%", m.getSymbol()));
                    }

                    ListaAdapterMoedas adapterMoedas =  new ListaAdapterMoedas(thisContext,  list);

                    ListView listView =  (ListView)view.findViewById(R.id.listViewCoin);

                    listView.setAdapter(adapterMoedas);
                }
            }

            @Override
            public void onFailure(Call<Moeda[]> call, Throwable t) {

            }
        });





        return view;
    }
}

