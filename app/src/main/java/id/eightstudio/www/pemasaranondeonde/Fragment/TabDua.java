package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import id.eightstudio.www.pemasaranondeonde.Adapter.AdapterRecyclerViewAllData;
import id.eightstudio.www.pemasaranondeonde.Database.OpenHelper;
import id.eightstudio.www.pemasaranondeonde.MainActivity;
import id.eightstudio.www.pemasaranondeonde.Provider.Konsumen;
import id.eightstudio.www.pemasaranondeonde.R;

public class TabDua extends Fragment {
    private static final String TAG = "TabDua";

    FloatingActionButton floatingActionButton;
    ArrayList<Konsumen> dataListKonsumen = new ArrayList<>();
    RecyclerView viewAllData;
    OpenHelper database;
    AdapterRecyclerViewAllData adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_tab_dua, container, false);

        viewAllData = view.findViewById(R.id.recyclerViewViewAllData);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshMain);
        floatingActionButton = view.findViewById(R.id.fabSearchData);

        database = new OpenHelper(view.getContext());
        dataListKonsumen = database.getAllKonsumen();

        adapter = new AdapterRecyclerViewAllData(dataListKonsumen, view.getContext());
        viewAllData.setAdapter(adapter);
        viewAllData.setHasFixedSize(true);
        viewAllData.setLayoutManager(new LinearLayoutManager(getContext()));

        //Refresh Datalist
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataListKonsumen.clear();
                dataListKonsumen.addAll(database.getAllKonsumen());
                adapter.notifyItemInserted(0);
                adapter.notifyDataSetChanged();
                onItemsLoadComplete(view.getContext(), database);
                database.close();
            }
        });

        //SearchData
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showSearchDialog(getContext());
            }
        });

        return view;
    }

    private void onItemsLoadComplete(Context context, OpenHelper database) {

        if (database.getAllKonsumen().size() <= 0) {
            Toast.makeText(context, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
            database.close();
        } else {
            Toast.makeText(context, "Up to Date", Toast.LENGTH_SHORT).show();
            database.close();
        }
        
        swipeRefreshLayout.setRefreshing(false);
    }

    private void showSearchDialog(Context context) {
        final Dialog dialog = new Dialog(context);


        //Set layout
        dialog.setContentView(R.layout.popup_search);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(true);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);


        dialog.show();
    }

}
