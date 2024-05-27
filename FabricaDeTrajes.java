import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class FabricaDeTrajes implements IFabricaDeTrajes {
    private ArrayList<Componente> componentesEnAlmacen;
    private TreeSet<Traje> trajesEnAlmacen;
    private boolean sonRebajas;

    public FabricaDeTrajes() {
        this.componentesEnAlmacen = new ArrayList<>();
        this.trajesEnAlmacen = new TreeSet<>((t1, t2) -> t1.getNombre().compareTo(t2.getNombre()));
        this.sonRebajas = false;
    }

    @Override
    public void escribirMenu() {
        System.out.println("MENU");
        System.out.println("1.- Añadir Componente a almacén");
        System.out.println("2.- Listar Componentes del almacén");
        System.out.println("3.- Crear traje y añadir a almacén");
        System.out.println("4.- Listar trajes del almacén");
        System.out.println("7.- Activar/Desactivar las rebajas");
        System.out.println("8.- Crear envío");
        System.out.println("9.- Crea componentes de prueba");
        System.out.println("0.- Salir");
    }

    @Override
    public void añadirComponenteAAlmacen() throws IdException, MuchoExtracomunitarioException, MangaException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el tipo de componente (1. Falda, 2. Chaqueta, 3. Pantalón, 4. Blusa): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.println("Ingrese el id del componente:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el nombre del componente:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la talla del componente:");
        String talla = scanner.nextLine();
        System.out.println("Ingrese el color del componente:");
        String color = scanner.nextLine();
        System.out.println("¿Es comunitario? (true/false):");
        boolean escomunitario = scanner.nextBoolean();
        System.out.println("Ingrese el precio del componente:");
        double precio;
        precio = scanner.nextDouble();
        scanner.nextLine();

        // Validar que el ID no exista
        for (Componente c : componentesEnAlmacen) {
            if (c.getId() == id) {
                throw new IdException("El ID ya existe.");
            }
        }

        // Validar porcentaje de componentes extracomunitarios
        long totalExtracomunitarios = componentesEnAlmacen.stream().filter(c -> !c.isEscomunitario()).count();
        if (!escomunitario && totalExtracomunitarios + 1 > componentesEnAlmacen.size() / 2) {
            throw new MuchoExtracomunitarioException("No se pueden añadir más componentes extracomunitarios.");
        }

        Componente componente = null;
        switch (tipo) {
            case 1:
                System.out.println("¿Tiene cremallera? (true/false):");
                boolean conCremalleraFalda = scanner.nextBoolean();
                precio += conCremalleraFalda ? 1 : 0;
                componente = new Falda(id, nombre, talla, color, escomunitario, precio, conCremalleraFalda);
                break;
            case 2:
                System.out.println("Ingrese el número de botones:");
                int numBotones = scanner.nextInt();
                precio += numBotones * 2;
                componente = new Chaqueta(id, nombre, talla, color, escomunitario, precio, numBotones);
                break;
            case 3:
                System.out.println("¿Tiene cremallera? (true/false):");
                boolean conCremalleraPantalon = scanner.nextBoolean();
                precio += conCremalleraPantalon ? 1 : 0;
                componente = new Pantalon(id, nombre, talla, color, escomunitario, precio, conCremalleraPantalon);
                break;
            case 4:
                System.out.println("¿Es de manga larga? (true/false):");
                boolean mangaLarga = scanner.nextBoolean();
                if (mangaLarga) {
                    boolean existeCortaMismoColor = componentesEnAlmacen.stream()
                            .anyMatch(c -> c instanceof Blusa && !((Blusa) c).isMangaLarga() && c.getColor().equals(color));
                    if (!existeCortaMismoColor) {
                        throw new MangaException("Debe existir una blusa de manga corta del mismo color.");
                    }
                } else {
                    boolean existeLargaMismoColor = componentesEnAlmacen.stream()
                            .anyMatch(c -> c instanceof Blusa && ((Blusa) c).isMangaLarga() && c.getColor().equals(color));
                    if (!existeLargaMismoColor) {
                        throw new MangaException("Debe existir una blusa de manga larga del mismo color.");
                    }
                }
                componente = new Blusa(id, nombre, talla, color, escomunitario, precio, mangaLarga);
                break;
            default:
                System.out.println("Tipo de componente no válido.");
                return;
        }

        componentesEnAlmacen.add(componente);
        System.out.println("Componente añadido al almacén.");
    }

    @Override
    public void listarComponentes() {
        if (componentesEnAlmacen.isEmpty()) {
            System.out.println("No hay componentes en el almacén.");
        } else {
            System.out.println("Componentes en el almacén:");
            for (Componente c : componentesEnAlmacen) {
                System.out.println(c);
            }
        }
    }

    @Override
    public void añadirTrajeAAlmacen() throws ColoresException, TallaException, TrajeYaExisteException {
        Scanner scanner = new Scanner(System.in);

        // Listar y seleccionar blusa
        System.out.println("Seleccione una blusa:");
        componentesEnAlmacen.stream()
                .filter(c -> c instanceof Blusa)
                .forEach(c -> System.out.println(c.getId() + ": " + c));
        int idBlusa = scanner.nextInt();
        scanner.nextLine();
        Blusa blusaSeleccionada = (Blusa) componentesEnAlmacen.stream()
                .filter(c -> c.getId() == idBlusa)
                .findFirst()
                .orElse(null);

        // Listar y seleccionar chaqueta
        System.out.println("Seleccione una chaqueta:");
        componentesEnAlmacen.stream()
                .filter(c -> c instanceof Chaqueta)
                .forEach(c -> System.out.println(c.getId() + ": " + c));
        int idChaqueta = scanner.nextInt();
        scanner.nextLine();
        Chaqueta chaquetaSeleccionada = (Chaqueta) componentesEnAlmacen.stream()
                .filter(c -> c.getId() == idChaqueta)
                .findFirst()
                .orElse(null);

        // Listar y seleccionar falda o pantalón
        System.out.println("Seleccione una falda o pantalón:");
        componentesEnAlmacen.stream()
                .filter(c -> c instanceof Falda || c instanceof Pantalon)
                .forEach(c -> System.out.println(c.getId() + ": " + c));
        int idFaldaPantalon = scanner.nextInt();
        scanner.nextLine();
        Componente faldaPantalonSeleccionado = componentesEnAlmacen.stream()
                .filter(c -> c.getId() == idFaldaPantalon)
                .findFirst()
                .orElse(null);

        // Validar colores
        if (!blusaSeleccionada.getColor().startsWith(chaquetaSeleccionada.getColor().substring(0, 1)) ||
                !blusaSeleccionada.getColor().startsWith(faldaPantalonSeleccionado.getColor().substring(0, 1))) {
            throw new ColoresException("Los componentes no tienen colores amigos.");
        }

        // Validar tallas
        if (!blusaSeleccionada.getTalla().equals(chaquetaSeleccionada.getTalla()) ||
                !blusaSeleccionada.getTalla().equals(faldaPantalonSeleccionado.getTalla())) {
            throw new TallaException("Los componentes no tienen la misma talla.");
        }

        // Crear el traje
        System.out.println("Ingrese el nombre del traje:");
        String nombreTraje = scanner.nextLine();
        if (trajesEnAlmacen.stream().anyMatch(t -> t.getNombre().equals(nombreTraje))) {
            throw new TrajeYaExisteException("El traje ya existe en el almacén.");
        }
        Traje nuevoTraje = new Traje(nombreTraje);
        nuevoTraje.getPiezas().add(blusaSeleccionada);
        nuevoTraje.getPiezas().add(chaquetaSeleccionada);
        nuevoTraje.getPiezas().add(faldaPantalonSeleccionado);

        trajesEnAlmacen.add(nuevoTraje);
        componentesEnAlmacen.remove(blusaSeleccionada);
        componentesEnAlmacen.remove(chaquetaSeleccionada);
        componentesEnAlmacen.remove(faldaPantalonSeleccionado);

        System.out.println("Traje añadido al almacén.");
    }

    @Override
    public void listarTrajes() {
        if (trajesEnAlmacen.isEmpty()) {
            System.out.println("No hay trajes en el almacén.");
        } else {
            System.out.println("Trajes en el almacén:");
            for (Traje t : trajesEnAlmacen) {
                System.out.println(t);
            }
        }
    }

    @Override

    public void activarDesactivarRebajas() {
        sonRebajas = !sonRebajas;
        String estado = sonRebajas ? "activadas" : "desactivadas";
        System.out.println("Rebajas " + estado + ".");
    }

    @Override
    public void crearEnvio() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Traje> envio = new ArrayList<>();

        while (true) {
            System.out.println("Seleccione un traje por nombre:");
            trajesEnAlmacen.forEach(t -> System.out.println(t.getNombre()));
            String nombreTraje = scanner.nextLine();
            Traje trajeSeleccionado = trajesEnAlmacen.stream()
                    .filter(t -> t.getNombre().equals(nombreTraje))
                    .findFirst()
                    .orElse(null);
            if (trajeSeleccionado != null) {
                envio.add(trajeSeleccionado);
                trajesEnAlmacen.remove(trajeSeleccionado);
                System.out.println("Traje añadido al envío.");
            } else {
                System.out.println("Traje no encontrado.");
            }

            System.out.println("¿Desea añadir otro traje al envío? (s/n):");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("n")) {
                break;
            }
        }

        System.out.println("Envío creado con los siguientes trajes:");
        envio.forEach(t -> System.out.println(t.getNombre()));
    }

    @Override
    public void crearComponentesDePrueba() {
        componentesEnAlmacen.add(new Falda(1, "Falda", "M", "Rojo", true, 19.99, true));
        componentesEnAlmacen.add(new Chaqueta(2, "Chaqueta", "L", "Azul", false, 49.99, 3));
        componentesEnAlmacen.add(new Pantalon(3, "Pantalón", "S", "Negro", true, 29.99, true));
        componentesEnAlmacen.add(new Blusa(4, "Blusa", "M", "Blanco", false, 24.99, true));
        System.out.println("Componentes de prueba creados.");
    }
}