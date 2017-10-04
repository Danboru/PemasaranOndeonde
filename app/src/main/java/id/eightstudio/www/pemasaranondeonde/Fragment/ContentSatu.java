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
import id.eightstudio.www.pemasaranondeonde.R;

public class ContentSatu extends Fragment {
    private static final String TAG = "ContentSatu";
    
    String persentaseFloat = "";
    
    float rainFall[] = { Float.parseFloat("70.0f"), 30.0f};
    String bulanSetahun[] = {"Kemungkinan Beli", "Kemungkinan Tidak Beli"};

    //Singleton
    public static ContentSatu newInstance() {
        ContentSatu fragment = new ContentSatu();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_content_satu, container, false);

        List<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0; i < rainFall.length; i++) {
            pieEntries.add(new PieEntry(rainFall[i], bulanSetahun[i]));
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

        return view;
    }
}
