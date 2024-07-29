package modelo;
import java.io.Serializable;
abstract public class Modalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;

    public Modalidad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract String descripcion();
}
