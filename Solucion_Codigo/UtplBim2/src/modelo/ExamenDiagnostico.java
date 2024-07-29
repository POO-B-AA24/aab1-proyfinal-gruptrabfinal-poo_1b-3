package modelo;
public class ExamenDiagnostico extends Examen {
    private static final long serialVersionUID = 1L;

    public ExamenDiagnostico(int puntaje, String carrera) {
        super(puntaje, carrera);
    }

    @Override
    public int puntajeAdicional() {
        return 2;  // Puntaje adicional fijo para examen diagnóstico
    }

    @Override
    public String descripcion() {
        return "El examen diagnóstico se utiliza para evaluar el nivel de conocimientos previos.";
    }
}