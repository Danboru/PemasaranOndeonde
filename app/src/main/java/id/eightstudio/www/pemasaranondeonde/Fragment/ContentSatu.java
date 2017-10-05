package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import id.eightstudio.www.pemasaranondeonde.Database.OpenHelper;
import id.eightstudio.www.pemasaranondeonde.Provider.Statistik;
import id.eightstudio.www.pemasaranondeonde.R;

public class ContentSatu extends Fragment {
    private static final String TAG = "ContentSatu";
    
    String persentaseFloat = "";
    OpenHelper database;

    String dataSatu , dataDua;

    //Singleton
    public static ContentSatu newInstance() {
        ContentSatu fragment = new ContentSatu();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_content_satu, container, false);

        database = new OpenHelper(getContext());

        //Init Data Satu dan Data Dua
        dataSatu = database.getAllStatistik().get(0).getPersentase();
        int dataStatistikTemp = 100 - Integer.parseInt(dataSatu);

        //Variable yang di butuhkan oelh chart
        float dataStatistik[] = { Float.parseFloat(dataSatu + "f") , Float.parseFloat(dataStatistikTemp + "f")};
        String labelStatistik[] = {"Jumlah Pembeli", "Jumlah Tidak Beli"};

        List<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0; i < dataStatistik.length; i++) {
            pieEntries.add(new PieEntry(dataStatistik[i], labelStatistik[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        //TODO Piechert masih belum di set dengan data real
        //Get the chart
        PieChart pieChart = view.findViewById(R.id.chartPractice);
        pieChart.setData(data);
        pieChart.animateY(1000);
        pieChart.invalidate();

        database.close();
        return view;
    }
}
