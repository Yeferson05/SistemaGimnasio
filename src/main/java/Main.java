import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        String option;
        while(true) {
            System.out.println();
            System.out.println("-----------------------");
            System.out.println("Bienvenido");
            System.out.println("Escoja una opcion:");
            System.out.println("1. Ingresar");
            System.out.println("2. Registrarse");
            System.out.println("0. Salir");
            System.out.println();
            option = input.next();
            if (option.equals("1")) {
                //ingresar();
            } else if (option.equals("2")) {
                // registrarse();
            } if(option.equals("0")){
                break;
            }
        }
    }
}
