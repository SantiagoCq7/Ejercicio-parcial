import java.util.ArrayList;

public class Traje {
    // Atributos
    private ArrayList<Componente> piezas;
    private String nombre;
    private ArrayList<Componente> precio;

    // Constructor
    public Traje(String nombre) {
        this.nombre = nombre;
        this.piezas = new ArrayList<>();
    }

    // Getters
    public ArrayList<Componente> getPiezas() {
        return piezas;
    }

    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setPiezas(ArrayList<Componente> piezas) {
        this.piezas = piezas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // toString
    @Override
    public String toString() {
        return "Traje{" +
                "nombre='" + nombre + '\'' +
                ", piezas=" + piezas +
                '}';
    }


    public void setPrecio(ArrayList<Componente> precio) {
        this.precio = precio;
    }




}
