public class saludoIngreso {
    public String nombre;
    public String apellido;

    public saludoIngreso( String nombre,String apelli) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return  nombre + " "+ apellido;
    }

}