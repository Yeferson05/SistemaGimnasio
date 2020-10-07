import java.util.Comparator;

public class GimnasioComparador implements Comparator<Gimnasios> {

    public String criterio;

    public GimnasioComparador(String criterio){this.criterio=criterio;}


    @Override
    public int compare(Gimnasios gmy1, Gimnasios gmy2) {
        if (criterio.equals("1")){
            return gmy1.nit - gmy2.nit;
        }else if (criterio.equals("2")) {
            int min = gmy1.nombre.length() < gmy2.nombre.length() ? gmy1.nombre.length() : gmy2.nombre.length();
            for (int i = 0; i < min; i++) {
                if (gmy1.nombre.charAt(i) > gmy2.nombre.charAt(i)) {
                    return 1;
                } else if (gmy1.nombre.charAt(i) < gmy2.nombre.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("2")) {
            int min = gmy1.siglas.length() < gmy2.siglas.length() ? gmy1.siglas.length() : gmy2.siglas.length();
            for (int i = 0; i < min; i++) {
                if (gmy1.siglas.charAt(i) > gmy2.siglas.charAt(i)) {
                    return 1;
                } else if (gmy1.siglas.charAt(i) < gmy2.siglas.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("3")) {
            int min = gmy1.direccion.length() < gmy2.direccion.length() ? gmy1.direccion.length() : gmy2.direccion.length();
            for (int i = 0; i < min; i++) {
                if (gmy1.direccion.charAt(i) > gmy2.direccion.charAt(i)) {
                    return 1;
                } else if (gmy1.direccion.charAt(i) < gmy2.direccion.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
        else{
            return gmy1.telefono - gmy2.telefono;
        }
    }
}
