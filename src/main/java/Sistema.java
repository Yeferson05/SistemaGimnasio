import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Usuario> usuarios= new ArrayList<>();
    public static ArrayList<Gimnasios> gimnasios= new ArrayList<>();
    public static ArrayList<Sede> sedes= new ArrayList<>();
    public static ArrayList<Zona> zonas= new ArrayList<>();
    public static ArrayList<Rutinas> rutinas= new ArrayList<>();
    public static ArrayList<saludoIngreso> saludo= new ArrayList<>();
    public static ArrayList<Entrenadores> entrenadores= new ArrayList<>();
    public static void main(String[] args) {
        Gimnasios nuevoGym1=new Gimnasios(123,"bodytech","bdt","avenida siempre viva",321);
        gimnasios.add(nuevoGym1);
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
        System.out.println("-                   Iniciar sesión                   -");
        System.out.println("-   Ingrese su numero de documento/correo electronico:");
        String ingreso= input.next();
        if(!(ingreso.contains("@"))){
            int docingreso=Integer.parseInt(ingreso);
            for (Usuario usuario : usuarios) {
                if (usuario.cedula == docingreso) {
                    System.out.println("-   Ingrese la contraseña:");
                    String Contraseña=input.next();
                    if(usuario.password.equals(Contraseña)){
                        System.out.println("---¡Ingreso exitoso!---");
                    }else{
                        System.out.println("--Contraseña incorrecta---");
                        return;
                    }
                }else{
                    System.out.println("---Este documento no se encuentra en la base de datos---");
                    return;
                }
            }
        }else{
            for (Usuario usuario : usuarios) {
                if (usuario.correo.equals(ingreso)) {
                    System.out.println("-   Ingrese la contraseña: ");
                    String Contraseña=input.next();
                    if(usuario.password.equals(Contraseña)){
                        System.out.println("---¡Ingreso exitoso!---");
                    }else{
                        System.out.println("---Contraseña incorrecta---");
                        return;
                    }
                }else{
                    System.out.println("---Este correo no se encuentra en la base de datos.---");
                    return;
                }
            }
        }
    }
    public static void registrarse() {
        System.out.println("-                   Registro                        -");
        System.out.println("-   Ingrese su documento de identidad: ");
        int documento = input.nextInt();
        if (documento < 0) {
            System.out.println("---El documento ingresado es invalido.---");
            return;
        }
        for (Usuario usuario : usuarios) {
            if (usuario.cedula == documento) {
                System.out.println("---El usuario ingresado ya existe.---");
                return;
            }
        }
        System.out.println("-   Ingrese su nombre: ");
        String nombre=input.next();
        System.out.println("-   Ingrese su apellido: ");
        String apellido=input.next();
        System.out.println("-   Ingrese su dirección de correo electronico completo.");
        String correo=input.next();
        for (Usuario usuario : usuarios) {
            if (usuario.correo.equals(correo)) {
                System.out.println("---El correo electronico ingresado ya existe.---");
                return;
            }
        }
        if (!(correo.contains("@"))){
            System.out.println("---El correo ingresado es invalido.---");
        }
        System.out.println("-   Ingrese la contraseña:");
        String contra=input.next();
        input.nextLine();
        Usuario nuevoUsuario = new Usuario(documento,nombre,apellido,correo,contra);
        usuarios.add(nuevoUsuario);
        System.out.println("---¡Registro exitoso!---");
        ingresar();
    }



    public static void menuPrincipal() {
        String option;
        while(true){
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("Hola "+saludo);
            System.out.println("escoja una opcion");
            System.out.println("1. Administracion");
            System.out.println("2. Busqueda ");
            System.out.println("3. Diagnostico de inconsistencias");
            System.out.println("4. Guardar ");
            System.out.println("5. Salir ");
            System.out.println("-----------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                administracion();
            }else if(option.equals("2")){
                busqueda();
            }else if(option.equals("3")){
                diagnostico();
            }else if(option.equals("4")){
                guardar();
            }else if(option.equals("5")){
                String opcion;
                System.out.println("-----------------------------");
                System.out.println("si se sale en este momento");
                System.out.println("se perderan los cambios sin");
                System.out.println("guardar ");
                System.out.println("¿Seguro que dessea salir? ");
                System.out.println("escoja una opcion");
                System.out.println("1. Yes");
                System.out.println("2. No ");
                System.out.println("-----------------------------");
                System.out.println();
                opcion=input.next();
                if(opcion.equals("1")){
                    break;
                }else if(opcion.equals("2")){
                    input.nextLine();
                }
            }
        }
    }

    public static void administracion() {
        String option;
        while(true){
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("escoja una opcion");
            System.out.println("1. Gimnasios");
            System.out.println("2. Sedes ");
            System.out.println("3. Zonas");
            System.out.println("4. Rutinas ");
            System.out.println("5. Entrenadores ");
            System.out.println("6. Usuarios ");
            System.out.println("7. Cancelar ");
            System.out.println("-----------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                Gimnasios();
            }else if(option.equals("2")){
                Sedes();
            }else if(option.equals("3")){
                Zonas();
            }else if(option.equals("4")){
                Rutinas();
            }else if(option.equals("5")){
                Entrenador();
            }else if(option.equals("6")){
                Usuarios();
            }else if(option.equals("7")){
                break;
            }
        }
    }

    public static void Gimnasios() {
        String option;
        while(true){
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("escoja una opcion");
            System.out.println("1. Ver gimnasios");
            System.out.println("2. Crear gimnasio");
            System.out.println("3. Editar gimnasio");
            System.out.println("4. Eliminar gimnasio ");
            System.out.println("5. Cancelar ");
            System.out.println("-----------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(gimnasios.size() == 0){
                    System.out.println("la lista de gimnasios se encuentra vacía");
                    return;
                }
                System.out.println("lista de gimnasios");
                for (Gimnasios gimnasio : gimnasios) {
                    System.out.println(gimnasio);
                }
            }else if(option.equals("2")){
                System.out.println("ingrese el NIT del gimnasio");
                int nit = input.nextInt();
                if (nit < 0) {
                    System.out.println("ingresaste un documento invalidad");
                    return;
                }
                for (Gimnasios gimnasio : gimnasios) {
                    if (gimnasio.nit == nit) {
                        System.out.println("Dicho gimnasio ya existe");
                        return;
                    }
                }
                System.out.println("ingrese el nombre del gimnasio");
                String nombreGym=input.next();
                System.out.println("ingrese las siglas ");
                String siglas=input.next();
                for (Gimnasios gimnasio : gimnasios) {
                    if (gimnasio.siglas.equals(siglas)) {
                        System.out.println("Estas siglas ya existen");
                        return;
                    }
                }
                System.out.println("ingrese la direccion");
                String direccion=input.nextLine();
                input.nextLine();
                System.out.println("ingrese el telefono del gimnasio");
                int telefono=input.nextInt();
                Gimnasios nuevoGym = new Gimnasios(nit,nombreGym,siglas,direccion,telefono);
                gimnasios.add(nuevoGym);
                System.out.println("el gimnasio se registró correctamente");
            }else if(option.equals("3")){
                System.out.println();
            }else if(option.equals("4")){
                // Couch();
            }else if(option.equals("5")){
                break;
            }
        }
    }

    public static void Sedes() {
        String option;
        while(true){
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("escoja una opcion");
            System.out.println("1. Ver sedes");
            System.out.println("2. Crear sede");
            System.out.println("3. Editar sede");
            System.out.println("4. Eliminar sede ");
            System.out.println("5. Cancelar ");
            System.out.println("-----------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(sedes.size() == 0){
                    System.out.println("la lista de sedes se encuentra vacía");
                    return;
                }
                System.out.println("lista de sedes");
                for (Sede sede : sedes) {
                    System.out.println(sede);
                }
            }else if(option.equals("2")){
                System.out.println("ingrese el nombre de la sede");
                String nombreSede=input.next();
                System.out.println("ingrese la ciudad donde está ubicada");
                String ciudadSede=input.next();
                System.out.println("ingrese la direccion");
                String direccionSede=input.nextLine();
                input.nextLine();
                Sede nuevaSede = new Sede(nombreSede,ciudadSede,direccionSede);
                sedes.add(nuevaSede);
                System.out.println("la sede se registró correctamente");
            }else if(option.equals("3")){
                System.out.println();
            }else if(option.equals("4")){
                //
            }else if(option.equals("5")){
                break;
            }
        }
    }


    public static void Zonas() {
        String option;
        while(true){
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("escoja una opcion");
            System.out.println("1. Ver las zonas");
            System.out.println("2. Crear zonas");
            System.out.println("3. Editar zonas");
            System.out.println("4. Eliminar Zonas ");
            System.out.println("5. Cancelar ");
            System.out.println("-----------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(zonas.size() == 0){
                    System.out.println("la lista de zonas se encuentra vacía");
                    return;
                }
                System.out.println("lista de zonas");
                for (Zona zona : zonas) {
                    System.out.println(zona);
                }
            }else if(option.equals("2")){
                System.out.println("ingrese el nombre de la zona 1");
                String zona1=input.next();
                System.out.println("ingrese el nombre de la zona 2");
                String zona2=input.next();
                System.out.println("ingrese el nombre de la zona 3");
                String zona3=input.next();
                System.out.println("ingrese el nombre de la zona 4");
                String zona4=input.next();
                System.out.println("ingrese el nombre de la zona 5");
                String zona5=input.next();
                Zona nuevaZona = new Zona(zona1,zona2,zona3,zona4,zona5);
                zonas.add(nuevaZona);
                System.out.println("las zonas se registraron correctamente");
            }else if(option.equals("3")){
                System.out.println();
            }else if(option.equals("4")){
                //
            }else if(option.equals("5")){
                break;
            }
        }
    }

    public static void Rutinas() {
        String option;
        while(true){
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("escoja una opcion");
            System.out.println("1. Ver las rutinas");
            System.out.println("2. Crear rutinas");
            System.out.println("3. Editar rutinas");
            System.out.println("4. Eliminar rutinas ");
            System.out.println("5. Cancelar ");
            System.out.println("-----------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(rutinas.size() == 0){
                    System.out.println("la lista de rutinas se encuentra vacía");
                    return;
                }
                System.out.println("lista de zonas");
                for (Rutinas rutina : rutinas) {
                    System.out.println(rutina);
                }
            }else if(option.equals("2")){
                System.out.println("ingrese el nombre de la rutina 1");
                String rutina1=input.next();
                System.out.println("ingrese el nombre de la rutina 2");
                String rutina2=input.next();
                System.out.println("ingrese el nombre de la rutina 3");
                String rutina3=input.next();
                System.out.println("ingrese el nombre de la rutina 4");
                String rutina4=input.next();
                System.out.println("ingrese el nombre de la rutina 5");
                String rutina5=input.next();
                Rutinas nuevaRutina = new Rutinas(rutina1,rutina2,rutina3,rutina4,rutina5);
                rutinas.add(nuevaRutina);
                System.out.println("las rutinas se registraron correctamente");
            }else if(option.equals("3")){
                System.out.println();
            }else if(option.equals("4")){
                //
            }else if(option.equals("5")){
                break;
            }
        }
    }

    public static void Entrenador() {
        String option;
        while(true){
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("escoja una opcion");
            System.out.println("1. Ver lista entrenadores");
            System.out.println("2. registrar entrenador");
            System.out.println("3. Editar datos entrenador");
            System.out.println("4. Eliminar entrenador ");
            System.out.println("5. Cancelar ");
            System.out.println("-----------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                System.out.println("ingrese el documento de identidad");
                int documento = input.nextInt();
                if (documento < 0) {
                    System.out.println("ingresaste un documento invalidad");
                    return;
                }
                for (Entrenadores entrenador : entrenadores) {
                    if (entrenador.documento == documento) {
                        System.out.println("el entrenador ya está registrado");
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
                Entrenadores nuevoEntrenador = new Entrenadores(documento,nombre,apellido,correoo);
                entrenadores.add(nuevoEntrenador);
                System.out.println("el usuario se registró correctamente");
            }else if(option.equals("3")){
                System.out.println();
            }else if(option.equals("4")){
                // Couch();
            }else if(option.equals("5")){
                break;
            }
        }
    }

    public static void Usuarios() {
        String option;
        while(true){
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("escoja una opcion");
            System.out.println("1. Ver lista de usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario ");
            System.out.println("5. Cancelar ");
            System.out.println("-----------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(usuarios.size() == 0){
                    System.out.println("la lista de usuarios se encuentra vacía");
                    return;
                }
                System.out.println("lista de usuarios");
                visualizar();
            }else if(option.equals("2")){
                registrarse();
            }else if(option.equals("3")){
                System.out.println();
            }else if(option.equals("4")){
                //
            }else if(option.equals("5")){
                break;
            }
        }
    }



    public static void busqueda() {
        String option;
        while(true) {
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("Ingrese la opcion que desee buscar");
            System.out.println("1. Gimnasios");
            System.out.println("2. Sedes");
            System.out.println("3. Zonas");
            System.out.println("4. Cursos");
            System.out.println("5. Usuarios");
            System.out.println("6. Entrenadores");
            System.out.println("7. Rutinas");
            System.out.println("5. Volver");
            System.out.println("-----------------------------");
            System.out.println();
            option = input.next();
            if(option.equals("1")) {
                if (gimnasios.size() == 0) {
                    System.out.println("la lista de gimnasios se encuentra vacía");
                    return;
                }
                String seleccion;
                while(true) {
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. NIT");
                    System.out.println("2. Nombre");
                    System.out.println("3. Siglas");
                    System.out.println("4. Direccion");
                    System.out.println("5. Telefono");
                    System.out.println("6. Cancelar");
                    System.out.println("-----------------------------");
                    System.out.println();
                    seleccion = input.next();
                    if (seleccion.equals("1")){
                        System.out.println("Ingrese el NIT del Gym");
                        int nit = input.nextInt();
                        for (Gimnasios gimnasio : gimnasios) {
                            if (gimnasio.nit== nit){
                                System.out.println(gimnasio.nit);
                            }else{
                                System.out.println("El NIT a buscar no se encuentra");
                            }

                        }
                    }else if (seleccion.equals("2")){
                        System.out.println("Ingrese el nombre del Gym");
                        String nombre = input.nextLine();
                        for (Gimnasios gimnasio : gimnasios) {
                            if (gimnasio.nombre.equals(nombre)){
                                System.out.println(gimnasio.nombre);
                            }else{
                                System.out.println("El nombre a buscar no se encuentra");
                            }

                        }
                    }else if (seleccion.equals("3")){
                        System.out.println("Ingrese las siglas del Gym");
                        String sigla = input.next();
                        for (Gimnasios gimnasio : gimnasios) {
                            if (gimnasio.siglas.equals(sigla)){
                                System.out.println(gimnasio.siglas);
                            }else{
                                System.out.println("Las siglas a buscar no se encuentra");
                            }
                        }
                    }else if (seleccion.equals("4")){
                        System.out.println("Ingrese la direccion del Gym");
                        String direccion = input.next();
                        for (Gimnasios gimnasio : gimnasios) {
                            if (gimnasio.direccion.equals(direccion)){
                                System.out.println(gimnasio.direccion);
                            }else{
                                System.out.println("La direccion a buscar no se encuentra");
                            }
                        }
                    }else if (seleccion.equals("5")){
                        System.out.println("Ingrese el telefono del Gym");
                        int telefono = input.nextInt();
                        for (Gimnasios gimnasio : gimnasios) {
                            if (gimnasio.telefono == telefono){
                                System.out.println(gimnasio.telefono);
                            }else{
                                System.out.println("El telefono a buscar no se encuentra");
                            }
                        }
                    }else if(seleccion.equals("6")){
                        break;
                    }
                }
            }
        }
    }

    public static void diagnostico() {

    }

    public static void guardar() {

    }





    public static void visualizar() {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}
