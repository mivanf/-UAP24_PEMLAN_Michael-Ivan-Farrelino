public class Pesanan {
    private final Film film;
    private final int kuantitas;

    public Pesanan(Film film, int kuantitas) {
        this.film = film;
        this.kuantitas = kuantitas;
    }

    public Film getFilm() {
        return film;
    }

    public int getKuantitas() {
        return kuantitas;
    }
}