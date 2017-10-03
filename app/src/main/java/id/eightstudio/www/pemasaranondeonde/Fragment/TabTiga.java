package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import id.eightstudio.www.pemasaranondeonde.R;

public class TabTiga extends Fragment {

    float rainFall[] = {54.8f, 45.8f, 20.8f, 80.8f, 98.8f, 38.8f, 95.8f, 12.8f,38.8f, 56.8f, 76.8f, 98.8f};
    String bulanSetahun[] = {"Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Augs", "Sept", "Okt", "Nov", "Des"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_tab_tiga, container, false);

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
