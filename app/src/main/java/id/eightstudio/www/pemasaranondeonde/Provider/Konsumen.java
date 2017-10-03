package id.eightstudio.www.pemasaranondeonde.Provider;

/**
 * Created by danbo on 10/2/17.
 */

public class Konsumen {

    private int idUser;

    private String konsumenName;

    //1 = laki - laki, 2 = Perempuan
    private int jenisKelamin;

    // 1 = 10 - 29, 2 = 30 - 49, 3 = >=50
    private int umurKonsumen;

    // 1 = Pelajar/Mahasiswa, 2 = PNS, 3 = Swasta, 4 = Lain - Lain
    private int pekerjaanKonsumen;

    // 1 = SD, 2 = SLTP, 3 = SLTA, 4 = Diploma, 5 = Sarjana, 5 = Lain - lain
    private int pendidikanKonsumen;

    //1 = True, 2= False
    int pengetahuanTentangBarang, ketertarikanBarang, hargaMenurutKonsumen, prediksiPembelian, realitaPembelian;

    public Konsumen() {
    }

    public Konsumen(int realitaPembelian) {
        this.realitaPembelian = realitaPembelian;
    }

    public Konsumen(String konsumenName, int jenisKelamin, int umurKonsumen, int pekerjaanKonsumen, int pendidikanKonsumen, int pengetahuanTentangBarang, int ketertarikanBarang, int hargaMenurutKonsumen, int prediksiPembelian, int realitaPembelian) {
        this.konsumenName = konsumenName;
        this.jenisKelamin = jenisKelamin;
        this.umurKonsumen = umurKonsumen;
        this.pekerjaanKonsumen = pekerjaanKonsumen;
        this.pendidikanKonsumen = pendidikanKonsumen;
        this.pengetahuanTentangBarang = pengetahuanTentangBarang;
        this.ketertarikanBarang = ketertarikanBarang;
        this.hargaMenurutKonsumen = hargaMenurutKonsumen;
        this.prediksiPembelian = prediksiPembelian;
        this.realitaPembelian = realitaPembelian;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getKonsumenName() {
        return konsumenName;
    }

    public void setKonsumenName(String konsumenName) {
        this.konsumenName = konsumenName;
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

    public int getPengetahuanTentangBarang() {
        return pengetahuanTentangBarang;
    }

    public void setPengetahuanTentangBarang(int pengetahuanTentangBarang) {
        this.pengetahuanTentangBarang = pengetahuanTentangBarang;
    }

    public int getKetertarikanBarang() {
        return ketertarikanBarang;
    }

    public void setKetertarikanBarang(int ketertarikanBarang) {
        this.ketertarikanBarang = ketertarikanBarang;
    }

    public int getHargaMenurutKonsumen() {
        return hargaMenurutKonsumen;
    }

    public void setHargaMenurutKonsumen(int hargaMenurutKonsumen) {
        this.hargaMenurutKonsumen = hargaMenurutKonsumen;
    }

    public int getPrediksiPembelian() {
        return prediksiPembelian;
    }

    public void setPrediksiPembelian(int prediksiPembelian) {
        this.prediksiPembelian = prediksiPembelian;
    }

    public int getRealitaPembelian() {
        return realitaPembelian;
    }

    public void setRealitaPembelian(int realitaPembelian) {
        this.realitaPembelian = realitaPembelian;
    }
}
