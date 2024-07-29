package modelo;
import java.io.Serializable;
import java.util.List;

public class Postulante extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private String periodo;
    private boolean abanderado;
    private boolean bachillerAfines;
    private boolean capacidadEspecial;
    private List<String> carrerasSeleccionadas;
    private List<ExamenAdmision> examenesAdmision;
    private List<ExamenDiagnostico> examenesDiagnostico;
    private String credenciales;
    private String contrasena;

    public Postulante(String nombres, String apellidos, String cedula, String periodo, boolean abanderado, boolean bachillerAfines, boolean capacidadEspecial) {
        super(nombres, apellidos, cedula);
        this.periodo = periodo;
        this.abanderado = abanderado;
        this.bachillerAfines = bachillerAfines;
        this.capacidadEspecial = capacidadEspecial;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public boolean isAbanderado() {
        return abanderado;
    }

    public void setAbanderado(boolean abanderado) {
        this.abanderado = abanderado;
    }

    public boolean isBachillerAfines() {
        return bachillerAfines;
    }

    public void setBachillerAfines(boolean bachillerAfines) {
        this.bachillerAfines = bachillerAfines;
    }

    public boolean isCapacidadEspecial() {
        return capacidadEspecial;
    }

    public void setCapacidadEspecial(boolean capacidadEspecial) {
        this.capacidadEspecial = capacidadEspecial;
    }

    public List<String> getCarrerasSeleccionadas() {
        return carrerasSeleccionadas;
    }

    public void setCarrerasSeleccionadas(List<String> carrerasSeleccionadas) {
        this.carrerasSeleccionadas = carrerasSeleccionadas;
    }

    public List<ExamenAdmision> getExamenesAdmision() {
        return examenesAdmision;
    }

    public void setExamenesAdmision(List<ExamenAdmision> examenesAdmision) {
        this.examenesAdmision = examenesAdmision;
    }

    public List<ExamenDiagnostico> getExamenesDiagnostico() {
        return examenesDiagnostico;
    }

    public void setExamenesDiagnostico(List<ExamenDiagnostico> examenesDiagnostico) {
        this.examenesDiagnostico = examenesDiagnostico;
    }

    public String getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(String credenciales) {
        this.credenciales = credenciales;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return super.toString() + ", Periodo: " + periodo + ", Abanderado: " + abanderado + 
               ", Bachiller Afines: " + bachillerAfines + ", Capacidad Especial: " + capacidadEspecial +
               ", Carreras Seleccionadas: " + carrerasSeleccionadas + ", Credenciales: " + credenciales + 
               ", Contraseña: " + contrasena;
    }
}
