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
    String TT, TF, FT, FF;

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

        //Data di ambil dari database
        //Dan fungsi insert ada di TabTiga
        OpenHelper database = new OpenHelper(getContext());
        jumlahPembeli = database.getAllStatistik().get(0).getJumlahPembeli();
        jumlahTidakBeli = database.getAllStatistik().get(0).getJumlahTidakBeli();
        persentaseData = database.getAllStatistik().get(0).getPersentase();

        //TODO : Debugging
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

                /**
                 * Data udah kesimpen tapi TextView yang di gunakan umntuk menampilkan data nggk bisa di set
                 * Kemungkinan terbesar view nggk ada context buat nyari id textViewnya
                 * */

                //FIX
                Dialog dialog = new Dialog(view.getContext());

                //Set layout
                dialog.setContentView(R.layout.popup_detail_kelayakan);

                dialog.setCanceledOnTouchOutside(true);

                //Make Responsive
                DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);


                //TT - TF - FT -FF
                OpenHelper database = new OpenHelper(getContext());
                TT = database.getAllStatistik().get(0).getTT();
                TF = database.getAllStatistik().get(0).getTF();
                FT = database.getAllStatistik().get(0).getFT();
                FF = database.getAllStatistik().get(0).getFF();

                TextView trueTrue;
                trueTrue = dialog.findViewById(R.id.txtTrueTrue);

                TextView trueFalse;
                trueFalse = dialog.findViewById(R.id.txtTrueFalse);

                TextView falseTrue;
                falseTrue = dialog.findViewById(R.id.txtFalseTrue);

                TextView falseFalse;
                falseFalse = dialog.findViewById(R.id.txtFalseFalse);

                try {
                    trueTrue.setText(TT);
                    trueFalse.setText(TF);
                    falseTrue.setText(FT);
                    falseFalse.setText(FF);
                } catch (NullPointerException e){
                    Log.d(TAG, e.getMessage());
                }

                Log.d(TAG, "Jumlah TT = " + TT);
                Log.d(TAG, "Jumlah TF = " + TF);
                Log.d(TAG, "Jumlah FT = " + FT);
                Log.d(TAG, "Jumlah FF = " + FF);

                dialog.show();
                database.close();
            }
        });

        return view;
    }


}
