import java.util.Comparator;

public class SedeComparador implements Comparator<Sede> {
    public String criterio;

    public SedeComparador(String criterio){this.criterio=criterio;}


    @Override
    public int compare(Sede sede1, Sede sede2) {
        if (criterio.equals("1")) {
            int min = sede1.nombre.length() < sede2.nombre.length() ? sede1.nombre.length() : sede2.nombre.length();
            for (int i = 0; i < min; i++) {
                if (sede1.nombre.charAt(i) > sede2.nombre.charAt(i)) {
                    return 1;
                } else if (sede1.nombre.charAt(i) < sede2.nombre.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("2")) {
            int min = sede1.direccion.length() < sede2.direccion.length() ? sede1.direccion.length() : sede2.direccion.length();
            for (int i = 0; i < min; i++) {
                if (sede1.direccion.charAt(i) > sede2.direccion.charAt(i)) {
                    return 1;
                } else if (sede1.direccion.charAt(i) < sede2.direccion.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else {
            int min = sede1.ciudad.length() < sede2.ciudad.length() ? sede1.ciudad.length() : sede2.ciudad.length();
            for (int i = 0; i < min; i++) {
                if (sede1.ciudad.charAt(i) > sede2.ciudad.charAt(i)) {
                    return 1;
                } else if (sede1.ciudad.charAt(i) < sede2.ciudad.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
