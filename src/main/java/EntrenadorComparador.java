import java.util.Comparator;

public class EntrenadorComparador implements Comparator<Entrenadores> {
    public String criterio;

    public EntrenadorComparador(String criterio){
        this.criterio = criterio; }

    @Override
    public int compare(Entrenadores entrenador1, Entrenadores entrenador2) {
        if (criterio.equals("1")){
            return entrenador1.documento - entrenador2.documento;
        }else if (criterio.equals("2")) {
            int min = entrenador1.nombre.length() < entrenador2.nombre.length() ? entrenador1.nombre.length() : entrenador2.nombre.length();
            for (int i = 0; i < min; i++) {
                if (entrenador1.nombre.charAt(i) > entrenador2.nombre.charAt(i)) {
                    return 1;
                } else if (entrenador1.nombre.charAt(i) < entrenador2.nombre.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("3")) {
            int min = entrenador1.apellido.length() < entrenador2.apellido.length() ? entrenador1.apellido.length() : entrenador2.apellido.length();
            for (int i = 0; i < min; i++) {
                if (entrenador1.apellido.charAt(i) > entrenador2.apellido.charAt(i)) {
                    return 1;
                } else if (entrenador1.apellido.charAt(i) < entrenador2.apellido.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else {
            int min = entrenador1.correo.length() < entrenador2.correo.length() ? entrenador1.correo.length() : entrenador2.correo.length();
            for (int i = 0; i < min; i++) {
                if (entrenador1.correo.charAt(i) > entrenador2.correo.charAt(i)) {
                    return 1;
                } else if (entrenador1.correo.charAt(i) < entrenador2.correo.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
