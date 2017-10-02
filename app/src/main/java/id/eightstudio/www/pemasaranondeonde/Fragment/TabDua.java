package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.eightstudio.www.pemasaranondeonde.R;

public class TabDua extends Fragment {

    RecyclerView viewAllData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_tab_dua, container, false);

        viewAllData = view.findViewById(R.id.recyclerViewViewAllData);

        return view;
    }
}
