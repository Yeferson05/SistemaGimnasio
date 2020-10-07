import java.util.Comparator;

public class UsuarioComparador implements Comparator<Usuario> {
    public String criterio;

    public UsuarioComparador(String criterio){
        this.criterio = criterio; }

    @Override
    public int compare(Usuario usuario1, Usuario usuario2) {
        if (criterio.equals("1")){
            return usuario1.cedula - usuario2.cedula;
        }
        else if (criterio.equals("2")) {
            int min = usuario1.nombre.length() < usuario2.nombre.length() ? usuario1.nombre.length() : usuario2.nombre.length();
            for (int i = 0; i < min; i++) {
                if (usuario1.nombre.charAt(i) > usuario2.nombre.charAt(i)) {
                    return 1;
                } else if (usuario1.nombre.charAt(i) < usuario2.nombre.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("3")) {
            int min = usuario1.apellido.length() < usuario2.apellido.length() ? usuario1.apellido.length() : usuario2.apellido.length();
            for (int i = 0; i < min; i++) {
                if (usuario1.apellido.charAt(i) > usuario2.apellido.charAt(i)) {
                    return 1;
                } else if (usuario1.apellido.charAt(i) < usuario2.apellido.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else {
            int min = usuario1.correo.length() < usuario2.correo.length() ? usuario1.correo.length() : usuario2.correo.length();
            for (int i = 0; i < min; i++) {
                if (usuario1.correo.charAt(i) > usuario2.correo.charAt(i)) {
                    return 1;
                } else if (usuario1.correo.charAt(i) < usuario2.correo.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
    }
}