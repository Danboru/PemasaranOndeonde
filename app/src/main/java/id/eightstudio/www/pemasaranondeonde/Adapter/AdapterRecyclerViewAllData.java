package id.eightstudio.www.pemasaranondeonde.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
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

        //Set Jenis Kelamin
        setJenisKelamin(dataList.get(position).getJenisKelamin(), position, holder);

        //Set Prediksi Pembelian
        setPrediksiPembelian(dataList.get(position).getPrediksiPembelian(), position, holder);

        //Set Verifikasi Pembelian
        setVerifikasiPembelian(dataList.get(position).getRealitaPembelian(), position, holder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int realitaPembelian = dataList.get(position).getRealitaPembelian();
                showDialog(position , context );
            }
        });
    }


    public void setJenisKelamin(int jenisKelamin, int position, ViewHolder holder){
        if (jenisKelamin == 1) {
            holder.jenisKelamin.setText("Laki - Laki");
        } else {
            holder.jenisKelamin.setText("Perempuan");
        }
    }

    public void setPrediksiPembelian(int pembelian, int position, ViewHolder holder){
        if (pembelian == 1) {
            holder.prediksiPembelian.setText("Hasil Prediksi : Membeli");
        } else {
            holder.prediksiPembelian.setText("Hasil Prediksi : Tidak Membeli");
        }
    }

    public void setVerifikasiPembelian(int verifikasi, int position, ViewHolder holder){
        if (verifikasi == 1) {
            holder.verifikasiPembelian.setText("Sudah Membeli");
            holder.verifikasiPembelian.setTextColor(Color.BLACK);
        } else if (verifikasi == 2) {
            holder.verifikasiPembelian.setText("Tidak Membeli");
            holder.verifikasiPembelian.setTextColor(Color.RED);
        } else {
            holder.verifikasiPembelian.setText("Belum Di Verifikasi");
            holder.verifikasiPembelian.setTextColor(Color.BLUE);
        }
    }

    //1
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namaUser, jenisKelamin, prediksiPembelian, verifikasiPembelian;
        public ViewHolder(View itemView) {
            super(itemView);

            namaUser = itemView.findViewById(R.id.txtNamaUser);
            jenisKelamin = itemView.findViewById(R.id.txtJenisKelamin);
            prediksiPembelian = itemView.findViewById(R.id.txtPrediksiPembelian);
            verifikasiPembelian = itemView.findViewById(R.id.txtVerifikasiPembelian);

        }

    }

    private void showDialog(final int posisi, final Context context) {

        final Dialog dialog = new Dialog(context);

        //Set layout
        dialog.setContentView(R.layout.popup_menu);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(true);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Tidak Beli (FIX)
        Button cancel = dialog.findViewById(R.id.cancelSave);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Konsumen konsumen = dataList.get(posisi);
                OpenHelper database = new OpenHelper(context);
                database.updateUser(new Konsumen(2), konsumen.getIdUser());

                database.close();
                dialog.dismiss();
            }
        });

        //Beli (FIX)
        Button save = dialog.findViewById(R.id.saveData);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Konsumen konsumen = dataList.get(posisi);
                OpenHelper database = new OpenHelper(context);
                database.updateUser(new Konsumen(1), konsumen.getIdUser());

                database.close();
                dialog.dismiss();
            }
        });


        //Delete (FIX)
        Button delete = dialog.findViewById(R.id.deleteData);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Konsumen konsumen = dataList.get(posisi);
                OpenHelper database = new OpenHelper(context);
                database.deleteKonsumen(konsumen.getIdUser());

                database.close();
                dialog.dismiss();
            }
        });

        //Detail Data (FIX)
        Button buttonDetailData = dialog.findViewById(R.id.lihatDetailData);
        buttonDetailData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDetail(posisi, context);
                dialog.dismiss();
            }
        });
        
        dialog.show();
    }


    private void showDialogDetail(final int posisi, final Context context) {
        final Dialog dialog = new Dialog(context);

        //Set layout
        dialog.setContentView(R.layout.popup_detail);

        //Membuat agar dialog tidak hilang saat di click di area luar dialog
        dialog.setCanceledOnTouchOutside(true);

        //Membuat dialog agar berukuran responsive
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout((6 * width) / 7, LinearLayout.LayoutParams.WRAP_CONTENT);

        //Set Data Ke TextView
        setDetailData(dialog, posisi);

        dialog.show();
    }

    public void setDetailData(Dialog dialog, int posisi) {

        //Database Declare
        OpenHelper database = new OpenHelper(context);

        TextView detailNama, detailKelamin, detailUmur, detailPekerjaan, detailPendidikan,
                detailPengetahuan, detailKetertarikan, detailHarga;

        //Set Nama
        detailNama = dialog.findViewById(R.id.txt_nameDetail);
        detailNama.setText(database.getAllKonsumen().get(posisi).getKonsumenName() + "");

        /***
         * Set Jenis Kelamin
         * @return 1 = laki - laki
         * @return 2 = perempuan
         * */
        detailKelamin = dialog.findViewById(R.id.txt_jenisKelamin);
        switch (database.getAllKonsumen().get(posisi).getJenisKelamin()) {
            case 1 : detailKelamin.setText("Laki - Laki"); break;
            default  :  detailKelamin.setText("Perempuan"); break;
        }

        /***
         * Set Umur
         * @return 1 = 10 - 29
         * @return 2 = 30 - 49
         * @return 3 = >=50
         * */
        detailUmur = dialog.findViewById(R.id.txt_umurKonsumen);
        switch (database.getAllKonsumen().get(posisi).getUmurKonsumen()) {
            case 1 : detailUmur.setText("10 - 29"); break;
            case 2 : detailUmur.setText("30 - 49"); break;
            default  :  detailUmur.setText(">=50"); break;
        }

        /***
         * Set Pekerjaan
         * @return 1 = Pelajar/Mahasiswa
         * @return 2 = PNS
         * @return 3 = Swasta
         * @return 4 = Lain - Lain
         * */
        detailPekerjaan = dialog.findViewById(R.id.txt_pekerjaanKonsumen);
        switch (database.getAllKonsumen().get(posisi).getPekerjaanKonsumen()) {
            case 1 : detailPekerjaan.setText("Pelajar"); break;
            case 2 : detailPekerjaan.setText("PNS"); break;
            case 3 : detailPekerjaan.setText("Swasta"); break;
            default  :  detailUmur.setText("Lain - Lain"); break;
        }

        /***
         * Set Pendidikan
         * @return 1 = SD
         * @return 2 = SLTP
         * @return 3 = SLTA
         * @return 4 = Diploma
         * @return 5 = Sarjana
         * @return 6 = Lain - lain
         * */
        detailPendidikan = dialog.findViewById(R.id.txt_pendidikanKonsumen);
        switch (database.getAllKonsumen().get(posisi).getPendidikanKonsumen()) {
            case 1 : detailPendidikan.setText("SD"); break;
            case 2 : detailPendidikan.setText("SLTP"); break;
            case 3 : detailPendidikan.setText("SLTA"); break;
            case 4 : detailPendidikan.setText("Diploma"); break;
            case 5 : detailPendidikan.setText("Sarjana"); break;
            default  :  detailUmur.setText("Lain - Lain"); break;
        }

        /***
         * Set Pengetahuan Barang
         * @return 1 = Tahu
         * @return 2 = Tidak Tahu
         * */
        detailPengetahuan = dialog.findViewById(R.id.txt_pengetahuanBarang);
        switch (database.getAllKonsumen().get(posisi).getPengetahuanTentangBarang()) {
            case 1 : detailPengetahuan.setText("Tahu"); break;
            default  :  detailPengetahuan.setText("Tidak Tahu"); break;
        }

        /***
         * Set Ketertarikan Barang
         * @return 1 = Tertarik
         * @return 2 = Tidak Tertarik
         * */
        detailKetertarikan = dialog.findViewById(R.id.txt_ketertarikanBarang);
        switch (database.getAllKonsumen().get(posisi).getKetertarikanBarang()) {
            case 1 : detailKetertarikan.setText("Tertarik"); break;
            default  :  detailKetertarikan.setText("Tidak Tertarik"); break;
        }

        /***
         * Set Harga Barang
         * @return 1 = Sesuai
         * @return 2 = Tidak Sesuai
         * */
        detailHarga = dialog.findViewById(R.id.txt_hargaBarang);
        switch (database.getAllKonsumen().get(posisi).getHargaMenurutKonsumen()) {
            case 1 : detailHarga.setText("Sesuai"); break;
            default  :  detailHarga.setText("Tidak Sesuai"); break;
        }

        database.close();
    }
}
