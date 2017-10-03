package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.eightstudio.www.pemasaranondeonde.Adapter.AdapterRecyclerViewAllData;
import id.eightstudio.www.pemasaranondeonde.Database.OpenHelper;
import id.eightstudio.www.pemasaranondeonde.Provider.Konsumen;
import id.eightstudio.www.pemasaranondeonde.R;
import id.eightstudio.www.pemasaranondeonde.Utils.PrediksiPembelian;

public class TabSatu extends Fragment {
    private static final String TAG = "TabSatu";

    private TextView idKonsumen;
    private Spinner jenisKelamin, umurKonsumen, pekerjaanKonsumen, pendidikanKonsumen,
            pengetahuanTentangBarang, ketertarikanBarang, hargaMenurutKonsumen;
    private ArrayAdapter adapterJenisKelamin, adapterUmurKonsumen, adapterPekerjaanKonsumen, adapterPendidikanKonsumen,
            adapterPengetahuanTentangBarang, adapterKetertarikanBarang, adapterHargaMenurutKonsumen;
    private Button buttonSimpanData;
    private EditText inputNama;

    ArrayList<Konsumen> dataKonsumen;

    //Konversi data
    int dataJenisKelamin, dataUmurKonsumen, dataPekerjaanKonsumen, dataPendidikanKonsumen;
    int dataPengetahuanTentangBarang, dataKetertarikanBarang, dataHargaMenurutKonsumen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_tab_satu, container, false);

        Log.d(TAG, "Database Status : " + "Database berhasil di buat ");
        final OpenHelper database = new OpenHelper(getContext());

        bindView(view);
        adapterInit();
        setAdapter();

        dataKonsumen = new ArrayList<>();

        //Simpan data
        buttonSimpanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pilihUmur(umurKonsumen.getSelectedItem().toString());
                pilihJenisKelamin(jenisKelamin.getSelectedItem().toString());
                pilihpekerjaanKonsumen(pekerjaanKonsumen.getSelectedItem().toString());
                pilihPendidikan(pendidikanKonsumen.getSelectedItem().toString());
                pengetahuanBarang(pengetahuanTentangBarang.getSelectedItem().toString());
                ketertarikanBarang(ketertarikanBarang.getSelectedItem().toString());
                hargaBarang(hargaMenurutKonsumen.getSelectedItem().toString());

                dataKonsumen.add(new Konsumen(inputNama.getText().toString() , dataJenisKelamin, dataUmurKonsumen, dataPekerjaanKonsumen,
                        dataPendidikanKonsumen, dataPengetahuanTentangBarang, dataKetertarikanBarang, dataHargaMenurutKonsumen,

                        PrediksiPembelian.prediksiPembelian(dataJenisKelamin, dataUmurKonsumen,
                                                            dataPekerjaanKonsumen, dataPendidikanKonsumen,
                                                            dataPengetahuanTentangBarang, dataKetertarikanBarang,
                                                            dataHargaMenurutKonsumen), 2));

                database.addUser(new Konsumen(inputNama.getText().toString() , dataJenisKelamin, dataUmurKonsumen, dataPekerjaanKonsumen,
                        dataPendidikanKonsumen, dataPengetahuanTentangBarang, dataKetertarikanBarang, dataHargaMenurutKonsumen,

                        PrediksiPembelian.prediksiPembelian(dataJenisKelamin, dataUmurKonsumen,
                                dataPekerjaanKonsumen, dataPendidikanKonsumen,
                                dataPengetahuanTentangBarang, dataKetertarikanBarang,
                                dataHargaMenurutKonsumen), 2));

            }
        });
        
        return view;
    }

    public void adapterInit(){

        adapterJenisKelamin = ArrayAdapter.createFromResource(getContext(),R.array.jenis_kelamin, android.R.layout.simple_spinner_item);
        adapterUmurKonsumen = ArrayAdapter.createFromResource(getContext(),R.array.umur_konsumen, android.R.layout.simple_spinner_item);
        adapterPekerjaanKonsumen = ArrayAdapter.createFromResource(getContext(),R.array.pekerjaan_konsumen, android.R.layout.simple_spinner_item);
        adapterPendidikanKonsumen = ArrayAdapter.createFromResource(getContext(),R.array.pendidikan_konsumen, android.R.layout.simple_spinner_item);
        adapterPengetahuanTentangBarang = ArrayAdapter.createFromResource(getContext(),R.array.pengetahuan_barang, android.R.layout.simple_spinner_item);
        adapterKetertarikanBarang = ArrayAdapter.createFromResource(getContext(),R.array.ketertarikan_barang, android.R.layout.simple_spinner_item);
        adapterHargaMenurutKonsumen = ArrayAdapter.createFromResource(getContext(),R.array.harga_barang, android.R.layout.simple_spinner_item);

        adapterJenisKelamin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterUmurKonsumen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPekerjaanKonsumen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPendidikanKonsumen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPengetahuanTentangBarang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterKetertarikanBarang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterHargaMenurutKonsumen.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    }

    /***
     * Fungsi ini di gunakan untuk menset adapter ke spinner/dropdown yang di gunakan
     * */
    private void setAdapter() {

        jenisKelamin.setAdapter(adapterJenisKelamin);
        umurKonsumen.setAdapter(adapterUmurKonsumen);
        pekerjaanKonsumen.setAdapter(adapterPekerjaanKonsumen);
        pendidikanKonsumen.setAdapter(adapterPendidikanKonsumen);
        pengetahuanTentangBarang.setAdapter(adapterPengetahuanTentangBarang);
        ketertarikanBarang.setAdapter(adapterKetertarikanBarang);
        hargaMenurutKonsumen.setAdapter(adapterHargaMenurutKonsumen);

    }

    /***
     * @param view
     * Fungsi ini untuk mendefinisikan setiap id dari view yang akan di gunakan
     * */
    private void bindView(View view) {

        buttonSimpanData = view.findViewById(R.id.buttonSimpan);

        jenisKelamin = view.findViewById(R.id.spinner_jenisKelamin);
        umurKonsumen = view.findViewById(R.id.spinner_umurKonsumen);
        pekerjaanKonsumen = view.findViewById(R.id.spinner_pekerjaanKonsumen);
        pendidikanKonsumen = view.findViewById(R.id.spinner_pendidikanKonsumen);
        pengetahuanTentangBarang = view.findViewById(R.id.spinner_pengetahuanBarang);
        ketertarikanBarang = view.findViewById(R.id.spinner_ketertarikanBarang);
        hargaMenurutKonsumen = view.findViewById(R.id.spinner_hargamenurutKonsumen);
        inputNama = view.findViewById(R.id.txtInputMain);

    }

    /***
     * @param jenisKelamin
     * @return 1 = laki - laki
     * @return 2 = perempuan
     * */
    int pilihJenisKelamin(String jenisKelamin){
        //Mengembalikan jenis kelamin
        if (jenisKelamin.equalsIgnoreCase("laki-laki")) {
            dataJenisKelamin = 1;
        } else {
            dataJenisKelamin = 2;
        }
        return dataJenisKelamin;
    }

    /***
     * @param umurPelanggan
     * @return 1 = 10 - 29
     * @return 2 = 30 - 49
     * @return 3 = >=50
     * */
    int pilihUmur(String umurPelanggan){
        switch (umurPelanggan) {
            case "10 - 29" : {
                dataUmurKonsumen = 1;
                break;
            }
            case "30 - 49" : {
                dataUmurKonsumen = 2;
               break;
            }
            default : {
                dataUmurKonsumen = 3;
                break;
            }
        }
        return dataUmurKonsumen;
    }

    // 1 = Pelajar/Mahasiswa, 2 = PNS, 3 = Swasta, 4 = Lain - Lain
    int pilihpekerjaanKonsumen(String pekerjaan){
        if (pekerjaan.equalsIgnoreCase("Pelajar/Mahasiswa")) {
            dataPekerjaanKonsumen = 1;
        }
        else if (pekerjaan.equalsIgnoreCase("PNS")){
            dataPekerjaanKonsumen = 2;
        }
        else if (pekerjaan.equalsIgnoreCase("Swasta")){
            dataPekerjaanKonsumen = 3;
        } else {
            dataPekerjaanKonsumen = 4;
        }
        return dataPekerjaanKonsumen;
    }

    // 1 = SD, 2 = SLTP, 3 = SLTA, 4 = Diploma, 5 = Sarjana, 6 = Lain - lain
    int pilihPendidikan(String pendidikan){
        if (pendidikan.equalsIgnoreCase("SD")){
            dataPendidikanKonsumen = 1;
        }
        else if (pendidikan.equalsIgnoreCase("SLTP")) {
            dataPendidikanKonsumen = 2;
        }
        else if (pendidikan.equalsIgnoreCase("SLTA")) {
            dataPendidikanKonsumen = 3;
        }
        else if (pendidikan.equalsIgnoreCase("Diploma")){
            dataPendidikanKonsumen = 4;
        }
        else if (pendidikan.equalsIgnoreCase("Sarjana")){
            dataPendidikanKonsumen = 5;
        }
        else {
            dataPendidikanKonsumen = 6;
        }
        return dataPendidikanKonsumen;
    }

    //True - False
    int pengetahuanBarang(String pengetahuanBarang) {
        if (pengetahuanBarang.equalsIgnoreCase("Tahu")){
            dataPengetahuanTentangBarang = 1;
        } else {
            dataPengetahuanTentangBarang = 2;
        }
        return dataPengetahuanTentangBarang;
    }

    //True - False
    int ketertarikanBarang(String ketertarikanBarang){
        if (ketertarikanBarang.equalsIgnoreCase("Tertarik")){
            dataKetertarikanBarang = 1;
        } else {
            dataKetertarikanBarang = 2;
        }
        return dataKetertarikanBarang;
    }

    //True - False
    int hargaBarang(String hargabarang){
        if (hargabarang.equalsIgnoreCase("Sesuai")){
            dataHargaMenurutKonsumen = 1;
        } else {
            dataHargaMenurutKonsumen = 2;
        }
        return dataHargaMenurutKonsumen;
    }


}
