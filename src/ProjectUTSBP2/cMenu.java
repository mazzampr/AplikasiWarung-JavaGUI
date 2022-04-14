package ProjectUTSBP2;

public class cMenu {
    private String nama;
    private double harga;
    private String kode;
    
    cMenu(String kd, String n, double h) {
        kode=kd ;nama=n; harga=h;
        System.out.println("Object "+nama+" berhasil dibuat");
    }
    
    // Setter
    public void setHarga(double h) {
        harga = h;
    }
    
    // Getter
    public String getNama() {
        return nama;
    }
    public double getHarga() {
        return harga;
    }
    public String ToString() {
        return nama+" ["+harga+"]";
    }
    public String getKode() {
        return kode;
    }
}
