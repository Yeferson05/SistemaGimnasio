public class Cursos {

    public String nombre;
    public int codigo;
    public int intesidadHora;

    public Cursos(String nombre, int codigo, int intesidadHora) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.intesidadHora = intesidadHora;
    }

    @Override
    public String toString() {
        return "Cursos{" +
                "nombre='" + nombre + '\'' +
                ", codigo=" + codigo +
                ", intesidadHora=" + intesidadHora +
                '}';
    }
}
