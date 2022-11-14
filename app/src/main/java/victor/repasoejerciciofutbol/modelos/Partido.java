package victor.repasoejerciciofutbol.modelos;

import java.io.Serializable;

public class Partido implements Serializable {
    private String equipoLocal;
    private String equipoVisitante;
    private int golesEquipoLocal;
    private int golesEquipoVisitante;
    private String descripcion;

    public Partido() {

    }

    public Partido(String equipoLocal, String equipoVisitante, int golesEquipoLocal,
                   int golesEquipoVisitante, String descripcion) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesEquipoLocal = golesEquipoLocal;
        this.golesEquipoVisitante = golesEquipoVisitante;
        this.descripcion = descripcion;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getGolesEquipoLocal() {
        return golesEquipoLocal;
    }

    public void setGolesEquipoLocal(int golesEquipoLocal) {
        this.golesEquipoLocal = golesEquipoLocal;
    }

    public int getGolesEquipoVisitante() {
        return golesEquipoVisitante;
    }

    public void setGolesEquipoVisitante(int golesEquipoVisitante) {
        this.golesEquipoVisitante = golesEquipoVisitante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "equipoLocal='" + equipoLocal + '\'' +
                ", equipoVisitante='" + equipoVisitante + '\'' +
                ", golesEquipoLocal=" + golesEquipoLocal +
                ", golesEquipoVisitante=" + golesEquipoVisitante +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
