package id.eightstudio.www.pemasaranondeonde.Provider;

/**
 * Created by danbo on 10/4/17.
 */

public class Statistik {

    private int idStatistik;
    private String jumlahPembeli;
    private String jumlahTidakBeli;
    private String persentase;
    private String TT;
    private String TF;
    private String FT;
    private String FF;

    public Statistik() {
    }


    public Statistik(String jumlahPembeli, String jumlahTidakBeli, String persentase, String TT, String TF, String FT, String FF) {
        this.jumlahPembeli = jumlahPembeli;
        this.jumlahTidakBeli = jumlahTidakBeli;
        this.persentase = persentase;
        this.TT = TT;
        this.TF = TF;
        this.FT = FT;
        this.FF = FF;
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

    public String getTT() {
        return TT;
    }

    public void setTT(String TT) {
        this.TT = TT;
    }

    public String getTF() {
        return TF;
    }

    public void setTF(String TF) {
        this.TF = TF;
    }

    public String getFT() {
        return FT;
    }

    public void setFT(String FT) {
        this.FT = FT;
    }

    public String getFF() {
        return FF;
    }

    public void setFF(String FF) {
        this.FF = FF;
    }
}
