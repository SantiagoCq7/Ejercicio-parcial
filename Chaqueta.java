public class Chaqueta extends Componente {
    // Atributos
    private int numBotones;

    // Constructor
    public Chaqueta(int id, String nombre, String talla, String color, boolean esComunitario, double precio, int numBotones) {
        super(id, nombre, talla, color, esComunitario, precio);
        this.numBotones = numBotones;
    }

    // Getters y Setters
    public int getNumBotones() {
        return numBotones;
    }

    public void setNumBotones(int numBotones) {
        this.numBotones = numBotones;
    }

    // toString
    @Override
    public String toString() {
        return "Chaqueta{" +
                "numBotones=" + numBotones +
                ", " + super.toString() +
                '}';
    }
}
