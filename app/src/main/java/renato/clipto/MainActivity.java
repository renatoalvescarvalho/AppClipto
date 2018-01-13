package renato.clipto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this, ScrollableTabsActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void centerTabsClick(View view) {
        intent = new Intent(MainActivity.this, CenterTabsActivity.class);
        startActivity(intent);
    }


    public void scrollableTabsClick(View view) {
        intent = new Intent(MainActivity.this, ScrollableTabsActivity.class);
        startActivity(intent);
    }

    public void customTabsClick(View view) {
        intent = new Intent(MainActivity.this, CustomTabsActivity.class);
        startActivity(intent);
    }


}
