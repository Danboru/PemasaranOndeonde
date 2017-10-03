package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Random;

import id.eightstudio.www.pemasaranondeonde.Adapter.AdapterRecyclerViewAllData;
import id.eightstudio.www.pemasaranondeonde.Database.OpenHelper;
import id.eightstudio.www.pemasaranondeonde.Provider.Konsumen;
import id.eightstudio.www.pemasaranondeonde.R;

public class TabDua extends Fragment {
    private static final String TAG = "TabDua";

    ArrayList<Konsumen> dataListKonsumen = new ArrayList<>();
    RecyclerView viewAllData;
    OpenHelper database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_tab_dua, container, false);

        viewAllData = view.findViewById(R.id.recyclerViewViewAllData);

//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            int acak = random.nextInt(2);
//            dataListKonsumen.add(new Konsumen("User " + i, acak, acak, acak, acak, acak, acak, acak, acak, acak));
//        }
        database = new OpenHelper(view.getContext());
        dataListKonsumen = database.getAllKonsumen();

        AdapterRecyclerViewAllData adapter = new AdapterRecyclerViewAllData(dataListKonsumen);
        viewAllData.setAdapter(adapter);
        viewAllData.setHasFixedSize(true);
        viewAllData.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
