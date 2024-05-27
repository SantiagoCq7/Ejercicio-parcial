public class Blusa extends Componente {
    // Atributos
    private boolean mangaLarga;

    // Constructor
    public Blusa(int id, String nombre, String talla, String color, boolean esComunitario, double precio, boolean mangaLarga) {
        super(id, nombre, talla, color, esComunitario, precio);
        this.mangaLarga = mangaLarga;
    }

    // Getters y Setters
    public boolean isMangaLarga() {
        return mangaLarga;
    }

    public void setMangaLarga(boolean mangaLarga) {
        this.mangaLarga = mangaLarga;
    }

    // toString
    @Override
    public String toString() {
        return "Blusa{" +
                "mangaLarga=" + mangaLarga +
                ", " + super.toString() +
                '}';
    }
}
