import org.json.simple.parser.ParseException;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sistema {
    public static int idActual = 1;
    public static int idZona = 1;
    public static String ruta = "src/main/resources/database";
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Gimnasios> gimnasios = new ArrayList<>();
    public static ArrayList<Sede> sedes = new ArrayList<>();
    public static ArrayList<Zona> zonas = new ArrayList<>();
    public static ArrayList<Rutinas> rutinas = new ArrayList<>();
    public static ArrayList<saludoIngreso> saludo = new ArrayList<>();
    public static ArrayList<Entrenadores> entrenadores = new ArrayList<>();
    public static ArrayList<Cursos> cursos = new ArrayList<>();


    public static void main(String[] args) {
        String option;
        Usuario nuevoUsuario = new Usuario(10298832, "Andres","Garcia","andres123@gmail.com","andres123");
        usuarios.add(nuevoUsuario);
        Gimnasios nuevoGym = new Gimnasios(8210923,"SmartFit","SMF","Calle Austral",4567382);
        gimnasios.add(nuevoGym);
        Sede nuevaSede = new Sede("Centro","Medellin","Calle 38");
        sedes.add(nuevaSede);
        Entrenadores nuevoEntrenador = new Entrenadores(104920392,"Carlos","Alzate","alzate91@yahoo.es");
        entrenadores.add(nuevoEntrenador);
        while (true) {
            System.out.println();
            System.out.println("-------      Menú principal del sistema       -------");
            System.out.println("Bienvenido al sistema del Gimnasio");
            System.out.println("Escoja una opcion");
            System.out.println("1. Ingresar");
            System.out.println("2. Registrarse ");
            System.out.println("0. salir");
            System.out.println("------------------------------------------------------");
            System.out.println();
            option = input.next();
            if (option.equals("1")) {
                ingresar();
            } else if (option.equals("2")) {
                registrarse();
            } else if (option.equals("0")) {
                break;
            }
        }
    }

    public static void ingresar() {
        System.out.println("-                   Iniciar sesión                   -");
        System.out.println("-   Ingrese su numero de documento/correo electronico:");
        String ingreso = input.next();
        boolean res;
        try {
            Integer.parseInt(ingreso);
            res = true;
        } catch (NumberFormatException excepcion) {
            res = false;
        }
        if (res == true) {
            int docingreso = Integer.parseInt(ingreso);
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i).cedula == docingreso) {
                    saludo.clear();
                    saludoIngreso ingreso1 = new saludoIngreso(usuarios.get(i).nombre, usuarios.get(i).apellido);
                    saludo.add(ingreso1);
                    System.out.println("-   Ingrese la contraseña:");
                    String Contraseña = input.next();
                    if (usuarios.get(i).password.equals(Contraseña)) {
                        System.out.println("---¡Ingreso exitoso!---");
                        menuPrincipal();
                        return;
                    } else {
                        System.out.println("--Contraseña incorrecta---");
                        return;
                    }
                }
            }
            System.out.println("---Este documento no se encuentra en la base de datos---");
        } else if (res == false && ingreso.contains("@") == true) {
            for (int i = 0; i < usuarios.size(); i++) {
                saludo.clear();
                saludoIngreso ingreso1 = new saludoIngreso(usuarios.get(i).nombre, usuarios.get(i).apellido);
                saludo.add(ingreso1);
                if (usuarios.get(i).correo.equals(ingreso)) {
                    System.out.println("-   Ingrese la contraseña: ");
                    String Contraseña = input.next();
                    if (usuarios.get(i).password.equals(Contraseña)) {
                        System.out.println("---¡Ingreso exitoso!---");
                        menuPrincipal();
                    } else {
                        System.out.println("---Contraseña incorrecta---");
                        return;
                    }
                }
            }
            System.out.println("---Este correo no se encuentra en la base de datos.---");

        } else {
            System.out.println("---Ingresaste valores invalidos.---");
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
        String nombre = input.next();
        System.out.println("-   Ingrese su apellido: ");
        String apellido = input.next();
        System.out.println("-   Ingrese su dirección de correo electronico completo.");
        String correo = input.next();
        for (Usuario usuario : usuarios) {
            if (usuario.correo.equals(correo)) {
                System.out.println("---El correo electronico ingresado ya existe.---");
                return;
            }
        }
        if (!(correo.contains("@"))) {
            System.out.println("---El correo ingresado es invalido.---");
            return;
        }
        System.out.println("-   Ingrese la contraseña:");
        String contra = input.next();
        input.nextLine();
        Usuario nuevoUsuario = new Usuario(documento, nombre, apellido, correo, contra);
        usuarios.add(nuevoUsuario);
        System.out.println("---¡Registro exitoso!---");
        ingresar();
    }


    public static void menuPrincipal() {
        String option;
        while (true) {
            System.out.println();
            System.out.println("-------       Menú principal       -------");
            System.out.println("Hola " + saludo);
            System.out.println("Escoja una opción");
            System.out.println("1. Administración");
            System.out.println("2. Busqueda ");
            System.out.println("3. Diagnostico de inconsistencias");
            System.out.println("4. Guardar ");
            System.out.println("5. Salir y cancelar ");
            System.out.println("-------------------------------------------");
            System.out.println();
            option = input.next();
            if (option.equals("1")) {
                administracion();
            } else if (option.equals("2")) {
                busqueda();
            } else if (option.equals("3")) {
                diagnostico();
            } else if (option.equals("4")) {
                guardar();
            } else if (option.equals("5")) {
                String opcion;
                System.out.println("-----------------------------");
                System.out.println("si se sale en este momento");
                System.out.println("se perderan los cambios sin");
                System.out.println("guardar ");
                System.out.println("¿Seguro que desea salir? ");
                System.out.println("escoja una opcion");
                System.out.println("1. Yes");
                System.out.println("2. No ");
                System.out.println("-----------------------------");
                System.out.println();
                opcion = input.next();
                if (opcion.equals("1")) {
                    break;
                } else if (opcion.equals("2")) {
                    input.nextLine();
                }
            }
        }
    }

    public static void administracion() {
        String option;
        while (true) {
            System.out.println();
            System.out.println("-------       Administración       -------");
            System.out.println("escoja una opcion");
            System.out.println("1. Gimnasios");
            System.out.println("2. Sedes ");
            System.out.println("3. Zonas");
            System.out.println("4. Rutinas ");
            System.out.println("5. Entrenadores ");
            System.out.println("6. Usuarios ");
            System.out.println("7. Cursos");
            System.out.println("0. Cancelar ");
            System.out.println("------------------------------------------");
            System.out.println();
            option = input.next();
            if (option.equals("1")) {
                Gimnasios();
            } else if (option.equals("2")) {
                Sedes();
            } else if (option.equals("3")) {
                Zonas();
            } else if (option.equals("4")) {
                Rutinas();
            } else if (option.equals("5")) {
                Entrenador();
            } else if (option.equals("6")) {
                Usuarios();
            } else if (option.equals("7")) {
                Cursos();
            } else if (option.equals("0")) {
                break;
            }
        }
    }

    public static void Gimnasios() {
        String option;
        while (true) {
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
            option = input.next();
            if (option.equals("1")) {
                if (gimnasios.size() == 0) {
                    System.out.println("---La lista de gimnasios se encuentra vacía.---");
                    return;
                }
                System.out.println("---Lista de gimnasios---");
                for (Gimnasios gimnasio : gimnasios) {
                    System.out.println(gimnasio);
                }
            } else if (option.equals("2")) {
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
                String nombreGym = input.next();
                System.out.println("-   Ingrese las siglas: ");
                String siglas = input.next();
                input.nextLine();
                for (Gimnasios gimnasio : gimnasios) {
                    if (gimnasio.siglas.equals(siglas)) {
                        System.out.println("---Estas siglas ya existen---");
                        return;
                    }
                }
                System.out.println("-   Ingrese la direccion: ");
                String direccion = input.nextLine();
                System.out.println("-   Ingrese el telefono del gimnasio: ");
                int telefono = input.nextInt();
                Gimnasios nuevoGym = new Gimnasios(nit, nombreGym, siglas, direccion, telefono);
                gimnasios.add(nuevoGym);
                System.out.println("---¡Registro del nuevo Gimnasio exitoso!---");
            } else if (option.equals("3")) {
                System.out.println("-------       Editar Gimnasio       -------");
                System.out.println("1. Seleccionar por NIT");
                System.out.println("2. Seleccionar por Siglas");
                String seleccion = input.next();
                if (seleccion.equals("1")) {
                    System.out.println("---Seleccionar por NIT---");
                    System.out.println("-   Ingrese el NIT: ");
                    String nitString = input.next();
                    String nitReplace = nitString.replace(".", "");
                    int intNIT = Integer.parseInt(nitReplace);
                    boolean comprobante = false;
                    for (Gimnasios gyms : gimnasios) {
                        if (gyms.nit == intNIT) {
                            comprobante = true;
                            System.out.println("NIT: " + gyms.nit);
                            input.nextLine();
                            String nuevoNIT = input.nextLine();
                            String lastNIT = nuevoNIT.replace(".", "");
                            System.out.println("Nombre: " + gyms.nombre);
                            String nuevoNombre = input.nextLine();
                            System.out.println("Siglas: " + gyms.siglas);
                            String nuevaSigla = input.nextLine();
                            System.out.println("Dirección: " + gyms.direccion);
                            String nuevaDir = input.nextLine();
                            System.out.println("Telefono: " + gyms.telefono);
                            String nuevoTel = input.nextLine();
                            String nTel = nuevoTel.replace(" ", "");
                            System.out.println("---¿Desea guardar los cambios?---");
                            System.out.println("---Ingrese Y o N según el caso.---");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String opcionGuardar = input.next();
                            if (opcionGuardar.equals("Y")) {
                                if (!nuevoNIT.isEmpty()) {
                                    int intLastNIT = Integer.parseInt(lastNIT);
                                    gyms.nit = intLastNIT;
                                }
                                if (!nuevoNombre.isEmpty()) {
                                    gyms.nombre = nuevoNombre;
                                }
                                if (!nuevaSigla.isEmpty()) {
                                    gyms.siglas = nuevaSigla;
                                }
                                if (!nuevaDir.isEmpty()) {
                                    gyms.direccion = nuevaDir;
                                }
                                if (!nuevoTel.isEmpty()) {
                                    int intTel = Integer.parseInt(nTel);
                                    gyms.telefono = intTel;
                                }
                                System.out.println("---Guardado exitoso.---");
                                break;
                            } else if (opcionGuardar.equals("N")) {
                                System.out.println("---Guardado cancelado.---");
                                break;
                            } else {
                                System.out.println("---Ingresa Y o N---");
                            }
                        }
                    }
                    if (!comprobante) {
                        System.out.println("---No se encontró ningún gimnasio con este NIT---");
                    }
                } else if (seleccion.equals("2")) {
                    System.out.println("---Seleccionar por Siglas---");
                    System.out.println("-   Ingrese las Siglas: ");
                    String Siglas = input.next();
                    boolean comprobante = false;
                    for (Gimnasios gymsigla : gimnasios) {
                        if (gymsigla.siglas.equalsIgnoreCase(Siglas)) {
                            comprobante = true;
                            System.out.println("Siglas: " + gymsigla.siglas);
                            input.nextLine();
                            String nuevaSigla = input.nextLine();
                            System.out.println("NIT: " + gymsigla.nit);
                            String nuevoNIT = input.nextLine();
                            String lastNIT = nuevoNIT.replace(".", "");
                            System.out.println("Nombre: " + gymsigla.nombre);
                            String nuevoNombre = input.nextLine();
                            System.out.println("Dirección: " + gymsigla.direccion);
                            String nuevaDir = input.nextLine();
                            System.out.println("Telefono: " + gymsigla.telefono);
                            String nuevoTel = input.nextLine();
                            String nTel = nuevoTel.replace(" ", "");
                            System.out.println("---¿Desea guardar los cambios?---");
                            System.out.println("---Ingrese Y o N según el caso.---");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String opcionGuardar = input.next();
                            if (opcionGuardar.equals("Y")) {
                                if (!nuevoNIT.isEmpty()) {
                                    int intLastNIT = Integer.parseInt(lastNIT);
                                    gymsigla.nit = intLastNIT;
                                }
                                if (!nuevoNombre.isEmpty()) {
                                    gymsigla.nombre = nuevoNombre;
                                }
                                if (!nuevaSigla.isEmpty()) {
                                    gymsigla.siglas = nuevaSigla;
                                }
                                if (!nuevaDir.isEmpty()) {
                                    gymsigla.direccion = nuevaDir;
                                }
                                if (!nuevoTel.isEmpty()) {
                                    int intTel = Integer.parseInt(nTel);
                                    gymsigla.telefono = intTel;
                                }
                                System.out.println("---Guardado exitoso.---");
                                break;
                            } else if (opcionGuardar.equals("N")) {
                                System.out.println("---Guardado cancelado.---");
                                break;
                            } else {
                                System.out.println("---Ingresa Y o N---");
                            }

                        }
                    }
                    if (comprobante == false) {
                        System.out.println("---No se encontró ningún gimnasio con estas siglas---");
                    }
                }

            } else if (option.equals("4")) {
                System.out.println("-------       Eliminar Gimnasios       -------");
                System.out.println("1. Seleccionar por NIT");
                System.out.println("2. Seleccionar por Siglas");
                String opcioneliminar = input.next();
                if (opcioneliminar.equals("1")) {
                    System.out.println("-   Ingrese el NIT: ");
                    String nitEliminar = input.next();
                    String nitEdit = nitEliminar.replace(".", "");
                    int nitEntero = Integer.parseInt(nitEdit);
                    boolean comprobar = false;
                    for (Gimnasios gymeliminar : gimnasios) {
                        if (gymeliminar.nit == nitEntero) {
                            comprobar = true;
                            System.out.println("¿Está seguro que desea eliminar el elemento?");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String yesornot = input.next();
                            if (yesornot.equals("Y")) {
                                gimnasios.remove(gymeliminar);
                                System.out.println("---El gimnasio ha sido eliminado.---");
                                break;
                            } else if (yesornot.equals("N")) {
                                System.out.println("---Se ha cancelado la eliminación del gimnasio.---");
                                break;
                            } else {
                                System.out.println("---Ingrese Y o N según el caso---");
                            }
                        }
                    }
                    if (comprobar == false) {
                        System.out.println("---No se encontró ningún gimnasio con este NIT---");
                    }
                } else if (opcioneliminar.equals("2")) {
                    System.out.println("-   Ingrese las siglas: ");
                    String siglasEliminar = input.next();
                    boolean check = false;
                    for (Gimnasios gymdel : gimnasios) {
                        if (gymdel.siglas.equalsIgnoreCase(siglasEliminar)) {
                            check = true;
                            System.out.println("¿Está seguro que desea eliminar el elemento?");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String yesornot = input.next();
                            if (yesornot.equals("Y")) {
                                gimnasios.remove(gymdel);
                                System.out.println("---El gimnasio ha sido eliminado.---");
                                break;
                            } else if (yesornot.equals("N")) {
                                System.out.println("---Se ha cancelado la eliminación del gimnasio.---");
                                break;
                            } else {
                                System.out.println("---Ingrese Y o N según el caso---");
                            }
                        }
                    }
                    if (check == false) {
                        System.out.println("---No se encontró ningún gimnasio con estas siglas---");
                    }
                }
            } else if (option.equals("5")) {
                break;
            }
        }
    }

    public static void Sedes() {
        String option;
        while (true) {
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
            option = input.next();
            if (option.equals("1")) {
                if (sedes.size() == 0) {
                    System.out.println("---La lista de sedes se encuentra vacía.---");
                    return;
                }
                System.out.println("---Lista de sedes---");
                for (Sede sede : sedes) {
                    System.out.println(sede);
                }
            } else if (option.equals("2")) {
                System.out.println("-   Ingrese el nombre de la sede:");
                String nombreSede = input.next();
                System.out.println("-   Ingrese la ciudad donde está ubicada");
                String ciudadSede = input.next();
                input.nextLine();
                System.out.println("-   Ingrese la dirección:");
                String direccionSede = input.nextLine();
                Sede nuevaSede = new Sede(nombreSede, ciudadSede, direccionSede);
                sedes.add(nuevaSede);
                System.out.println("---La sede se registró correctamente.---");
            } else if (option.equals("3")) {
                System.out.println("-------       Editar Sedes       -------");
                System.out.println("1. Seleccionar por Dirección");
                String seleccion = input.next();
                if (seleccion.equals("1")) {
                    System.out.println("---Seleccionar por Dirección---");
                    System.out.println("-   Ingrese la dirección: ");
                    input.nextLine();
                    String dirBuscar = input.nextLine();
                    boolean comprobante = false;
                    for (Sede sedesdir : sedes) {
                        if (sedesdir.direccion.equalsIgnoreCase(dirBuscar)) {
                            comprobante = true;
                            System.out.println("Dirección: " + sedesdir.direccion);
                            String nuevaDir = input.nextLine();
                            System.out.println("Nombre: " + sedesdir.nombre);
                            String nuevoNombre = input.nextLine();
                            System.out.println("Ciudad: " + sedesdir.ciudad);
                            String nuevaCiudad = input.nextLine();
                            System.out.println("---¿Desea guardar los cambios?---");
                            System.out.println("---Ingrese Y o N según el caso.---");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String opcionGuardar = input.next();
                            if (opcionGuardar.equals("Y")) {
                                if (!nuevaDir.isEmpty()) {
                                    sedesdir.direccion = nuevaDir;
                                }
                                if (!nuevoNombre.isEmpty()) {
                                    sedesdir.nombre = nuevoNombre;
                                }
                                if (!nuevaCiudad.isEmpty()) {
                                    sedesdir.ciudad = nuevaCiudad;
                                }
                                System.out.println("---Guardado exitoso.---");
                                break;
                            } else if (opcionGuardar.equals("N")) {
                                System.out.println("---Guardado cancelado.---");
                                break;
                            } else {
                                System.out.println("---Ingresa Y o N---");
                                break;
                            }
                        }
                    }
                    if (comprobante == false) {
                        System.out.println("---No se encontró ninguna sede con esta dirección---");
                    }
                } else {
                    System.out.println("Ingrese 1-> Dirección");
                }

            } else if (option.equals("4")) {
                System.out.println("-------       Eliminar Sedes       -------");
                System.out.println("1. Seleccionar por Dirección");
                String opcioneliminar = input.next();
                if (opcioneliminar.equals("1")) {
                    System.out.println("-   Ingrese la dirección de la sede a eliminar");
                    input.nextLine();
                    String dirEliminar = input.nextLine();
                    boolean comprobar = false;
                    for (Sede sedeeliminar : sedes) {
                        if (sedeeliminar.direccion.equalsIgnoreCase(dirEliminar)) {
                            comprobar = true;
                            System.out.println("¿Está seguro que desea eliminar el elemento?");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String yesornot = input.next();
                            if (yesornot.equals("Y")) {
                                sedes.remove(sedeeliminar);
                                System.out.println("---La sede ha sido eliminada---");
                                break;
                            } else if (yesornot.equals("N")) {
                                System.out.println("---Se ha cancelado la eliminación de la sede.---");
                                break;
                            } else {
                                System.out.println("---Ingrese Y o N según el caso---");
                            }
                        }
                    }
                    if (comprobar == false) {
                        System.out.println("---No se encontró ninguna sede con esta dirección---");
                    }
                }
            } else if (option.equals("5")) {
                break;
            }
        }
    }


    public static void Zonas() {
        String option;
        while (true) {
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
            option = input.next();
            if (option.equals("1")) {
                if (zonas.size() == 0) {
                    System.out.println("---La lista de zonas se encuentra vacía---");
                    return;
                }
                System.out.println("---Lista de zonas---");
                for (Zona zona : zonas) {
                    System.out.println(zona);
                }
            } else if (option.equals("2")) {
                System.out.println("-   Ingrese el nombre de la zona 1:");
                String zona1 = input.next();
                System.out.println("-   Ingrese el nombre de la zona 2:");
                String zona2 = input.next();
                System.out.println("-   Ingrese el nombre de la zona 3:");
                String zona3 = input.next();
                System.out.println("-   Ingrese el nombre de la zona 4:");
                String zona4 = input.next();
                System.out.println("-   Ingrese el nombre de la zona 5:");
                String zona5 = input.next();
                Zona nuevaZona = new Zona(zona1, zona2, zona3, zona4, zona5, idZona);
                zonas.add(nuevaZona);
                idZona++;
                System.out.println("---Las zonas se registraron correctamente---");
            } else if (option.equals("3")) {
                System.out.println("-------       Editar Zona       -------");
                System.out.println("-   Ingrese el ID de la lista de las zonas: ");
                String idString = input.next();
                String idReplace = idString.replace(".", "");
                int intID = Integer.parseInt(idReplace);
                boolean comprobante = false;
                for (Zona zona : zonas) {
                    if (zona.id == intID) {
                        comprobante = true;
                        System.out.println("ID: " + zona.id);
                        input.nextLine();
                        String nuevoID = input.nextLine();
                        String lastID = nuevoID.replace(".", "");
                        System.out.println("zona 1: " + zona.zona1);
                        String nuevoNombre1 = input.nextLine();
                        System.out.println("zona 2: " + zona.zona2);
                        String nuevoNombre2 = input.nextLine();
                        System.out.println("zona 3: " + zona.zona3);
                        String nuevoNombre3 = input.nextLine();
                        System.out.println("zona 4: " + zona.zona4);
                        String nuevoNombre4 = input.nextLine();
                        System.out.println("zona 5: " + zona.zona5);
                        String nuevoNombre5 = input.nextLine();
                        System.out.println("---¿Desea guardar los cambios?---");
                        System.out.println("---Ingrese Y o N según el caso.---");
                        System.out.println("Y -> Confirmar");
                        System.out.println("N -> Cancelar");
                        String opcionGuardar = input.next();
                        if (opcionGuardar.equals("Y")) {
                            if (!nuevoID.isEmpty()) {
                                int intLastID = Integer.parseInt(lastID);
                                zona.id = intLastID;
                            }
                            if (!nuevoNombre1.isEmpty()) {
                                zona.zona1 = nuevoNombre1;
                            }
                            if (!nuevoNombre2.isEmpty()) {
                                zona.zona2 = nuevoNombre2;
                            }
                            if (!nuevoNombre3.isEmpty()) {
                                zona.zona3 = nuevoNombre3;
                            }
                            if (!nuevoNombre4.isEmpty()) {
                                zona.zona4 = nuevoNombre4;
                            }
                            if (!nuevoNombre5.isEmpty()) {
                                zona.zona5 = nuevoNombre5;
                            }
                            System.out.println("---Guardado exitoso.---");
                            break;
                        } else if (opcionGuardar.equals("N")) {
                            System.out.println("---Guardado cancelado.---");
                            break;
                        } else {
                            System.out.println("---Ingresa Y o N---");
                        }
                    }
                }
                if (!comprobante) {
                    System.out.println("---No se encontró ningúna lista de Zonas con ese ID---");
                }
            } else if (option.equals("4")) {
                System.out.println("-------       Eliminar Zona       -------");
                System.out.println("-   Ingrese el ID de la lista de zonas: ");
                String idEliminar = input.next();
                String idEdit = idEliminar.replace(".", "");
                int idEntero = Integer.parseInt(idEdit);
                boolean comprobar = false;
                for (Zona listeliminar : zonas) {
                    if (listeliminar.id == idEntero) {
                        comprobar = true;
                        System.out.println("¿Está seguro que desea eliminar el elemento?");
                        System.out.println("Y -> Confirmar");
                        System.out.println("N -> Cancelar");
                        String yesornot = input.next();
                        if (yesornot.equals("Y")) {
                            zonas.remove(listeliminar);
                            System.out.println("---La lista de Rutinas ha sido eliminada.---");
                            break;
                        } else if (yesornot.equals("N")) {
                            System.out.println("---Se ha cancelado la eliminación de la lista de Rutinas.---");
                            break;
                        } else {
                            System.out.println("---Ingrese Y o N según el caso---");
                        }
                    }
                }
                if (!comprobar) {
                    System.out.println("---No se encontró ningúna lista de zonas con este ID---");
                }
            }
        }
    }

    public static void Rutinas() {
        String option;
        while (true) {
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
            option = input.next();
            if (option.equals("1")) {
                if (rutinas.size() == 0) {
                    System.out.println("---La lista de rutinas se encuentra vacía.---");
                    return;
                }
                System.out.println("---Lista de rutinas---");
                for (Rutinas rutina : rutinas) {
                    System.out.println(rutina);
                }
            } else if (option.equals("2")) {
                System.out.println("-   Ingrese el nombre de la rutina 1");
                String rutina1 = input.next();
                System.out.println("-   Ingrese el nombre de la rutina 2");
                String rutina2 = input.next();
                System.out.println("-   Ingrese el nombre de la rutina 3");
                String rutina3 = input.next();
                System.out.println("-   Ingrese el nombre de la rutina 4");
                String rutina4 = input.next();
                System.out.println("-   Ingrese el nombre de la rutina 5");
                String rutina5 = input.next();
                Rutinas nuevaRutina = new Rutinas(rutina1, rutina2, rutina3, rutina4, rutina5, idActual);
                idActual++;
                rutinas.add(nuevaRutina);
                System.out.println("---Las rutinas se registraron correctamente---");
            } else if (option.equals("3")) {
                System.out.println("-------       Editar Rutina       -------");
                System.out.println("-   Ingrese el ID de la lista de las rutinas: ");
                String idString = input.next();
                String idReplace = idString.replace(".", "");
                int intID = Integer.parseInt(idReplace);
                boolean comprobante = false;
                for (Rutinas rutina : rutinas) {
                    if (rutina.id == intID) {
                        comprobante = true;
                        System.out.println("ID: " + rutina.id);
                        input.nextLine();
                        String nuevoID = input.nextLine();
                        String lastID = nuevoID.replace(".", "");
                        System.out.println("Rutina 1: " + rutina.rutina1);
                        String nuevoNombre1 = input.nextLine();
                        System.out.println("Rutina 2: " + rutina.rutina2);
                        String nuevoNombre2 = input.nextLine();
                        System.out.println("Rutina 3: " + rutina.rutina3);
                        String nuevoNombre3 = input.nextLine();
                        System.out.println("Rutina 4: " + rutina.rutina4);
                        String nuevoNombre4 = input.nextLine();
                        System.out.println("Rutina 5: " + rutina.rutina5);
                        String nuevoNombre5 = input.nextLine();
                        System.out.println("---¿Desea guardar los cambios?---");
                        System.out.println("---Ingrese Y o N según el caso.---");
                        System.out.println("Y -> Confirmar");
                        System.out.println("N -> Cancelar");
                        String opcionGuardar = input.next();
                        if (opcionGuardar.equals("Y")) {
                            if (!nuevoID.isEmpty()) {
                                int intLastID = Integer.parseInt(lastID);
                                rutina.id = intLastID;
                            }
                            if (!nuevoNombre1.isEmpty()) {
                                rutina.rutina1 = nuevoNombre1;
                            }
                            if (!nuevoNombre2.isEmpty()) {
                                rutina.rutina2 = nuevoNombre2;
                            }
                            if (!nuevoNombre3.isEmpty()) {
                                rutina.rutina3 = nuevoNombre3;
                            }
                            if (!nuevoNombre4.isEmpty()) {
                                rutina.rutina4 = nuevoNombre4;
                            }
                            if (!nuevoNombre5.isEmpty()) {
                                rutina.rutina5 = nuevoNombre5;
                            }
                            System.out.println("---Guardado exitoso.---");
                            break;
                        } else if (opcionGuardar.equals("N")) {
                            System.out.println("---Guardado cancelado.---");
                            break;
                        } else {
                            System.out.println("---Ingresa Y o N---");
                        }
                    }
                }
                if (!comprobante) {
                    System.out.println("---No se encontró ningúna lista de Rutina con ese ID---");
                }
            } else if (option.equals("4")) {
                System.out.println("-   Ingrese el ID de la lista de rutinas: ");
                String idEliminar = input.next();
                String idEdit = idEliminar.replace(".", "");
                int idEntero = Integer.parseInt(idEdit);
                boolean comprobar = false;
                for (Rutinas listeliminar : rutinas) {
                    if (listeliminar.id == idEntero) {
                        comprobar = true;
                        System.out.println("¿Está seguro que desea eliminar el elemento?");
                        System.out.println("Y -> Confirmar");
                        System.out.println("N -> Cancelar");
                        String yesornot = input.next();
                        if (yesornot.equals("Y")) {
                            rutinas.remove(listeliminar);
                            System.out.println("---La lista de Rutinas ha sido eliminada.---");
                            break;
                        } else if (yesornot.equals("N")) {
                            System.out.println("---Se ha cancelado la eliminación de la lista de Rutinas.---");
                            break;
                        } else {
                            System.out.println("---Ingrese Y o N según el caso---");
                        }
                    }
                }
                if (!comprobar) {
                    System.out.println("---No se encontró ningúna lista de rutinas con este ID---");
                }
            } else if (option.equals("5")) {
                break;
            }
        }
    }

    public static void Entrenador() {
        String option;
        while (true) {
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
            option = input.next();
            if (option.equals("1")) {
                if (entrenadores.size() == 0) {
                    System.out.println("---La lista de entrenadores se encuentra vacía.---");
                    return;
                }
                System.out.println("---Lista de entrenadores---");
                for (Entrenadores entrenador : entrenadores) {
                    System.out.println(entrenador);
                }
            } else if (option.equals("2")) {
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
                String nombre = input.next();
                System.out.println("-   Ingrese el apellido: ");
                String apellido = input.next();
                System.out.println("-   Ingrese el correo: ");
                String correo = input.next();
                int conteo = 0;
                for (Usuario usuario : usuarios) {
                    if (usuario.correo.equals(correo)) {
                        System.out.println("---El correo ya existe---");
                        return;
                    }
                }
                for (int i = 0; i < correo.length(); i++) {
                    if (correo.charAt(i) == '@') {
                        conteo += 1;
                    }
                }
                if (conteo < 1) {
                    System.out.println("----Ingresaste un correo invalido---");
                    return;
                }
                Entrenadores nuevoEntrenador = new Entrenadores(documento, nombre, apellido, correo);
                entrenadores.add(nuevoEntrenador);
                System.out.println("---¡Registro exitoso!---");
            } else if (option.equals("3")) {
                System.out.println("-------       Editar Entrenador       -------");
                System.out.println("1. Seleccionar por documento");
                System.out.println("2. Seleccionar por correo");
                String seleccion = input.next();
                if (seleccion.equals("1")) {
                    System.out.println("---Seleccionar por documento---");
                    System.out.println("-   Ingrese el documento: ");
                    String docString = input.next();
                    String docReplace = docString.replace(".", "");
                    int intDOC = Integer.parseInt(docReplace);
                    boolean comprobante = false;
                    for (Entrenadores entrenador : entrenadores) {
                        if (entrenador.documento == intDOC) {
                            comprobante = true;
                            System.out.println("documento: " + entrenador.documento);
                            input.nextLine();
                            String nuevoDOC = input.nextLine();
                            String lastDOC = nuevoDOC.replace(".", "");
                            System.out.println("Nombre: " + entrenador.nombre);
                            String nuevoNombre = input.nextLine();
                            System.out.println("Apellido: " + entrenador.apellido);
                            String nuevoApellido = input.nextLine();
                            System.out.println("Correo: " + entrenador.correo);
                            String nuevoCorr = input.nextLine();
                            System.out.println("---¿Desea guardar los cambios?---");
                            System.out.println("---Ingrese Y o N según el caso.---");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String opcionGuardar = input.next();
                            if (opcionGuardar.equals("Y")) {
                                if (!nuevoDOC.isEmpty()) {
                                    int intLastDOC = Integer.parseInt(lastDOC);
                                    entrenador.documento = intLastDOC;
                                }
                                if (!nuevoNombre.isEmpty()) {
                                    entrenador.nombre = nuevoNombre;
                                }
                                if (!nuevoApellido.isEmpty()) {
                                    entrenador.apellido = nuevoApellido;
                                }
                                if (!nuevoCorr.isEmpty()) {
                                    entrenador.correo = nuevoCorr;
                                }
                                System.out.println("---Guardado exitoso.---");
                            } else if (opcionGuardar.equals("N")) {
                                System.out.println("---Guardado cancelado.---");
                            } else {
                                System.out.println("---Ingresa Y o N---");
                            }
                        }
                    }
                    if (!comprobante) {
                        System.out.println("---No se encontró ningún Entrenador con este documento---");
                    }
                } else if (seleccion.equals("2")) {
                    System.out.println("---Seleccionar por correo---");
                    System.out.println("-   Ingrese el correo: ");
                    String corrString = input.next();
                    boolean comprobante = false;
                    for (Entrenadores entrenador : entrenadores) {
                        if (entrenador.correo.equals(corrString)) {
                            comprobante = true;
                            System.out.println("documento: " + entrenador.documento);
                            input.nextLine();
                            String nuevoDOC = input.nextLine();
                            String lastDOC = nuevoDOC.replace(".", "");
                            System.out.println("Nombre: " + entrenador.nombre);
                            String nuevoNombre = input.nextLine();
                            System.out.println("Apellido: " + entrenador.apellido);
                            String nuevoApellido = input.nextLine();
                            System.out.println("Correo: " + entrenador.correo);
                            String nuevoCorr = input.nextLine();
                            System.out.println("---¿Desea guardar los cambios?---");
                            System.out.println("---Ingrese Y o N según el caso.---");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String opcionGuardar = input.next();
                            if (opcionGuardar.equals("Y")) {
                                if (!nuevoDOC.isEmpty()) {
                                    int intLastDOC = Integer.parseInt(lastDOC);
                                    entrenador.documento = intLastDOC;
                                }
                                if (!nuevoNombre.isEmpty()) {
                                    entrenador.nombre = nuevoNombre;
                                }
                                if (!nuevoApellido.isEmpty()) {
                                    entrenador.apellido = nuevoApellido;
                                }
                                if (!nuevoCorr.isEmpty()) {
                                    entrenador.correo = nuevoCorr;
                                }
                                System.out.println("---Guardado exitoso.---");
                            } else if (opcionGuardar.equals("N")) {
                                System.out.println("---Guardado cancelado.---");
                            } else {
                                System.out.println("---Ingresa Y o N---");
                            }
                        }
                    }
                    if (!comprobante) {
                        System.out.println("---No se encontró ningún Entrenador con este correo---");
                    }
                }
            } else if (option.equals("4")) {
                System.out.println("-------       Eliminar Entrenadores       -------");
                System.out.println("1. Seleccionar por documento");
                System.out.println("2. Seleccionar por correo");
                String opcioneliminar = input.next();
                if (opcioneliminar.equals("1")) {
                    System.out.println("-   Ingrese el documento: ");
                    String DocEliminar = input.next();
                    String docEdit = DocEliminar.replace(".", "");
                    int docEntero = Integer.parseInt(docEdit);
                    boolean comprobar = false;
                    for (Entrenadores doceliminar : entrenadores) {
                        if (doceliminar.documento == docEntero) {
                            comprobar = true;
                            System.out.println("¿Está seguro que desea eliminar el elemento?");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String yesornot = input.next();
                            if (yesornot.equals("Y")) {
                                entrenadores.remove(doceliminar);
                                System.out.println("---El entrenador ha sido eliminado.---");
                                break;
                            } else if (yesornot.equals("N")) {
                                System.out.println("---Se ha cancelado la eliminación del entrenador.---");
                                break;
                            } else {
                                System.out.println("---Ingrese Y o N según el caso---");
                            }
                        }
                    }
                    if (!comprobar) {
                        System.out.println("---No se encontró ningún entrenador con este documento---");
                    }
                } else if (opcioneliminar.equals("2")) {
                    System.out.println("-   Ingrese el correo: ");
                    String CorreoEliminar = input.next();
                    boolean check = false;
                    for (Entrenadores Corrreodel : entrenadores) {
                        if (Corrreodel.correo.equals(CorreoEliminar)) {
                            check = true;
                            System.out.println("¿Está seguro que desea eliminar el elemento?");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String yesornot = input.next();
                            if (yesornot.equals("Y")) {
                                entrenadores.remove(Corrreodel);
                                System.out.println("---El entrenador ha sido eliminado.---");
                                break;
                            } else if (yesornot.equals("N")) {
                                System.out.println("---Se ha cancelado la eliminación del entrenador.---");
                                break;
                            } else {
                                System.out.println("---Ingrese Y o N según el caso---");
                            }
                        }
                    }
                    if (!check) {
                        System.out.println("---No se encontró ningún entrenador con este correo---");
                    }
                }
            } else if (option.equals("5")) {
                break;
            }
        }
    }

    public static void Usuarios() {
        String option;
        while (true) {
            System.out.println();
            System.out.println("--------       Menú Usuarios       -------");
            System.out.println("Escoja una opcion");
            System.out.println("1. Ver lista de usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario ");
            System.out.println("5. Cancelar ");
            System.out.println("------------------------------------------");
            System.out.println();
            option = input.next();
            if (option.equals("1")) {
                if (usuarios.size() == 0) {
                    System.out.println("---La lista de usuarios se encuentra vacía---");
                    return;
                }
                System.out.println("---Lista de usuarios---");
                visualizar();
            } else if (option.equals("2")) {
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
                String nombre = input.next();
                System.out.println("-   Ingrese su apellido: ");
                String apellido = input.next();
                System.out.println("-   Ingrese su dirección de correo electronico completo.");
                String correo = input.next();
                for (Usuario usuario : usuarios) {
                    if (usuario.correo.equals(correo)) {
                        System.out.println("---El correo electronico ingresado ya existe.---");
                        return;
                    }
                }
                if (!(correo.contains("@"))) {
                    System.out.println("---El correo ingresado es invalido.---");
                    return;
                }
                System.out.println("-   Ingrese la contraseña:");
                String contra = input.next();
                input.nextLine();
                Usuario nuevoUsuario = new Usuario(documento, nombre, apellido, correo, contra);
                usuarios.add(nuevoUsuario);
                System.out.println("---¡Registro exitoso!---");
            } else if (option.equals("3")) {
                System.out.println("-------       Editar Usuario       -------");
                System.out.println("1. Seleccionar por documento");
                System.out.println("2. Seleccionar por correo");
                String seleccion = input.next();
                if (seleccion.equals("1")) {
                    System.out.println("---Seleccionar por documento---");
                    System.out.println("-   Ingrese el documento: ");
                    String docString = input.next();
                    String docReplace = docString.replace(".", "");
                    int intDOC = Integer.parseInt(docReplace);
                    boolean comprobante = false;
                    for (Usuario usuario : usuarios) {
                        if (usuario.cedula == intDOC) {
                            comprobante = true;
                            System.out.println("documento: " + usuario.cedula);
                            input.nextLine();
                            String nuevoDOC = input.nextLine();
                            String lastDOC = nuevoDOC.replace(".", "");
                            System.out.println("Nombre: " + usuario.nombre);
                            String nuevoNombre = input.nextLine();
                            System.out.println("Apellido: " + usuario.apellido);
                            String nuevoApellido = input.nextLine();
                            System.out.println("Correo: " + usuario.correo);
                            String nuevoCorr = input.nextLine();
                            System.out.println("Contraseña: " + usuario.password);
                            String nuevacontra = input.nextLine();
                            System.out.println("---¿Desea guardar los cambios?---");
                            System.out.println("---Ingrese Y o N según el caso.---");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String opcionGuardar = input.next();
                            if (opcionGuardar.equals("Y")) {
                                if (!nuevoDOC.isEmpty()) {
                                    int intLastDOC = Integer.parseInt(lastDOC);
                                    usuario.cedula = intLastDOC;
                                }
                                if (!nuevoNombre.isEmpty()) {
                                    usuario.nombre = nuevoNombre;
                                }
                                if (!nuevoApellido.isEmpty()) {
                                    usuario.apellido = nuevoApellido;
                                }
                                if (!nuevoCorr.isEmpty()) {
                                    usuario.correo = nuevoCorr;
                                }
                                if (!nuevacontra.isEmpty()) {
                                    usuario.password = nuevacontra;
                                }
                                System.out.println("---Guardado exitoso.---");
                            } else if (opcionGuardar.equals("N")) {
                                System.out.println("---Guardado cancelado.---");
                            } else {
                                System.out.println("---Ingresa Y o N---");
                            }
                        }
                    }
                    if (!comprobante) {
                        System.out.println("---No se encontró ningún Usuario con este documento---");
                    }
                } else if (seleccion.equals("2")) {
                    System.out.println("---Seleccionar por correo---");
                    System.out.println("-   Ingrese el correo: ");
                    String corrString = input.next();
                    boolean comprobante = false;
                    for (Usuario usuario : usuarios) {
                        if (usuario.correo.equals(corrString)) {
                            comprobante = true;
                            System.out.println("documento: " + usuario.cedula);
                            input.nextLine();
                            String nuevoDOC = input.nextLine();
                            String lastDOC = nuevoDOC.replace(".", "");
                            System.out.println("Nombre: " + usuario.nombre);
                            String nuevoNombre = input.nextLine();
                            System.out.println("Apellido: " + usuario.apellido);
                            String nuevoApellido = input.nextLine();
                            System.out.println("Correo: " + usuario.correo);
                            String nuevoCorr = input.nextLine();
                            System.out.println("Contraseña: " + usuario.password);
                            String nuevacontra = input.nextLine();
                            System.out.println("---¿Desea guardar los cambios?---");
                            System.out.println("---Ingrese Y o N según el caso.---");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String opcionGuardar = input.next();
                            if (opcionGuardar.equals("Y")) {
                                if (!nuevoDOC.isEmpty()) {
                                    int intLastDOC = Integer.parseInt(lastDOC);
                                    usuario.cedula = intLastDOC;
                                }
                                if (!nuevoNombre.isEmpty()) {
                                    usuario.nombre = nuevoNombre;
                                }
                                if (!nuevoApellido.isEmpty()) {
                                    usuario.apellido = nuevoApellido;
                                }
                                if (!nuevoCorr.isEmpty()) {
                                    usuario.correo = nuevoCorr;
                                }
                                if (!nuevacontra.isEmpty()) {
                                    usuario.password = nuevacontra;
                                }
                                System.out.println("---Guardado exitoso.---");
                            } else if (opcionGuardar.equals("N")) {
                                System.out.println("---Guardado cancelado.---");
                            } else {
                                System.out.println("---Ingresa Y o N---");
                            }
                        }
                    }
                    if (!comprobante) {
                        System.out.println("---No se encontró ningún Usuario con este correo---");
                    }
                }
            } else if (option.equals("4")) {
                System.out.println("-------       Eliminar Usuario       -------");
                System.out.println("1. Seleccionar por documento");
                System.out.println("2. Seleccionar por correo");
                String opcioneliminar = input.next();
                if (opcioneliminar.equals("1")) {
                    System.out.println("-   Ingrese el documento: ");
                    String DocEliminar = input.next();
                    String docEdit = DocEliminar.replace(".", "");
                    int docEntero = Integer.parseInt(docEdit);
                    boolean comprobar = false;
                    for (Usuario doceliminar : usuarios) {
                        if (doceliminar.cedula == docEntero) {
                            comprobar = true;
                            System.out.println("¿Está seguro que desea eliminar el elemento?");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String yesornot = input.next();
                            if (yesornot.equals("Y")) {
                                usuarios.remove(doceliminar);
                                System.out.println("---El Usuario ha sido eliminado.---");
                                break;
                            } else if (yesornot.equals("N")) {
                                System.out.println("---Se ha cancelado la eliminación del Usuario.---");
                                break;
                            } else {
                                System.out.println("---Ingrese Y o N según el caso---");
                            }
                        }
                    }
                    if (!comprobar) {
                        System.out.println("---No se encontró ningún Usuario con este documento---");
                    }
                } else if (opcioneliminar.equals("2")) {
                    System.out.println("-   Ingrese el correo: ");
                    String CorreoEliminar = input.next();
                    boolean check = false;
                    for (Usuario Corrreodel : usuarios) {
                        if (Corrreodel.correo.equals(CorreoEliminar)) {
                            check = true;
                            System.out.println("¿Está seguro que desea eliminar el elemento?");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String yesornot = input.next();
                            if (yesornot.equals("Y")) {
                                usuarios.remove(Corrreodel);
                                System.out.println("---El Usuario ha sido eliminado.---");
                                break;
                            } else if (yesornot.equals("N")) {
                                System.out.println("---Se ha cancelado la eliminación del Usuario.---");
                                break;
                            } else {
                                System.out.println("---Ingrese Y o N según el caso---");
                            }
                        }
                    }
                    if (!check) {
                        System.out.println("---No se encontró ningún Usuario con este correo---");
                    }
                }
            } else if (option.equals("5")) {
                break;
            }
        }
    }

    public static void Cursos() {
        String option;
        while (true) {
            System.out.println();
            System.out.println("-------       Menú Cursos       -------");
            System.out.println("Escoja una opcion");
            System.out.println("1. Ver cursos");
            System.out.println("2. Crear cursos");
            System.out.println("3. Editar cursos");
            System.out.println("4. Eliminar curso ");
            System.out.println("5. Cancelar ");
            System.out.println("------------------------------------------");
            System.out.println();
            option = input.next();
            if (option.equals("1")) {
                if (cursos.size() == 0) {
                    System.out.println("---La lista de cursos se encuentra vacía.---");
                    return;
                }
                System.out.println("---Lista de gimnasios---");
                for (Cursos curso : cursos) {
                    System.out.println(curso);
                }
            } else if (option.equals("2")) {
                System.out.println("-------       Crear nuevo Curso       -------");
                System.out.println("-   Ingrese el codigo del curso: ");
                int codigo = input.nextInt();
                if (codigo < 0) {
                    System.out.println("---Ingresaste un codigo invalido.---");
                    return;
                }
                for (Cursos curso : cursos) {
                    if (curso.codigo == codigo) {
                        System.out.println("---Ya existe un curso con este codigo---");
                        return;
                    }
                }
                System.out.println("-   Ingrese el nombre del nuevo curso: ");
                input.nextLine();
                String nombreCurso = input.nextLine();

                for (Cursos curso : cursos) {
                    if (curso.nombre.equals(nombreCurso)) {
                        System.out.println("---Este nombre de curso ya existe---");
                        return;
                    }
                }
                System.out.println("-   Ingrese la intensidad del curso: ");
                int intensidad = input.nextInt();
                if (intensidad < 0) {
                    System.out.println("---Ingrese un numero positivo.---");
                    return;
                }
                Cursos nuevoCurso = new Cursos(nombreCurso, codigo, intensidad);
                cursos.add(nuevoCurso);
                System.out.println("---¡Registro del nuevo curso exitoso!---");
            } else if (option.equals("3")) {
                System.out.println("-------       Editar Curso       -------");
                System.out.println("1. Seleccionar por Codigo");
                System.out.println("2. Seleccionar por Nombre");
                String seleccion = input.next();
                if (seleccion.equals("1")) {
                    System.out.println("---Seleccionar por Codigo---");
                    System.out.println("-   Ingrese el Codigo: ");
                    int code = input.nextInt();
                    boolean comprobante = false;
                    for (Cursos curso : cursos) {
                        if (curso.codigo == code) {
                            comprobante = true;
                            input.nextLine();
                            System.out.println("Codigo: " + curso.codigo);
                            String nuevoCode = input.nextLine();
                            System.out.println("Nombre: " + curso.nombre);
                            String nuevoNombre = input.nextLine();
                            System.out.println("Intensidad: " + curso.intesidadHora);
                            String nuevaIntensidad = input.nextLine();
                            System.out.println("---¿Desea guardar los cambios?---");
                            System.out.println("---Ingrese Y o N según el caso.---");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String opcionGuardar = input.next();
                            if (opcionGuardar.equals("Y")) {
                                if (!nuevoCode.isEmpty()) {
                                    int intLastCode = Integer.parseInt(nuevoCode);
                                    curso.codigo = intLastCode;
                                }
                                if (!nuevoNombre.isEmpty()) {
                                    curso.nombre = nuevoNombre;
                                }
                                if (!nuevoCode.isEmpty()) {
                                    int intInten = Integer.parseInt(nuevaIntensidad);
                                    curso.intesidadHora = intInten;
                                }
                                System.out.println("---Guardado exitoso.---");
                                break;
                            } else if (opcionGuardar.equals("N")) {
                                System.out.println("---Guardado cancelado.---");
                                break;
                            } else {
                                System.out.println("---Ingresa Y o N---");
                            }
                        }
                    }
                    if (!comprobante) {
                        System.out.println("---No se encontró ningún curso con este codigo---");
                    }
                } else if (seleccion.equals("2")) {
                    System.out.println("---Seleccionar por nombre---");
                    System.out.println("-   Ingrese el nombre: ");
                    input.nextLine();
                    String nombre = input.nextLine();
                    boolean comprobante = false;
                    for (Cursos curso : cursos) {
                        if (curso.nombre.equalsIgnoreCase(nombre)) {
                            comprobante = true;
                            input.nextLine();
                            System.out.println("Nombre: " + curso.nombre);
                            String nuevoNombre = input.nextLine();
                            System.out.println("Codigo: " + curso.codigo);
                            String nuevoCode = input.nextLine();
                            System.out.println("Intensidad: " + curso.intesidadHora);
                            String nuevaIntensidad = input.nextLine();
                            System.out.println("---¿Desea guardar los cambios?---");
                            System.out.println("---Ingrese Y o N según el caso.---");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String opcionGuardar = input.next();
                            if (opcionGuardar.equals("Y")) {
                                if (!nuevoCode.isEmpty()) {
                                    int intLastCode = Integer.parseInt(nuevoCode);
                                    curso.codigo = intLastCode;
                                }
                                if (!nuevoNombre.isEmpty()) {
                                    curso.nombre = nuevoNombre;
                                }
                                if (!nuevoCode.isEmpty()) {
                                    int intInten = Integer.parseInt(nuevaIntensidad);
                                    curso.intesidadHora = intInten;
                                }
                                System.out.println("---Guardado exitoso.---");
                                break;
                            } else if (opcionGuardar.equals("N")) {
                                System.out.println("---Guardado cancelado.---");
                                break;
                            } else {
                                System.out.println("---Ingresa Y o N---");
                            }
                        }
                    }
                    if (!comprobante) {
                        System.out.println("---No se encontró ningún curso con este codigo---");
                    }
                }

            } else if (option.equals("4")) {
                System.out.println("-------       Eliminar Cursos       -------");
                System.out.println("1. Seleccionar por Codigo");
                System.out.println("2. Seleccionar por Nombre");
                String opcioneliminar = input.next();
                if (opcioneliminar.equals("1")) {
                    System.out.println("-   Ingrese el Codigo: ");
                    String codeEliminar = input.next();
                    int codeEntero = Integer.parseInt(codeEliminar);
                    boolean comprobar = false;
                    for (Cursos cursoEliminar : cursos) {
                        if (cursoEliminar.codigo == codeEntero) {
                            comprobar = true;
                            System.out.println("¿Está seguro que desea eliminar el elemento?");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String yesornot = input.next();
                            if (yesornot.equals("Y")) {
                                cursos.remove(cursoEliminar);
                                System.out.println("---El curso ha sido eliminado.---");
                                break;
                            } else if (yesornot.equals("N")) {
                                System.out.println("---Se ha cancelado la eliminación del gimnasio.---");
                                break;
                            } else {
                                System.out.println("---Ingrese Y o N según el caso---");
                            }
                        }
                    }
                    if (comprobar == false) {
                        System.out.println("---No se encontró ningún curso con este codigo---");
                    }
                } else if (opcioneliminar.equals("2")) {
                    System.out.println("-   Ingrese el nombre: ");
                    String nombreEliminar = input.next();
                    boolean check = false;
                    for (Cursos cursoEliminar : cursos) {
                        if (cursoEliminar.nombre.equalsIgnoreCase(nombreEliminar)) {
                            check = true;
                            System.out.println("¿Está seguro que desea eliminar el elemento?");
                            System.out.println("Y -> Confirmar");
                            System.out.println("N -> Cancelar");
                            String yesornot = input.next();
                            if (yesornot.equals("Y")) {
                                cursos.remove(nombreEliminar);
                                System.out.println("---El curso ha sido eliminado.---");
                                break;
                            } else if (yesornot.equals("N")) {
                                System.out.println("---Se ha cancelado la eliminación del curso.---");
                                break;
                            } else {
                                System.out.println("---Ingrese Y o N según el caso---");
                            }
                        }
                    }
                    if (check == false) {
                        System.out.println("---No se encontró ningún curso con este nombre---");
                    }
                }
            } else if (option.equals("5")) {
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
                ArrayList<Gimnasios> Compagimnasios = new ArrayList<>();
                System.out.println(Compagimnasios + "este");
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
                    System.out.println("6. Mostrar todos los gimnasios");
                    System.out.println("7. Volver");
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
                                        c++;
                                        Compagimnasios.add(gimnasio);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual desea ordenar");
                                    System.out.println("1. Nit");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Siglas");
                                    System.out.println("4. Direccion");
                                    System.out.println("5. Telefono");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No hay NIT que mostrar---");
                                }
                            } else if (option1.equals("3")) {
                                System.out.println("-   Ingrese un valor de NIT: ");
                                int valorMaximo = input.nextInt();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.nit <= valorMaximo) {
                                        c++;
                                        Compagimnasios.add(gimnasio);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Nit");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Siglas");
                                    System.out.println("4. Direccion");
                                    System.out.println("5. Telefono");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    }
                                } else if (c == 0) {
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
                                input.nextLine();
                                String nombre = input.nextLine();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.nombre.equals(nombre)) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---El nombre a buscar no fue encontrado---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("Ingrese el nombre del Gym");
                                input.nextLine();
                                String nombre = input.nextLine();
                                String nombreMinu = nombre.toLowerCase();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.nombre.toLowerCase().equals(nombreMinu)) {
                                        c++;
                                        Compagimnasios.add(gimnasio);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Nit");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Siglas");
                                    System.out.println("4. Direccion");
                                    System.out.println("5. Telefono");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---El nombre a buscar no fue encontrado---");
                                }
                            } else if (option2.equals("3")) {
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
                                System.out.println("Ingrese las siglas del Gym");
                                input.nextLine();
                                String sigla = input.next();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.siglas.equals(sigla)) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---Las siglas a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("Ingrese las siglas del Gym");
                                input.nextLine();
                                String nombre = input.nextLine();
                                String nombreMinu = nombre.toLowerCase();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.siglas.toLowerCase().equals(nombreMinu)) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---Las siglas a buscar no se encuentra---");
                                }
                            } else if (option2.equals("3")) {
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
                                input.nextLine();
                                String direccion = input.next();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.direccion.equals(direccion)) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La direccion a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("Ingrese la direccion del Gym");
                                input.nextLine();
                                String direccion = input.nextLine();
                                String direccionMinu = direccion.toLowerCase();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.direccion.toLowerCase().equals(direccionMinu)) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La direccion a buscar no se encuentra---");
                                }
                            } else if (option2.equals("3")) {
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
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.telefono == numeroTelefono) {
                                        System.out.println(gimnasio);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---El telefono a buscar no se encunetra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese el numero de telefono a buscar: ");
                                int numero = input.nextInt();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.telefono >= numero) {
                                        c++;
                                        Compagimnasios.add(gimnasio);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Nit");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Siglas");
                                    System.out.println("4. Direccion");
                                    System.out.println("5. Telefono");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No hay telefonos que mostrar---");
                                }
                            } else if (option2.equals("3")) {
                                System.out.println("-   Ingrese el numero de telefono a buscar:");
                                int numero = input.nextInt();
                                int c = 0;
                                for (Gimnasios gimnasio : gimnasios) {
                                    if (gimnasio.telefono <= numero) {
                                        c++;
                                        Compagimnasios.add(gimnasio);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Nit");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Siglas");
                                    System.out.println("4. Direccion");
                                    System.out.println("5. Telefono");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                            Collections.reverse(Compagimnasios);
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                            System.out.println(Compagimnasios);
                                            Compagimnasios.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No hay telefonos que mostrar---");
                                }
                            } else if (option2.equals("4")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("6")) {
                        int c = 0;
                        for (Gimnasios gimnasio : gimnasios) {
                            c++;
                            Compagimnasios.add(gimnasio);
                        }
                        if (c != 0) {
                            System.out.println("Por cual dese ordenar");
                            System.out.println("1. Nit");
                            System.out.println("2. Nombre");
                            System.out.println("3. Siglas");
                            System.out.println("4. Direccion");
                            System.out.println("5. Telefono");
                            String compa = input.next();
                            if (compa.equals("1")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                    Collections.reverse(Compagimnasios);
                                    System.out.println(Compagimnasios);
                                    Compagimnasios.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(Compagimnasios, new GimnasioComparador("1"));
                                    System.out.println(Compagimnasios);


                                    Compagimnasios.clear();
                                }
                            } else if (compa.equals("2")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                    Collections.reverse(Compagimnasios);
                                    System.out.println(Compagimnasios);
                                    Compagimnasios.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(Compagimnasios, new GimnasioComparador("2"));
                                    System.out.println(Compagimnasios);
                                    Compagimnasios.clear();
                                }
                            } else if (compa.equals("3")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                    Collections.reverse(Compagimnasios);
                                    System.out.println(Compagimnasios);
                                    Compagimnasios.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(Compagimnasios, new GimnasioComparador("3"));
                                    System.out.println(Compagimnasios);
                                    Compagimnasios.clear();
                                }
                            } else if (compa.equals("4")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                    Collections.reverse(Compagimnasios);
                                    System.out.println(Compagimnasios);
                                    Compagimnasios.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(Compagimnasios, new GimnasioComparador("4"));
                                    System.out.println(Compagimnasios);
                                    Compagimnasios.clear();
                                }
                            } else if (compa.equals("5")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                    Collections.reverse(Compagimnasios);
                                    System.out.println(Compagimnasios);
                                    Compagimnasios.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(Compagimnasios, new GimnasioComparador("5"));
                                    System.out.println(Compagimnasios);
                                    Compagimnasios.clear();
                                }
                            }
                        }
                    } else if (seleccion.equals("7")) {
                        break;
                    }
                }
            } else if (option.equals("2")) {
                if (sedes.size() == 0) {
                    System.out.println("---La lista de las sedes se encuentra vacía---");
                    return;
                }
                ArrayList<Sede> CompaSede = new ArrayList<>();
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. Nombre");
                    System.out.println("2. Direccion");
                    System.out.println("3. Ciudad");
                    System.out.println("4. Mostrar todas las sedes");
                    System.out.println("5. volver");
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
                                System.out.println("Ingrese el nombre de la sede a buscar");
                                input.nextLine();
                                String sede = input.nextLine();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.nombre.equals(sede)) {
                                        System.out.println(sede1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro el nombre de la sede---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("Ingrese el nombre de la sede a buscar");
                                input.nextLine();
                                String nombre = input.nextLine();
                                String nombreMInus = nombre.toLowerCase();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.nombre.toLowerCase().equals(nombreMInus)) {
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
                                input.nextLine();
                                String dire = input.nextLine();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.direccion.equals(dire)) {
                                        System.out.println(sede1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la direccion de la sede---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("Ingrese la direccion de la sede a buscar");
                                input.nextLine();
                                String direccion = input.nextLine();
                                String direccionMInus = direccion.toLowerCase();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.direccion.toLowerCase().equals(direccionMInus)) {
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
                                System.out.println("Ingrese la ciudad de la sede a buscar");
                                input.nextLine();
                                String ciudad = input.nextLine();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.ciudad.equals(ciudad)) {
                                        System.out.println(sede1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la ciudad de la sede---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-  Ingrese la ciudad de la sede a buscar: ");
                                input.nextLine();
                                String ciudad = input.nextLine();
                                String ciudadMInus = ciudad.toLowerCase();
                                int c = 0;
                                for (Sede sede1 : sedes) {
                                    if (sede1.ciudad.toLowerCase().equals(ciudadMInus)) {
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
                    } else if (seleccion.equals("4")) {
                        int c = 0;
                        for (Sede sede : sedes) {
                            c++;
                            CompaSede.add(sede);
                        }
                        if (c != 0) {
                            System.out.println("Por cual dese ordenar");
                            System.out.println("1. Nombre");
                            System.out.println("2. Direccion");
                            System.out.println("3. Ciudad");
                            String compa = input.next();
                            if (compa.equals("1")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaSede, new SedeComparador("1"));
                                    Collections.reverse(CompaSede);
                                    System.out.println(CompaSede);
                                    CompaSede.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaSede, new SedeComparador("1"));
                                    System.out.println(CompaSede);
                                    CompaSede.clear();
                                }
                            } else if (compa.equals("2")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaSede, new SedeComparador("2"));
                                    Collections.reverse(CompaSede);
                                    System.out.println(CompaSede);
                                    CompaSede.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaSede, new SedeComparador("2"));
                                    System.out.println(CompaSede);
                                    CompaSede.clear();
                                }
                            } else if (compa.equals("3")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaSede, new SedeComparador("3"));
                                    Collections.reverse(CompaSede);
                                    System.out.println(CompaSede);
                                    CompaSede.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaSede, new SedeComparador("3"));
                                    System.out.println(CompaSede);
                                    CompaSede.clear();
                                }
                            }
                        }
                    } else if (seleccion.equals("5")) {
                        break;
                    }
                }
            } else if (option.equals("3")) {
                if (zonas.size() == 0) {
                    System.out.println("---La lista de zonas se encuentra vacía---");
                    return;
                }
                ArrayList<Zona> CompaZona = new ArrayList<>();
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
                    System.out.println("6. Mostrar todas las zonas");
                    System.out.println("7. Volver");
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
                                input.nextLine();
                                String zona = input.nextLine();
                                int c = 0;
                                for (Zona zona1 : zonas) {
                                    if (zona1.zona1.equals(zona)) {
                                        System.out.println(zona1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la zona a buscar:");
                                input.nextLine();
                                String zona = input.nextLine();
                                String zonaMInus = zona.toLowerCase();
                                int c = 0;
                                for (Zona zona1 : zonas) {
                                    if (zona1.zona1.toLowerCase().equals(zonaMInus)) {
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
                    } else if (seleccion.equals("2")) {
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
                                input.nextLine();
                                String zona = input.nextLine();
                                int c = 0;
                                for (Zona zona2 : zonas) {
                                    if (zona2.zona2.equals(zona)) {
                                        System.out.println(zona2);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la zona a buscar:");
                                input.nextLine();
                                String zona = input.nextLine();
                                String zonaMInus = zona.toLowerCase();
                                int c = 0;
                                for (Zona zona2 : zonas) {
                                    if (zona2.zona2.toLowerCase().equals(zonaMInus)) {
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
                                System.out.println("-   Ingrese el nombre de la zona a buscar:");
                                input.nextLine();
                                String zona = input.nextLine();
                                int c = 0;
                                for (Zona zona3 : zonas) {
                                    if (zona3.zona3.equals(zona)) {
                                        System.out.println(zona3);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la zona a buscar:");
                                input.nextLine();
                                String zona = input.nextLine();
                                String zona3MInus = zona.toLowerCase();
                                int c = 0;
                                for (Zona zona3 : zonas) {
                                    if (zona3.zona3.toLowerCase().equals(zona3MInus)) {
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
                    } else if (seleccion.equals("4")) {
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
                                input.nextLine();
                                String zona = input.nextLine();
                                int c = 0;
                                for (Zona zona4 : zonas) {
                                    if (zona4.zona4.equals(zona)) {
                                        System.out.println(zona4);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la zona a buscar:");
                                input.nextLine();
                                String zona = input.nextLine();
                                String zona4MInus = zona.toLowerCase();
                                int c = 0;
                                for (Zona zona4 : zonas) {
                                    if (zona4.zona4.toLowerCase().equals(zona4MInus)) {
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
                    } else if (seleccion.equals("5")) {
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
                                input.nextLine();
                                String zona = input.nextLine();
                                int c = 0;
                                for (Zona zona5 : zonas) {
                                    if (zona5.zona5.equals(zona)) {
                                        System.out.println(zona5);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro la zona---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la zona a buscar:");
                                input.nextLine();
                                String zona = input.nextLine();
                                String zona5MInus = zona.toLowerCase();
                                int c = 0;
                                for (Zona zona5 : zonas) {
                                    if (zona5.zona5.toLowerCase().equals(zona5MInus)) {
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
                    } else if (seleccion.equals("6")) {
                        int c = 0;
                        for (Zona zona : zonas) {
                            c++;
                            CompaZona.add(zona);
                        }
                        if (c != 0) {
                            System.out.println("Por cual dese ordenar");
                            System.out.println("1. Zona1");
                            System.out.println("2. Zona2");
                            System.out.println("3. Zona3");
                            System.out.println("4. Zona4");
                            System.out.println("5. Zona5");
                            String compa = input.next();
                            if (compa.equals("1")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaZona, new ZonaComparador("1"));
                                    Collections.reverse(CompaZona);
                                    System.out.println(CompaZona);
                                    CompaZona.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaZona, new ZonaComparador("1"));
                                    System.out.println(CompaZona);
                                    CompaZona.clear();
                                }
                            } else if (compa.equals("2")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaZona, new ZonaComparador("2"));
                                    Collections.reverse(CompaZona);
                                    System.out.println(CompaZona);
                                    CompaZona.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaZona, new ZonaComparador("2"));
                                    System.out.println(CompaZona);
                                    CompaZona.clear();
                                }
                            } else if (compa.equals("3")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaZona, new ZonaComparador("3"));
                                    Collections.reverse(CompaZona);
                                    System.out.println(CompaZona);
                                    CompaZona.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaZona, new ZonaComparador("3"));
                                    System.out.println(CompaZona);
                                    CompaZona.clear();
                                }
                            } else if (compa.equals("4")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaZona, new ZonaComparador("4"));
                                    Collections.reverse(CompaZona);
                                    System.out.println(CompaZona);
                                    CompaZona.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaZona, new ZonaComparador("4"));
                                    System.out.println(CompaZona);
                                    CompaZona.clear();
                                }
                            } else if (compa.equals("5")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaZona, new ZonaComparador("5"));
                                    Collections.reverse(CompaZona);
                                    System.out.println(CompaZona);
                                    CompaZona.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaZona, new ZonaComparador("5"));
                                    System.out.println(CompaZona);
                                    CompaZona.clear();
                                }
                            }
                        }
                    } else if (seleccion.equals("7")) {
                        break;
                    }
                }
            } else if (option.equals("4")) {
                if (cursos.size() == 0) {
                    System.out.println("---La lista de cursos se encuentra vacía---");
                    return;
                }
                ArrayList<Cursos> CompaCurso = new ArrayList<>();
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. Nombre");
                    System.out.println("2. Codigo");
                    System.out.println("3. Intensidad horaria");
                    System.out.println("4. Mostrar todos los cursos");
                    System.out.println("5. Volver");
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
                                input.nextLine();
                                String curso = input.nextLine();
                                int c = 0;
                                for (Cursos curso1 : cursos) {
                                    if (curso1.nombre.equals(curso)) {
                                        System.out.println(curso1);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---No se encontro el curso---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el curso a buscar:");
                                input.nextLine();
                                String curso = input.nextLine();
                                String cursoMInus = curso.toLowerCase();
                                int c = 0;
                                for (Cursos curso1 : cursos) {
                                    if (curso1.nombre.toLowerCase().equals(cursoMInus)) {
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
                    } else if (seleccion.equals("2")) {
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
                                int c = 0;
                                for (Cursos curso : cursos) {
                                    if (curso.codigo == codigo) {
                                        System.out.println(curso);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---El codigo a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese el codigo a buscar: ");
                                int cidigo = input.nextInt();
                                int c = 0;
                                for (Cursos curso : cursos) {
                                    if (curso.codigo >= cidigo) {
                                        c++;
                                        CompaCurso.add(curso);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Nombre");
                                    System.out.println("2. Codigo");
                                    System.out.println("3. IntensidadHora");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("1"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("1"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("2"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("2"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("3"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("3"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No hay codigos que mostrar---");
                                }
                            } else if (option2.equals("3")) {
                                System.out.println("-   Ingrese el codigo a buscar:");
                                int codigo = input.nextInt();
                                int c = 0;
                                for (Cursos curso : cursos) {
                                    if (curso.codigo <= codigo) {
                                        c++;
                                        CompaCurso.add(curso);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Nombre");
                                    System.out.println("2. Codigo");
                                    System.out.println("3. IntensidadHora");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("1"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("1"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("2"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("2"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("3"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("3"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No hay codigos que mostrar---");
                                }
                            } else if (option2.equals("4")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("3")) {
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
                                int c = 0;
                                for (Cursos curso : cursos) {
                                    if (curso.intesidadHora == codigo) {
                                        System.out.println(curso);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La intensidad de hora a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese la intesidad de hora a buscar:");
                                int codigo = input.nextInt();
                                int c = 0;
                                for (Cursos curso : cursos) {
                                    if (curso.codigo >= codigo) {
                                        c++;
                                        CompaCurso.add(curso);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Nombre");
                                    System.out.println("2. Codigo");
                                    System.out.println("3. IntensidadHora");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("1"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("1"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("2"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("2"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("3"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("3"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No hay intensidad de hora que mostrar---");
                                }
                            } else if (option2.equals("3")) {
                                System.out.println("-   Ingrese la intesidad de hora a buscar:");
                                int codigo = input.nextInt();
                                int c = 0;
                                for (Cursos curso : cursos) {
                                    if (curso.codigo <= codigo) {
                                        c++;
                                        CompaCurso.add(curso);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Nombre");
                                    System.out.println("2. Codigo");
                                    System.out.println("3. IntensidadHora");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("1"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("1"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("2"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("2"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaCurso, new CursoComparador("3"));
                                            Collections.reverse(CompaCurso);
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaCurso, new CursoComparador("3"));
                                            System.out.println(CompaCurso);
                                            CompaCurso.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No hay intensidad de hora que mostrar---");
                                }
                            } else if (option2.equals("4")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("4")) {
                        int c = 0;
                        for (Cursos curso : cursos) {
                            System.out.println(curso);
                            c++;
                            CompaCurso.add(curso);
                        }
                        if (c != 0) {
                            System.out.println("Por cual dese ordenar");
                            System.out.println("1. Nombre");
                            System.out.println("2. Codigo");
                            System.out.println("3. IntensidadHora");
                            String compa = input.next();
                            if (compa.equals("1")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaCurso, new CursoComparador("1"));
                                    Collections.reverse(CompaCurso);
                                    System.out.println(CompaCurso);
                                    CompaCurso.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaCurso, new CursoComparador("1"));
                                    System.out.println(CompaCurso);
                                    CompaCurso.clear();
                                }
                            } else if (compa.equals("2")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaCurso, new CursoComparador("2"));
                                    Collections.reverse(CompaCurso);
                                    System.out.println(CompaCurso);
                                    CompaCurso.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaCurso, new CursoComparador("2"));
                                    System.out.println(CompaCurso);
                                    CompaCurso.clear();
                                }
                            } else if (compa.equals("3")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaCurso, new CursoComparador("3"));
                                    Collections.reverse(CompaCurso);
                                    System.out.println(CompaCurso);
                                    CompaCurso.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaCurso, new CursoComparador("3"));
                                    System.out.println(CompaCurso);
                                    CompaCurso.clear();
                                }
                            }
                        }
                    } else if (seleccion.equals("5")) {
                        break;
                    }
                }
            } else if (option.equals("5")) {
                if (usuarios.size() == 0) {
                    System.out.println("---La lista de usuarios se encuentra vacía---");
                    return;
                }
                ArrayList<Usuario> CompaUsuario = new ArrayList<>();
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. Cedula");
                    System.out.println("2. Nombre");
                    System.out.println("3. Apellido");
                    System.out.println("4. Correo");
                    System.out.println("5. Mostrar todos los usuarios");
                    System.out.println("6. Volver");
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
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.cedula == cedula) {
                                        System.out.println(usuario);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese la cedula a buscar:");
                                int cedula = input.nextInt();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.cedula >= cedula) {
                                        c++;
                                        CompaUsuario.add(usuario);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Cedula");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("3")) {
                                System.out.println("-   Ingrese la cedula a buscar:");
                                int cedula = input.nextInt();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.cedula <= cedula) {
                                        c++;
                                        CompaUsuario.add(usuario);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Cedula");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("4")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("2")) {
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
                                input.nextLine();
                                String nombre = input.nextLine();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.nombre.equals(nombre)) {
                                        c++;
                                        CompaUsuario.add(usuario);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Cedula");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontro el nombre---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el nombre a buscar:");
                                input.nextLine();
                                String nombre = input.nextLine();
                                String nombreMInus = nombre.toLowerCase();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.nombre.toLowerCase().equals(nombreMInus)) {
                                        c++;
                                        CompaUsuario.add(usuario);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Cedula");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontro el nombre---");
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
                                System.out.println("-   Ingrese el apellido a buscar:");
                                input.nextLine();
                                String apellido = input.nextLine();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.apellido.equals(apellido)) {
                                        c++;
                                        CompaUsuario.add(usuario);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Cedula");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontro el apellido---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el apellido a buscar:");
                                input.nextLine();
                                String apellido = input.nextLine();
                                String apellidoMInus = apellido.toLowerCase();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.apellido.toLowerCase().equals(apellidoMInus)) {
                                        c++;
                                        CompaUsuario.add(usuario);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Cedula");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontro el apellido---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("4")) {
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
                                input.nextLine();
                                String correo = input.nextLine();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.correo.equals(correo)) {
                                        c++;
                                        CompaUsuario.add(usuario);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Cedula");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontro el correo---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el correo a buscar: ");
                                input.nextLine();
                                String correo = input.nextLine();
                                String correoMInus = correo.toLowerCase();
                                int c = 0;
                                for (Usuario usuario : usuarios) {
                                    if (usuario.correo.toLowerCase().equals(correoMInus)) {
                                        c++;
                                        CompaUsuario.add(usuario);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Cedula");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            Collections.reverse(CompaUsuario);
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                            System.out.println(CompaUsuario);
                                            CompaUsuario.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontro ningun correo---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("5")) {
                        int c = 0;
                        for (Usuario usuario : usuarios) {
                            c++;
                            CompaUsuario.add(usuario);
                        }
                        if (c != 0) {
                            System.out.println("Por cual dese ordenar");
                            System.out.println("1. Cedula");
                            System.out.println("2. Nombre");
                            System.out.println("3. Apellido");
                            System.out.println("4. Correo");
                            String compa = input.next();
                            if (compa.equals("1")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                    Collections.reverse(CompaUsuario);
                                    System.out.println(CompaUsuario);
                                    CompaUsuario.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaUsuario, new UsuarioComparador("1"));
                                    System.out.println(CompaUsuario);
                                    CompaUsuario.clear();
                                }
                            } else if (compa.equals("2")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                    Collections.reverse(CompaUsuario);
                                    System.out.println(CompaUsuario);
                                    CompaUsuario.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaUsuario, new UsuarioComparador("2"));
                                    System.out.println(CompaUsuario);
                                    CompaUsuario.clear();
                                }
                            } else if (compa.equals("3")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                    Collections.reverse(CompaUsuario);
                                    System.out.println(CompaUsuario);
                                    CompaUsuario.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaUsuario, new UsuarioComparador("3"));
                                    System.out.println(CompaUsuario);
                                    CompaUsuario.clear();
                                }
                            } else if (compa.equals("4")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                    Collections.reverse(CompaUsuario);
                                    System.out.println(CompaUsuario);
                                    CompaUsuario.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaUsuario, new UsuarioComparador("4"));
                                    System.out.println(CompaUsuario);
                                    CompaUsuario.clear();
                                }
                            }
                        }
                    } else if (seleccion.equals("6")) {
                        break;
                    }
                }
            } else if (option.equals("6")) {
                if (entrenadores.size() == 0) {
                    System.out.println("---La lista de entrenadores se encuentra vacía---");
                    return;
                }
                ArrayList<Entrenadores> CompaEntrenadores = new ArrayList<>();
                String seleccion;
                while (true) {
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("Seleccione la opcion con la cual desea hacer la busqueda");
                    System.out.println("1. Cedula");
                    System.out.println("2. Nombre");
                    System.out.println("3. Apellido");
                    System.out.println("4. Correo");
                    System.out.println("5. Mostrar todos los entrenadores");
                    System.out.println("6. Volver");
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
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.documento == cedula) {
                                        System.out.println(entrenadore);
                                        c++;
                                    }
                                }
                                if (c == 0) {
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("2")) {
                                System.out.println("-   Ingrese la cedula a buscar:");
                                int cedula = input.nextInt();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.documento >= cedula) {
                                        c++;
                                        CompaEntrenadores.add(entrenadore);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("3")) {
                                System.out.println("-   Ingrese la cedula a buscar:");
                                int cedula = input.nextInt();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.documento <= cedula) {
                                        c++;
                                        CompaEntrenadores.add(entrenadore);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---La cedula a buscar no se encuentra---");
                                }
                            } else if (option2.equals("4")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("2")) {
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
                                input.nextLine();
                                String nombre = input.nextLine();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.nombre.equals(nombre)) {
                                        c++;
                                        CompaEntrenadores.add(entrenadore);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró el nombre---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el nombre a buscar: ");
                                input.nextLine();
                                String nombre = input.nextLine();
                                String nombreMInus = nombre.toLowerCase();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.nombre.toLowerCase().equals(nombreMInus)) {
                                        c++;
                                        CompaEntrenadores.add(entrenadore);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró el nombre---");
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
                                System.out.println("-   Ingrese el apellido a buscar: ");
                                input.nextLine();
                                String apellido = input.nextLine();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.apellido.equals(apellido)) {
                                        c++;
                                        CompaEntrenadores.add(entrenadore);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró el apellido---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese el apellido a buscar: ");
                                input.nextLine();
                                String apellido = input.nextLine();
                                String apellidoMInus = apellido.toLowerCase();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.apellido.toLowerCase().equals(apellidoMInus)) {
                                        c++;
                                        CompaEntrenadores.add(entrenadore);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró el apellido---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("4")) {
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
                                input.nextLine();
                                String correo = input.nextLine();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.correo.equals(correo)) {
                                        c++;
                                        CompaEntrenadores.add(entrenadore);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró el correo---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("Ingrese el correo a buscar");
                                input.nextLine();
                                String correo = input.nextLine();
                                String correoMInus = correo.toLowerCase();
                                int c = 0;
                                for (Entrenadores entrenadore : entrenadores) {
                                    if (entrenadore.correo.toLowerCase().equals(correoMInus)) {
                                        c++;
                                        CompaEntrenadores.add(entrenadore);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            Collections.reverse(CompaEntrenadores);
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                            System.out.println(CompaEntrenadores);
                                            CompaEntrenadores.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró ningun correo---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("5")) {
                        int c = 0;
                        for (Entrenadores entrenadore : entrenadores) {
                            c++;
                            CompaEntrenadores.add(entrenadore);
                        }
                        if (c != 0) {
                            System.out.println("Por cual dese ordenar");
                            System.out.println("1. Documento");
                            System.out.println("2. Nombre");
                            System.out.println("3. Apellido");
                            System.out.println("4. Correo");
                            String compa = input.next();
                            if (compa.equals("1")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                    Collections.reverse(CompaEntrenadores);
                                    System.out.println(CompaEntrenadores);
                                    CompaEntrenadores.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaEntrenadores, new EntrenadorComparador("1"));
                                    System.out.println(CompaEntrenadores);
                                    CompaEntrenadores.clear();
                                }
                            } else if (compa.equals("2")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                    Collections.reverse(CompaEntrenadores);
                                    System.out.println(CompaEntrenadores);
                                    CompaEntrenadores.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaEntrenadores, new EntrenadorComparador("2"));
                                    System.out.println(CompaEntrenadores);
                                    CompaEntrenadores.clear();
                                }
                            } else if (compa.equals("3")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                    Collections.reverse(CompaEntrenadores);
                                    System.out.println(CompaEntrenadores);
                                    CompaEntrenadores.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaEntrenadores, new EntrenadorComparador("3"));
                                    System.out.println(CompaEntrenadores);
                                    CompaEntrenadores.clear();
                                }
                            } else if (compa.equals("4")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                    Collections.reverse(CompaEntrenadores);
                                    System.out.println(CompaEntrenadores);
                                    CompaEntrenadores.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaEntrenadores, new EntrenadorComparador("4"));
                                    System.out.println(CompaEntrenadores);
                                    CompaEntrenadores.clear();
                                }
                            }
                        }
                    } else if (seleccion.equals("6")) {
                        break;
                    }
                }
            } else if (option.equals("7")) {
                if (rutinas.size() == 0) {
                    System.out.println("---la lista de rutinas se encuentra vacía---");
                    return;
                }
                ArrayList<Rutinas> CompaRutina = new ArrayList<>();
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
                    System.out.println("6. Mostrar todas las rutinas");
                    System.out.println("7. Volver");
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
                                input.nextLine();
                                String rutina = input.nextLine();
                                int c = 0;
                                for (Rutinas rutina1 : rutinas) {
                                    if (rutina1.rutina1.equals(rutina)) {
                                        c++;
                                        CompaRutina.add(rutina1);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la rutina a buscar:");
                                input.nextLine();
                                String rutina = input.nextLine();
                                String rutinaMInus = rutina.toLowerCase();
                                int c = 0;
                                for (Rutinas rutina1 : rutinas) {
                                    if (rutina1.rutina2.toLowerCase().equals(rutinaMInus)) {
                                        c++;
                                        CompaRutina.add(rutina1);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("2")) {
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
                                input.nextLine();
                                String rutina = input.nextLine();
                                int c = 0;
                                for (Rutinas rutina2 : rutinas) {
                                    if (rutina2.rutina2.equals(rutina)) {
                                        c++;
                                        CompaRutina.add(rutina2);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la rutina a buscar: ");
                                input.nextLine();
                                String rutina = input.nextLine();
                                String rutinaMInus = rutina.toLowerCase();
                                int c = 0;
                                for (Rutinas rutina2 : rutinas) {
                                    if (rutina2.rutina2.toLowerCase().equals(rutinaMInus)) {
                                        c++;
                                        CompaRutina.add(rutina2);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
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
                                System.out.println("-   Ingrese el nombre de la rutina a buscar: ");
                                input.nextLine();
                                String rutina = input.nextLine();
                                int c = 0;
                                for (Rutinas rutina3 : rutinas) {
                                    if (rutina3.rutina3.equals(rutina)) {
                                        c++;
                                        CompaRutina.add(rutina3);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la rutina a buscar: ");
                                input.nextLine();
                                String rutina = input.nextLine();
                                String rutina3MInus = rutina.toLowerCase();
                                int c = 0;
                                for (Rutinas rutina3 : rutinas) {
                                    if (rutina3.rutina3.toLowerCase().equals(rutina3MInus)) {
                                        c++;
                                        CompaRutina.add(rutina3);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("4")) {
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
                                input.nextLine();
                                String rutina = input.nextLine();
                                int c = 0;
                                for (Rutinas rutina4 : rutinas) {
                                    if (rutina4.rutina4.equals(rutina)) {
                                        c++;
                                        CompaRutina.add(rutina4);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontro la rutina---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la rutina a buscar:   ");
                                input.nextLine();
                                String rutina = input.nextLine();
                                String rutina4MInus = rutina.toLowerCase();
                                int c = 0;
                                for (Rutinas rutina4 : rutinas) {
                                    if (rutina4.rutina4.toLowerCase().equals(rutina4MInus)) {
                                        c++;
                                        CompaRutina.add(rutina4);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("5")) {
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
                                input.nextLine();
                                String rutina = input.nextLine();
                                int c = 0;
                                for (Rutinas rutina5 : rutinas) {
                                    if (rutina5.rutina5.equals(rutina)) {
                                        c++;
                                        CompaRutina.add(rutina5);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontro la rutina---");
                                }
                            } else if (option1.equals("2")) {
                                System.out.println("-   Ingrese la rutina a buscar: ");
                                input.nextLine();
                                String rutina = input.nextLine();
                                String rutina5MInus = rutina.toLowerCase();
                                int c = 0;
                                for (Rutinas rutina5 : rutinas) {
                                    if (rutina5.rutina5.toLowerCase().equals(rutina5MInus)) {
                                        c++;
                                        CompaRutina.add(rutina5);
                                    }
                                }
                                if (c != 0) {
                                    System.out.println("Por cual dese ordenar");
                                    System.out.println("1. Documento");
                                    System.out.println("2. Nombre");
                                    System.out.println("3. Apellido");
                                    System.out.println("4. Correo");
                                    String compa = input.next();
                                    if (compa.equals("1")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("1"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("2")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("2"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("3")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("3"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("4")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("4"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    } else if (compa.equals("5")) {
                                        System.out.println("Desea ver la lista de manera: ");
                                        System.out.println("1. Descendente");
                                        System.out.println("2. Ascendente");
                                        String op = input.next();
                                        if (op.equals("1")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            Collections.reverse(CompaRutina);
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        } else if (op.equals("2")) {
                                            Collections.sort(CompaRutina, new RutinaComparador("5"));
                                            System.out.println(CompaRutina);
                                            CompaRutina.clear();
                                        }
                                    }
                                } else if (c == 0) {
                                    System.out.println("---No se encontró la rutina---");
                                }
                            } else if (option1.equals("3")) {
                                break;
                            }
                        }
                    } else if (seleccion.equals("6")) {
                        int c = 0;
                        for (Rutinas rutina : rutinas) {
                            c++;
                            CompaRutina.add(rutina);
                        }
                        if (c != 0) {
                            System.out.println("Por cual dese ordenar");
                            System.out.println("1. Documento");
                            System.out.println("2. Nombre");
                            System.out.println("3. Apellido");
                            System.out.println("4. Correo");
                            String compa = input.next();
                            if (compa.equals("1")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaRutina, new RutinaComparador("1"));
                                    Collections.reverse(CompaRutina);
                                    System.out.println(CompaRutina);
                                    CompaRutina.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaRutina, new RutinaComparador("1"));
                                    System.out.println(CompaRutina);
                                    CompaRutina.clear();
                                }
                            } else if (compa.equals("2")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaRutina, new RutinaComparador("2"));
                                    Collections.reverse(CompaRutina);
                                    System.out.println(CompaRutina);
                                    CompaRutina.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaRutina, new RutinaComparador("2"));
                                    System.out.println(CompaRutina);
                                    CompaRutina.clear();
                                }
                            } else if (compa.equals("3")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaRutina, new RutinaComparador("3"));
                                    Collections.reverse(CompaRutina);
                                    System.out.println(CompaRutina);
                                    CompaRutina.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaRutina, new RutinaComparador("3"));
                                    System.out.println(CompaRutina);
                                    CompaRutina.clear();
                                }
                            } else if (compa.equals("4")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaRutina, new RutinaComparador("4"));
                                    Collections.reverse(CompaRutina);
                                    System.out.println(CompaRutina);
                                    CompaRutina.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaRutina, new RutinaComparador("4"));
                                    System.out.println(CompaRutina);
                                    CompaRutina.clear();
                                }
                            } else if (compa.equals("5")) {
                                System.out.println("Desea ver la lista de manera: ");
                                System.out.println("1. Descendente");
                                System.out.println("2. Ascendente");
                                String op = input.next();
                                if (op.equals("1")) {
                                    Collections.sort(CompaRutina, new RutinaComparador("5"));
                                    Collections.reverse(CompaRutina);
                                    System.out.println(CompaRutina);
                                    CompaRutina.clear();
                                } else if (op.equals("2")) {
                                    Collections.sort(CompaRutina, new RutinaComparador("5"));
                                    System.out.println(CompaRutina);
                                    CompaRutina.clear();
                                }
                            }
                        }
                    } else if (seleccion.equals("7")) {
                        break;
                    }
                }
            } else if (option.equals("8")) {
                break;
            }
        }
    }

    public static void diagnostico() {

        Gimnasios gimnasio1 = new Gimnasios(1, "", "", "", 234);
        gimnasios.add(gimnasio1);
        Gimnasios gimnasio2 = new Gimnasios(2, "", "", "", 235);
        gimnasios.add(gimnasio2);
        Gimnasios gimnasio3 = new Gimnasios(3, "", "", "", 236);
        gimnasios.add(gimnasio3);

        Sede sede1 = new Sede("", "", "");
        sedes.add(sede1);
        Sede sede2 = new Sede("", "", "");
        sedes.add(sede2);
        Sede sede3 = new Sede("", "", "");
        sedes.add(sede3);
        Sede sede4 = new Sede("", "", "");
        sedes.add(sede4);

        Cursos curso1 = new Cursos("", 1, 2);
        cursos.add(curso1);
        Cursos curso2 = new Cursos("", 2, 3);
        cursos.add(curso2);
        Cursos curso3 = new Cursos("", 3, 4);
        cursos.add(curso3);
        Cursos curso4 = new Cursos("", 4, 4);
        cursos.add(curso4);

        Usuario usuario1 = new Usuario(1, "", "", "", "");
        usuarios.add(usuario1);
        Usuario usuario2 = new Usuario(2, "", "", "", "");
        usuarios.add(usuario2);
        Usuario usuario3 = new Usuario(3, "", "", "", "");
        usuarios.add(usuario3);

        Zona zonas1 = new Zona("", "", "", "", "", idZona);
        zonas.add(zonas1);
        Zona zonas2 = new Zona("", "", "", "", "", idZona);
        zonas.add(zonas2);
        Zona zonas3 = new Zona("", "", "", "", "", idZona);
        zonas.add(zonas3);
        Zona zonas4 = new Zona("", "", "", "", "", idZona);
        zonas.add(zonas4);

        Entrenadores entrenador1 = new Entrenadores(1, "", "", "");
        entrenadores.add(entrenador1);
        Entrenadores entrenador2 = new Entrenadores(2, "", "", "");
        entrenadores.add(entrenador2);
        Entrenadores entrenador3 = new Entrenadores(3, "", "", "");
        entrenadores.add(entrenador3);

        Rutinas rutina1 = new Rutinas("pecho", "brazo", "cardio", "espealda", "recistencia", 90);
        rutinas.add(rutina1);
        Rutinas rutina2 = new Rutinas("", "", "", "", "", 2);
        rutinas.add(rutina2);
        Rutinas rutina3 = new Rutinas("", "", "", "", "", 3);
        rutinas.add(rutina3);
        Rutinas rutina4 = new Rutinas("", "", "", "", "", 4);
        rutinas.add(rutina4);

        inconcistencias();
    }

    public static void inconcistencias() {

        System.out.println("Gimnasio inconcistente en el sistema");
        for (Gimnasios gimnasio : gimnasios) {
            if (gimnasio.misSedes.size() == 0 || gimnasio.misCursos.size() == 0 || gimnasio.misZonas.size() == 0 || gimnasio.misEntrenadores.size() == 0 ||
                    gimnasio.misRutinas.size() == 0) {
                System.out.println(gimnasio);
            }
            System.out.println();
        }
        System.out.println("Sedes inconcistentes en el sistema");
        for (Sede sede : sedes) {
            if (sede.gimnasio == null || sede.misCursos.size() == 0 || sede.misZonas.size() == 0 || sede.misEntrenadores.size() == 0 ||
                    sede.misRutinas.size() == 0) {
                System.out.println(sede);
            }
            System.out.println();
        }
        System.out.println("Cursos inconcistente en el sistema");
        for (Cursos curso : cursos) {
            if (curso.misZonas.size() == 0 || curso.misEntrenadores.size() == 0 || curso.misRutinas.size() == 0) {
                System.out.println(curso);
            }
            System.out.println();
        }
        System.out.println("Usuarios inconcistente en el sistema");
        for (Usuario usuario : usuarios) {
            if (usuario.misEntrenadores.size() == 0) {
                System.out.println(usuario);
            }
            System.out.println();
        }
        System.out.println("Zonas inconcistente en el sistema");
        for (Zona zona : zonas) {
            if (zona.misEntrenadores.size() == 0 || zona.misRutinas.size() == 0) {
                System.out.println(zona);
            }
            System.out.println();
        }
        System.out.println("Entrenadores inconcistente en el sistema");
        for (Entrenadores entrenador : entrenadores) {
            if (entrenador.misRutinas.size() == 0) {
                System.out.println(entrenador);
            }
            System.out.println();
        }
    }

    public static void guardar() {
        genrarJSON();
        JSONgimnasios();
        leerUsuariosJsonArrayList();
        leerGymsJsonArrayList();
        JSONentrenadores();
        leerEntrenadoresJsonArrayList();
        leerRutinaJsonArrayList();
        JSONrutinas();
        JSONsede();
        leerSedeJsonArrayList();
        JSONzonas();
        leerZonasJsonArrayList();
        JSONcurso();
        leerCursosJsonArrayList();
    }

    public static void JSONgimnasios() {
        JSONArray GymLista = new JSONArray();
        for (Gimnasios gym : gimnasios) {
            JSONObject gimnasioDatos = new JSONObject();
            gimnasioDatos.put("NIT",String.valueOf(gym.nit));
            gimnasioDatos.put("nombre",gym.nombre);
            gimnasioDatos.put("siglas",gym.siglas);
            gimnasioDatos.put("direccion",gym.direccion);
            gimnasioDatos.put("telefono",String.valueOf(gym.telefono));
            JSONObject GimnasioPerfil = new JSONObject();
            GimnasioPerfil.put("Gimnasio",gimnasioDatos);

            GymLista.add(GimnasioPerfil);
        }
        try(FileWriter file = new FileWriter(ruta+"GimnasiosJSON.json")){
            file.write(GymLista.toJSONString());
            file.flush();
        } catch (Exception e){
            System.out.println("Error en :"+e);
        }
    }

    public static void leerGymsJsonArrayList() {
        gimnasios = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(ruta + "GimnasiosJSON.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray ListaGyms = (JSONArray) obj;
            for (Object gymObjeto : ListaGyms) {
                JSONObject gimnasioJSON = (JSONObject) gymObjeto;
                gimnasios.add(parseGym(gimnasioJSON));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Gimnasios parseGym(JSONObject gimnasioJSON) {
        JSONObject atributos = (JSONObject) gimnasioJSON.get("Gimnasio");
        int nit = Integer.parseInt((String) atributos.get("NIT"));
        String nombre = (String) atributos.get("nombre");
        String siglas = (String) atributos.get("siglas");
        String direccion = (String) atributos.get("direccion");
        int telefono = Integer.parseInt((String) atributos.get("telefono"));
        Gimnasios gymLeido = new Gimnasios(nit, nombre, siglas, direccion, telefono);
        return gymLeido;
    }


    public static void genrarJSON() {
        JSONArray gimnasio = new JSONArray();
        JSONArray UsuarioLista = new JSONArray();
        for (Usuario usuario : usuarios) {
            JSONObject usuarioDatos = new JSONObject();
            usuarioDatos.put("cedula", String.valueOf(usuario.cedula));
            usuarioDatos.put("nombre", usuario.nombre);
            usuarioDatos.put("apellido", usuario.apellido);
            usuarioDatos.put("correo", usuario.correo);
            usuarioDatos.put("contraseña", usuario.password);
            JSONObject UsuarioPerfil = new JSONObject();
            UsuarioPerfil.put("Usuario", usuarioDatos);

            UsuarioLista.add(UsuarioPerfil);
        }

        try (FileWriter file = new FileWriter(ruta + "UsuariosJSON.json")) {
            file.write(UsuarioLista.toJSONString());
            file.flush();
        } catch (Exception e) {
            System.out.println("Error en :" + e);
        }
        gimnasio.add(gimnasios);
    }

    public static void leerUsuariosJsonArrayList() {
        usuarios = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(ruta + "UsuariosJSON.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray ListaUsuarios = (JSONArray) obj;
            for (Object usuarioObjeto : ListaUsuarios) {
                JSONObject usuarioJSON = (JSONObject) usuarioObjeto;
                usuarios.add(parseUsuario(usuarioJSON));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static Usuario parseUsuario(JSONObject usuarioJSON) {
        JSONObject atributos = (JSONObject) usuarioJSON.get("Usuario");
        int cedula = Integer.parseInt((String) atributos.get("cedula"));
        String nombre = (String) atributos.get("nombre");
        String apellido = (String) atributos.get("apellido");
        String correo = (String) atributos.get("correo");
        String contraseña = (String) atributos.get("contraseña");
        Usuario usuarioLeido = new Usuario(cedula, nombre, apellido, correo, contraseña);
        return usuarioLeido;
    }


    public static void JSONentrenadores() {
        JSONArray EntrenadorLista = new JSONArray();
        for (Entrenadores entrenador : entrenadores) {
            JSONObject entrenadorDatos = new JSONObject();
            entrenadorDatos.put("documento",String.valueOf(entrenador.documento));
            entrenadorDatos.put("nombre",entrenador.nombre);
            entrenadorDatos.put("apellido",entrenador.apellido);
            entrenadorDatos.put("correo",entrenador.correo);
            JSONObject EntrenadorPerfil = new JSONObject();
            EntrenadorPerfil.put("Entrenador",entrenadorDatos);

            EntrenadorLista.add(EntrenadorPerfil);
        }
        try(FileWriter file = new FileWriter(ruta+"EntrenadoresJSON.json")){
            file.write(EntrenadorLista.toJSONString());
            file.flush();
        } catch (Exception e){
            System.out.println("Error en :"+e);
        }
    }

    public static void leerEntrenadoresJsonArrayList() {
        entrenadores = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(ruta + "EntrenadoresJSON.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray ListaEntrenadores = (JSONArray) obj;
            for (Object entrenadorObjeto : ListaEntrenadores) {
                JSONObject entrenadorJSON = (JSONObject) entrenadorObjeto;
                entrenadores.add(parseEntrenador(entrenadorJSON));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static Entrenadores parseEntrenador(JSONObject entrenadorJSON) {
        JSONObject atributos = (JSONObject) entrenadorJSON.get("Entrenador");
        int documento = Integer.parseInt((String) atributos.get("documento"));
        String nombre = (String) atributos.get("nombre");
        String apellido = (String) atributos.get("apellido");
        String correo = (String) atributos.get("correo");
        Entrenadores EntrenadorLeido = new Entrenadores(documento, nombre, apellido, correo);
        return EntrenadorLeido;
    }





    public static void JSONrutinas() {
        JSONArray RutinaLista = new JSONArray();
        for (Rutinas rutina : rutinas) {
            JSONObject rutinaDatos = new JSONObject();
            rutinaDatos.put("rutina 1",rutina.rutina1);
            rutinaDatos.put("rutina 2",rutina.rutina2);
            rutinaDatos.put("rutina 3",rutina.rutina3);
            rutinaDatos.put("rutina 4",rutina.rutina4);
            rutinaDatos.put("rutina 5",rutina.rutina5);
            rutinaDatos.put("id",String.valueOf(rutina.id));
            JSONObject RutinaPerfil = new JSONObject();
            RutinaPerfil.put("Rutina",rutinaDatos);

            RutinaLista.add(RutinaPerfil);
        }
        try(FileWriter file = new FileWriter(ruta+"RutinasJSON.json")){
            file.write(RutinaLista.toJSONString());
            file.flush();
        } catch (Exception e){
            System.out.println("Error en :"+e);
        }
    }

    public static void leerRutinaJsonArrayList() {
        rutinas = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(ruta + "RutinasJSON.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray ListaRutinas = (JSONArray) obj;
            for (Object rutinaObjeto : ListaRutinas) {
                JSONObject rutinaJSON = (JSONObject) rutinaObjeto;
                rutinas.add(parseRutina(rutinaJSON));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static Rutinas parseRutina(JSONObject rutinaJSON) {
        JSONObject atributos = (JSONObject) rutinaJSON.get("Rutina");
        String rutina1 = (String) atributos.get("rutina 1");
        String rutina2 = (String) atributos.get("rutina 2");
        String rutina3 = (String) atributos.get("rutina 3");
        String rutina4 = (String) atributos.get("rutina 4");
        String rutina5 = (String) atributos.get("rutina 5");
        int id = Integer.parseInt((String) atributos.get("id"));
        Rutinas RutinaLeido = new Rutinas(rutina1, rutina2, rutina3, rutina4,rutina5,id);
        return RutinaLeido;
    }





    public static void JSONsede() {
        JSONArray SedeLista = new JSONArray();
        for (Sede sede : sedes) {
            JSONObject sedeDatos = new JSONObject();
            sedeDatos.put("nombre",sede.nombre);
            sedeDatos.put("direccion",sede.direccion);
            sedeDatos.put("ciudad",sede.ciudad);
            JSONObject SedePerfil = new JSONObject();
            SedePerfil.put("Sede",sedeDatos);

            SedeLista.add(SedePerfil);
        }
        try(FileWriter file = new FileWriter(ruta+"SedesJSON.json")){
            file.write(SedeLista.toJSONString());
            file.flush();
        } catch (Exception e){
            System.out.println("Error en :"+e);
        }
    }

    public static void leerSedeJsonArrayList() {
        sedes = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(ruta + "SedesJSON.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray ListaSedes = (JSONArray) obj;
            for (Object sedeObjeto : ListaSedes) {
                JSONObject sedeJSON = (JSONObject) sedeObjeto;
                sedes.add(parseSede(sedeJSON));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static Sede parseSede(JSONObject sedeJSON) {
        JSONObject atributos = (JSONObject) sedeJSON.get("Sede");
        String nombre = (String) atributos.get("nombre");
        String direccion = (String) atributos.get("direccion");
        String ciudad = (String) atributos.get("ciudad");
        Sede SedeLeido = new Sede(nombre,direccion,ciudad);
        return SedeLeido;
    }


    public static void JSONzonas() {
        JSONArray ZonaLista = new JSONArray();
        for (Zona zona : zonas) {
            JSONObject zonaDatos = new JSONObject();
            zonaDatos.put("zona 1",zona.zona1);
            zonaDatos.put("zona 2",zona.zona2);
            zonaDatos.put("zona 3",zona.zona3);
            zonaDatos.put("zona 4",zona.zona4);
            zonaDatos.put("zona 5",zona.zona5);
            zonaDatos.put("id",String.valueOf(zona.id));
            JSONObject ZonaPerfil = new JSONObject();
            ZonaPerfil.put("Zona",zonaDatos);

            ZonaLista.add(ZonaPerfil);
        }
        try(FileWriter file = new FileWriter(ruta+"ZonasJSON.json")){
            file.write(ZonaLista.toJSONString());
            file.flush();
        } catch (Exception e){
            System.out.println("Error en :"+e);
        }
    }

    public static void leerZonasJsonArrayList() {
        zonas = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(ruta + "ZonasJSON.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray ListaZonas = (JSONArray) obj;
            for (Object zonasObjeto : ListaZonas) {
                JSONObject zonaJSON = (JSONObject) zonasObjeto;
                zonas.add(parseZona(zonaJSON));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static Zona parseZona(JSONObject zonaJSON) {
        JSONObject atributos = (JSONObject) zonaJSON.get("Zona");
        String zona1 = (String) atributos.get("zona 1");
        String zona2 = (String) atributos.get("zona 2");
        String zona3 = (String) atributos.get("zona 3");
        String zona4 = (String) atributos.get("zona 4");
        String zona5 = (String) atributos.get("zona 5");
        int id = Integer.parseInt((String) atributos.get("id"));
        Zona ZonaLeido = new Zona(zona1, zona2, zona3, zona4,zona5,id);
        return ZonaLeido;
    }




    public static void JSONcurso() {
        JSONArray CursoLista = new JSONArray();
        for (Cursos curso : cursos) {
            JSONObject cursoDatos = new JSONObject();
            cursoDatos.put("nombre",curso.nombre);
            cursoDatos.put("codigo",String.valueOf(curso.codigo));
            cursoDatos.put("intesidad",String.valueOf(curso.intesidadHora));
            JSONObject CursoPerfil = new JSONObject();
            CursoPerfil.put("Curso",cursoDatos);

            CursoLista.add(CursoPerfil);
        }
        try(FileWriter file = new FileWriter(ruta+"cursosJSON.json")){
            file.write(CursoLista.toJSONString());
            file.flush();
        } catch (Exception e){
            System.out.println("Error en :"+e);
        }
    }

    public static void leerCursosJsonArrayList() {
        cursos = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(ruta + "CursosJSON.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray ListaCursos = (JSONArray) obj;
            for (Object cursoObjeto : ListaCursos) {
                JSONObject cursoJSON = (JSONObject) cursoObjeto;
                cursos.add(parseCurso(cursoJSON));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private static Cursos parseCurso(JSONObject cursoJSON) {
        JSONObject atributos = (JSONObject) cursoJSON.get("Curso");
        String nombre = (String) atributos.get("nombre");
        int codigo = Integer.parseInt((String) atributos.get("codigo"));
        int intensidad = Integer.parseInt((String) atributos.get("intensidad"));
        Cursos CursoLeido = new Cursos(nombre,codigo,intensidad);
        return CursoLeido;
    }


    public static void visualizar() {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}

