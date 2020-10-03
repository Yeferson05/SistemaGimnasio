import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Usuario> usuarios= new ArrayList<Usuario>();
    public static void main(String[] args) {
        String option;
        while(true){
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("Bienvenido al sistema del Gimnasio");
            System.out.println("escoja una opcion");
            System.out.println("1. Ingresar");
            System.out.println("2. Registrarse ");
            System.out.println("3. solo para visualizar los users(momentanea) ");
            System.out.println("0. salir");
            System.out.println("-----------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                ingresar();
            }else if(option.equals("2")){
                registrarse();
            }else if(option.equals("3")){
                visualizar();
            }else if(option.equals("0")){
                break;
            }
        }
    }
    public static void ingresar() {
        System.out.println("ingrese su documento o correo,lo que desee ");
        String ingreso= input.next();
        if(!(ingreso.contains("@"))){
            int docingreso=Integer.parseInt(ingreso);
            for (Usuario usuario : usuarios) {
                if (usuario.documento == docingreso) {
                    System.out.println("ingrese la contraseña");
                    String Contraseña=input.next();
                    if(usuario.contraseña.equals(Contraseña)){
                        System.out.println("ingreso exitoso");
                    }else{
                        System.out.println("contraseña incorrecta");
                    }
                }else{
                    System.out.println("documento incorrecto");
                }
            }
        }else{
            for (Usuario usuario : usuarios) {
                if (usuario.correo.equals(ingreso)) {
                    System.out.println("ingrese la contraseña");
                    String Contraseña=input.next();
                    if(usuario.contraseña.equals(Contraseña)){
                        System.out.println("ingreso exitoso");
                    }else{
                        System.out.println("contraseña incorrecta");
                    }
                }else{
                    System.out.println("correo incorrecto");
                }
            }
        }
    }

    public static void registrarse() {
        System.out.println("ingrese el documento de identidad");
        int documento = input.nextInt();
        if (documento < 0) {
            System.out.println("ingresaste un documento invalidad");
            return;
        }
        for (Usuario usuario : usuarios) {
            if (usuario.documento == documento) {
                System.out.println("el usuario ya existe");
                return;
            }
        }
        System.out.println("ingrese el nombre ");
        String nombre=input.next();
        System.out.println("ingrese el apellido ");
        String apellido=input.next();
        System.out.println("ingrese el correo");
        String correoo=input.next();
        int conteo=0;
        for (Usuario usuario : usuarios) {
            if (usuario.correo.equals(correoo)) {
                System.out.println("el correo ya existe");
                return;
            }
        }
        for(int i=0;i<correoo.length();i++){
            if(correoo.charAt(i)=='@'){
                conteo+=1;
            }
        }
        if(conteo<1){
            System.out.println("ingresaste un correo invalido");
            return;
        }
        System.out.println("ingrese la contraseña");
        String contra=input.next();
        input.nextLine();
        Usuario nuevoUsuario = new Usuario(documento,nombre,apellido,correoo,contra);
        usuarios.add(nuevoUsuario);
        System.out.println("el usuario se registró correctamente");
    }

    public static void visualizar() {
        System.out.println(usuarios);
    }
}
