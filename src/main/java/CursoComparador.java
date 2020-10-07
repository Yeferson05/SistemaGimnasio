import java.util.Comparator;

public class CursoComparador implements Comparator<Cursos> {
    public String criterio;

    public CursoComparador(String criterio){this.criterio=criterio;}

    @Override
    public int compare(Cursos curso1, Cursos curso2) {
        if (criterio.equals("1")) {
            int min = curso1.nombre.length() < curso2.nombre.length() ? curso1.nombre.length() : curso2.nombre.length();
            for (int i = 0; i < min; i++) {
                if (curso1.nombre.charAt(i) > curso2.nombre.charAt(i)) {
                    return 1;
                } else if (curso1.nombre.charAt(i) < curso2.nombre.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }else if (criterio.equals("2")){
            return curso1.codigo - curso1.codigo;
        }else{
            return curso1.intesidadHora - curso1.intesidadHora;
        }
    }
}
