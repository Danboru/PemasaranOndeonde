package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
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

public class TabTiga extends Fragment {
    private static final String TAG = "TabTiga";

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    double dataTemp = 0.0;

    Button hitungTrueFalseData;
    int TT, TF, FT, FF;

    int tidakBeli, intBeli;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_tab_tiga, container, false);

        hitungTrueFalseData = view.findViewById(R.id.hitungTrueFalse);
        bottomNavigationView = view.findViewById(R.id.bottomNavViewMain);
        frameLayout = view.findViewById(R.id.frameLayoutMain);

        bottomNavigationView.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.VISIBLE);

        hitungTrueFalseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "Mengkalkulasi", Toast.LENGTH_SHORT).show();

                int belumVerifikasi = 0;
                OpenHelper database = new OpenHelper(getContext());
                SQLiteDatabase dataMain = database.getReadableDatabase();

                if (database.getAllKonsumen().size() > 0) {
                    for (int i = 0; i < database.getAllKonsumen().size(); i++) {
                        if (database.getAllKonsumen().get(i).getRealitaPembelian() == 3){
                            belumVerifikasi +=1;
                            break;
                        }
                    }
                }
                
                //Memverifikasi semua data
                if ( (belumVerifikasi > 0) && database.getAllKonsumen().size() > 0 ){
                    Toast.makeText(getContext(), "Verifikasi Semua Data", Toast.LENGTH_SHORT).show();
                } else {

                    if ((TT + TF + FT + FF) == database.getAllKonsumen().size() ) {
                        Log.d(TAG, "onClick: " + "Mencapai nilai Max");
                    } else {

                        for (int i = 0; i < database.getAllKonsumen().size(); i++) {

                            Log.d(TAG, "Data : " + i);
                            Log.d(TAG, "Perdiksi = : " + database.getAllKonsumen().get(i).getPrediksiPembelian());
                            Log.d(TAG, "Realita = : " + database.getAllKonsumen().get(i).getRealitaPembelian());
                            Log.d(TAG, "\n");

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

                //FIX
                Log.d(TAG, "TT = " + TT ); //0
                Log.d(TAG, "TF = " + TF); //0
                Log.d(TAG, "FT = " + FT ); //0
                Log.d(TAG, "FF = " + FF ); //0

                double pembilang = TT + FF;
                Log.d(TAG, "Pembilang: " + pembilang);

                double penyebut = TT + TF + FT + FF;
                Log.d(TAG, "Penyebut: " + penyebut);

                //Menentukan minimal data kalkulasi
                if (database.getAllKonsumen().size() < 5) {
                    Toast.makeText(getContext(), "Data Terlalu Sedikit", Toast.LENGTH_SHORT).show();
                } else {

                    //Menangani devine by zero
                    try {
                        dataTemp = pembilang / penyebut;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "Persentase = " + dataTemp * 100);
                }

                intBeli = 0; tidakBeli = 0;
                for (int i = 0; i < database.getAllKonsumen().size(); i++) {

                    if (database.getAllKonsumen().get(i).getRealitaPembelian() == 1){
                        intBeli += 1;
                    } else {
                        tidakBeli += 1;
                    }
                }

                database.deleteAllStatistik(dataMain);
                database.addDataStatistik(new Statistik(String.valueOf(intBeli), String.valueOf(tidakBeli), String.valueOf(Math.round(dataTemp * 100))));

                database.close();
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
}
