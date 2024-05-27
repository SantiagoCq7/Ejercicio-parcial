import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FabricaDeTrajes fabrica = new FabricaDeTrajes();
        boolean salir = false;

        while (!salir) {
            fabrica.escribirMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            try {
                switch (opcion) {
                    case 1:
                        fabrica.añadirComponenteAAlmacen();
                        break;
                    case 2:
                        fabrica.listarComponentes();
                        break;
                    case 3:
                        fabrica.añadirTrajeAAlmacen();
                        break;
                    case 4:
                        fabrica.listarTrajes();
                        break;
                    case 7:
                        fabrica.activarDesactivarRebajas();
                        break;
                    case 8:
                        fabrica.crearEnvio();
                        break;
                    case 9:
                        fabrica.crearComponentesDePrueba();
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (IdException | MuchoExtracomunitarioException | MangaException | ColoresException | TallaException | TrajeYaExisteException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}