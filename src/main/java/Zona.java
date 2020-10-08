import java.util.ArrayList;

public class Zona {


    public ArrayList<Entrenadores> misEntrenadores = new ArrayList<>();
    public ArrayList <Rutinas> misRutinas = new ArrayList<>();


    public String zona1;
    public String zona2;
    public String zona3;
    public String zona4;
    public String zona5;

    public Zona( String zona1, String zona2, String zona3, String zona4,String zona5) {

        this.zona1=zona1;
        this.zona2=zona2;
        this.zona3=zona3;
        this.zona4=zona4;
        this.zona5=zona5;

        misEntrenadores = new ArrayList<>();
        misRutinas = new ArrayList<>();

    }

    @Override
    public String toString() {
        return "Zona{" +
                "misEntrenadores=" + misEntrenadores +
                ", misRutinas=" + misRutinas +
                ", zona1='" + zona1 + '\'' +
                ", zona2='" + zona2 + '\'' +
                ", zona3='" + zona3 + '\'' +
                ", zona4='" + zona4 + '\'' +
                ", zona5='" + zona5 + '\'' +
                '}';
    }
}