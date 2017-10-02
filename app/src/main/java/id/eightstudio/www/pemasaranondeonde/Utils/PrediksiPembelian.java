package id.eightstudio.www.pemasaranondeonde.Utils;

import android.util.Log;

/**
 * Created by danbo on 10/2/17.
 */

public class PrediksiPembelian {
    private static final String TAG = "TabSatu";

    public static boolean keputusanBeli = false;

    public static boolean prediksiPembelian(int jenisKelamin, int umurPelanggan, int pekerjaanKonsumen,
                                            int pendidikanKonsumen, boolean pengetahuanBarang, boolean ketertarikanBarang, boolean hargaBarang) {

        if ((hargaBarang == true) && (ketertarikanBarang == true)) {
            Log.d(TAG, "Menjalankan Rule : " + 1);
            keputusanBeli = true;
        } else {
            Log.d(TAG, "Menjalankan Rule : " + 2);
            if ((hargaBarang == true) && (ketertarikanBarang == false) && (pendidikanKonsumen == 4)) {
                keputusanBeli = false;
            } else {
                Log.d(TAG, "Menjalankan Rule : " + 3);
                if((hargaBarang == true) && (ketertarikanBarang == false) && (pendidikanKonsumen == 5)){
                    ketertarikanBarang = false;
                } else {
                    Log.d(TAG, "Menjalankan Rule : " + 4);
                    if ((hargaBarang == true) && (ketertarikanBarang == false) && (pendidikanKonsumen == 1) && (umurPelanggan == 2)) {
                        keputusanBeli = true;
                    } else {
                        Log.d(TAG, "Menjalankan Rule : " + 5);
                        if ((hargaBarang == true) && (ketertarikanBarang == false) && (pendidikanKonsumen == 1) && (umurPelanggan == 1)) {
                            keputusanBeli = false;
                        } else {
                            Log.d(TAG, "Menjalankan Rule : " + 6);
                            if ((hargaBarang == true) && (ketertarikanBarang == false) && (pendidikanKonsumen == 3) && (umurPelanggan == 2)) {
                                keputusanBeli = true;
                            } else {
                                Log.d(TAG, "Menjalankan Rule : " + 7);
                                if ((hargaBarang == true) && (ketertarikanBarang == false) && (pendidikanKonsumen == 3) && (umurPelanggan == 1) && (jenisKelamin == 2)) {
                                    keputusanBeli = true;
                                } else {
                                    Log.d(TAG, "Menjalankan Rule : " + 8);
                                    if ((umurPelanggan == 1) && (jenisKelamin == 1) && (pekerjaanKonsumen == 3) && (pengetahuanBarang == true)){
                                        keputusanBeli = true;
                                    } else {
                                        Log.d(TAG, "Menjalankan Rule : " + 9);
                                        if ((hargaBarang = true) && (ketertarikanBarang = false) && (pendidikanKonsumen == 3) && (umurPelanggan == 1) && (jenisKelamin == 1) && (pekerjaanKonsumen == 3) && (pengetahuanBarang = true)) {
                                            keputusanBeli = false;
                                        } else {
                                            Log.d(TAG, "Menjalankan Rule : " + 10);
                                            if ((hargaBarang == false) && (ketertarikanBarang == false)){
                                                keputusanBeli = false;
                                            } else {
                                                Log.d(TAG, "Menjalankan Rule : " + 11);
                                                if ((hargaBarang == false) && (ketertarikanBarang == true) && (umurPelanggan == 2)) {
                                                    keputusanBeli = true;
                                                } else {
                                                    Log.d(TAG, "Menjalankan Rule : " + 12);
                                                    if ((hargaBarang == false) && (ketertarikanBarang == true) && (umurPelanggan == 1)){
                                                        keputusanBeli = false;
                                                    } else {
                                                        keputusanBeli = false;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return keputusanBeli;
    }
}




