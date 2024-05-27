import java.util.Objects;

public class Componente implements Comparable<Componente> {
    // Atributos
    private int id;
    private String nombre;
    private String talla;
    private String color;
    private boolean esComunitario;
    private double precio;

    // Constructor
    public Componente(int id, String nombre, String talla, String color, boolean esComunitario, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.talla = talla;
        this.color = color;
        this.esComunitario = esComunitario;
        this.precio = precio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isEsComunitario() {
        return esComunitario;
    }

    public void setEsComunitario(boolean esComunitario) {
        this.esComunitario = esComunitario;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // toString
    @Override
    public String toString() {
        return "Componente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", talla='" + talla + '\'' +
                ", color='" + color + '\'' +
                ", esComunitario=" + esComunitario +
                ", precio=" + precio +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Componente that = (Componente) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // compareTo
    @Override
    public int compareTo(Componente o) {
        return Integer.compare(this.id, o.id);
    }

    public boolean isEscomunitario() {
        return false;
    }
}


