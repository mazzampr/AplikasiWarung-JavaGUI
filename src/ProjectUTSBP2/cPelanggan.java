package ProjectUTSBP2;

public class cPelanggan {
    private String id,nama;
    private String alamat;
    private double totalBeli;
    
    cPelanggan(String kd, String nm) {
        id = kd; nama = nm;
        System.out.println("Object non Pelanggan dibuat");
    }
    cPelanggan(String kd, String nm, String a) {
        id=kd; nama=nm; alamat=a;
        System.out.println("Object Pelanggan berhasil dibuat");
    }
    // Setter
    public void setNama(String nm) {
        nama = nm;
    }
    public void setID(String kd) {
        id=kd;
    }
    public void setAlamat(String a) {
        alamat = a;
    }
    public void setTotalBeli(double tb) {
        totalBeli = tb;
    } 
    // Getter 
    public String getNama() {
        return nama;
    }
    public String getID() {
        return id;
    }
    public String getAlamat() {
        return alamat;
    }
    public double getTotalBeli() {
        return totalBeli;
    }
}
