package renato.clipto;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import renato.clipto.Models.Moeda;
import renato.clipto.adapters.ListaAdapterMoedas;

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

        ArrayList<Moeda> list =  new ArrayList<Moeda>();

        for(int l=0; l<= 50; l++){
            list.add(new Moeda("","Moeda " + l, l+",00","%"+l));
        }

        ListaAdapterMoedas adapterMoedas =  new ListaAdapterMoedas(this, list);

        ListView listView =  (ListView)findViewById(R.id.listViewCoin);

        listView.setAdapter(adapterMoedas);
    }
}
