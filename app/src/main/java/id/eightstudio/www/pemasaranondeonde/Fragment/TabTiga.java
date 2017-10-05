package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import id.eightstudio.www.pemasaranondeonde.Database.OpenHelper;
import id.eightstudio.www.pemasaranondeonde.Provider.Statistik;
import id.eightstudio.www.pemasaranondeonde.R;
import id.eightstudio.www.pemasaranondeonde.Utils.Common;

public class TabTiga extends Fragment {
    private static final String TAG = "TabTiga";

    BottomNavigationView bottomNavigationView;
    Button hitungTrueFalseData;
    FrameLayout frameLayout;

    double dataTemp = 0.0;
    int TT, TF, FT, FF;
    int tidakBeli, intBeli;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_tab_tiga, container, false);

        hitungTrueFalseData = view.findViewById(R.id.hitungTrueFalse);
        bottomNavigationView = view.findViewById(R.id.bottomNavViewMain);
        frameLayout = view.findViewById(R.id.frameLayoutMain);

        //Hide Kalkulasi
//        bottomNavigationView.setVisibility(View.INVISIBLE);
//        frameLayout.setVisibility(View.INVISIBLE);

        //Button Kalkulasi
        hitungTrueFalseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Memanggil Fungsi
                hitungTruFalse(view);


            }
        });

        //
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragmentSelected = null;
                switch (item.getItemId()) {

                    case R.id.itemSatu: {
                        fragmentSelected = ContentSatu.newInstance();
                        break;
                    }
                    case R.id.itemDua: {
                        fragmentSelected = ContentDua.newInstance();
                        break;
                    }
                }

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutMain, fragmentSelected);
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutMain, ContentSatu.newInstance());
        transaction.commit();

        return view;
    }

    /***
     * Cek apakah semua data sudah di veifikasi
     * */
    public void hitungTruFalse(View view){

        OpenHelper database = new OpenHelper(getContext());
        SQLiteDatabase dataMain = database.getReadableDatabase();

        //Menghitung data yang belum di verifikasi
        if (database.getAllKonsumen().size() > 0) {
            for (int i = 0; i < database.getAllKonsumen().size(); i++) {
                if (database.getAllKonsumen().get(i).getRealitaPembelian() == 3){
                    Common.belumVerifikasi = 1;
                    break;
                }
            }
        }

        //Cek Dta yang belum di verifikasi
        if ( (Common.belumVerifikasi == 1) && database.getAllKonsumen().size() > 0 ){
            //Jik ada data yang belum di verifikasi
            Snackbar snackbar = Snackbar.make(view, "Verifikasi Semua Data", Snackbar.LENGTH_LONG);
            snackbar.show();

        } else {

            if ((TT + TF + FT + FF) < database.getAllKonsumen().size() ) {
                //Memanggil Fungsi
                setTrueFalseData(database);

                //Show Kalkulasi
//                bottomNavigationView.setVisibility(View.VISIBLE);
//                frameLayout.setVisibility(View.VISIBLE);

                //TODO Debugging
                Log.d(TAG, "Berhasil Melakukan Kalkulasi");
                Snackbar snackbar = Snackbar.make(view, "Berhasil", Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        }

        //TODO : Debugging TT - TF - FT -FF
        Log.d(TAG, "TT = " + TT ); Log.d(TAG, "TF = " + TF); Log.d(TAG, "FT = " + FT ); Log.d(TAG, "FF = " + FF );

        double pembilang = TT + FF;
        Log.d(TAG, "Pembilang: " + pembilang);

        double penyebut = TT + TF + FT + FF;
        Log.d(TAG, "Penyebut: " + penyebut);

        //Menentukan minimal data kalkulasi
        if (database.getAllKonsumen().size() < 5) {
            Snackbar snackbar = Snackbar.make(view, "Data Terlalu Sedikit", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            //Menangani devine by zero
            try {
                dataTemp = pembilang / penyebut;
            } catch (Exception e) {
                e.printStackTrace();
            }

            //TODO : Debugging
            Log.d(TAG, "Persentase = " + dataTemp * 100);
        }

        //Menghitung pembeli dan yang tidak beli
        hitungPembeli(getContext());

        //Insert Data ke Table statistik
        database.deleteAllStatistik(dataMain);
        database.addDataStatistik(new Statistik(String.valueOf(intBeli), String.valueOf(tidakBeli),
                String.valueOf(Math.round(dataTemp * 100)), String.valueOf(TT), String.valueOf(TF),
                String.valueOf(FT), String.valueOf(FF) ));

        database.close();
    }

    /***
     * Menghasilkan jumlah pembeli dan yang tidak beli
     * */
    public void hitungPembeli(Context context){
        OpenHelper database = new OpenHelper(context);

        intBeli = 0; tidakBeli = 0;
        for (int i = 0; i < database.getAllKonsumen().size(); i++) {

            if (database.getAllKonsumen().get(i).getRealitaPembelian() == 1){
                intBeli += 1;
            } else {
                tidakBeli += 1;
            }
        }
    }


    public void setTrueFalseData(OpenHelper database){
        //Reset Data
        TT=0; TF=0; FT=0; FF=0;

        for (int i = 0; i < database.getAllKonsumen().size(); i++) {

            //TODO : Debugging
            Log.d(TAG, "Data : " + i);
            Log.d(TAG, "Perdiksi = : " + database.getAllKonsumen().get(i).getPrediksiPembelian());
            Log.d(TAG, "Realita = : " + database.getAllKonsumen().get(i).getRealitaPembelian());

            int prediksi = database.getAllKonsumen().get(i).getPrediksiPembelian();
            int realita = database.getAllKonsumen().get(i).getRealitaPembelian();

            if (prediksi == 1 && realita == 1) {
                TT += 1;
            } else if (prediksi == 1 && realita == 2) {
                TF += 1;
            } else if (prediksi == 2 && realita == 1) {
                FT += 1;
            } else {
                FF += 1;
            }
        }

    }


}
