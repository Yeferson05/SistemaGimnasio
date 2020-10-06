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
    public static ArrayList<Cursos> cursos= new ArrayList<>();
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
        boolean res;
        try {
            Integer.parseInt(ingreso);
            res = true;
        } catch (NumberFormatException excepcion) {
            res = false;
        }
        if(res == true){
            int docingreso=Integer.parseInt(ingreso);
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).cedula == docingreso) {
                    saludo.clear();
                    saludoIngreso ingreso1 = new saludoIngreso(usuarios.get(i).nombre, usuarios.get(i).apellido);
                    saludo.add(ingreso1);
                    System.out.println("-   Ingrese la contraseña:");
                    String Contraseña=input.next();
                    if(usuarios.get(i).password.equals(Contraseña)){
                        System.out.println("---¡Ingreso exitoso!---");
                        menuPrincipal();
                        return;
                    }else{
                        System.out.println("--Contraseña incorrecta---");
                        return;
                    }
                }
            }
            System.out.println("---Este documento no se encuentra en la base de datos---");
        }else if (res == false && ingreso.contains("@") == true){
            for (int i = 0; i < usuarios.size(); i++) {
                saludo.clear();
                saludoIngreso ingreso1 = new saludoIngreso(usuarios.get(i).nombre, usuarios.get(i).apellido);
                saludo.add(ingreso1);
                if (usuarios.get(i).correo.equals(ingreso)) {
                    System.out.println("-   Ingrese la contraseña: ");
                    String Contraseña=input.next();
                    if(usuarios.get(i).password.equals(Contraseña)){
                        System.out.println("---¡Ingreso exitoso!---");
                        menuPrincipal();
                    }else{
                        System.out.println("---Contraseña incorrecta---");
                        return;
                    }
                }
            }
            System.out.println("---Este correo no se encuentra en la base de datos.---");

        }else{
            System.out.println ("---Ingresaste valores invalidos.---");
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
            return;
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
            System.out.println("-------       Menú principal       -------");
            System.out.println("Hola "+saludo);
            System.out.println("Escoja una opción");
            System.out.println("1. Administración");
            System.out.println("2. Busqueda ");
            System.out.println("3. Diagnostico de inconsistencias");
            System.out.println("4. Guardar ");
            System.out.println("5. Salir y cancelar ");
            System.out.println("-------------------------------------------");
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
            System.out.println("-------       Administración       -------");
            System.out.println("escoja una opcion");
            System.out.println("1. Gimnasios");
            System.out.println("2. Sedes ");
            System.out.println("3. Zonas");
            System.out.println("4. Rutinas ");
            System.out.println("5. Entrenadores ");
            System.out.println("6. Usuarios ");
            System.out.println("7. Cancelar ");
            System.out.println("------------------------------------------");
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
            System.out.println("-------       Menú Gimnasios       -------");
            System.out.println("Escoja una opcion");
            System.out.println("1. Ver gimnasios");
            System.out.println("2. Crear gimnasio");
            System.out.println("3. Editar gimnasio");
            System.out.println("4. Eliminar gimnasio ");
            System.out.println("5. Cancelar ");
            System.out.println("------------------------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(gimnasios.size() == 0){
                    System.out.println("---La lista de gimnasios se encuentra vacía.---");
                    return;
                }
                System.out.println("---Lista de gimnasios---");
                for (Gimnasios gimnasio : gimnasios) {
                    System.out.println(gimnasio);
                }
            }else if(option.equals("2")){
                System.out.println("-------       Crear nuevo Gimnasio       -------");
                System.out.println("-   Ingrese el NIT del gimnasio: ");
                int nit = input.nextInt();
                if (nit < 0) {
                    System.out.println("---Ingresaste un documento invalido.---");
                    return;
                }
                for (Gimnasios gimnasio : gimnasios) {
                    if (gimnasio.nit == nit) {
                        System.out.println("---Ya existe un gimnasio con este NIT---");
                        return;
                    }
                }
                System.out.println("-   Ingrese el nombre del nuevo gimnasio: ");
                String nombreGym=input.next();
                System.out.println("-   Ingrese las siglas: ");
                String siglas=input.next();
                input.nextLine();
                for (Gimnasios gimnasio : gimnasios) {
                    if (gimnasio.siglas.equals(siglas)) {
                        System.out.println("---Estas siglas ya existen---");
                        return;
                    }
                }
                System.out.println("-   Ingrese la direccion: ");
                String direccion=input.nextLine();
                System.out.println("-   Ingrese el telefono del gimnasio: ");
                int telefono=input.nextInt();
                Gimnasios nuevoGym = new Gimnasios(nit,nombreGym,siglas,direccion,telefono);
                gimnasios.add(nuevoGym);
                System.out.println("---¡Registro del nuevo Gimnasio exitoso!---");
            }else if(option.equals("3")){
                System.out.println("-------       Editar Gimnasio       -------");
                System.out.println("1. Seleccionar por NIT");
                System.out.println("2. Seleccionar por Siglas");
                String seleccion = input.next();
                if (seleccion.equals("1")){
                    System.out.println("-   Ingrese el NIT: ");
                    String nitString = input.next();
                    String nitReplace = nitString.replace(".","");
                    int intNIT = Integer.parseInt(nitReplace);
                    for (Gimnasios gyms : gimnasios){
                        if (gyms.nit == intNIT){
                            System.out.println("NIT: " + gyms.nit);
                            String nuevoNIT = input.next();

                        }
                    }
                }

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
            System.out.println("-------       Menú Sedes       -------");
            System.out.println("Escoja una opcion:");
            System.out.println("1. Ver sedes");
            System.out.println("2. Crear sede");
            System.out.println("3. Editar sede");
            System.out.println("4. Eliminar sede ");
            System.out.println("5. Cancelar ");
            System.out.println("--------------------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(sedes.size() == 0){
                    System.out.println("---La lista de sedes se encuentra vacía.---");
                    return;
                }
                System.out.println("---Lista de sedes---");
                for (Sede sede : sedes) {
                    System.out.println(sede);
                }
            }else if(option.equals("2")){
                System.out.println("-   Ingrese el nombre de la sede:");
                String nombreSede=input.next();
                System.out.println("-   Ingrese la ciudad donde está ubicada");
                String ciudadSede=input.next();
                input.nextLine();
                System.out.println("-   Ingrese la dirección:");
                String direccionSede=input.nextLine();
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
            System.out.println("-------       Menú Zonas       -------");
            System.out.println("Escoja una opcion:");
            System.out.println("1. Ver las zonas");
            System.out.println("2. Crear zonas");
            System.out.println("3. Editar zonas");
            System.out.println("4. Eliminar Zonas ");
            System.out.println("5. Cancelar ");
            System.out.println("--------------------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(zonas.size() == 0){
                    System.out.println("---La lista de zonas se encuentra vacía---");
                    return;
                }
                System.out.println("---Lista de zonas---");
                for (Zona zona : zonas) {
                    System.out.println(zona);
                }
            }else if(option.equals("2")){
                System.out.println("-   ingrese el nombre de la zona 1:");
                String zona1=input.next();
                System.out.println("-   Ingrese el nombre de la zona 2:");
                String zona2=input.next();
                System.out.println("-   Ingrese el nombre de la zona 3:");
                String zona3=input.next();
                System.out.println("-   Ingrese el nombre de la zona 4:");
                String zona4=input.next();
                System.out.println("-   Ingrese el nombre de la zona 5:");
                String zona5=input.next();
                Zona nuevaZona = new Zona(zona1,zona2,zona3,zona4,zona5);
                zonas.add(nuevaZona);
                System.out.println("---Las zonas se registraron correctamente---");
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
            System.out.println("-------       Menú Rutinas       -------");
            System.out.println("Escoja una opcion:");
            System.out.println("1. Ver las rutinas");
            System.out.println("2. Crear rutinas");
            System.out.println("3. Editar rutinas");
            System.out.println("4. Eliminar rutinas ");
            System.out.println("5. Cancelar ");
            System.out.println("----------------------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(rutinas.size() == 0){
                    System.out.println("---La lista de rutinas se encuentra vacía.---");
                    return;
                }
                System.out.println("---Lista de zonas---");
                for (Rutinas rutina : rutinas) {
                    System.out.println(rutina);
                }
            }else if(option.equals("2")){
                System.out.println("-   Ingrese el nombre de la rutina 1");
                String rutina1=input.next();
                System.out.println("-   Ingrese el nombre de la rutina 2");
                String rutina2=input.next();
                System.out.println("-   Ingrese el nombre de la rutina 3");
                String rutina3=input.next();
                System.out.println("-   Ingrese el nombre de la rutina 4");
                String rutina4=input.next();
                System.out.println("-   Ingrese el nombre de la rutina 5");
                String rutina5=input.next();
                Rutinas nuevaRutina = new Rutinas(rutina1,rutina2,rutina3,rutina4,rutina5);
                rutinas.add(nuevaRutina);
                System.out.println("---Las rutinas se registraron correctamente---");
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
            System.out.println("-------       Menú Entrenadores       -------");
            System.out.println("Escoja una opción:");
            System.out.println("1. Ver lista entrenadores");
            System.out.println("2. registrar entrenador");
            System.out.println("3. Editar datos entrenador");
            System.out.println("4. Eliminar entrenador ");
            System.out.println("5. Cancelar ");
            System.out.println("---------------------------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(entrenadores.size() == 0){
                    System.out.println("---La lista de entrenadores se encuentra vacía.---");
                    return;
                }
                System.out.println("---Lista de entrenadores---");
                for (Entrenadores entrenador : entrenadores) {
                    System.out.println(entrenador);
                }
            }else if(option.equals("2")){
                System.out.println("-   Ingrese el documento de identidad:");
                int documento = input.nextInt();
                if (documento < 0) {
                    System.out.println("---Ingresaste un documento invalido---");
                    return;
                }
                for (Entrenadores entrenador : entrenadores) {
                    if (entrenador.documento == documento) {
                        System.out.println("---El entrenador ya se encuentra registrado---");
                        return;
                    }
                }
                System.out.println("-   Ingrese el nombre: ");
                String nombre=input.next();
                System.out.println("-   Ingrese el apellido: ");
                String apellido=input.next();
                System.out.println("-   Ingrese el correo: ");
                String correo=input.next();
                int conteo=0;
                for (Usuario usuario : usuarios) {
                    if (usuario.correo.equals(correo)) {
                        System.out.println("---El correo ya existe---");
                        return;
                    }
                }
                for(int i=0;i<correo.length();i++){
                    if(correo.charAt(i)=='@'){
                        conteo+=1;
                    }
                }
                if(conteo<1){
                    System.out.println("----Ingresaste un correo invalido---");
                    return;
                }
                Entrenadores nuevoEntrenador = new Entrenadores(documento,nombre,apellido,correo);
                entrenadores.add(nuevoEntrenador);
                System.out.println("---¡Registro exitoso!---");
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
            System.out.println("--------       Menú Usuarios       -------");
            System.out.println("escoja una opcion");
            System.out.println("1. Ver lista de usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario ");
            System.out.println("5. Cancelar ");
            System.out.println("------------------------------------------");
            System.out.println();
            option=input.next();
            if(option.equals("1")){
                if(usuarios.size() == 0){
                    System.out.println("---La lista de usuarios se encuentra vacía---");
                    return;
                }
                System.out.println("---Lista de usuarios---");
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
        while (true) {
            System.out.println();
            System.out.println("-------       Menú de Busqueda       --------");
            System.out.println("Ingrese la opcion que desee buscar");
            System.out.println("1. Gimnasios");
            System.out.println("2. Sedes");
            System.out.println("3. Zonas");
            System.out.println("4. Cursos");
            System.out.println("5. Usuarios");
            System.out.println("6. Entrenadores");
            System.out.println("7. Rutinas");
            System.out.println("8. Volver");
            System.out.println("-------------------------------------------- -");
            System.out.println();
            option = input.next();
            if (option.equals("1")) {
                if (gimnasios.size() == 0) {
                    System.out.println("---La lista de gimnasios se encuentra vacía---");
                    return;
                }
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. NIT");
                    System.out.println("2. Nombre");
                    System.out.println("3. Siglas");
                    System.out.println("4. Direccion");
                    System.out.println("5. Telefono");
                    System.out.println("6. Volver");
                    System.out.println("--------------------------------------------------- ----");
                    System.out.println();
                    seleccion = input.next();
                    if (seleccion.equals("1")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor minimo");
                            System.out.println("3. Valor maximo");
                            System.out.println("4. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el numero del NIT:");
                                int valorExacto = input.nextInt();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.nit == valorExacto) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }

                                }
                                if (c == 0) {
                                    System.out.println("El NIT a buscar no se encuentra");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese un valor de NIT: ");
                                int valorMinimo = input.nextInt();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.nit >= valorMinimo) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No hay NIT que mostrar---");
                                }
                            } else if (option1.equals("3")) {
                                System.out.println("-   Ingrese un valor de NIT: ");
                                int valorMaximo = input.nextInt();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.nit <= valorMaximo) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No hay NIT que mostrar---");
                                }
                            } else if (option1.equals("4")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("2")) {
                        String option2;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option2 = input.next();
                            if (option2.equals("1")) {
                                System.out.println("-   Ingrese el nombre del gimnasio: ");
                                String nombre = input.nextLine();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.nombre == nombre) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---El nombre a buscar no fue encontrado---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese el nombre del gimnasio: ");
                                String nombre = input.nextLine();
                                String nombreMinu = nombre.toLowerCase();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.nombre.toLowerCase() == nombreMinu) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---El nombre a buscar no fue encontrado---");
                                }
                            }else if (option2.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("3")) {
                        String option2;
                        while (true) {
                            System.out.println();
                            System.out.println("----------------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option2 = input.next();
                            if (option2.equals("1")) {
                                System.out.println("-   Ingrese las siglas del Gimnasio: ");
                                String sigla = input.next();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.siglas == sigla) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---Las siglas a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese las siglas del gimnasio: ");
                                String nombre = input.nextLine();
                                String nombreMinu = nombre.toLowerCase();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.siglas.toLowerCase() == nombreMinu) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---Las siglas a buscar no se encuentra---");
                                }
                            }else if (option2.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("4")) {
                        String option2;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option2 = input.next();
                            if (option2.equals("1")) {
                                System.out.println("Ingrese la direccion Gym");
                                String direccion = input.next();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.direccion == direccion) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La direccion a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese la direccion del Gimnasio: ");
                                String direccion = input.nextLine();
                                String direccionMinu = direccion.toLowerCase();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.direccion.toLowerCase() == direccionMinu) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La direccion a buscar no se encuentra---");
                                }
                            }else if (option2.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("5")) {
                        String option2;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor minimo");
                            System.out.println("3. valor maximo");
                            System.out.println("4. Volver");
                            System.out.println();
                            option2 = input.next();
                            if (option2.equals("1")) {
                                System.out.println("-   Ingrese el numero de telefono a buscar:");
                                int numeroTelefono = input.nextInt();
                                int c=0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.telefono == numeroTelefono) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }if (c==0){
                                    System.out.println("---El telefono a buscar no se encunetra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese el numero de telefono a buscar: ");
                                int numero = input.nextInt();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.telefono >= numero) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No hay telefonos que mostrar---");
                                }
                            } else if (option2.equals("3")) {
                                System.out.println("-   Ingrese el numero de telefono a buscar:");
                                int numero = input.nextInt();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.telefono <= numero) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No hay telefonos que mostrar---");
                                }
                            } else if (option2.equals("4")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("6")) {
                        break;
                    }
                }
            } else if (option.equals("2")) {
                if (sedes.size() == 0) {
                    System.out.println("---La lista de las sedes se encuentra vacía---");
                    return;
                }
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. Nombre");
                    System.out.println("2. Direccion");
                    System.out.println("3. Ciudad");
                    System.out.println("4. volver");
                    System.out.println("-----------------------------");
                    System.out.println();
                    seleccion = input.next();
                    if (seleccion.equals("1")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre de la sede a buscar: ");
                                String sede = input.nextLine();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.nombre == sede) {
                                        System.out.println(sede1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro el nombre de la sede---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el nombre de la sede a buscar: ");
                                String nombre = input.nextLine();
                                String nombreMInus = nombre.toLowerCase();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.nombre.toLowerCase() == nombreMInus) {
                                        System.out.println(sede1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro el nombre de la sede--");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }
                    if (seleccion.equals("2")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("Ingrese la direccion de la sede a buscar");
                                String dire = input.nextLine();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.direccion == dire) {
                                        System.out.println(sede1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la direccion de la sede---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la direccion de la sede a buscar: ");
                                String direccion = input.nextLine();
                                String direccionMInus = direccion.toLowerCase();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.direccion.toLowerCase() == direccionMInus) {
                                        System.out.println(sede1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la direccion de la sede---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("3")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese la ciudad de la sede a buscar: ");
                                String ciudad = input.nextLine();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.ciudad == ciudad) {
                                        System.out.println(sede1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la ciudad de la sede---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-  Ingrese la ciudad de la sede a buscar: ");
                                String ciudad = input.nextLine();
                                String ciudadMInus = ciudad.toLowerCase();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.ciudad.toLowerCase() == ciudadMInus) {
                                        System.out.println(sede1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la ciudad de la sede---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }
                }
            } else if (option.equals("3")) {
                if (zonas.size() == 0) {
                    System.out.println("---La lista de zonas se encuentra vacía---");
                    return;
                }
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. Zona 1");
                    System.out.println("2. Zona 2");
                    System.out.println("3. Zona 3");
                    System.out.println("4. Zona 4");
                    System.out.println("5. Zona 5");
                    System.out.println("6. Volver");
                    System.out.println("-----------------------------");
                    System.out.println();
                    seleccion = input.next();
                    if (seleccion.equals("1")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre de la zona a buscar: ");
                                String zona = input.nextLine();
                                int c = 0;
                                for (Zona zona1 : zonas) {
                                    if (zona1.zona1 == zona) {
                                        System.out.println(zona1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la zona a buscar:");
                                String zona = input.nextLine();
                                String zonaMInus = zona.toLowerCase();
                                int c = 0;
                                for (Zona zona1 : zonas) {
                                    if (zona1.zona1.toLowerCase() == zonaMInus) {
                                        System.out.println(zona1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("2")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre de la zona a buscar: ");
                                String zona = input.nextLine();
                                int c = 0;
                                for (Zona zona2 : zonas) {
                                    if (zona2.zona2 == zona) {
                                        System.out.println(zona2);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la zona a buscar:");
                                String zona = input.nextLine();
                                String zonaMInus = zona.toLowerCase();
                                int c = 0;
                                for (Zona zona2 : zonas) {
                                    if (zona2.zona2.toLowerCase() == zonaMInus) {
                                        System.out.println(zona2);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("3")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre de la zona a buscar:");
                                String zona = input.nextLine();
                                int c = 0;
                                for (Zona zona3 : zonas) {
                                    if (zona3.zona3 == zona) {
                                        System.out.println(zona3);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la zona a buscar:");
                                String zona = input.nextLine();
                                String zona3MInus = zona.toLowerCase();
                                int c = 0;
                                for (Zona zona3 : zonas) {
                                    if (zona3.zona3.toLowerCase() == zona3MInus) {
                                        System.out.println(zona3);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("4")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre de la zona a buscar:");
                                String zona = input.nextLine();
                                int c = 0;
                                for (Zona zona4 : zonas) {
                                    if (zona4.zona4 == zona) {
                                        System.out.println(zona4);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la zona a buscar:");
                                String zona = input.nextLine();
                                String zona4MInus = zona.toLowerCase();
                                int c = 0;
                                for (Zona zona4 : zonas) {
                                    if (zona4.zona4.toLowerCase() == zona4MInus) {
                                        System.out.println(zona4);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("5")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre de la zona a buscar:");
                                String zona = input.nextLine();
                                int c = 0;
                                for (Zona zona5 : zonas) {
                                    if (zona5.zona5 == zona) {
                                        System.out.println(zona5);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la zona a buscar:");
                                String zona = input.nextLine();
                                String zona5MInus = zona.toLowerCase();
                                int c = 0;
                                for (Zona zona5 : zonas) {
                                    if (zona5.zona5.toLowerCase() == zona5MInus) {
                                        System.out.println(zona5);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("6")) {
                        break;
                    }
                }
            }else if (option.equals("4")) {
                if (cursos.size() == 0) {
                    System.out.println("---La lista de cursos se encuentra vacía---");
                    return;
                }
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. Nombre");
                    System.out.println("2. Codigo");
                    System.out.println("3. Intensidad horaria");
                    System.out.println("4. Volver");
                    System.out.println("-----------------------------");
                    System.out.println();
                    seleccion = input.next();
                    if (seleccion.equals("1")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre del curso a buscar:");
                                String curso = input.nextLine();
                                int c = 0;
                                for (Cursos curso1 : cursos) {
                                    if (curso1.nombre == curso) {
                                        System.out.println(curso1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro el curso---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el curso a buscar:");
                                String curso = input.nextLine();
                                String cursoMInus = curso.toLowerCase();
                                int c = 0;
                                for (Cursos curso1 : cursos) {
                                    if (curso1.nombre.toLowerCase() == cursoMInus) {
                                        System.out.println(curso1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("2")) {
                        String option2;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. valor exacto");
                            System.out.println("2. Valor minimo");
                            System.out.println("3. valor maximo");
                            System.out.println("4. Volver");
                            System.out.println();
                            option2 = input.next();
                            if (option2.equals("1")) {
                                System.out.println("-   Ingrese el codigo a buscar: ");
                                int codigo = input.nextInt();
                                int c=0;
                                for (Cursos curso : cursos) {
                                    if (curso.codigo == codigo) {
                                        System.out.println(curso);
                                        c++;
                                    }
                                }if (c==0){
                                    System.out.println("---El codigo a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese el codigo a buscar: ");
                                int cidigo = input.nextInt();
                                int c = 0;
                                for (Cursos curso : cursos) {
                                    if (curso.codigo >= cidigo) {
                                        System.out.println(curso);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No hay codigos que mostrar---");
                                }
                            } else if (option2.equals("3")) {
                                System.out.println("-   Ingrese el codigo a buscar:");
                                int codigo = input.nextInt();
                                int c = 0;
                                for (Cursos curso : cursos) {
                                    if (curso.codigo <= codigo) {
                                        System.out.println(curso);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No hay codigos que mostrar---");
                                }
                            } else if (option2.equals("4")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("3")) {
                        String option2;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor minimo");
                            System.out.println("3. valor maximo");
                            System.out.println("4. Volver");
                            System.out.println();
                            option2 = input.next();
                            if (option2.equals("1")) {
                                System.out.println("-   Ingrese la intesidad de hora a buscar:");
                                int codigo = input.nextInt();
                                int c=0;
                                for (Cursos curso : cursos) {
                                    if (curso.intesidadHora == codigo) {
                                        System.out.println(curso);
                                        c++;
                                    }
                                }if (c==0){
                                    System.out.println("---La intensidad de hora a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese la intesidad de hora a buscar:");
                                int codigo = input.nextInt();
                                int c = 0;
                                for (Cursos curso : cursos) {
                                    if (curso.codigo >= codigo) {
                                        System.out.println(curso);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No hay intensidad de hora que mostrar---");
                                }
                            } else if (option2.equals("3")) {
                                System.out.println("-   Ingrese la intesidad de hora a buscar:");
                                int codigo = input.nextInt();
                                int c = 0;
                                for (Cursos curso : cursos) {
                                    if (curso.codigo <= codigo) {
                                        System.out.println(curso);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No hay intensidad de hora que mostrar---");
                                }
                            } else if (option2.equals("4")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("4")) {
                        break;
                    }
                }
            }else if (option.equals("5")) {
                if (usuarios.size() == 0) {
                    System.out.println("---La lista de usuarios se encuentra vacía---");
                    return;
                }
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. Cedula");
                    System.out.println("2. Nombre");
                    System.out.println("3. Apellido");
                    System.out.println("4. Correo");
                    System.out.println("5. Volver");
                    System.out.println("-----------------------------");
                    System.out.println();
                    seleccion = input.next();
                    if (seleccion.equals("1")) {
                        String option2;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. valor exacto");
                            System.out.println("2. Valor minimo");
                            System.out.println("3. valor maximo");
                            System.out.println("4. Volver");
                            System.out.println();
                            option2 = input.next();
                            if (option2.equals("1")) {
                                System.out.println("-   Ingrese la cedula a buscar:");
                                int cedula = input.nextInt();
                                int c=0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.cedula == cedula) {
                                        System.out.println(usuario);
                                        c++;
                                    }
                                }if (c==0){
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese la cedula a buscar:");
                                int cedula = input.nextInt();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.cedula >= cedula) {
                                        System.out.println(usuario);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("3")) {
                                System.out.println("-   Ingrese la cedula a buscar:");
                                int cedula = input.nextInt();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.cedula <= cedula) {
                                        System.out.println(usuario);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("4")) {
                                break;
                            }
                        }
                    }
                    else if (seleccion.equals("2")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre a buscar:");
                                String nombre = input.nextLine();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.nombre == nombre) {
                                        System.out.println(usuario);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro el nombre---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el nombre a buscar:");
                                String nombre = input.nextLine();
                                String nombreMInus = nombre.toLowerCase();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.nombre.toLowerCase() == nombreMInus) {
                                        System.out.println(usuario);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro el nombre---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("3")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el apellido a buscar:");
                                String apellido = input.nextLine();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.apellido == apellido) {
                                        System.out.println(usuario);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro el apellido---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el apellido a buscar:");
                                String apellido = input.nextLine();
                                String apellidoMInus = apellido.toLowerCase();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.apellido.toLowerCase() == apellidoMInus) {
                                        System.out.println(usuario);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro el apellido---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }
                    else if (seleccion.equals("4")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el correo a buscar: ");
                                String correo = input.nextLine();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.correo == correo) {
                                        System.out.println(usuario);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro el correo---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el correo a buscar: ");
                                String correo = input.nextLine();
                                String correoMInus = correo.toLowerCase();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.correo.toLowerCase() == correoMInus) {
                                        System.out.println(usuario);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro ningun correo---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }
                    else if (seleccion.equals("5")) {
                        break;
                    }
                }
            }else if (option.equals("6")) {
                if (entrenadores.size() == 0) {
                    System.out.println("---La lista de entrenadores se encuentra vacía---");
                    return;
                }
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. Cedula");
                    System.out.println("2. Nombre");
                    System.out.println("3. Apellido");
                    System.out.println("4. Correo");
                    System.out.println("5. Volver");
                    System.out.println("-----------------------------");
                    System.out.println();
                    seleccion = input.next();
                    if (seleccion.equals("1")) {
                        String option2;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor minimo");
                            System.out.println("3. valor maximo");
                            System.out.println("4. Volver");
                            System.out.println();
                            option2 = input.next();
                            if (option2.equals("1")) {
                                System.out.println("-   Ingrese la cedula a buscar:");
                                int cedula = input.nextInt();
                                int c=0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.documento == cedula) {
                                        System.out.println(entrenadore);
                                        c++;
                                    }
                                }if (c==0){
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese la cedula a buscar:");
                                int cedula = input.nextInt();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.documento >= cedula) {
                                        System.out.println(entrenadore);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("3")) {
                                System.out.println("-   Ingrese la cedula a buscar:");
                                int cedula = input.nextInt();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.documento <= cedula) {
                                        System.out.println(entrenadore);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("4")) {
                                break;
                            }
                        }
                    }
                    else if (seleccion.equals("2")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre a buscar: ");
                                String nombre = input.nextLine();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.nombre == nombre) {
                                        System.out.println(entrenadore);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró el nombre---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el nombre a buscar: ");
                                String nombre = input.nextLine();
                                String nombreMInus = nombre.toLowerCase();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.nombre.toLowerCase() == nombreMInus) {
                                        System.out.println(entrenadore);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró el nombre---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("3")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el apellido a buscar: ");
                                String apellido = input.nextLine();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.apellido == apellido) {
                                        System.out.println(entrenadore);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró el apellido---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el apellido a buscar: ");
                                String apellido = input.nextLine();
                                String apellidoMInus = apellido.toLowerCase();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.apellido.toLowerCase() == apellidoMInus) {
                                        System.out.println(entrenadore);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró el apellido---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }
                    else if (seleccion.equals("4")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-------------------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el correo a buscar: ");
                                String correo = input.nextLine();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.correo == correo) {
                                        System.out.println(entrenadore);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró el correo---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("Ingrese el correo a buscar");
                                String correo = input.nextLine();
                                String correoMInus = correo.toLowerCase();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.correo.toLowerCase() == correoMInus) {
                                        System.out.println(entrenadore);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró ningun correo---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }
                    else if (seleccion.equals("5")) {
                        break;
                    }
                }
            }else if (option.equals("7")) {
                if (rutinas.size() == 0) {
                    System.out.println("---la lista de rutinas se encuentra vacía---");
                    return;
                }
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("-------       Busqueda de Rutinas       -------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. Rutina 1");
                    System.out.println("2. Rutina 2");
                    System.out.println("3. Rutina 3");
                    System.out.println("4. Rutina 4");
                    System.out.println("5. Rutina 5");
                    System.out.println("6. Volver");
                    System.out.println("------------------------------------------------");
                    System.out.println();
                    seleccion = input.next();
                    if (seleccion.equals("1")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre de la rutina a buscar: ");
                                String rutina = input.nextLine();
                                int c = 0;
                                for (Rutinas rutina1 : rutinas) {
                                    if (rutina1.rutina1== rutina) {
                                        System.out.println(rutina1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la rutina a buscar:");
                                String rutina = input.nextLine();
                                String rutinaMInus = rutina.toLowerCase();
                                int c = 0;
                                for (Rutinas rutina1 : rutinas) {
                                    if (rutina1.rutina2.toLowerCase() == rutinaMInus) {
                                        System.out.println(rutina1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("2")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre de la rutina a buscar: ");
                                String rutina = input.nextLine();
                                int c = 0;
                                for (Rutinas rutina2 : rutinas) {
                                    if (rutina2.rutina2 == rutina) {
                                        System.out.println(rutina2);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la rutina a buscar: ");
                                String rutina = input.nextLine();
                                String rutinaMInus = rutina.toLowerCase();
                                int c = 0;
                                for (Rutinas rutina2 : rutinas) {
                                    if (rutina2.rutina2.toLowerCase() == rutinaMInus) {
                                        System.out.println(rutina2);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("3")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre de la rutina a buscar: ");
                                String rutina = input.nextLine();
                                int c = 0;
                                for (Rutinas rutina3 : rutinas) {
                                    if (rutina3.rutina3 == rutina) {
                                        System.out.println(rutina3);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la rutina a buscar: ");
                                String rutina = input.nextLine();
                                String rutina3MInus = rutina.toLowerCase();
                                int c = 0;
                                for (Rutinas rutina3 : rutinas) {
                                    if (rutina3.rutina3.toLowerCase() == rutina3MInus) {
                                        System.out.println(rutina3);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("4")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("-   Ingrese el nombre de la rutina a buscar: ");
                                String rutina = input.nextLine();
                                int c = 0;
                                for (Rutinas rutina4 : rutinas) {
                                    if (rutina4.rutina4 == rutina) {
                                        System.out.println(rutina4);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la rutina---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la rutina a buscar:   ");
                                String rutina = input.nextLine();
                                String rutina4MInus = rutina.toLowerCase();
                                int c = 0;
                                for (Rutinas rutina4 : rutinas) {
                                    if (rutina4.rutina4.toLowerCase() == rutina4MInus) {
                                        System.out.println(rutina4);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("5")) {
                        String option1;
                        while (true) {
                            System.out.println();
                            System.out.println("-----------------------------");
                            System.out.println("Seleccione la opcion que desee ");
                            System.out.println("1. Valor exacto");
                            System.out.println("2. Valor sin considerar mayusculas");
                            System.out.println("3. Volver");
                            System.out.println();
                            option1 = input.next();
                            if (option1.equals("1")) {
                                System.out.println("---Ingrese el nombre de la rutina a buscar---");
                                String rutina = input.nextLine();
                                int c = 0;
                                for (Rutinas rutina5 : rutinas) {
                                    if (rutina5.rutina5 == rutina) {
                                        System.out.println(rutina5);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la rutina---");
                                }
                            }else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la rutina a buscar: ");
                                String rutina = input.nextLine();
                                String rutina5MInus = rutina.toLowerCase();
                                int c = 0;
                                for (Rutinas rutina5 : rutinas) {
                                    if (rutina5.rutina5.toLowerCase() == rutina5MInus) {
                                        System.out.println(rutina5);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    }else if (seleccion.equals("6")) {
                        break;
                    }
                }
            }else if (option.equals("8")) {
                break;
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
