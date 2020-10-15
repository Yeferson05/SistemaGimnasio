import java.util.ArrayList;

public class Usuario {


    public ArrayList<Entrenadores> misEntrenadores = new ArrayList<>();


    public int cedula;
    public String nombre;
    public String apellido;
    public String correo;
    public String password;

    public Usuario( int cedula, String nombre, String apellido, String correo, String password) {

        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;

        misEntrenadores = new ArrayList<>();
    }

    public String toString() {
        return  "-  Usuario:"+nombre+", "+"Apellido:"+apellido+", "+"Cedula:"+cedula+", "+"Correo:"+correo+
                ", "+"Contraseña:"+password+"\n"+
                "-  Entrenadores:"+misEntrenadores;

    }

    public int getCedula(){
        return cedula;
    }
    public String getCorreo(){
        return correo;
    }
    public String getPassword() {
        return password;
    }

}
