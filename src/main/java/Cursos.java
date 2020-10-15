import java.util.ArrayList;

public class Cursos {


    public ArrayList<Zona> misZonas = new ArrayList<>();
    public ArrayList <Entrenadores> misEntrenadores = new ArrayList<>();
    public ArrayList <Rutinas> misRutinas = new ArrayList<>();


    public String nombre;
    public int codigo;
    public int intesidadHora;

    public Cursos(String nombre, int codigo, int intesidadHora) {

        this.nombre = nombre;
        this.codigo = codigo;
        this.intesidadHora = intesidadHora;

        misZonas = new ArrayList<>();
        misEntrenadores = new ArrayList<>();
        misRutinas = new ArrayList<>();

    }

    @Override
    public String toString() {
        return  "-  Curso:"+nombre+", "+"Codigo:"+codigo+", "+"Intensidad:"+ intesidadHora+"\n"+
                "-  Entrenadores:"+ misEntrenadores + "\n"+
                "-  Rutinas:"+ misRutinas + "\n"+
                "-  Zonas:"+ misZonas;
    }
}
