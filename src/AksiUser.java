import java.util.Map;
import java.util.Scanner;

public class AksiUser extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat Saldo");
        System.out.println("3. Lihat List Film");
        System.out.println("4. Lihat Pesanan");
        System.out.println("5. Logout");
        System.out.println("6. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        Map<String, Film> films = Film.getFilms();
        if (films.isEmpty()) {
            System.out.println("Tidak ada film yang tersedia.");
        } else {
            for (Film film : films.values()) {
                System.out.println("Film: " + film.getName() + 
                " - Deskripsi: " + film.getDescription() + 
                " - Harga: " + film.getPrice() + 
                " - Stok: " + film.getStock());
            }
        }
    }

    public void lihatSaldo() {
        User user = Akun.getCurrentUser();
        if (user != null) {
            System.out.println("Saldo anda: " + user.getSaldo());
        } else {
            System.out.println("Gagal mengambil saldo, user tidak ditemukan.");
        }
    }

    public void pesanFilm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama Film yang ingin dipesan: ");
        String filmName = scanner.nextLine();
        Film film = Film.getFilms().get(filmName);

        if (film == null) {
            System.out.println("Film yang dicari tidak ditemukan.");
            return;
        }

        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int jumlahTiket = scanner.nextInt();
        
        if (jumlahTiket > film.getStock()) {
            System.out.println("Stok tiket tidak mencukupi.");
            return;
        }

        double totalHarga = jumlahTiket * film.getPrice();
        User user = Akun.getCurrentUser();

        if (user.getSaldo() < totalHarga) {
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki " + user.getSaldo());
            return;
        }

        user.setSaldo(user.getSaldo() - totalHarga);
        film.setStock(film.getStock() - jumlahTiket);
        user.addPesanan(film, jumlahTiket);

        System.out.println("Tiket berhasil dipesan.");
    }

    public void lihatPesanan() {
        User user = Akun.getCurrentUser();
        Map<String, Pesanan> pesanan = user.getPesanan();

        if (pesanan.isEmpty()) {
            System.out.println("Kamu belum pernah melakukan pemesanan.");
        } else {
            for (Pesanan p : pesanan.values()) {
                System.out.println("Film: " + p.getFilm().getName() + 
                " - Jumlah: " + p.getKuantitas() + 
                " - Total Harga: " + (p.getKuantitas() * p.getFilm().getPrice()));
            }
        }
    }
}