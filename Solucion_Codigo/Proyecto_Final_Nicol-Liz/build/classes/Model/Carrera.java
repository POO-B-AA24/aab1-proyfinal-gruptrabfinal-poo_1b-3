
package Model;
import java.io.Serializable;

public class Carrera implements Serializable {
    private String nombre;
    private int cupo;
    private double puntajeMinimo;
    private Postulante[] listaPostulantes;
    private int numPostulantes;

    public Carrera(String nombre, int cupo, double puntajeMinimo) {
        this.nombre = nombre;
        this.cupo = cupo;
        this.puntajeMinimo = puntajeMinimo;
        this.listaPostulantes = new Postulante[cupo];
        this.numPostulantes = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public double getPuntajeMinimo() {
        return puntajeMinimo;
    }

    public void setPuntajeMinimo(double puntajeMinimo) {
        this.puntajeMinimo = puntajeMinimo;
    }

    public Postulante[] getListaPostulantes() {
        return listaPostulantes;
    }

    public void setListaPostulantes(Postulante[] listaPostulantes) {
        this.listaPostulantes = listaPostulantes;
    }

    public int getNumPostulantes() {
        return numPostulantes;
    }

    public void setNumPostulantes(int numPostulantes) {
        this.numPostulantes = numPostulantes;
    }
    public void añadirPostulante(Postulante postulante) {
        if (numPostulantes < cupo) {
            listaPostulantes[numPostulantes++] = postulante;
        }
    }
}