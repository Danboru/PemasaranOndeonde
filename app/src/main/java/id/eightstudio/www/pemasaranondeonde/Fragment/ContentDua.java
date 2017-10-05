package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import id.eightstudio.www.pemasaranondeonde.Database.OpenHelper;
import id.eightstudio.www.pemasaranondeonde.R;

public class ContentDua extends Fragment {
    private static final String TAG = "ContentDua";
    
    TextView konsumenBeli, konsumenTidakBeli, persentase;
    CardView detailKelayakan;

    private String jumlahPembeli, jumlahTidakBeli, persentaseData;

    //Singleton
    public static ContentDua newInstance() {
        ContentDua fragment = new ContentDua();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_content_dua, container, false);

        konsumenBeli = view.findViewById(R.id.txtJumlahPembeli);
        konsumenTidakBeli = view.findViewById(R.id.txtJumlahTidakBeli);
        persentase = view.findViewById(R.id.txtPersentase);
        detailKelayakan = view.findViewById(R.id.detailPersentaseKelayakan);

        OpenHelper database = new OpenHelper(getContext());
        jumlahPembeli = database.getAllStatistik().get(0).getJumlahPembeli();
        jumlahTidakBeli = database.getAllStatistik().get(0).getJumlahTidakBeli();
        persentaseData = database.getAllStatistik().get(0).getPersentase();

        //database.addDataStatistik(new Statistik("123", "456", "987"));
        Log.d(TAG, "Jumlah Pembeli = " + jumlahPembeli);
        Log.d(TAG, "Jumlah Tidak Beli = " + jumlahTidakBeli);
        Log.d(TAG, "Jumlah Persentase = " + persentaseData);

        konsumenBeli.setText(jumlahPembeli);
        konsumenTidakBeli.setText(jumlahTidakBeli);
        persentase.setText(persentaseData + " %");

        database.close();

        //Detail Kelayakan
        detailKelayakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getContext());

                //Set layout
                dialog.setContentView(R.layout.popup_detail_kelayakan);

                //Membuat agar dialog tidak hilang saat di click di area luar dialog
                dialog.setCanceledOnTouchOutside(true);

                //Membuat dialog agar berukuran responsive
                DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);


                dialog.show();

            }
        });

        return view;
    }


}
