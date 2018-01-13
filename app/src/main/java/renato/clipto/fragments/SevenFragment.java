package renato.clipto.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;

import renato.clipto.MercadoService;
import renato.clipto.Models.Coinmarketcap;
import renato.clipto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by renato on 21/09/16.
 */
public class SevenFragment  extends Fragment {
    private static final String TAG = "";

    public SevenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        ImageView imgMoeda = (ImageView) view.findViewById(R.id.imgMoeda);
        imgMoeda.setImageResource(R.drawable.icon_ripple);

        final TextView txtUltimo = (TextView) view.findViewById(R.id.txtUltimo);
        final TextView txtMaior = (TextView) view.findViewById(R.id.txtMaior);
        final TextView txtMenor = (TextView) view.findViewById(R.id.txtMenor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MercadoService.BASE_URLCOINMARKETCAP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MercadoService service =  retrofit.create(MercadoService.class);

        Call<Coinmarketcap[]> requestMercado =  service.getRipple();

        requestMercado.enqueue(new Callback<Coinmarketcap[]>() {
            @Override
            public void onResponse(Call<Coinmarketcap[]> call, Response<Coinmarketcap[]> response) {

                if(!response.isSuccessful()){
                    Log.i("TAG","Erro:" + response.code());

                    txtUltimo.setText("Erro "+ response.code());
                }
                else{
                    Coinmarketcap[] moeda =  response.body();

                    BigDecimal _last =  new BigDecimal(0);

                    for (Coinmarketcap m : moeda) {
                        _last = new BigDecimal(m.price_brl);
                    }

                    NumberFormat nf = NumberFormat.getCurrencyInstance();

                    txtUltimo.setText(nf.format(_last));
                    txtMaior.setText("-");
                    txtMenor.setText("-");
                }
            }

            @Override
            public void onFailure(Call<Coinmarketcap[]> call, Throwable t) {
                Log.e(TAG,"Erro: " + t.getMessage());

                txtUltimo.setText(t.getMessage());
            }
        });

        return view;
    }
}
