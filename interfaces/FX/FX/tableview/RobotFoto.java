package tableview;
//Robot creado con Alt+Insert Code tras introducir las propiedades privadas
public class RobotFoto extends Robot{
    private String fotoURL;
    private boolean sigueconvida;
    public RobotFoto(String nombre, String peli, String fotoURL, boolean sigueconvida) {
        super(nombre, peli);
        this.fotoURL=fotoURL;
        this.sigueconvida=sigueconvida;
    }
    public boolean isSigueconvida() {
        return sigueconvida;
    }
    public void setSigueconvida(boolean sigueconvida) {
        this.sigueconvida=sigueconvida;
    }
    public String getFotoURL() {
        return fotoURL;
    }
    public void setFotoURL(String fotoURL) {
        this.fotoURL=fotoURL;
    } 
}