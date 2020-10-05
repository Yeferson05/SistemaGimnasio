public class Gym {
    public int nit;
    public String nombre;
    public String siglas;
    public String direccion;
    public int telefono;

    public Gym(int nit, String nombre, String siglas, String direccion,int telefono) {
        this.nit = nit;
        this.nombre = nombre;
        this.siglas = siglas;
        this.direccion = direccion;
        this.telefono = telefono;

    }

    @Override
    public String toString() {
        return "Gym " + "\n" +
                "\t Nit : " + nit + "\n" +
                "\t nombre : " + nombre + "\n" +
                "\t siglas : " + siglas + "\n" +
                "\t direccion : " + direccion + "\n" +
                "\t telefono : " + telefono + "\n" +
                "\t ";
    }
}
