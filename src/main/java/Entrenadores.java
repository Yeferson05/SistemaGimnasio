import java.util.ArrayList;

public class Entrenadores {


    public ArrayList<Rutinas> misRutinas = new ArrayList<>();

    public int documento;
    public String nombre;
    public String apellido;
    public String correo;


    public Entrenadores(int documento, String nombre, String apellido, String correoo) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correoo;

        misRutinas = new ArrayList<>();
    }

    public String toString() {
        return "-  Entrenador:"+nombre+", "+"Apellido:"+apellido+", "+"Documento:"+documento+", "+"Correo"+
                correo+"\n"+
                "Rutinas:"+misRutinas;
    }

}