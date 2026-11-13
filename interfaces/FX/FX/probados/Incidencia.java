package probados;

public class Incidencia {

    private String codigo;
    private String aula;
    private String descripcion;
    private String prioridad;
    private String estado;
    private String tecnico;

    public Incidencia(String codigo, String aula, String descripcion,
                      String prioridad, String estado, String tecnico) {

        this.codigo = codigo;
        this.aula = aula;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.tecnico = tecnico;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getAula() {
        return aula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }
}