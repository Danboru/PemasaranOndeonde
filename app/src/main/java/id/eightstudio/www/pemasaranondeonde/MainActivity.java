package id.eightstudio.www.pemasaranondeonde;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import id.eightstudio.www.pemasaranondeonde.Adapter.TabAdapter;
import id.eightstudio.www.pemasaranondeonde.Database.OpenHelper;
import id.eightstudio.www.pemasaranondeonde.Provider.Statistik;
import id.eightstudio.www.pemasaranondeonde.Utils.Common;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    OpenHelper database;
    SQLiteDatabase sqliteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database= new OpenHelper(MainActivity.this);
        sqliteDatabase = database.getReadableDatabase();

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutMain);
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));
        viewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);

        int page = 1;
        viewPager.setCurrentItem(page);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //Defaulth Value
        database.addDataStatistik(new Statistik("0","0","0","0","0","0","0"));
        Common.dataSatu = database.getAllStatistik().get(0).getPersentase();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item1 : {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Reset Database");
                alertDialog.setMessage("Kamu Yakin Ingin Menghapus Seluruh Data ?");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "YAKIN",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                database.deleteAllKonsumen(sqliteDatabase);

                                Toast.makeText(MainActivity.this, "Reset Database Berhasil", Toast.LENGTH_SHORT).show();

                                dialog.dismiss();
                            }
                        });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "BATAL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Reset Di Batalkan", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
                break;
            }
            default : {
                popupAbout(MainActivity.this);
                break;
            }
        }
        return true;
    }

    public void popupAbout(Context context){

        final Dialog dialog = new Dialog(context);

        //Set layout
        dialog.setContentView(R.layout.popup_about);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(true);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        dialog.show();

    }

}
