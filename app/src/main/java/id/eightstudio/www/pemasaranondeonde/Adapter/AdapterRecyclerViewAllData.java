package id.eightstudio.www.pemasaranondeonde.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.eightstudio.www.pemasaranondeonde.Database.OpenHelper;
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
        holder.namaUser.setText(dataList.get(position).getKonsumenName().toString());
        holder.jenisKelamin.setText(String.valueOf(dataList.get(position).getJenisKelamin()));
        holder.prediksiPembelian.setText(String.valueOf(dataList.get(position).getPrediksiPembelian()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int realitaPembelian = dataList.get(position).getRealitaPembelian();
                showDialog(position , context, realitaPembelian );
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

    private void showDialog(final int posisi, final Context context, final int data) {

        final Dialog dialog = new Dialog(context);

        //Mengeset judul dialog
        dialog.setTitle("Update Pembelian");

        //Set layout
        dialog.setContentView(R.layout.popup_tester);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(false);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        EditText keputusanBeli = dialog.findViewById(R.id.edt_keputusanBeli);
        keputusanBeli.setText(String.valueOf(data));

        //Cancel
        Button cancel = dialog.findViewById(R.id.cancelSave);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Konsumen konsumen = new Konsumen();
                Toast.makeText(context, "" + posisi , Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        //Save
        Button save = dialog.findViewById(R.id.saveData);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenHelper database = new OpenHelper(context);
                database.updateUser(new Konsumen(1), posisi + 1);
                database.close();

                dialog.dismiss();
            }
        });


        //Delete
        Button delete = dialog.findViewById(R.id.deleteData);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenHelper database = new OpenHelper(context);
                database.deleteKonsumen(posisi);
                Toast.makeText(context, "" + posisi, Toast.LENGTH_SHORT).show();
                database.close();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}
