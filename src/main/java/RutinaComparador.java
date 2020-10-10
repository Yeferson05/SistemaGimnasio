import java.util.Comparator;

public class RutinaComparador implements Comparator<Rutinas> {
    public String criterio;

    public RutinaComparador(String criterio){
        this.criterio = criterio; }

    @Override
    public int compare(Rutinas rutina1, Rutinas rutina2) {
        if (criterio.equals("1")) {
            int min = rutina1.rutina1.length() < rutina2.rutina1.length() ? rutina1.rutina1.length() : rutina2.rutina1.length();
            for (int i = 0; i < min; i++) {
                if (rutina1.rutina1.charAt(i) > rutina2.rutina1.charAt(i)) {
                    return 1;
                } else if (rutina1.rutina1.charAt(i) < rutina2.rutina1.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("2")) {
            int min = rutina1.rutina2.length() < rutina2.rutina2.length() ? rutina1.rutina2.length() : rutina2.rutina2.length();
            for (int i = 0; i < min; i++) {
                if (rutina1.rutina2.charAt(i) > rutina2.rutina2.charAt(i)) {
                    return 1;
                } else if (rutina1.rutina2.charAt(i) < rutina2.rutina2.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("3")) {
            int min = rutina1.rutina3.length() < rutina2.rutina3.length() ? rutina1.rutina3.length() : rutina2.rutina3.length();
            for (int i = 0; i < min; i++) {
                if (rutina1.rutina3.charAt(i) > rutina2.rutina3.charAt(i)) {
                    return 1;
                } else if (rutina1.rutina3.charAt(i) < rutina2.rutina3.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("4")) {
            int min = rutina1.rutina4.length() < rutina2.rutina4.length() ? rutina1.rutina4.length() : rutina2.rutina4.length();
            for (int i = 0; i < min; i++) {
                if (rutina1.rutina4.charAt(i) > rutina2.rutina4.charAt(i)) {
                    return 1;
                } else if (rutina1.rutina4.charAt(i) < rutina2.rutina4.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else {
            int min = rutina1.rutina5.length() < rutina2.rutina5.length() ? rutina1.rutina5.length() : rutina2.rutina5.length();
            for (int i = 0; i < min; i++) {
                if (rutina1.rutina5.charAt(i) > rutina2.rutina5.charAt(i)) {
                    return 1;
                } else if (rutina1.rutina5.charAt(i) < rutina2.rutina5.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
