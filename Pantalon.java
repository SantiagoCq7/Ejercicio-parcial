public class Pantalon extends Componente {
    // Atributos
    private boolean conCremallera;

    // Constructor
    public Pantalon(int id, String nombre, String talla, String color, boolean esComunitario, double precio, boolean conCremallera) {
        super(id, nombre, talla, color, esComunitario, precio);
        this.conCremallera = conCremallera;
    }

    // Getters y Setters
    public boolean isConCremallera() {
        return conCremallera;
    }

    public void setConCremallera(boolean conCremallera) {
        this.conCremallera = conCremallera;
    }

    // toString
    @Override
    public String toString() {
        return "Pantalon{" +
                "conCremallera=" + conCremallera +
                ", " + super.toString() +
                '}';
    }
}
