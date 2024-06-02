package View;

import Controller.Controlador;
import Model.Postulante;
import Model.Carrera;
import Model.SistemaAdmision;
import java.io.IOException;
import java.util.Scanner;

public class Ejecutor {

    private Controlador controlador;
    //Variable de tipo Controlado que esta en Controller 
    private Scanner sc;
    //Scanner 
    //Constructor generado con el alt insert
    public Ejecutor(Controlador controlador) {
        this.controlador = controlador;
        this.sc = new Scanner(System.in);
    }
    //Simulacion de Menu de la interfaz solo para esto se ocupo el Scanner en lo demas es quema de datos
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("1. Añadir Carrera");
            System.out.println("2. Añadir Postulante");
            System.out.println("3. Procesar Resultados");
            System.out.println("4. Guardar Datos");
            System.out.println("5. Cargar Datos");
            System.out.println("6. Mostrar Resultados");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    añadirCarrera();
                    break;
                case 2:
                    añadirPostulante();
                    break;
                case 3:
                    controlador.procesarResultados();
                    break;
                case 4:
                    guardarDatos();
                    break;
                case 5:
                    cargarDatos();
                    break;
                case 6:
                    mostrarResultados();
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private void añadirCarrera() {
        String[] nombresCarreras = {"Computacion", "Fisioterapia", "Ing Civil", "Arquitectura", "Gastronomia", "Administracion de empresas", "Agropecuaria", " Alimentos", "Artes Escenicas", "Artes Visuales", "Biologia", "Contabilidad y Autitoria", "Derecho", "Economia", "Finanzas", "Geologia", "Ingenieria Ambiental", "Ingenieria Industrial", "Ingenieria Quimica", "Pedagogia de los idiomas nacionales y extranjeros", "Psicopedagogia", "Telecomunicaciones", "Nutricion y Dietetica", "Psicologia", "Psicologia Clinica", "Medicina", "Bioquimica y Farmacia", "Enfermeria"};
        int[] cupos = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
        double[] puntajesMinimos = {70.0, 70.0, 75.0, 70.0, 65.0, 75.0, 80.0, 79.0, 89.0, 66.0, 70.0, 75.0, 89.0, 90.0, 88.0, 86.0, 90.0, 97.0, 99.0, 100.0, 99.0, 95.0, 77.0, 65.0, 88.0, 74.0, 77.0, 95.0};
        // length es la longitud 
        for (int i = 0; i < nombresCarreras.length; i++) {
            controlador.añadirCarrera(nombresCarreras[i], cupos[i], puntajesMinimos[i]);
        }

        System.out.println("Carreras añadidas automáticamente.");
    }
    //Quema de datos ingual que en anadir 
    private void añadirPostulante() {
        // Datos predeterminados
        String[] nombres = {"Alejandro", "Beatriz", "Carlos", "Diana", "Eduardo", "Fernanda",
            "Gabriel", "Hilda", "Ignacio", "Julia", "Kevin", "Laura",
            "Manuel", "Natalia", "Oscar", "Patricia", "Ricardo", "Sofía",
            "Tomás", "Ursula", "Vicente", "Wendy", "Ximena", "Yolanda",
            "Zacarías", "Ana", "Beto", "Cecilia", "Diego", "Elena"};
        String[] apellidos = {"García", "Martínez", "Rodríguez", "López", "Hernández", "Pérez",
            "González", "Sánchez", "Ramírez", "Torres", "Flores", "Rivera",
            "Gómez", "Díaz", "Álvarez", "Vázquez", "Cruz", "Morales",
            "Ortiz", "Gutiérrez", "Chávez", "Ramos", "Reyes", "Ruiz",
            "Molina", "Castro", "Romero", "Mendoza", "Silva", "Delgado"};
        String[] carreras = {"Computacion", "Fisioterapia", "Computacion", "Fisioterapia", "Ing Civil", "Arquitectura", "Gastronomia", "Administracion de empresas", "Agropecuaria", " Alimentos", "Artes Escenicas", "Artes Visuales", "Biologia", "Contabilidad y Autitoria", "Derecho", "Economia", "Finanzas", "Geologia", "Ingenieria Ambiental", "Ingenieria Industrial", "Ingenieria Quimica", "Pedagogia de los idiomas nacionales y extranjeros", "Psicopedagogia", "Telecomunicaciones", "Nutricion y Dietetica", "Psicologia", "Psicologia Clinica", "Medicina", "Bioquimica y Farmacia", "Enfermeria"};
        String[] meritos = {"Abanderado", "Bachillerato Afin", "Capacidad Especial", "Sin Merito", "Abanderado", "Abanderado", "Bachillerato Afin", "Capacidad Especial", "Sin Merito", "Abanderado", "Abanderado", "Bachillerato Afin", "Capacidad Especial", "Sin Merito", "Abanderado", "Abanderado", "Bachillerato Afin", "Capacidad Especial", "Sin Merito", "Abanderado", "Abanderado", "Bachillerato Afin", "Capacidad Especial", "Sin Merito", "Abanderado", "Abanderado", "Bachillerato Afin", "Capacidad Especial", "Sin Merito", "Abanderado"};
        double[] notas = {85.0, 75.0, 90.0, 60.0, 80.0, 89.0, 85.0, 75.0, 90.0, 60.0, 80.0, 89.0, 85.0, 75.0, 90.0, 60.0, 80.0, 89.0, 85.0, 75.0, 90.0, 64.0, 85.0, 99.0, 85.0, 75.0, 70.0, 60.0, 80.0, 92.0,};
        int[] fechasExamen = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        for (int i = 0; i < nombres.length; i++) {
            controlador.añadirPostulante(nombres[i], apellidos[i], carreras[i], meritos[i], notas[i], fechasExamen[i]);
        }

        System.out.println("Postulantes añadidos automáticamente.");
    }

    private void guardarDatos() {
        System.out.print("Nombre del archivo: ");
        String archivo = sc.nextLine();
        try {
            controlador.guardarDatos(archivo);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    private void cargarDatos() {
        System.out.print("Nombre del archivo: ");
        String archivo = sc.nextLine();
        try {
            controlador.cargarDatos(archivo);
            System.out.println("Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    private void mostrarResultados() {
        for (Carrera carrera : controlador.getSistemaAdmision().getListaCarreras()) {
            if (carrera != null) {
                System.out.println("Carrera: " + carrera.getNombre());
                if (carrera.getNumPostulantes() < 1) {
                    System.out.println("No existen cupos para esta carrera");
                } else {
                    for (int i = 0; i < carrera.getNumPostulantes(); i++) {
                        Postulante postulante = carrera.getListaPostulantes()[i];
                        System.out.println(postulante.getNombre() + " " + postulante.getApellido() + " - " + postulante.getResultado());
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        //28 carreras 30 cupos es el parametro
        SistemaAdmision sistemaAdmision = new SistemaAdmision(28, 30);
        //Nombre de clase el nombre de la variable 
        Controlador controlador = new Controlador(sistemaAdmision);
        Ejecutor ejecutor = new Ejecutor(controlador);
        ejecutor.mostrarMenu();
    }
}
