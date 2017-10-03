package id.eightstudio.www.pemasaranondeonde.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import id.eightstudio.www.pemasaranondeonde.Provider.Konsumen;
import id.eightstudio.www.pemasaranondeonde.R;

/**
 * Created by danbo on 10/2/17.
 */
public class AdapterRecyclerViewAllData extends RecyclerView.Adapter<AdapterRecyclerViewAllData.ViewHolder> {

    ArrayList<Konsumen> dataList = new ArrayList<>();
    Context context;

    public AdapterRecyclerViewAllData(ArrayList<Konsumen> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    //2
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_all_data, parent, false);

        return new ViewHolder(view);
    }

    //3
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.namaUser.setText(dataList.get(position).getKonsumenId().toString());
        holder.jenisKelamin.setText(String.valueOf(dataList.get(position).getJenisKelamin()));
        holder.prediksiPembelian.setText(String.valueOf(dataList.get(position).getPrediksiPembelian()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dataSatu;

                dataSatu = dataList.get(position).getKonsumenId().toString();

                showDialog(position , context, dataSatu );
            }
        });
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

    private void showDialog(int posisi, Context context, String data) {

        final Dialog dialog = new Dialog(context);

        //Mengeset judul dialog
        dialog.setTitle("Update Pembelian");

        //Menset layout
        dialog.setContentView(R.layout.popup_tester);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(false);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView tester = dialog.findViewById(R.id.txtPopupTester);
        tester.setText(data);

        Button cancel = dialog.findViewById(R.id.cancelPopup);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}
