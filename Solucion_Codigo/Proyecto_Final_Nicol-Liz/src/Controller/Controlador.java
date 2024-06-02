package Controller;
import Model.*;
import java.io.IOException;
public class Controlador {
    private SistemaAdmision sistemaAdmision;

    public Controlador(SistemaAdmision sistemaAdmision) {
        this.sistemaAdmision = sistemaAdmision;
    }

    public void añadirCarrera(String nombre, int cupo, double puntajeMinimo) {
        Carrera carrera = new Carrera(nombre, cupo, puntajeMinimo);
        sistemaAdmision.añadirCarrera(carrera);
    }

    public void añadirPostulante(String nombre, String apellido, String carrera, String merito, double nota, int fechaExamen) {
        Postulante postulante = new Postulante(nombre, apellido, carrera, merito, nota, fechaExamen);
        sistemaAdmision.añadirPostulante(postulante);
    }
    public void procesarResultados() {
        sistemaAdmision.procesarResultados();
    }

    public void guardarDatos(String archivo) throws IOException {
        sistemaAdmision.guardarDatos(archivo);
    }

    public void cargarDatos(String archivo) throws IOException, ClassNotFoundException {
        sistemaAdmision = SistemaAdmision.cargarDatos(archivo);
    }

    public SistemaAdmision getSistemaAdmision() {
        return sistemaAdmision;
    }
}