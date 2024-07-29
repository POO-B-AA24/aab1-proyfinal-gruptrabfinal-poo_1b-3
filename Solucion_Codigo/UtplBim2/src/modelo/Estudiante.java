package modelo;
import java.io.Serializable;
public class Estudiante extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private String credenciales;
    private String contrasena;
    private String periodo;

    public Estudiante(String nombres, String apellidos, String cedula, String credenciales, String contrasena, String periodo) {
        super(nombres, apellidos, cedula);
        this.credenciales = credenciales;
        this.contrasena = contrasena;
        this.periodo = periodo;
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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return super.toString() + ", Credenciales: " + credenciales + ", Periodo: " + periodo;
    }
}
