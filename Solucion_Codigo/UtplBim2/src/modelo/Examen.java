package modelo;
import java.io.Serializable;

abstract class Examen implements Serializable {
    private static final long serialVersionUID = 1L;
    private int puntaje;
    private String carrera;

    public Examen(int puntaje, String carrera) {
        this.puntaje = puntaje;
        this.carrera = carrera;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public abstract int puntajeAdicional();

    public abstract String descripcion();
}
