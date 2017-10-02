package id.eightstudio.www.pemasaranondeonde;

import android.content.res.ColorStateList;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.eightstudio.www.pemasaranondeonde.Adapter.TabAdapter;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutMain);
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));

        viewPager = (ViewPager) findViewById(R.id.viewPagerMain);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
