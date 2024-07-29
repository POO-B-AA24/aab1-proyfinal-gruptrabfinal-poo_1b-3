package modelo;
public class Administrador extends Persona {
    private String contrasena;

    public Administrador(String nombres, String apellidos, String cedula, String contrasena) {
        super(nombres, apellidos, cedula);
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
