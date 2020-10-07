import java.util.Comparator;

public class ZonaComparador implements Comparator<Zona> {
    public String criterio;

    public ZonaComparador(String criterio){this.criterio=criterio;}

    @Override
    public int compare(Zona zona1, Zona zona2) {
        if (criterio.equals("1")) {
            int min = zona1.zona1.length() < zona2.zona1.length() ? zona1.zona1.length() : zona2.zona1.length();
            for (int i = 0; i < min; i++) {
                if (zona1.zona1.charAt(i) > zona2.zona1.charAt(i)) {
                    return 1;
                } else if (zona1.zona1.charAt(i) < zona2.zona1.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("2")) {
            int min = zona1.zona2.length() < zona2.zona2.length() ? zona1.zona2.length() : zona2.zona2.length();
            for (int i = 0; i < min; i++) {
                if (zona1.zona2.charAt(i) > zona2.zona2.charAt(i)) {
                    return 1;
                } else if (zona1.zona2.charAt(i) < zona2.zona2.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("3")) {
            int min = zona1.zona3.length() < zona2.zona3.length() ? zona1.zona3.length() : zona2.zona3.length();
            for (int i = 0; i < min; i++) {
                if (zona1.zona3.charAt(i) > zona2.zona3.charAt(i)) {
                    return 1;
                } else if (zona1.zona3.charAt(i) < zona2.zona3.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("4")) {
            int min = zona1.zona4.length() < zona2.zona4.length() ? zona1.zona4.length() : zona2.zona4.length();
            for (int i = 0; i < min; i++) {
                if (zona1.zona4.charAt(i) > zona2.zona4.charAt(i)) {
                    return 1;
                } else if (zona1.zona4.charAt(i) < zona2.zona4.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else {
            int min = zona1.zona5.length() < zona2.zona5.length() ? zona1.zona5.length() : zona2.zona5.length();
            for (int i = 0; i < min; i++) {
                if (zona1.zona5.charAt(i) > zona2.zona5.charAt(i)) {
                    return 1;
                } else if (zona1.zona5.charAt(i) < zona2.zona5.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
