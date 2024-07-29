package modelo;

public class ModalidadPresencial extends Modalidad {
    private static final long serialVersionUID = 1L;

    public ModalidadPresencial() {
        super("Presencial");
    }

    @Override
    public String descripcion() {
        return "La modalidad presencial requiere la asistencia física a las aulas de clase.";
    }
}

