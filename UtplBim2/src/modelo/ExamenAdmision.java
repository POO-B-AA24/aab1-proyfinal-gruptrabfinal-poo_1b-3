package modelo;
public class ExamenAdmision extends Examen {
    private static final long serialVersionUID = 1L;

    public ExamenAdmision(int puntaje, String carrera) {
        super(puntaje, carrera);
    }

    @Override
    public int puntajeAdicional() {
        return 5;  // Puntaje adicional fijo para examen de admisión
    }

    @Override
    public String descripcion() {
        return "El examen de admisión es obligatorio para todas las modalidades presenciales.";
    }
}