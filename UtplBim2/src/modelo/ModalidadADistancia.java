package modelo;



public class ModalidadADistancia extends Modalidad {
    private static final long serialVersionUID = 1L;

    public ModalidadADistancia() {
        super("A distancia");
    }

    @Override
    public String descripcion() {
        return "La modalidad a distancia combina el autoestudio con tutorías periódicas.";
    }
}
