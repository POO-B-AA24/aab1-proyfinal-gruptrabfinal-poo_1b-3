package Model;
import java.io.Serializable;

public class Postulante implements Serializable {
    private String nombre;
    private String apellido;
    private String carrera;
    private String merito;
    private double nota;
    private int fechaExamen;
    private String resultado;

    public Postulante(String nombre, String apellido, String carrera, String merito, double nota, int fechaExamen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.merito = merito;
        this.nota = nota;
        this.fechaExamen = fechaExamen;
        this.resultado = "Pendiente";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getMerito() {
        return merito;
    }

    public void setMerito(String merito) {
        this.merito = merito;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(int fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
}
