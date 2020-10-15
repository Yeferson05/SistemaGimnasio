import java.util.ArrayList;


public class Sede {


    public Gimnasios gimnasio;
    public ArrayList <Cursos> misCursos = new ArrayList<>();
    public ArrayList <Zona> misZonas = new ArrayList<>();
    public ArrayList <Entrenadores> misEntrenadores = new ArrayList<>();
    public ArrayList <Rutinas> misRutinas = new ArrayList<>();


    public String nombre;
    public String direccion;
    public String ciudad;

    public Sede( String nombre, String ciudad, String direccion) {

        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;

        misCursos = new ArrayList<>();
        misZonas = new ArrayList<>();
        misEntrenadores = new ArrayList<>();
        misRutinas = new ArrayList<>();

    }

    @Override
    public String toString() {
        return  "-  Sede:"+" "+"Nombre:"+nombre+", "+"Ciudad:"+ciudad+", "+"Dirección:"+direccion+"\n"+
                "-  Zonas:"+misZonas;
    }
}



