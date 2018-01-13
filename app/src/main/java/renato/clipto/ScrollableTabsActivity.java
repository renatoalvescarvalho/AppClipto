package renato.clipto;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import renato.clipto.adapters.ViewPagerAdapter;
import renato.clipto.fragments.FiveFragment;
import renato.clipto.fragments.FourFragment;
import renato.clipto.fragments.OneFragment;
import renato.clipto.fragments.SevenFragment;
import renato.clipto.fragments.SixFragment;
import renato.clipto.fragments.ThreeFragment;
import renato.clipto.fragments.TwoFragment;

public class ScrollableTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_favorite_white_24dp,
            R.drawable.ic_call_white_24dp,
            R.drawable.ic_contacts_white_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable_tabs);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Criptomoedas");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), false);
        adapter.addFrag(new OneFragment(), "Bitcoin");
        adapter.addFrag(new TwoFragment(), "Litecoin");
        adapter.addFrag(new ThreeFragment(), "BCash");
        adapter.addFrag(new FourFragment(), "Ethereum");
        adapter.addFrag(new FiveFragment(), "Dash");
        adapter.addFrag(new SixFragment(), "IOTA");
        adapter.addFrag(new SevenFragment(), "Ripple");
        viewPager.setAdapter(adapter);
    }
}
