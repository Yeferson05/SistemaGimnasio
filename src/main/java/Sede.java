public class Sede {
    public String nombre;
    public String direccion;
    public String ciudad;

    public Sede(String nombre, String ciudad, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;

    }

    @Override
    public String toString() {
        return "Gimnasio: " + "\n" +
                "\t nombre : " + nombre + "\n" +
                "\t ciudad : " + ciudad + "\n" +
                "\t direccion : " + direccion + "\n" +
                "\t ";
    }
}
