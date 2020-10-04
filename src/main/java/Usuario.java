public class Usuario {
    public int cedula;
    public String nombre;
    public String apellido;
    public String correo;
    public String password;

    public Usuario(int cedula, String nombre, String apellido, String correo, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User " + "\n" +
                "\t cedula : " + cedula + "\n" +
                "\t nombre : " + nombre + "\n" +
                "\t apellido : " + apellido + "\n" +
                "\t correo : " + correo + "\n" +
                "\t contra : " + password + "\n" +
                "\t ";
    }
}
