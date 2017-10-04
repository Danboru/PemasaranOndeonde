package id.eightstudio.www.pemasaranondeonde.Provider;

/**
 * Created by danbo on 10/4/17.
 */

public class Statistik {

    private int idStatistik;
    private String jumlahPembeli;
    private String jumlahTidakBeli;
    private String persentase;
    private int trueTrue;
    private int trueFalse;
    private int falseTrue;
    private int falseFalse;

    public Statistik() {
    }

    public Statistik(int i, String string, String cursorString, String s) {
    }

    public Statistik(String jumlahPembeli, String jumlahTidakBeli, String persentase) {
        this.jumlahPembeli = jumlahPembeli;
        this.jumlahTidakBeli = jumlahTidakBeli;
        this.persentase = persentase;
    }

    public int getIdStatistik() {
        return idStatistik;
    }

    public void setIdStatistik(int idStatistik) {
        this.idStatistik = idStatistik;
    }

    public String getJumlahPembeli() {
        return jumlahPembeli;
    }

    public void setJumlahPembeli(String jumlahPembeli) {
        this.jumlahPembeli = jumlahPembeli;
    }

    public String getJumlahTidakBeli() {
        return jumlahTidakBeli;
    }

    public void setJumlahTidakBeli(String jumlahTidakBeli) {
        this.jumlahTidakBeli = jumlahTidakBeli;
    }

    public String getPersentase() {
        return persentase;
    }

    public void setPersentase(String persentase) {
        this.persentase = persentase;
    }
}
