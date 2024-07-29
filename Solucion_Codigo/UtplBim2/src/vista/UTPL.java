package vista;
import controlador.ControladorUTPL;
import java.util.Scanner;

public class UTPL {

    public static void main(String[] args) {
        ControladorUTPL controlador = new ControladorUTPL();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Bienvenido al sistema de gestión de UTPL");
            System.out.println("Seleccione su rol:");
            System.out.println("1. Postulante");
            System.out.println("2. Estudiante");
            System.out.println("3. Administrador");
            System.out.println("4. Salir");
            int rol = scanner.nextInt();

            switch (rol) {
                case 1:
                    controlador.gestionarPostulante(scanner);
                    break;
                case 2:
                    controlador.gestionarEstudiante(scanner);
                    break;
                case 3:
                    controlador.gestionarAdministrador(scanner);
                    break;
                case 4:
                    continuar = false;
                    controlador.guardarDatos();  // Guardar los datos al salir
                    System.out.println("Gracias por usar el sistema de gestión de UTPL. ¡Adiós!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }
}
