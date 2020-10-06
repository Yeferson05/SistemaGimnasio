public class Gimnasios {
    public int nit;
    public String nombre;
    public String siglas;
    public String direccion;
    public int telefono;

    public Gimnasios(int nit, String nombre, String siglas, String direccion,int telefono) {
        this.nit = nit;
        this.nombre = nombre;
        this.siglas = siglas;
        this.direccion = direccion;
        this.telefono = telefono;

    }

    @Override
    public String toString() {
        return "-   Gimnasio:    " + "Nombre:"+ nombre +"," + " NIT:" + nit +"," + " Siglas:"+siglas+
                ","+ " Dirección:"+ direccion +","+ " Telefono:" + telefono + ".";
    }
}
