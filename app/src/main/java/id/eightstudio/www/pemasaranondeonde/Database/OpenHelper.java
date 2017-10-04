package id.eightstudio.www.pemasaranondeonde.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import id.eightstudio.www.pemasaranondeonde.Provider.Konsumen;
import id.eightstudio.www.pemasaranondeonde.Provider.Statistik;

/**
 * Created by danbo on 10/3/17.
 */

public class OpenHelper extends SQLiteOpenHelper  {
    private static final String TAG = "OpenHelper";

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "penjualan";
    // User Taable Name
    private static final String TABLE_USER = "konsumen";
    private static final String TABLE_STATISTIK = "statistik";

    //Kolom Table konsumen
    private static final String KEY_COSTUMER_ID = "id_user";
    private static final String KEY_COSTUMER_NAME = "nama_user";
    private static final String KEY_COSTUMER_JENISKELAMIN = "kategori_jeniskelamin_user";
    private static final String KEY_COSTUMER_UMUR = "kategori_umur_user";
    private static final String KEY_COSTUMER_PEKERJAAN = "kategori_pekerjaan_user";
    private static final String KEY_COSTUMER_PENDIDIKAN = "kategori_pendidikan_user";
    private static final String KEY_COSTUMER_PENGETAHUAN = "kategori_pengetahuan_user";
    private static final String KEY_COSTUMER_KETERTARIKAN = "kategori_ketertarikan_user";
    private static final String KEY_COSTUMER_HARGABARANG = "kategori_hargabarang_user";
    private static final String KEY_COSTUMER_PREDIKSIPEMBELIAN = "prediksipembelian_user";
    private static final String KEY_COSTUMER_REALITAPEMBELIAN = "realitapembelian_user";

    //Kolom Table statistik
    private static final String KEY_STATISTIK_ID = "id_statistik";
    private static final String KEY_STATISTIK_JUMLAHPEMBELI = "jumlah_pembeli";
    private static final String KEY_STATISTIK_JUMLAHTIDAKBELI = "jumlah_tidakbeli";
    private static final String KEY_STATISTIK__HASIL_PERSENTASE = "persentase";

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //FIX
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_COSTUMER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_COSTUMER_ID + " INTEGER PRIMARY KEY, " + KEY_COSTUMER_NAME + " TEXT, "
                + KEY_COSTUMER_JENISKELAMIN + " INTEGER, " + KEY_COSTUMER_UMUR + " INTEGER, "
                + KEY_COSTUMER_PEKERJAAN + " INTEGER, " + KEY_COSTUMER_PENDIDIKAN + " INTEGER, "
                + KEY_COSTUMER_PENGETAHUAN + " TEXT, " + KEY_COSTUMER_KETERTARIKAN + " TEXT, "
                + KEY_COSTUMER_HARGABARANG + " TEXT, " + KEY_COSTUMER_PREDIKSIPEMBELIAN + " TEXT, "
                + KEY_COSTUMER_REALITAPEMBELIAN + " TEXT" + ")";

        String CREATE_STATISTIK_TABLE = "CREATE TABLE " + TABLE_STATISTIK + "("
                + KEY_STATISTIK_ID + " INTEGER PRIMARY KEY, " + KEY_STATISTIK_JUMLAHPEMBELI + " TEXT, "
                + KEY_STATISTIK_JUMLAHTIDAKBELI + " TEXT, " + KEY_STATISTIK__HASIL_PERSENTASE + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_COSTUMER_TABLE);
        sqLiteDatabase.execSQL(CREATE_STATISTIK_TABLE);
    }

    //FIX
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTIK);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    //FIX
    public void addUser(Konsumen konsumen) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COSTUMER_NAME, konsumen.getKonsumenName());
        values.put(KEY_COSTUMER_JENISKELAMIN, konsumen.getJenisKelamin());
        values.put(KEY_COSTUMER_UMUR, konsumen.getUmurKonsumen());
        values.put(KEY_COSTUMER_PEKERJAAN, konsumen.getPekerjaanKonsumen());
        values.put(KEY_COSTUMER_PENDIDIKAN, konsumen.getPendidikanKonsumen());
        values.put(KEY_COSTUMER_PENGETAHUAN, konsumen.getPengetahuanTentangBarang());
        values.put(KEY_COSTUMER_KETERTARIKAN, konsumen.getKetertarikanBarang());
        values.put(KEY_COSTUMER_HARGABARANG, konsumen.getHargaMenurutKonsumen());
        values.put(KEY_COSTUMER_PREDIKSIPEMBELIAN, konsumen.getPrediksiPembelian());
        values.put(KEY_COSTUMER_REALITAPEMBELIAN, konsumen.getRealitaPembelian());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    //FIX
    public void addDataStatistik(Statistik statistik) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put(KEY_STATISTIK_JUMLAHPEMBELI, statistik.getJumlahPembeli());
        data.put(KEY_STATISTIK_JUMLAHTIDAKBELI, statistik.getJumlahTidakBeli());
        data.put(KEY_STATISTIK__HASIL_PERSENTASE, statistik.getPersentase());

        // Inserting Row
        db.insert(TABLE_STATISTIK, null, data);
        db.close(); // Closing database connection
    }


    //FIX
    public Statistik getDataStatistik(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STATISTIK, new String[] { KEY_STATISTIK_JUMLAHPEMBELI,
                        KEY_STATISTIK_JUMLAHTIDAKBELI, KEY_STATISTIK__HASIL_PERSENTASE
                }, KEY_STATISTIK_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Statistik statistikData = new Statistik(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),cursor.getString(3));

        //Return user
        return statistikData;
    }

    //FIX
    public ArrayList<Konsumen> getAllKonsumen() {
        ArrayList<Konsumen> konsumenList = new ArrayList<Konsumen>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping semua data dan menginputkan data ke dalamnya
        if (cursor.moveToFirst()) {
            do {
                Konsumen user = new Konsumen();
                user.setIdUser(Integer.parseInt(cursor.getString(0)));
                user.setKonsumenName(cursor.getString(1));
                user.setJenisKelamin(Integer.parseInt(cursor.getString(2)));
                user.setUmurKonsumen(Integer.parseInt(cursor.getString(3)));
                user.setPekerjaanKonsumen(Integer.parseInt(cursor.getString(4)));
                user.setPendidikanKonsumen(Integer.parseInt(cursor.getString(5)));
                user.setPengetahuanTentangBarang(Integer.parseInt(cursor.getString(6)));
                user.setKetertarikanBarang(Integer.parseInt(cursor.getString(7)));
                user.setHargaMenurutKonsumen(Integer.parseInt(cursor.getString(8)));
                user.setPrediksiPembelian(Integer.parseInt(cursor.getString(9)));
                user.setRealitaPembelian(Integer.parseInt(cursor.getString(10)));

                //Menambahkan user ke list
                konsumenList.add(user);

            } while (cursor.moveToNext());
        }

        // return konsumenList
        return konsumenList;
    }

    //FIX
    public ArrayList<Statistik> getAllStatistik() {
        ArrayList<Statistik> statistikList = new ArrayList<Statistik>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_STATISTIK;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping semua data dan menginputkan data ke dalamnya
        if (cursor.moveToFirst()) {
            do {
                Statistik statistik = new Statistik();
                statistik.setIdStatistik(Integer.parseInt(cursor.getString(0)));
                statistik.setJumlahPembeli(cursor.getString(1));
                statistik.setJumlahTidakBeli(cursor.getString(2));
                statistik.setPersentase(cursor.getString(3));
//                statistik.setTrueTrue(cursor.getString(4));
//                statistik.setTrueFalse(cursor.getString(5));
//                statistik.setFalseTrue(cursor.getString(6));
//                statistik.setFalseFalse(cursor.getString(7));


                //Menambahkan user ke list
                statistikList.add(statistik);

            } while (cursor.moveToNext());
        }

        // return konsumenList
        return statistikList;
    }

    //FIX
    public int updateUser(Konsumen kosumen, int position) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COSTUMER_REALITAPEMBELIAN, kosumen.getRealitaPembelian());

        // updating
        return db.update(TABLE_USER, values, KEY_COSTUMER_ID + " = ?",
                new String[] { String.valueOf(position) });
    }

    //FIX
    public int updateStatistik(Statistik statistik, int position) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COSTUMER_REALITAPEMBELIAN, statistik.getJumlahPembeli());
        values.put(KEY_COSTUMER_REALITAPEMBELIAN, statistik.getJumlahTidakBeli());
        values.put(KEY_COSTUMER_REALITAPEMBELIAN, statistik.getPersentase());

        // updating
        return db.update(TABLE_STATISTIK, values, KEY_COSTUMER_ID + " = ?",
                new String[] { "1" });
    }

    //FIX
    public void deleteKonsumen(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_COSTUMER_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public void deleteAllStatistik(SQLiteDatabase db){
        db.execSQL("delete from "+ TABLE_STATISTIK);
    }

    //FIX
    public int getKonsumenCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

}
