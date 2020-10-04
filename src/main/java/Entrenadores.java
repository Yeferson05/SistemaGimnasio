public class Entrenadores {
    public int documento;
    public String nombre;
    public String apellido;
    public String correo;


    public Entrenadores(int documento, String nombre, String apellido, String correoo) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correoo;
    }

    @Override
    public String toString() {
        return "User " + "\n" +
                "\t cedula : " + documento + "\n" +
                "\t nombre : " + nombre + "\n" +
                "\t apellido : " + apellido + "\n" +
                "\t correo : " + correo + "\n" +
                "\t ";
    }
}