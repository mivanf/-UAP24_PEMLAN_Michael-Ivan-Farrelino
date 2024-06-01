public abstract class Aksi implements TampilanInterface {
    public abstract void keluar();

    public abstract void tutupAplikasi();

    public abstract void lihatListFilm();

    public static void welcome() {
        System.out.println("||" + "=".repeat(40) + "||");
        System.out.println("||" + " ".repeat(14) + "Bioskop DSI" + " ".repeat(15) + "||");
        System.out.println("||" + "=".repeat(40) + "||");
        System.out.println("Selamat Datang >_<");
        System.out.println("1. Login");
        System.out.println("2. Tutup Aplikasi");
    }
}