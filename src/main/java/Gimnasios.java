import java.util.ArrayList;

public class Gimnasios {


    public ArrayList <Sede> misSedes = new ArrayList<>();
    public ArrayList<Cursos> misCursos = new ArrayList<>();
    public ArrayList <Zona> misZonas = new ArrayList<>();
    public ArrayList <Entrenadores> misEntrenadores = new ArrayList<>();
    public ArrayList <Rutinas> misRutinas = new ArrayList<>();


    public int nit;
    public String nombre;
    public String siglas;
    public String direccion;
    public int telefono;

    public Gimnasios( int nit, String nombre, String siglas, String direccion,int telefono) {

        this.nit = nit;
        this.nombre = nombre;
        this.siglas = siglas;
        this.direccion = direccion;
        this.telefono = telefono;

        misSedes = new ArrayList<>();
        misCursos = new ArrayList<>();
        misZonas = new ArrayList<>();
        misEntrenadores = new ArrayList<>();
        misRutinas = new ArrayList<>();

    }

    @Override
    public String toString() {
        return "Gimnasios{" +
                "misSedes=" + misSedes +
                ", misCursos=" + misCursos +
                ", misZonas=" + misZonas +
                ", misEntrenadores=" + misEntrenadores +
                ", misRutinas=" + misRutinas +
                ", nit=" + nit +
                ", nombre='" + nombre + '\'' +
                ", siglas='" + siglas + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
