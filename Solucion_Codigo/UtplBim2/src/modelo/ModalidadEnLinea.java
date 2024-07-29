package modelo;

public class ModalidadEnLinea extends Modalidad {
    private static final long serialVersionUID = 1L;

    public ModalidadEnLinea() {
        super("En línea");
    }

    @Override
    public String descripcion() {
        return "La modalidad en línea permite asistir a las clases de manera virtual.";
    }
}