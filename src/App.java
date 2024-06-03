import java.util.Scanner;
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

    }

    // setelah disimpan di arraylist tampilkan di method view
    public void view(){
        System.out.println("Data saat ini: ");
        for (int i = 0; i < data.size(); i++){
            String[] datum = data.get(i);
            System.out.println("Data ke-"+ (i + 1) + ": " + "Kode: " + datum[0] + ", Nama: " + datum[1] + ", Alamat: " + datum[2] + ", Nomor Telepon: " + datum[3]);
        }
    }
}

// produk

// stok

// penjualan

// laporan penjualan





public class App {
    public static void main(String[] args) throws Exception {
        // pegawai
        Pegawai pegawai = new Pegawai(); 


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
                System.out.println("4. penjualan");
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

                    break;
                
                case 3:
                // disini buat pilihan mau liat data atau buat data stok

                    break;

                case 4: 
                // buat penjualan 

                    break;

                case 5:
                // setelah buat penjualan bisa liat laporan penjualan nya disini

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
