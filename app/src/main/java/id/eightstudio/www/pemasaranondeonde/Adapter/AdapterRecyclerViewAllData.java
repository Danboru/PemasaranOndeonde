package id.eightstudio.www.pemasaranondeonde.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import id.eightstudio.www.pemasaranondeonde.Provider.Konsumen;
import id.eightstudio.www.pemasaranondeonde.R;

/**
 * Created by danbo on 10/2/17.
 */
public class AdapterRecyclerViewAllData extends RecyclerView.Adapter<AdapterRecyclerViewAllData.ViewHolder> {

    ArrayList<Konsumen> dataList = new ArrayList<>();

    public AdapterRecyclerViewAllData(ArrayList<Konsumen> dataList) {
        this.dataList = dataList;
    }

    //2
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_all_data, parent, false);

        return new ViewHolder(view);
    }

    //3
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.namaUser.setText(dataList.get(position).getKonsumenId().toString());
        holder.jenisKelamin.setText(String.valueOf(dataList.get(position).getJenisKelamin()));
        holder.prediksiPembelian.setText(String.valueOf(dataList.get(position).getPrediksiPembelian()));
    }

    //1
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namaUser, jenisKelamin, prediksiPembelian;
        public ViewHolder(View itemView) {
            super(itemView);

            namaUser = itemView.findViewById(R.id.txtNamaUser);
            jenisKelamin = itemView.findViewById(R.id.txtJenisKelamin);
            prediksiPembelian = itemView.findViewById(R.id.txtPrediksiPembelian);

        }

    }

}
