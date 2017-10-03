package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import id.eightstudio.www.pemasaranondeonde.Database.OpenHelper;
import id.eightstudio.www.pemasaranondeonde.R;

public class TabTiga extends Fragment {
    private static final String TAG = "TabTiga";

    float rainFall[] = {54.8f, 45.8f, 20.8f, 80.8f, 98.8f, 38.8f, 95.8f, 12.8f,38.8f, 56.8f, 76.8f, 98.8f};
    String bulanSetahun[] = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Augs", "Sept", "Okt", "Nov", "Des"};

    Button hitungTrueFalseData;
    int TT, TF, FT, FF;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_tab_tiga, container, false);

        hitungTrueFalseData = view.findViewById(R.id.hitungTrueFalse);

        hitungTrueFalseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int belumVerifikasi = 0;
                OpenHelper database = new OpenHelper(getContext());

                if (database.getAllKonsumen().size() > 0) {
                    for (int i = 0; i < database.getAllKonsumen().size(); i++) {
                        if (database.getAllKonsumen().get(i).getRealitaPembelian() == 3){
                            belumVerifikasi +=1;
                            break;
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                }
                
                //Memverifikasi semua data
                if (belumVerifikasi > 0){
                    Toast.makeText(getContext(), "Verifikasi Semua Data Dude", Toast.LENGTH_SHORT).show();
                } else {

                    for (int i = 0; i < database.getAllKonsumen().size(); i++) {

                        if ( (database.getAllKonsumen().get(i).getPrediksiPembelian() == 1)
                                && (database.getAllKonsumen().get(i).getRealitaPembelian() == 1) ) {
                            TT += 1;
                        }
                        else if ( (database.getAllKonsumen().get(i).getPrediksiPembelian() == 1)
                                && (database.getAllKonsumen().get(i).getRealitaPembelian() == 2) ) {
                            TF += 1;
                        }
                        else if ((database.getAllKonsumen().get(i).getPrediksiPembelian() == 2)
                                && (database.getAllKonsumen().get(i).getRealitaPembelian() == 1)) {
                            FT += 1;
                        } else {
                            FF += 1;
                        }
                    }
                }

                Log.d(TAG, "TT = " + TT ); //0
                Log.d(TAG, "TF = " + TF); //0
                Log.d(TAG, "FT = " + FT ); //3
                Log.d(TAG, "FF = " + FF ); //1

            }
        });

        List<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0; i < rainFall.length; i++) {
            pieEntries.add(new PieEntry(rainFall[i], bulanSetahun[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        //Get the chart
        PieChart pieChart = view.findViewById(R.id.chartPractice);
        pieChart.setData(data);
        pieChart.animateY(1000);
        pieChart.invalidate();


        return view;
    }
}
