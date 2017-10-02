package id.eightstudio.www.pemasaranondeonde.Provider;

/**
 * Created by danbo on 10/2/17.
 */

public class Konsumen {

    private String konsumenId;

    //1 = laki - laki, 2 = Perempuan
    private int jenisKelamin;

    // 1 = 10 - 29, 2 = 30 - 49, 3 = >=50
    private int umurKonsumen;

    // 1 = Pelajar/Mahasiswa, 2 = PNS, 3 = Swasta, 4 = Lain - Lain
    private int pekerjaanKonsumen;

    // 1 = SD, 2 = SLTP, 3 = SLTA, 4 = Diploma, 5 = Sarjana, 5 = Lain - lain
    private int pendidikanKonsumen;

    //True - False
    boolean pengetahuanTentangBarang, ketertarikanBarang, hargaMenurutKonsumen;

    public Konsumen() {
    }

    public Konsumen(String konsumenId, int jenisKelamin, int umurKonsumen, int pekerjaanKonsumen, int pendidikanKonsumen, boolean pengetahuanTentangBarang, boolean ketertarikanBarang, boolean hargaMenurutKonsumen) {
        this.konsumenId = konsumenId;
        this.jenisKelamin = jenisKelamin;
        this.umurKonsumen = umurKonsumen;
        this.pekerjaanKonsumen = pekerjaanKonsumen;
        this.pendidikanKonsumen = pendidikanKonsumen;
        this.pengetahuanTentangBarang = pengetahuanTentangBarang;
        this.ketertarikanBarang = ketertarikanBarang;
        this.hargaMenurutKonsumen = hargaMenurutKonsumen;
    }

    public String getKonsumenId() {
        return konsumenId;
    }

    public void setKonsumenId(String konsumenId) {
        this.konsumenId = konsumenId;
    }

    public int getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(int jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public int getUmurKonsumen() {
        return umurKonsumen;
    }

    public void setUmurKonsumen(int umurKonsumen) {
        this.umurKonsumen = umurKonsumen;
    }

    public int getPekerjaanKonsumen() {
        return pekerjaanKonsumen;
    }

    public void setPekerjaanKonsumen(int pekerjaanKonsumen) {
        this.pekerjaanKonsumen = pekerjaanKonsumen;
    }

    public int getPendidikanKonsumen() {
        return pendidikanKonsumen;
    }

    public void setPendidikanKonsumen(int pendidikanKonsumen) {
        this.pendidikanKonsumen = pendidikanKonsumen;
    }

    public boolean isPengetahuanTentangBarang() {
        return pengetahuanTentangBarang;
    }

    public void setPengetahuanTentangBarang(boolean pengetahuanTentangBarang) {
        this.pengetahuanTentangBarang = pengetahuanTentangBarang;
    }

    public boolean isKetertarikanBarang() {
        return ketertarikanBarang;
    }

    public void setKetertarikanBarang(boolean ketertarikanBarang) {
        this.ketertarikanBarang = ketertarikanBarang;
    }

    public boolean isHargaMenurutKonsumen() {
        return hargaMenurutKonsumen;
    }

    public void setHargaMenurutKonsumen(boolean hargaMenurutKonsumen) {
        this.hargaMenurutKonsumen = hargaMenurutKonsumen;
    }
}
