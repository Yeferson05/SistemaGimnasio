import java.util.Comparator;

public class UsuarioComparador implements Comparator<Usuario> {
    public String criterio;

    public UsuarioComparador(String criterio){
        this.criterio = criterio; }

    @Override
    public int compare(Usuario usuario1, Usuario usuario2) {
        if (criterio.equals("1")) {
            int min = usuario1.nombre.length() < usuario2.nombre.length() ? usuario1.nombre.length() : usuario2.nombre.length();
            for (int i = 0; i < min; i++) {
                if (usuario1.nombre.charAt(i) > usuario2.nombre.charAt(i)) {
                    return 1;
                } else if (usuario1.nombre.charAt(i) < usuario2.nombre.charAt(i)) {
                    return -1;
                }
            }
            return 0;

        }else if (criterio.equals("2")){
            return usuario1.cedula - usuario2.cedula;
        }
        return 0;
    }
}