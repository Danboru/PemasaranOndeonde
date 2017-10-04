package id.eightstudio.www.pemasaranondeonde.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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

    private Button buttonSimpanData;
    private EditText inputNama;

    ArrayList<Konsumen> dataKonsumen;
    CustomSpinnerAdapter jenisKelaminAdapter, umurKonsumenAdapter, pekerjaanKonsumenAdapter, pendidikanKonsumenAdapter,
            pengetahuanTentangBarangAdapter, ketertarikanBarangAdapter, hargaMenurutKonsumenAdapter;

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

                database.addUser(new Konsumen(inputNama.getText().toString() , dataJenisKelamin, dataUmurKonsumen, dataPekerjaanKonsumen,
                        dataPendidikanKonsumen, dataPengetahuanTentangBarang, dataKetertarikanBarang, dataHargaMenurutKonsumen,

                        PrediksiPembelian.prediksiPembelian(dataJenisKelamin, dataUmurKonsumen,
                                dataPekerjaanKonsumen, dataPendidikanKonsumen,
                                dataPengetahuanTentangBarang, dataKetertarikanBarang,
                                dataHargaMenurutKonsumen), 3));

                database.close();

                //Reset EditText
                inputNama.setText("");
            }
        });
        
        return view;
    }

    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private ArrayList<String> listString;

        public CustomSpinnerAdapter(Context context, ArrayList<String> dataString) {
            this.listString = dataString;
            this.activity = context;
        }

        public int getCount()
        {
            return listString.size();
        }

        public Object getItem(int i)
        {
            return listString.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(getContext());
            txt.setPadding(70, 50, 70, 50);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText((CharSequence) listString.get(position));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(getContext());
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(18, 18, 18, 18);
            txt.setTextSize(18);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            txt.setText((CharSequence) listString.get(i));
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }
    }

    /***
     * Fungsi ini di gunakan untuk menset adapter ke spinner/dropdown yang di gunakan
     * */
    private void setAdapter() {

        ArrayList<String> array_jenisKelamin = new ArrayList<>();
        ArrayList<String> array_umurKonsumen = new ArrayList<>();
        ArrayList<String> array_pekerjaanKonsumen = new ArrayList<>();
        ArrayList<String> array_pendidikanKonsumen = new ArrayList<>();
        ArrayList<String> array_pengetahuanKonsumen = new ArrayList<>();
        ArrayList<String> array_ketertarikanKonsumen = new ArrayList<>();
        ArrayList<String> array_hargaMenurutKonsumen = new ArrayList<>();

        for (String data : getResources().getStringArray(R.array.jenis_kelamin)) {
            array_jenisKelamin.add(data);
        }

        for (String data : getResources().getStringArray(R.array.umur_konsumen)) {
            array_umurKonsumen.add(data);
        }

        for (String data : getResources().getStringArray(R.array.pekerjaan_konsumen)) {
            array_pekerjaanKonsumen.add(data);
        }

        for (String data : getResources().getStringArray(R.array.pendidikan_konsumen)) {
            array_pendidikanKonsumen.add(data);
        }

        for (String data : getResources().getStringArray(R.array.pengetahuan_barang)) {
            array_pengetahuanKonsumen.add(data);
        }

        for (String data : getResources().getStringArray(R.array.ketertarikan_barang)) {
            array_ketertarikanKonsumen.add(data);
        }

        for (String data : getResources().getStringArray(R.array.harga_barang)) {
            array_hargaMenurutKonsumen.add(data);
        }

        jenisKelaminAdapter = new CustomSpinnerAdapter(getContext(), array_jenisKelamin);
        jenisKelamin.setAdapter(jenisKelaminAdapter);

        umurKonsumenAdapter = new CustomSpinnerAdapter(getContext(), array_umurKonsumen);
        umurKonsumen.setAdapter(umurKonsumenAdapter);

        pekerjaanKonsumenAdapter = new CustomSpinnerAdapter(getContext(), array_pekerjaanKonsumen);
        pekerjaanKonsumen.setAdapter(pekerjaanKonsumenAdapter);

        pendidikanKonsumenAdapter = new CustomSpinnerAdapter(getContext(), array_pendidikanKonsumen);
        pendidikanKonsumen.setAdapter(pendidikanKonsumenAdapter);

        pengetahuanTentangBarangAdapter = new CustomSpinnerAdapter(getContext(), array_pengetahuanKonsumen);
        pengetahuanTentangBarang.setAdapter(pengetahuanTentangBarangAdapter);

        ketertarikanBarangAdapter = new CustomSpinnerAdapter(getContext(), array_ketertarikanKonsumen);
        ketertarikanBarang.setAdapter(ketertarikanBarangAdapter);

        hargaMenurutKonsumenAdapter = new CustomSpinnerAdapter(getContext(), array_hargaMenurutKonsumen);
        hargaMenurutKonsumen.setAdapter(hargaMenurutKonsumenAdapter);

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
        if (jenisKelamin.equalsIgnoreCase("Laki-Laki")) {
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

    /***
     * @param pekerjaan
     * @return 1 = Pelajar/Mahasiswa
     * @return 2 = PNS
     * @return 3 = Swasta
     * @return 4 = Lain - Lain
     * */
    int pilihpekerjaanKonsumen(String pekerjaan){
        if (pekerjaan.equalsIgnoreCase("Pelajar/Mahasiswa")) {
            dataPekerjaanKonsumen = 1;
        }
        else if (pekerjaan.equalsIgnoreCase("Pegawai Negeri")){
            dataPekerjaanKonsumen = 2;
        }
        else if (pekerjaan.equalsIgnoreCase("Pegawai Swasta")){
            dataPekerjaanKonsumen = 3;
        } else {
            dataPekerjaanKonsumen = 4;
        }
        return dataPekerjaanKonsumen;
    }

    /***
     * @param pendidikan
     * @return 1 = SD
     * @return 2 = SLTP
     * @return 3 = SLTA
     * @return 4 = Diploma
     * @return 5 = Sarjana
     * @return 6 = Lain - lain
     * */
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

    /***
     * @param pengetahuanBarang
     * @return 1 = True
     * @return 2 = False
     * */
    int pengetahuanBarang(String pengetahuanBarang) {
        if (pengetahuanBarang.equalsIgnoreCase("Tahu")){
            dataPengetahuanTentangBarang = 1;
        } else {
            dataPengetahuanTentangBarang = 2;
        }
        return dataPengetahuanTentangBarang;
    }

    /***
     * @param ketertarikanBarang
     * @return 1 = True
     * @return 2 = False
     * */
    int ketertarikanBarang(String ketertarikanBarang){
        if (ketertarikanBarang.equalsIgnoreCase("Tertarik")){
            dataKetertarikanBarang = 1;
        } else {
            dataKetertarikanBarang = 2;
        }
        return dataKetertarikanBarang;
    }

    /***
     * @param hargabarang
     * @return 1 = True
     * @return 2 = False
     * */
    int hargaBarang(String hargabarang){
        if (hargabarang.equalsIgnoreCase("Sesuai")){
            dataHargaMenurutKonsumen = 1;
        } else {
            dataHargaMenurutKonsumen = 2;
        }
        return dataHargaMenurutKonsumen;
    }
}
