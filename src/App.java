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
    public void create_data() {
        System.out.print("Masukkan Kode: ");
        kode = pegawai.nextLine();
        System.out.print("Masukkan Nama: ");
        nama = pegawai.nextLine();
        System.out.print("Masukkan Alamat: ");
        alamat = pegawai.nextLine();
        System.out.print("Masukkan Nomor Telepon: ");
        tlp = pegawai.nextLine();

        String[] newpegawai = { kode, nama, alamat, tlp };
        data.add(newpegawai);
        System.out.println("Data telah ditambahkan: " + String.join(", ", newpegawai));

        // save created data
        save(newpegawai);
    }

    // save created data ke pegawai.txt
    private void save(String[] newpegawai) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pegawai.txt", true))) {
            writer.write(String.join(", ", newpegawai));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

    // menampilkan data dari pegawai.txt
    public void view() {
        System.out.println("Data saat ini: ");
        data.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("pegawai.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] loadeddata = line.split(", ");
                data.add(loadeddata);
            }
            for (int i = 0; i < data.size(); i++) {
                String[] datum = data.get(i);
                System.out.println("Data ke-" + (i + 1) + ": " + "Kode: " + datum[0] + ", Nama: " + datum[1]
                        + ", Alamat: " + datum[2] + ", Nomor Telepon: " + datum[3]);
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

        // save created data
        save(newProduk);
    }

    // save created data ke produk.txt
    private void save(String[] newProduk) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("produk.txt", true))) {
            writer.write(String.join(", ", newProduk));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

    // setelah disimpan di arraylist tampilkan di method view
    public void view() {
        System.out.println("Data saat ini: ");
        data.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("produk.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] loadeddata = line.split(", ");
                data.add(loadeddata);
            }
            for (int i = 0; i < data.size(); i++) {
                String[] datum = data.get(i);
                System.out.println("Data ke-" + (i + 1) + ": " + "Nama Produk: " + datum[0] + ", Harga: " + datum[1]
                        + ", Distributo: " + datum[2]);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Terjadi kesalahan saat memuat data: " + e.getMessage());
        }
    }

    public List<String[]> getData() {
        return this.data;
    }
}

// stok
// nama, stok, tanggal masuk, tanggal kadaluarsa, kategori
class Stok extends Datahandler {
    private List<String[]> data;
    private List<String[]> produkData;

    public Stok(List<String[]> produkData) {
        this.data = new ArrayList<>();
        this.produkData = produkData;
        loadData();
    }

    String nama, kategori, stok, tanggal_m, tanggal_k;

    Scanner stokin = new Scanner(System.in);

    public void create_data() {
        // cek ketersediaan produk
        if (produkData.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia. Tambahkan produk terlebih dahulu");
        }

        // jika produk tersedia
        System.out.println("Pilih produk: ");
        for (int i = 0; i < produkData.size(); i++) {
            // menampilkan nama produk yang tersedia
            String[] produk = produkData.get(i);
            System.out.println((i + 1) + ". " + produk[0]);
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

        String[] newStock = { nama, kategori, stok, tanggal_m, tanggal_k };
        data.add(newStock);
        System.out.println("Data telah ditambahkan: " + String.join(", ", newStock));

        save(newStock);
    }

    private void save(String[] newStok) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("stok.txt", true))) {
            writer.write(String.join(", ", newStok));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

    public void view() {
        System.out.println("Data saat ini: ");
        data.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("stok.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] loadeddata = line.split(", ");
                data.add(loadeddata);
            }
            for (int i = 0; i < data.size(); i++) {
                String[] datum = data.get(i);
                System.out.println("Data ke-" + (i + 1) + ": " + "Nama Produk: " + datum[0] + ", Kategori: " + datum[1]
                        + ", Stok: " + datum[2] + ", Tanggal Masuk: " + datum[3] + ", Tanggal kadaluarsa:" + datum[4]);

            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Terjadi kesalahan saat memuat data: " + e.getMessage());
        }
    }

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("produk.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] produk = line.split(", ");
                produkData.add(produk);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat data produk: " + e.getMessage());
        }
    }

}

// transaksi penjualan menampung seluruh penjualan
class TransaksiPenjualan {
    // 3 private berguna mendaftarkan variable
    private List<String[]> items;
    private String tanggal;
    private int totalHarga;
    private String namaPembeli;

    // 3 variable diatas di panggil di constructor guna untuk menyimpan data
    // penjualan
    public TransaksiPenjualan(List<String[]> items, String namaPembeli, String tanggal, int totalHarga) {
        this.items = items;
        this.namaPembeli = namaPembeli;
        this.tanggal = tanggal;
        this.totalHarga = totalHarga;
    }

    // getter untuk mengambil data penjualan lalu di pasang di method setter di atas
    public List<String[]> getItems() {
        return items;
    }

    public String getNamaPembeli() {
        return namaPembeli;
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
            String[] item = items.get(i);
            System.out.println("Item ke-" + (i + 1) + ": Nama Pembeli: " + namaPembeli + ", Nama Produk: " + item[0] + ", Harga: " + item[1] + ", Jumlah: " + item[2] + ", Subtotal: " + item[3]);
        }
        // menampilkan total harga
        System.out.println("Total Harga: " + totalHarga);
    }
}
// penjualan
// penjualan extends datahandler agar bisa menggunakan method create_data dan
// view dari datahandler
class Penjualan extends Datahandler {
    private List<TransaksiPenjualan> data;
    private List<String[]> produkData;

    public Penjualan(List<String[]> produkData) {
        this.data = new ArrayList<>();
        this.produkData = produkData;
    }

    Scanner penjualan = new Scanner(System.in);

    public void create_data() {
        List<String[]> items = new ArrayList<>();
        String lanjut = "y";
        int totalHarga = 0;

        System.out.print("Masukkan Nama Pembeli: ");
        String namaPembeli = penjualan.nextLine();

        do {
            System.out.println("Pilih Produk untuk Dijual: ");
            for (int i = 0; i < produkData.size(); i++) {
                String[] produk = produkData.get(i);
                System.out.println((i + 1) + ". " + produk[0] + " - " + produk[1]);
            }

            System.out.print("Masukkan Nomor Produk: ");
            int produkChoice = penjualan.nextInt();
            penjualan.nextLine(); // clear newline from buffer

            if (produkChoice < 1 || produkChoice > produkData.size()) {
                System.out.println("Pilihan produk tidak valid.");
                continue;
            }

            String[] selectedProduk = produkData.get(produkChoice - 1);
            String namaProduk = selectedProduk[0];
            int hargaProduk = Integer.parseInt(selectedProduk[1]);

            System.out.print("Masukkan Jumlah: ");
            String jumlah = penjualan.nextLine();
            int qty = Integer.parseInt(jumlah);

            int subtotal = hargaProduk * qty;
            totalHarga += subtotal;

            String[] item = { namaPembeli, namaProduk, String.valueOf(hargaProduk), jumlah, String.valueOf(subtotal), "" };
            items.add(item);

            System.out.print("Apakah ingin menambah produk lagi? (y/n): ");
            lanjut = penjualan.nextLine();
        } while (lanjut.equalsIgnoreCase("y"));

        System.out.print("Masukkan Tanggal: ");
        String tanggal = penjualan.nextLine();

        if (totalHarga > 100000) {
            int diskon = (int) (totalHarga * 0.05);
            totalHarga -= diskon;
            System.out.println("Diskon sebesar 5% diterapkan: " + diskon);
        }

        for (String[] item : items) {
            item[5] = tanggal;
            TransaksiPenjualan transaksi = new TransaksiPenjualan(items, namaPembeli, tanggal, totalHarga);
            data.add(transaksi);
            save(transaksi);
        }

        System.out.println("Transaksi penjualan telah ditambahkan.");
    }

    private void save(TransaksiPenjualan transaksi) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("penjualan.txt", true));BufferedWriter writerLaporan = new BufferedWriter(new FileWriter("laporanpenjualan.txt", true))) {

        // Write to penjualan.txt
        writer.write("Nama Pembeli: " + transaksi.getNamaPembeli() + ", Tanggal: " + transaksi.getTanggal() + ", Total Harga: " + transaksi.getTotalHarga());
        writer.newLine();
        for (String[] item : transaksi.getItems()) {
            writer.write("Nama Produk: " + item[1] + ", Harga: " + item[2] + ", Jumlah: " + item[3] + ", Subtotal: " + item[4]);
            writer.newLine();
        }
        writer.write("------");
        writer.newLine();

        // Write to laporanpenjualan.txt
        writerLaporan.write("Nama Pembeli: " + transaksi.getNamaPembeli() + ", Tanggal: " + transaksi.getTanggal() + ", Total Harga: " + transaksi.getTotalHarga());
        writerLaporan.newLine();
        for (String[] item : transaksi.getItems()) {
            writerLaporan.write("Nama Produk: " + item[1] + ", Harga: " + item[2] + ", Jumlah: " + item[3] + ", Subtotal: " + item[4]);
            writerLaporan.newLine();
        }
        writerLaporan.write("------");
        writerLaporan.newLine();

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

    public void view() {
        System.out.println("Data penjualan saat ini: ");
        int totalSeluruhTransaksi = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("penjualan.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("Nama Pembeli:")) {
                    String[] parts = line.split(", ");
                    for (String part : parts) {
                        if (part.startsWith("Total Harga:")) {
                            String totalStr = part.split(": ")[1];
                            totalSeluruhTransaksi += Integer.parseInt(totalStr);
                        }
                    }
                }
            }
            System.out.println("------");
            System.out.println("Total dari seluruh transaksi: " + totalSeluruhTransaksi);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat data: " + e.getMessage());
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
        
        int totalSeluruhTransaksi = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("laporanpenjualan.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(", ");
                for (String part : parts) {
                    if (part.startsWith("Total Harga:")) {
                        String totalStr = part.split(": ")[1];
                        totalSeluruhTransaksi += Integer.parseInt(totalStr);
                    }
                }
            }
            System.out.println("Total dari seluruh transaksi: " + totalSeluruhTransaksi);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat memuat data produk: " + e.getMessage());
        }
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

        do {
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
                input.nextLine(); // clear newline from buffer

                System.out.println("======================================================");
            } while (choice < 1 || choice > 6); // choice harus valid

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
                    } while (kembali.equalsIgnoreCase("y"));

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
                    } while (kembali.equalsIgnoreCase("y"));

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
                    } while (kembali.equalsIgnoreCase("y"));
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
                case 6:
                    System.out.println("Terima kasih telah menggunakan sistem kedai kopi!");
                    input.close();
                break;
            }
            System.out.print("Apakah ingin kembali ke menu utama ? (y/n) : ");
            kembali = input.nextLine();

        } while (kembali.equalsIgnoreCase("y"));

        System.out.println("Terima kasih telah menggunakan sistem kedai kopi!");
        input.close();
    }

}
