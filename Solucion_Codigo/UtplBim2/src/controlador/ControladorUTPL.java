package controlador;
import modelo.ModalidadEnLinea;
import modelo.Materia;
import modelo.ExamenAdmision;
import modelo.ModalidadPresencial;
import modelo.ExamenDiagnostico;
import modelo.Administrador;
import modelo.Modalidad;
import modelo.Postulante;
import modelo.ModalidadADistancia;
import modelo.Estudiante;
import java.io.*;
import java.util.*;

public class ControladorUTPL {
    private List<Materia> materiasDisponibles;
    private List<Estudiante> estudiantes;
    private List<Postulante> postulantes;
    private String estudiante_file = "estudiantes.ser";
    private String postulante_file = "postulantes.ser";
    private int cupos_max = 100;

    public ControladorUTPL() {
        this.materiasDisponibles = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
        this.postulantes = new ArrayList<>();
        inicializarMaterias();
        cargarDatos();
    }

    public void inicializarMaterias() {
        materiasDisponibles.add(new Materia("Matemáticas Básicas", "MAT101"));
        materiasDisponibles.add(new Materia("Programación I", "PRO101"));
        materiasDisponibles.add(new Materia("Química General", "QUI101"));
        materiasDisponibles.add(new Materia("Física I", "FIS101"));
    }

    public void cargarDatos() {
        cargarEstudiantes();
        cargarPostulantes();
    }

    public void cargarEstudiantes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(estudiante_file))) {
            estudiantes = (List<Estudiante>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar el archivo de estudiantes.");
        }
    }

    public void cargarPostulantes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(postulante_file))) {
            postulantes = (List<Postulante>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar el archivo de postulantes.");
        }
    }

    public void guardarDatos() {
        guardarEstudiantes();
        guardarPostulantes();
    }

    public void guardarEstudiantes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(estudiante_file))) {
            oos.writeObject(estudiantes);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo de estudiantes.");
        }
    }

    public void guardarPostulantes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(postulante_file))) {
            oos.writeObject(postulantes);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo de postulantes.");
        }
    }

    public void gestionarPostulante(Scanner scanner) {
        System.out.println("Seleccione el periodo en el que desea matricularse:");
        System.out.println("1. Octubre 2024 - Febrero 2025");
        System.out.println("2. Abril 2025 - Julio 2025");
        int periodoSeleccionado = scanner.nextInt();
        String periodo = (periodoSeleccionado == 1) ? "Octubre 2024 - Febrero 2025" : "Abril 2025 - Julio 2025";

        scanner.nextLine();  // Limpiar buffer del scanner
        System.out.println("Ingrese sus nombres:");
        String nombres = scanner.nextLine();
        System.out.println("Ingrese sus apellidos:");
        String apellidos = scanner.nextLine();
        System.out.println("Ingrese su número de cédula:");
        String cedula = scanner.nextLine();

        System.out.println("¿Es abanderado? (si/no)");
        boolean abanderado = scanner.nextLine().equalsIgnoreCase("si");
        System.out.println("¿Tiene bachillerato afín a la carrera que postula? (si/no)");
        boolean bachillerAfines = scanner.nextLine().equalsIgnoreCase("si");
        System.out.println("¿Tiene alguna capacidad especial mayor al 35%? (si/no)");
        boolean capacidadEspecial = scanner.nextLine().equalsIgnoreCase("si");

        Postulante postulante = new Postulante(nombres, apellidos, cedula, periodo, abanderado, bachillerAfines, capacidadEspecial);
        postulantes.add(postulante);

        seleccionarModalidad(postulante, scanner);
        generarPuntaje(postulante);

        String carreraAdmitida = validarAdmisión(postulante);
        if (carreraAdmitida != null) {
            generarCredenciales(postulante);
            System.out.println("¡Felicitaciones Jaguar! Has sido admitido en la siguiente carrera:");
            System.out.println("- " + carreraAdmitida);
            System.out.println("Tus credenciales son: " + postulante.getCredenciales());

            Estudiante estudiante = new Estudiante(postulante.getNombres(), postulante.getApellidos(), postulante.getCedula(), postulante.getCredenciales(), postulante.getContrasena(), postulante.getPeriodo());
            estudiantes.add(estudiante);
        } else {
            System.out.println("Lamentablemente no has sido admitido en ninguna de las carreras seleccionadas.");
        }

        System.out.println("Inscripción completada.");
    }

    public void gestionarEstudiante(Scanner scanner) {
        scanner.nextLine();  // Limpiar buffer del scanner
        System.out.println("Ingrese sus credenciales para acceder:");
        System.out.println("Correo:");
        String correo = scanner.nextLine();
        System.out.println("Contraseña:");
        String contrasena = scanner.nextLine();

        boolean encontrado = false;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCredenciales().equals(correo) && estudiante.getContrasena().equals(contrasena)) {
                encontrado = true;
                boolean menuEstudiante = true;
                while (menuEstudiante) {
                    System.out.println("Bienvenido, " + estudiante.getNombres());
                    System.out.println("Seleccione una opción:");
                    System.out.println("1. Ver credenciales");
                    System.out.println("2. Ver datos personales");
                    System.out.println("3. Ver y matricularse en materias");
                    System.out.println("4. Salir");
                    int opcion = scanner.nextInt();

                    switch (opcion) {
                        case 1:
                            System.out.println("Credenciales: " + estudiante.getCredenciales());
                            break;
                        case 2:
                            System.out.println("Datos personales: " + estudiante);
                            break;
                        case 3:
                            gestionarMaterias(scanner);
                            break;
                        case 4:
                            menuEstudiante = false;
                            break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                            break;
                    }
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Credenciales incorrectas. Intente de nuevo.");
        }
    }

    public void gestionarAdministrador(Scanner scanner) {
        scanner.nextLine();  // Limpiar buffer del scanner
        System.out.println("Ingrese sus credenciales:");
        System.out.println("Nombre de usuario:");
        String usuario = scanner.nextLine();
        System.out.println("Contraseña:");
        String contrasena = scanner.nextLine();

        Administrador admin = new Administrador("Liz Anahí", "González", "1234567890", contrasena);

        if (usuario.equals("lagonzalez47@utpl.edu.ec") && contrasena.equals("231020")) {
            boolean menuAdministrador = true;
            while (menuAdministrador) {
                System.out.println("Bienvenido, " + admin.getNombres());
                System.out.println("Seleccione una opción:");
                System.out.println("1. Ver todos los estudiantes");
                System.out.println("2. Ver todos los postulantes");
                System.out.println("3. Generar reportes");
                System.out.println("4. Salir");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        verEstudiantes();
                        break;
                    case 2:
                        verPostulantes();
                        break;
                    case 3:
                        generarReportes();
                        break;
                    case 4:
                        menuAdministrador = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            }
        } else {
            System.out.println("Credenciales incorrectas. Intente de nuevo.");
        }
    }

    public void verEstudiantes() {
        System.out.println("Lista de Estudiantes:");
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante);
        }
    }

    public void verPostulantes() {
        System.out.println("Lista de Postulantes:");
        for (Postulante postulante : postulantes) {
            System.out.println(postulante);
        }
    }

    public void generarReportes() {
        System.out.println("Generando reportes...");
        
        Map<String, Integer> cuposPorCarrera = new HashMap<>();
        for (Estudiante estudiante : estudiantes) {
            cuposPorCarrera.put(estudiante.getPeriodo(), cuposPorCarrera.getOrDefault(estudiante.getPeriodo(), 0) + 1);
        }
        
        System.out.println("Carreras que no han logrado captar ni el 50% de los cupos:");
        for (Map.Entry<String, Integer> entry : cuposPorCarrera.entrySet()) {
            if (entry.getValue() < cupos_max / 2) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " cupos ocupados de " + cupos_max);
            }
        }
        
        System.out.println("Carreras que han tenido que rechazar postulantes por falta de cupos:");
        for (Postulante postulante : postulantes) {
            String carreraAdmitida = validarAdmisión(postulante);
            if (carreraAdmitida != null && cuposPorCarrera.getOrDefault(carreraAdmitida, 0) >= cupos_max) {
                System.out.println(carreraAdmitida + " ha rechazado postulantes.");
            }
        }
    }

    public void seleccionarModalidad(Postulante postulante, Scanner scanner) {
        System.out.println("Seleccione la modalidad de estudio:");
        System.out.println("1. Presencial");
        System.out.println("2. En línea");
        System.out.println("3. A distancia");
        int modalidadSeleccionada = scanner.nextInt();

        Modalidad modalidad;
        switch (modalidadSeleccionada) {
            case 1:
                modalidad = new ModalidadPresencial();
                break;
            case 2:
                modalidad = new ModalidadEnLinea();
                break;
            case 3:
                modalidad = new ModalidadADistancia();
                break;
            default:
                System.out.println("Opción no válida. Seleccionando modalidad Presencial por defecto.");
                modalidad = new ModalidadPresencial();
                break;
        }

        System.out.println("Modalidad seleccionada: " + modalidad.getNombre());
        System.out.println(modalidad.descripcion());

        seleccionarCarreras(postulante, scanner);
    }

    public void seleccionarCarreras(Postulante postulante, Scanner scanner) {
    List<String> facultades = new ArrayList<>();
    facultades.add("Facultad de Ciencias de la Salud");
    facultades.add("Facultad de Ciencias Económicas y Empresariales");
    facultades.add("Facultad de Ciencias Exactas y Naturales");
    facultades.add("Facultad de Ciencias Sociales, Educación y Humanidades");
    facultades.add("Facultad de Ciencias Jurídicas y Políticas");
    facultades.add("Facultad de Ingenierías y Arquitectura");

    List<String> carrerasSeleccionadas = new ArrayList<>();

    for (int i = 1; i <= 2; i++) {
        System.out.println("Seleccione la facultad para la carrera " + i + ":");
        for (int j = 0; j < facultades.size(); j++) {
            System.out.println((j + 1) + ". " + facultades.get(j));
        }
        int facultadSeleccionada = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer

        String facultad = facultades.get(facultadSeleccionada - 1);
        System.out.println("Facultad seleccionada: " + facultad);

        System.out.println("Seleccione la carrera:");
        List<String> carreras = obtenerCarrerasPorFacultad(facultad);
        for (int j = 0; j < carreras.size(); j++) {
            System.out.println((j + 1) + ". " + carreras.get(j));
        }
        int carreraSeleccionada = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer

        String carrera = carreras.get(carreraSeleccionada - 1);
        carrerasSeleccionadas.add(carrera);
        System.out.println("Carrera seleccionada: " + carrera);

        if (i == 1) {
            System.out.println("¿Desea seleccionar la segunda carrera de la misma facultad? (si/no)");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("si")) {
                facultades.remove(facultad);
            }
        }
    }

    postulante.setCarrerasSeleccionadas(carrerasSeleccionadas);
    System.out.println("Carreras seleccionadas: " + carrerasSeleccionadas);
}

public List<String> obtenerCarrerasPorFacultad(String facultad) {
    List<String> carreras = new ArrayList<>();
    switch (facultad) {
        case "Facultad de Ciencias de la Salud":
            carreras.add("Medicina");
            carreras.add("Enfermería");
            carreras.add("Psicología");
            carreras.add("Nutrición y Dietética");
            carreras.add("Bioquímica y Farmacia");
            carreras.add("Fisioterapia");
            break;
        case "Facultad de Ciencias Económicas y Empresariales":
            carreras.add("Administración de Empresas");
            carreras.add("Economía");
            carreras.add("Contabilidad y Auditoría");
            carreras.add("Finanzas");
            carreras.add("Marketing");
            carreras.add("Turismo");
            break;
        case "Facultad de Ciencias Exactas y Naturales":
            carreras.add("Biología");
            carreras.add("Ingeniería en Geología");
            carreras.add("Ingeniería Química");
            carreras.add("Ingeniería Ambiental");
            carreras.add("Matemáticas");
            carreras.add("Ciencias de la Computación");
            break;
        case "Facultad de Ciencias Sociales, Educación y Humanidades":
            carreras.add("Derecho");
            carreras.add("Comunicación");
            carreras.add("Educación Básica");
            carreras.add("Educación Inicial");
            carreras.add("Psicopedagogía");
            carreras.add("Pedagogía de la Lengua y Literatura");
            break;
        case "Facultad de Ciencias Jurídicas y Políticas":
            carreras.add("Derecho");
            carreras.add("Ciencias Políticas");
            carreras.add("Relaciones Internacionales");
            break;
        case "Facultad de Ingenierías y Arquitectura":
            carreras.add("Ingeniería Civil");
            carreras.add("Ingeniería Industrial");
            carreras.add("Ingeniería en Sistemas Computacionales");
            carreras.add("Ingeniería en Electrónica y Telecomunicaciones");
            carreras.add("Arquitectura");
            break;
    }
    return carreras;
}

    public void generarPuntaje(Postulante postulante) {
        Random rand = new Random();
        List<ExamenAdmision> examenesAdmision = new ArrayList<>();
        List<ExamenDiagnostico> examenesDiagnostico = new ArrayList<>();

        for (String carrera : postulante.getCarrerasSeleccionadas()) {
            int puntaje = rand.nextInt(66) + 35; // Puntaje entre 35 y 100
            if (esCarreraDeAltaDemanda(carrera)) {
                puntaje += calcularPuntajeAdicional(postulante);
                ExamenAdmision examen = new ExamenAdmision(puntaje, carrera);
                examenesAdmision.add(examen);
                System.out.println(examen.descripcion() + " Puntaje: " + examen.getPuntaje() + ", Puntaje adicional: " + examen.puntajeAdicional());
            } else {
                ExamenDiagnostico examen = new ExamenDiagnostico(puntaje, carrera);
                examenesDiagnostico.add(examen);
                System.out.println(examen.descripcion() + " Puntaje: " + examen.getPuntaje() + ", Puntaje adicional: " + examen.puntajeAdicional());
            }
        }

        postulante.setExamenesAdmision(examenesAdmision);
        postulante.setExamenesDiagnostico(examenesDiagnostico);

        System.out.println("Puntajes generados:");
        for (ExamenAdmision examen : examenesAdmision) {
            System.out.println("Carrera: " + examen.getCarrera() + ", Puntaje: " + examen.getPuntaje());
        }
        for (ExamenDiagnostico examen : examenesDiagnostico) {
            System.out.println("Carrera: " + examen.getCarrera() + ", Puntaje: " + examen.getPuntaje());
        }
    }

    public int calcularPuntajeAdicional(Postulante postulante) {
        int puntajeAdicional = 0;
        if (postulante.isAbanderado()) {
            puntajeAdicional += 5;
        }
        if (postulante.isBachillerAfines()) {
            puntajeAdicional += 2;
        }
        if (postulante.isCapacidadEspecial()) {
            puntajeAdicional += 3;
        }
        return puntajeAdicional;
    }

    public String validarAdmisión(Postulante postulante) {
        for (ExamenAdmision examen : postulante.getExamenesAdmision()) {
            if (examen.getPuntaje() >= obtenerPuntajeMinimo(examen.getCarrera())) {
                return examen.getCarrera();
            }
        }
        for (ExamenDiagnostico examen : postulante.getExamenesDiagnostico()) {
            if (examen.getPuntaje() >= 35) { // Supuesto puntaje mínimo para examen diagnóstico
                return examen.getCarrera();
            }
        }
        return null;
    }

    public int obtenerPuntajeMinimo(String carrera) {
        switch (carrera) {
            case "Medicina":
                return 90;
            case "Enfermería":
            case "Psicología":
            case "Nutrición y Dietética":
            case "Bioquímica y Farmacia":
            case "Fisioterapia":
                return 75;
            case "Ingeniería Civil":
            case "Arquitectura":
                return 85;
            case "Ingeniería en Sistemas Computacionales":
            case "Ingeniería Industrial":
            case "Ingeniería en Electrónica y Telecomunicaciones":
                return 75;
            default:
                return 60;
        }
    }

    public boolean esCarreraDeAltaDemanda(String carrera) {
        switch (carrera) {
            case "Medicina":
            case "Ingeniería Civil":
            case "Arquitectura":
                return true;
            default:
                return false;
        }
    }

    public void generarCredenciales(Postulante postulante) {
        String correo = generarCorreo(postulante.getNombres(), postulante.getApellidos());
        String contrasena = generarContrasena();
        postulante.setCredenciales(correo + "@" + "utpl.edu.ec");
        postulante.setContrasena(contrasena);

        System.out.println("Credenciales generadas:");
        System.out.println("Correo: " + postulante.getCredenciales());
        System.out.println("Contraseña: " + postulante.getContrasena());
    }

    public String generarCorreo(String nombres, String apellidos) {
        String[] nombresArray = nombres.split(" ");
        StringBuilder iniciales = new StringBuilder();
        for (String nombre : nombresArray) {
            iniciales.append(nombre.charAt(0));
        }
        String[] apellidosArray = apellidos.split(" ");
        String apellidoPrincipal = apellidosArray[0].toLowerCase();
        return iniciales.toString().toLowerCase() + apellidoPrincipal + new Random().nextInt(100);
    }

    public String generarContrasena() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder contrasena = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            contrasena.append(caracteres.charAt(rand.nextInt(caracteres.length())));
        }
        return contrasena.toString();
    }

    public void gestionarMaterias(Scanner scanner) {
        System.out.println("Materias disponibles:");
        for (int i = 0; i < materiasDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + materiasDisponibles.get(i));
        }
        System.out.println("Seleccione el número de la materia para matricularse o 0 para salir:");
        int seleccion = scanner.nextInt();

        if (seleccion > 0 && seleccion <= materiasDisponibles.size()) {
            Materia materiaSeleccionada = materiasDisponibles.get(seleccion - 1);
            System.out.println("Te has matriculado en: " + materiaSeleccionada);
        } else {
            System.out.println("Saliendo de la gestión de materias.");
        }
    }
}
