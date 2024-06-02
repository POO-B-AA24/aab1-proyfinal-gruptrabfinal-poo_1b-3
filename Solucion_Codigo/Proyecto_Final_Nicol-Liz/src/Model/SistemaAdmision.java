package Model;

import java.io.*;

public class SistemaAdmision implements Serializable {

    private Carrera[] listaCarreras;
    private Postulante[] listaPostulantes;
    private int numCarreras;
    private int numPostulantes;
    //Esto se maneja en el main xd
    public SistemaAdmision(int maxCarreras, int maxPostulantes) {
        this.listaCarreras = new Carrera[maxCarreras];
        this.listaPostulantes = new Postulante[maxPostulantes];
        this.numCarreras = 0;
        this.numPostulantes = 0;
    }

    public Carrera[] getListaCarreras() {
        return listaCarreras;
    }

    public void setListaCarreras(Carrera[] listaCarreras) {
        this.listaCarreras = listaCarreras;
    }

    public Postulante[] getListaPostulantes() {
        return listaPostulantes;
    }

    public void setListaPostulantes(Postulante[] listaPostulantes) {
        this.listaPostulantes = listaPostulantes;
    }

    public int getNumCarreras() {
        return numCarreras;
    }

    public void setNumCarreras(int numCarreras) {
        this.numCarreras = numCarreras;
    }

    public int getNumPostulantes() {
        return numPostulantes;
    }

    public void setNumPostulantes(int numPostulantes) {
        this.numPostulantes = numPostulantes;
    }

    public void añadirCarrera(Carrera carrera) {
        if (numCarreras < listaCarreras.length) {
            listaCarreras[numCarreras++] = carrera;
        }
    }

    public void añadirPostulante(Postulante postulante) {
        if (numPostulantes < listaPostulantes.length) {
            listaPostulantes[numPostulantes++] = postulante;
        }
    }

    public void procesarResultados() {
        for (int i = 0; i < numCarreras; i++) {
            Carrera carrera = listaCarreras[i];
            Postulante[] admitidos = new Postulante[carrera.getCupo()];
            int numAdmitidos = 0;
            for (int j = 0; j < numPostulantes; j++) {
                Postulante postulante = listaPostulantes[j];
                if (postulante.getCarrera().equals(carrera.getNombre()) && postulante.getNota() >= carrera.getPuntajeMinimo()) {
                    admitidos[numAdmitidos++] = postulante;
                }
            }
            // Ordenar y seleccionar admitidos
            for (int k = 0; k < numAdmitidos - 1; k++) {
                for (int l = k + 1; l < numAdmitidos; l++) {
                    if (admitidos[k].getNota() < admitidos[l].getNota()) {
                        Postulante temp = admitidos[k];
                        admitidos[k] = admitidos[l];
                        admitidos[l] = temp;
                    }
                }
            }
            for (int k = 0; k < carrera.getCupo() && k < numAdmitidos; k++) {
                admitidos[k].setResultado("Admitido");
                carrera.añadirPostulante(admitidos[k]);
            }
        }
    }

    public void guardarDatos(String archivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            out.writeObject(this);
        }
    }

    public static SistemaAdmision cargarDatos(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
            return (SistemaAdmision) in.readObject();
        }
    }
}
