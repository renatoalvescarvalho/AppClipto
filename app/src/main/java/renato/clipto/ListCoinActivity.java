package renato.clipto;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

import renato.clipto.Models.Coinmarketcap;
import renato.clipto.Models.Mercado;
import renato.clipto.Models.Moeda;
import renato.clipto.adapters.ListaAdapterMoedas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListCoinActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_coin);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Menu");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



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

                        list.add(new Moeda("",m.getName(), nf.format(_last),m.getPercent_change_24h()));

                    }


                    ListaAdapterMoedas adapterMoedas =  new ListaAdapterMoedas(ListCoinActivity.this,  list);

                    ListView listView =  (ListView)findViewById(R.id.listViewCoin);

                    listView.setAdapter(adapterMoedas);
                }
            }

            @Override
            public void onFailure(Call<Moeda[]> call, Throwable t) {

            }
        });
    }
}
