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
        return "-   Sede:    " + "Nombre:"+ nombre +"," + " Dirección:" + direccion +"," + " Ciudad:"+ciudad+".";
    }
}
