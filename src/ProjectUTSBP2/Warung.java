package ProjectUTSBP2;

public class Warung {
    static cPelanggan plg[];
    static cMakanan mk[];
    static cMinuman mi[];
    static cMenu menu[];
    static int jplg,jmk,jmi;
    static int mplg,mmk,mmi;
    
    public static void main(String[] args) {
        plg = new cPelanggan[6];
        mk = new cMakanan[6];
        mi = new cMinuman[6];
        // Master Pelanggan
        plg[0] = new cPelanggan("P01","Budi","Sukolilo");
        plg[1] = new cPelanggan("P02","Adi","Simo");
        plg[2] = new cPelanggan("P03","Suneo","Sukorejo");
        // Master makanan
        mk[0] = new cMakanan("MK01", "Rujak Kacang", 8000);
        mk[1] = new cMakanan("MK02", "Rujak Pentol", 6000);
        mk[2] = new cMakanan("MK03", "Soto Madura", 12000);
        // Master minuman
        mi[0] = new cMinuman("MI01", "Es Teh", 3000);
        mi[1] = new cMinuman("MI02", "Es Jeruk", 4000);
        mi[2] = new cMinuman("MI03", "Es Soda Gembira", 7000);
        
        jplg=3; jmk= 3; jmi=3;
        mplg = 6; mmk=6; mmi=6;
        
        System.out.println("Object Warung berhasil dibuat");
        new SplashScreen().show();
    }
}
