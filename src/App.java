import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// datahandler
abstract class Datahandler {
    public abstract void create_data();
    public abstract void view();
    // public abstract void save();
}

// pegawai 
// beri pilihan lagi mau lihat data pegawai / buat data pegawai baru
class Pegawai extends Datahandler {
    private List<String[]> data;

    public Pegawai() {
        this.data = new ArrayList<>();
    }

    String kode, nama, alamat, tlp;

    Scanner pegawai = new Scanner(System.in);
    // method ini buat data 
    public void create_data(){
        System.out.print("Masukkan Kode: ");
        kode = pegawai.nextLine();
        System.out.print("Masukkan Nama: ");
        nama = pegawai.nextLine();
        System.out.print("Masukkan Alamat: ");
        alamat = pegawai.nextLine();
        System.out.print("Masukkan Nomor Telepon: ");
        tlp = pegawai.nextLine();

        String[] newpegawai ={kode,nama,alamat,tlp};
        data.add(newpegawai);
        System.out.println("Data telah ditambahkan: "+ String.join(", ", newpegawai));

        // save created data
        save(newpegawai);
    }

    // save created data ke pegawai.txt
    private void save(String[] newpegawai){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pegawai.txt", true))) {
            writer.write(String.join(", ",newpegawai));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

    // menampilkan data dari pegawai.txt
    public void view(){
        data.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("pegawai.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] loadeddata= line.split(", ");
                data.add(loadeddata);
                System.out.println("Data saat ini: ");
            }
            for (int i = 0; i < data.size(); i++){
                String[] datum = data.get(i);
                System.out.println("Data ke-"+ (i + 1) + ": " + "Kode: " + datum[0] + ", Nama: " + datum[1] + ", Alamat: " + datum[2] + ", Nomor Telepon: " + datum[3]);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Terjadi kesalahan saat memuat data: " + e.getMessage());
        }
    }
    
}

// produk
class Produk extends Datahandler {
    private List<String[]> data;

    public Produk() {
        this.data = new ArrayList<>();
    }

    String namaProduk, harga, distributor;

    Scanner produk = new Scanner(System.in);

    // method ini buat data 
    public void create_data() {
        System.out.print("Masukkan Nama Produk: ");
        namaProduk = produk.nextLine();
        System.out.print("Masukkan Harga: ");
        harga = produk.nextLine();
        System.out.print("Masukkan Distributor: ");
        distributor = produk.nextLine();

        String[] newProduk = { namaProduk, harga, distributor };
        data.add(newProduk);
        System.out.println("Data telah ditambahkan: " + String.join(", ", newProduk));
    }

    // setelah disimpan di arraylist tampilkan di method view
    public void view() {
        System.out.println("Data saat ini: ");
        for (int i = 0; i < data.size(); i++) {
            String[] datum = data.get(i);
            System.out.println("Data ke-" + (i + 1) + ": " + "Nama Produk: " + datum[0] + ", Harga: " + datum[1] + ", Distributor: " + datum[2]);
        }
    }

    public List<String[]> getData() {
        return this.data;
    }
}

// stok
// nama, stok, tanggal masuk, tanggal kadaluarsa, kategori
class Stok extends Datahandler{
    private List<String[]> data;
    private List<String[]> produkData;

    public Stok( List<String[]> produkData){
        this.data = new ArrayList<>();
        this.produkData = produkData;
    }

    String nama,kategori ,stok, tanggal_m, tanggal_k;

    Scanner stokin = new Scanner(System.in);

    public void create_data(){
        // cek ketersediaan produk 
        if (produkData.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia. Tambahkan produk terlebih dahulu");
        }

        // jika produk tersedia
        System.out.println("Pilih produk: ");
        for (int i = 0; i < produkData.size(); i++) {
            // menampilkan nama produk yang tersedia
            String[] produk = produkData.get(i);
            System.out.println((i +  1) + ". " + produk[0]);
        }

        int pilihan = -1;
        while (pilihan < 1 || pilihan > produkData.size()) {
            System.out.print("Masukkan nomor produk: ");
            // pilihan = stokin.nextInt();
            pilihan = Integer.parseInt(stokin.nextLine());
        }
        String[] selectedproduct = produkData.get(pilihan - 1);
        nama = selectedproduct[0];       

        System.out.print("Masukkan Kategori: ");
        kategori = stokin.nextLine();
        System.out.print("Masukkan Stok: ");
        stok = stokin.nextLine();
        System.out.print("Masukkan Tanggal masuk : ");
        tanggal_m = stokin.nextLine();
        System.out.print("Masukkan Tanggal kadaluarsa: ");
        tanggal_k = stokin.nextLine();
        
        String[] newStock = { nama, kategori ,stok, tanggal_m, tanggal_k };
        data.add(newStock);
        System.out.println("Data telah ditambahkan: " + String.join(", ", newStock));
    }

    public void view(){
        System.out.println("Data saat ini: ");
        for (int i = 0; i < data.size(); i++) {
            String[] datum = data.get(i);
            System.out.println("Data ke-" + (i + 1) + ": " + "Nama Produk: " + datum[0] + ", Kategori: " + datum[1] + ", Stok: " + datum[2] + ", Tanggal masuk: " + datum[3]+ ", Tanggal kadaluarsa: "+ datum[4]);
        }
    }
}

// transaksi penjualan menampung seluruh penjualan 
class TransaksiPenjualan {
    // 3 private berguna mendaftarkan variable 
    private List<String[]> items;
    private String tanggal;
    private int totalHarga;

    // 3 variable diatas di panggil di constructor guna untuk menyimpan data penjualan 
    public TransaksiPenjualan(List<String[]> items, String tanggal, int totalHarga) {
        this.items = items;
        this.tanggal = tanggal;
        this.totalHarga = totalHarga;
    }
    // getter untuk mengambil data penjualan lalu di pasang di method setter di atas
    public List<String[]> getItems() {
        return items;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getTotalHarga() {
        return totalHarga;
    }
    // method print untuk menampilkan data penjualan
    public void print() {
        // menampilkan tanggal penjualan
        System.out.println("Tanggal: " + tanggal);

        // for loop untuk menampilkan data penjualan
        for (int i = 0; i < items.size(); i++) {
            // mengambil data penjualan dari arraylist items
            String[] item = items.get(i);
            // menampilkan data penjualan sesaui dengan tamplate
            System.out.println("Item ke-" + (i + 1) + ": " + "Nama Produk: " + item[0] + ", Harga: " + item[1]
                    + ", Jumlah: " + item[2] + ", Subtotal: " + item[3]);
        }
        // menampilkan total harga
        System.out.println("Total Harga: " + totalHarga);
    }
}
// penjualan
// penjualan itu data barang di simpan di penjualan, 

// penjualan extends datahandler agar bisa menggunakan method create_data dan view dari datahandler
class Penjualan extends Datahandler {
    // private data untuk menyimpan data penjualan
    private List<TransaksiPenjualan> data;
    private List<String[]> produkData;

    // constructor untuk menyimpan data produk
    public Penjualan(List<String[]> produkData) {
        this.data = new ArrayList<>();
        this.produkData = produkData;
    }

    // untuk menerima imputan dari user
    Scanner penjualan = new Scanner(System.in);

    // method create_data untuk membuat data penjualan
    public void create_data() {
        // listString untuk mendefinisikan variable items
        List<String[]> items = new ArrayList<>();
        String lanjut = "y";
        int totalHarga = 0;

        // do while untuk melakukan perulangan jika user ingin menambahkan produk lagi
        do {
            // menampilkan produk yang tersedia
            System.out.println("Pilih Produk untuk Dijual: ");
            for (int i = 0; i < produkData.size(); i++) {
                String[] produk = produkData.get(i);
                System.out.println((i + 1) + ". " + produk[0] + " - " + produk[1]);
            }
            // inputan user untuk memilih produk
            System.out.print("Masukkan Nomor Produk: ");
            int produkChoice = penjualan.nextInt();
            penjualan.nextLine(); // clear newline from buffer

            // jika produkChoice tidak valid
            if (produkChoice < 1 || produkChoice > produkData.size()) {
                System.out.println("Pilihan produk tidak valid.");
                continue;
            }

            // mengambil data produk yang dipilih user dari arraylist produkData
            String[] selectedProduk = produkData.get(produkChoice - 1);
            // menyimpan nama produk dan harga produk ke dalam variable 
            String namaProduk = selectedProduk[0];
            // mengubah string harga produk menjadi integer parsing
            int hargaProduk = Integer.parseInt(selectedProduk[1]);

            // inputan user untuk memasukkan jumlah produk yang akan dijual
            System.out.print("Masukkan Jumlah: ");

            // mengambil inputan user
            String jumlah = penjualan.nextLine();

            // mengubah string jumlah menjadi integer parsing
            int qty = Integer.parseInt(jumlah);
            // menghitung subtotal
            int subtotal = hargaProduk * qty;
            // menghitung total harga
            totalHarga += subtotal;

            // nama produk, harga produk, jumlah , subtotal produk di simpan di arraylist items
            String[] item = { namaProduk, String.valueOf(hargaProduk), jumlah, 
            String.valueOf(subtotal) };

            // menambahkan item ke dalam arraylist items
            items.add(item);

            // menampilkan item yang telah ditambahkan
            System.out.print("Apakah ingin menambah produk lagi? (y/n): ");

            // inputan user untuk menambahkan produk lagi
            lanjut = penjualan.nextLine();

            // jika user tidak ingin menambahkan produk lagi maka keluar dari perulangan
        } while (lanjut.equalsIgnoreCase("y"));

        // inputan user untuk memasukkan tanggal penjualan
        System.out.print("Masukkan Tanggal: ");

        // mengambil inputan user untuk tanggal penjualan 
        String tanggal = penjualan.nextLine();

        // membuat objek transaksi penjualan dengan parameter items, tanggal, totalHarga dan menyimpan ke dalam variable transaksipenjualan  
        TransaksiPenjualan transaksi = new TransaksiPenjualan(items, tanggal, totalHarga);

        // menambahkan transaksi penjualan ke dalam arraylist data
        data.add(transaksi);

        // menampilkan data penjualan
        System.out.println("Transaksi penjualan telah ditambahkan.");
        transaksi.print();
    }
    // method view untuk menampilkan data penjualan
    public void view() {
        // menampilkan data penjualan saat ini
        System.out.println("Data penjualan saat ini: ");

        // for loop untuk menampilkan data penjualan 
        for (int i = 0; i < data.size(); i++) {

            // menampilkan data penjualan ke-i dengan method print dari class TransaksiPenjualan ke i 
            System.out.println("Transaksi Penjualan ke-" + (i + 1) + ": ");
            data.get(i).print();
        }
    }

    // Method getData()
    public List<TransaksiPenjualan> getData() {
        return this.data;
    }
}


// laporan penjualan
class LaporanPenjualan {
    private List<TransaksiPenjualan> data;

    public LaporanPenjualan() {
        this.data = new ArrayList<>();
    }

    public void addPenjualan(TransaksiPenjualan penjualan) {
        this.data.add(penjualan);
    }

    public void printLaporan() {
        System.out.println("Laporan Penjualan:");
        int totalHarga = 0;
        for (int i = 0; i < data.size(); i++) {
            TransaksiPenjualan penjualan = data.get(i);
            System.out.println("Transaksi Penjualan ke-" + (i + 1) + ": ");
            penjualan.print();
            totalHarga += penjualan.getTotalHarga();
        }
        // System.out.println("Total Harga: " + totalHarga);
    }
}




public class App {
    public static void main(String[] args) throws Exception {
        // pegawai
        Pegawai pegawai = new Pegawai(); 
        // produk
        Produk produk = new Produk();
        // stok
        Stok stok = new Stok(produk.getData());

        Penjualan penjualan = new Penjualan(produk.getData());


        Scanner input = new Scanner(System.in);
        int choice;
        String kembali;

        do{
            do {
                System.out.println("===================Sistem Kedai kopi==================");
                System.out.println("Menu: ");
                System.out.println("1. Pegawai");
                System.out.println("2. Produk");
                System.out.println("3. Stok");
                System.out.println("4. Penjualan");
                System.out.println("5. Laporan penjualan");
                System.out.println("6. Exit");
                System.out.print("Masukkan Pilihan: ");
                choice = input.nextInt();
                input.nextLine(); //clear newline from buffer

                System.out.println( "======================================================");
            }while (choice < 1 || choice > 6); //choice harus valid
    
            switch (choice) {
                case 1:
                // disini buat pilihan mau liat data atau buat data baru
                    do {
                        System.out.println("1. Tambah Data Pegawai");
                        System.out.println("2. Lihat Data Pegawai");
                        System.out.print("Masukkan Pilihan: ");
                        choice = input.nextInt();
                        input.nextLine();

                        switch (choice) {
                            case 1:
                                pegawai.create_data();
                                break;
                            case 2:
                                pegawai.view();
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                                break;
                        } 
                        System.out.print("Apakah ingin kembali ke menu pegawai? (y/n): ");
                        kembali = input.nextLine();
                    } while(kembali.equalsIgnoreCase("y"));
                        
                    break;
                case 2: 
                // disini buat pilihan mau liat data atau buat data produk
                    do {
                        System.out.println("1. Tambah Data Produk");
                        System.out.println("2. Lihat Data Produk");
                        System.out.print("Masukkan Pilihan: ");
                        choice = input.nextInt();
                        input.nextLine();

                        switch (choice) {
                            case 1:
                                produk.create_data();
                                break;
                            case 2:
                                produk.view();
                                break;
                            default:
                                System.out.println("Pilihan tidak valid.");
                                break;
                        } 
                        System.out.print("Apakah ingin kembali ke menu produk? (y/n): ");
                        kembali = input.nextLine();
                    } while(kembali.equalsIgnoreCase("y"));

                    break;
                
                case 3:
                // disini buat pilihan mau liat data atau buat data stok
                do {
                    System.out.println("1. Tambah Data Stok");
                    System.out.println("2. Lihat Data Stok");
                    System.out.print("Masukkan Pilihan: ");
                    choice = input.nextInt();
                    input.nextLine();

                    switch (choice) {
                        case 1:
                            stok.create_data();
                            break;
                        case 2:
                            stok.view();
                            break;
                        default:
                            System.out.println("Pilihan tidak valid.");
                            break;
                    } 
                    System.out.print("Apakah ingin kembali ke menu stok? (y/n): ");
                    kembali = input.nextLine();
                } while(kembali.equalsIgnoreCase("y"));
                    break;

                case 4: 
                // buat penjualan 
                do {
                    System.out.println("1. Tambah Data Penjualan");
                    System.out.println("2. Lihat Data Penjualan");
                    System.out.print("Masukkan Pilihan: ");
                    choice = input.nextInt();
                    input.nextLine();

                    switch (choice) {
                        case 1:
                            penjualan.create_data();
                            break;
                        case 2:
                            penjualan.view();
                            break;
                        default:
                            System.out.println("Pilihan tidak valid.");
                            break;
                    }
                    System.out.print("Apakah ingin kembali ke menu penjualan? (y/n): ");
                    kembali = input.nextLine();
                } while (kembali.equalsIgnoreCase("y"));
                break;
                case 5:
                // setelah buat penjualan bisa liat laporan penjualan nya disini
                LaporanPenjualan laporanPenjualan = new LaporanPenjualan();
                    for (TransaksiPenjualan transaksiPenjualan : penjualan.getData()) {
                        laporanPenjualan.addPenjualan(transaksiPenjualan);
                    }
                    laporanPenjualan.printLaporan();
                    break;
            
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
            System.out.print("Apakah ingin kembali ke menu utama ? (y/n) : ");
            kembali = input.nextLine();
            
        } while (kembali.equalsIgnoreCase("y"));

        System.out.println("Terima kasih telah menggunakan sistem kedai kopi!");
        input.close();
    }
        
}
