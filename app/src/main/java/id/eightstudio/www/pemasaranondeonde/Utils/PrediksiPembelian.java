package id.eightstudio.www.pemasaranondeonde.Utils;

import android.util.Log;

/**
 * Created by danbo on 10/2/17.
 */

public class PrediksiPembelian {
    private static final String TAG = "TabSatu";

    public static int keputusanBeli = 0;

    public static int prediksiPembelian(int jenisKelamin, int umurPelanggan, int pekerjaanKonsumen,
                                            int pendidikanKonsumen, int pengetahuanBarang, int ketertarikanBarang, int hargaBarang) {

        if ((hargaBarang == 1) && (ketertarikanBarang == 1)) {
            Log.d(TAG, "Menjalankan Rule : " + 1);
            keputusanBeli = 1;
        } else {
            Log.d(TAG, "Menjalankan Rule : " + 2);
            if ((hargaBarang == 1) && (ketertarikanBarang == 2) && (pendidikanKonsumen == 4)) {
                keputusanBeli = 1;
            } else {
                Log.d(TAG, "Menjalankan Rule : " + 3);
                if((hargaBarang == 1) && (ketertarikanBarang == 2) && (pendidikanKonsumen == 5)){
                    ketertarikanBarang = 2;
                } else {
                    Log.d(TAG, "Menjalankan Rule : " + 4);
                    if ((hargaBarang == 1) && (ketertarikanBarang == 2) && (pendidikanKonsumen == 1) && (umurPelanggan == 2)) {
                        keputusanBeli = 1;
                    } else {
                        Log.d(TAG, "Menjalankan Rule : " + 5);
                        if ((hargaBarang == 1) && (ketertarikanBarang == 2) && (pendidikanKonsumen == 1) && (umurPelanggan == 1)) {
                            keputusanBeli = 2;
                        } else {
                            Log.d(TAG, "Menjalankan Rule : " + 6);
                            if ((hargaBarang == 1) && (ketertarikanBarang == 2) && (pendidikanKonsumen == 3) && (umurPelanggan == 2)) {
                                keputusanBeli = 1;
                            } else {
                                Log.d(TAG, "Menjalankan Rule : " + 7);
                                if ((hargaBarang == 1) && (ketertarikanBarang == 2) && (pendidikanKonsumen == 3) && (umurPelanggan == 1) && (jenisKelamin == 2)) {
                                    keputusanBeli = 1;
                                } else {
                                    Log.d(TAG, "Menjalankan Rule : " + 8);
                                    if ((umurPelanggan == 1) && (jenisKelamin == 1) && (pekerjaanKonsumen == 3) && (pengetahuanBarang == 1)){
                                        keputusanBeli = 1;
                                    } else {
                                        Log.d(TAG, "Menjalankan Rule : " + 9);
                                        if ((hargaBarang == 1) && (ketertarikanBarang == 2) && (pendidikanKonsumen == 3) && (umurPelanggan == 1) && (jenisKelamin == 1) && (pekerjaanKonsumen == 3) && (pengetahuanBarang == 1)) {
                                            keputusanBeli = 2;
                                        } else {
                                            Log.d(TAG, "Menjalankan Rule : " + 10);
                                            if ((hargaBarang == 2) && (ketertarikanBarang == 2)){
                                                keputusanBeli = 2;
                                            } else {
                                                Log.d(TAG, "Menjalankan Rule : " + 11);
                                                if ((hargaBarang == 2) && (ketertarikanBarang == 1) && (umurPelanggan == 2)) {
                                                    keputusanBeli = 1;
                                                } else {
                                                    Log.d(TAG, "Menjalankan Rule : " + 12);
                                                    if ((hargaBarang == 2) && (ketertarikanBarang == 1) && (umurPelanggan == 1)){
                                                        keputusanBeli = 2;
                                                    } else {
                                                        keputusanBeli = 2;
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




