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
import renato.clipto.Models.Mercado;
import renato.clipto.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by renato on 21/09/16.
 */
public class OneFragment extends Fragment {

    private static final String TAG = "";

    public OneFragment() {
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
        imgMoeda.setImageResource(R.drawable.icon_bitcoin);

        final TextView txtUltimo = (TextView) view.findViewById(R.id.txtUltimo);
        final TextView txtMaior = (TextView) view.findViewById(R.id.txtMaior);
        final TextView txtMenor = (TextView) view.findViewById(R.id.txtMenor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MercadoService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MercadoService service =  retrofit.create(MercadoService.class);


        Call<Mercado> requestMercado =  service.getBitcoin();

        requestMercado.enqueue(new Callback<Mercado>() {
            @Override
            public void onResponse(Call<Mercado> call, Response<Mercado> response) {

                if(!response.isSuccessful()){
                    Log.i("TAG","Erro:" + response.code());
                }
                else{
                    Mercado moeda =  response.body();

                    BigDecimal _last = new BigDecimal (moeda.ticker.last);
                    BigDecimal _high = new BigDecimal (moeda.ticker.high);
                    BigDecimal _low = new BigDecimal (moeda.ticker.low);

                    NumberFormat nf = NumberFormat.getCurrencyInstance();

                    txtUltimo.setText(nf.format (_last));
                    txtMaior.setText(nf.format (_high));
                    txtMenor.setText(nf.format (_low));
                }
            }

            @Override
            public void onFailure(Call<Mercado> call, Throwable t) {
                Log.e(TAG,"Erro: " + t.getMessage());
            }
        });



        return view;
    }

}
