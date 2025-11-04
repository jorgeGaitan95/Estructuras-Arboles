import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal interactiva para probar el Trie de Autocompletado
 * Permite insertar palabras y buscar autocompletado desde la consola
 */
public class MainTrie {
    private static Scanner scanner = new Scanner(System.in);
    private static TrieAutocompletado trie = new TrieAutocompletado();
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("       TRIE DE AUTOCOMPLETADO - CONSOLA");
        System.out.println("═══════════════════════════════════════════════════\n");
        
        // Insertar algunas palabras iniciales de ejemplo
        inicializarPalabras();
        
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            
            switch (opcion) {
                case 1:
                    insertarPalabra();
                    break;
                case 2:
                    buscarAutocompletado();
                    break;
                case 3:
                    buscarPalabra();
                    break;
                case 4:
                    eliminarPalabra();
                    break;
                case 5:
                    mostrarTodasLasPalabras();
                    break;
                case 6:
                    mostrarEstadisticas();
                    break;
                case 7:
                    limpiarTrie();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("\n¡Hasta luego!");
                    break;
                default:
                    System.out.println("\n❌ Opción no válida. Por favor, intente nuevamente.");
            }
            
            if (continuar) {
                System.out.println("\n" + "─".repeat(55));
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
                System.out.println();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Muestra el menú principal
     */
    private static void mostrarMenu() {
        System.out.println("╔═══════════════════════════════════════════════════╗");
        System.out.println("║                    MENÚ PRINCIPAL                 ║");
        System.out.println("╠═══════════════════════════════════════════════════╣");
        System.out.println("║  1. Insertar palabra                              ║");
        System.out.println("║  2. Buscar autocompletado (por prefijo)          ║");
        System.out.println("║  3. Buscar si existe una palabra                  ║");
        System.out.println("║  4. Eliminar palabra                             ║");
        System.out.println("║  5. Mostrar todas las palabras                   ║");
        System.out.println("║  6. Mostrar estadísticas                         ║");
        System.out.println("║  7. Limpiar trie (eliminar todas las palabras)   ║");
        System.out.println("║  0. Salir                                         ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.print("\nSeleccione una opción: ");
    }
    
    /**
     * Lee la opción del menú desde la consola
     */
    private static int leerOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());
            return opcion;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Inserta palabras iniciales de ejemplo
     */
    private static void inicializarPalabras() {
        System.out.println("📚 Cargando palabras...\n");
        
        // Intentar cargar desde CSV primero
        int palabrasCargadas = cargarPalabrasDesdeCSV();
        
        if (palabrasCargadas == 0) {
            // Si no se pudo cargar desde CSV, usar palabras de ejemplo hardcodeadas
            System.out.println("⚠️  No se pudo cargar el archivo CSV. Usando palabras de ejemplo...\n");
            String[] palabrasEjemplo = {
                "casa", "casamiento", "casero", "casita", "castillo",
                "carro", "carrito", "carta", "cartel", "cartón",
                "perro", "pera", "periscopio", "período", "perfección",
                "programacion", "programa", "progreso", "proyecto", "proceso",
                "computadora", "computar", "computo", "compuesto",
                "java", "javascript", "javier", "jardin", "jarro"
            };
            
            for (String palabra : palabrasEjemplo) {
                trie.insertar(palabra);
            }
            
            System.out.println("✅ Se insertaron " + palabrasEjemplo.length + " palabras de ejemplo.");
        }
        
        System.out.println("   Total de palabras únicas en el trie: " + trie.contarPalabras() + "\n");
    }
    
    /**
     * Carga palabras desde un archivo CSV ubicado en src/main/resources/palabras.csv
     * El archivo debe tener una palabra por línea
     * @return El número de palabras cargadas exitosamente
     */
    public static int cargarPalabrasDesdeCSV() {
        String nombreArchivo = "palabras.csv";
        int palabrasCargadas = 0;
        int palabrasDuplicadas = 0;
        
        try {
            // Obtener el InputStream del archivo desde resources
            InputStream inputStream = MainTrie.class.getClassLoader().getResourceAsStream(nombreArchivo);
            
            if (inputStream == null) {
                System.out.println("❌ No se encontró el archivo '" + nombreArchivo + "' en resources.");
                return 0;
            }
            
            // Leer el archivo línea por línea
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            );
            
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Limpiar la línea y dividir por comas si es necesario
                linea = linea.trim();
                
                // Si la línea está vacía o es un comentario, saltarla
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }
                
                // Si hay comas, tomar solo la primera columna (palabra)
                String palabra = linea;
                if (linea.contains(",")) {
                    palabra = linea.split(",")[0].trim();
                }
                
                // Validar que la palabra no esté vacía
                if (!palabra.isEmpty()) {
                    boolean existia = trie.buscar(palabra);
                    trie.insertar(palabra);
                    
                    if (existia) {
                        palabrasDuplicadas++;
                    } else {
                        palabrasCargadas++;
                    }
                }
            }
            
            reader.close();
            inputStream.close();
            
            System.out.println("✅ Se cargaron " + palabrasCargadas + " palabra(s) desde el CSV.");
            if (palabrasDuplicadas > 0) {
                System.out.println("   ⚠️  Se encontraron " + palabrasDuplicadas + " palabra(s) duplicada(s).");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error al cargar el archivo CSV: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
        
        return palabrasCargadas;
    }
    
    /**
     * Permite insertar una palabra desde la consola
     */
    private static void insertarPalabra() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║              INSERTAR PALABRA                      ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.print("\nIngrese la palabra a insertar: ");
        String palabra = scanner.nextLine().trim();
        
        if (palabra.isEmpty()) {
            System.out.println("\n❌ No se puede insertar una palabra vacía.");
            return;
        }
        
        boolean existia = trie.buscar(palabra);
        trie.insertar(palabra);
        
        if (existia) {
            System.out.println("\n✅ La palabra '" + palabra + "' ya existía. Se mantiene en el trie.");
        } else {
            System.out.println("\n✅ Palabra '" + palabra + "' insertada correctamente.");
        }
        
        System.out.println("   Total de palabras en el trie: " + trie.contarPalabras());
    }
    
    /**
     * Busca autocompletado por prefijo ingresado desde la consola
     */
    private static void buscarAutocompletado() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║           BÚSQUEDA DE AUTOCOMPLETADO               ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.print("\nIngrese el prefijo para buscar: ");
        String prefijo = scanner.nextLine().trim();
        
        if (prefijo.isEmpty()) {
            System.out.println("\n⚠️  Prefijo vacío. Mostrando todas las palabras del trie:");
            prefijo = "";
        }
        
        List<String> sugerencias = trie.autocompletar(prefijo);
        
        System.out.println("\n" + "─".repeat(55));
        if (sugerencias.isEmpty()) {
            System.out.println("❌ No se encontraron palabras que comiencen con '" + prefijo + "'");
        } else {
            System.out.println("✅ Se encontraron " + sugerencias.size() + " palabra(s) que comienzan con '" + prefijo + "':\n");
            
            // Ordenar las sugerencias alfabéticamente
            sugerencias.sort(String::compareToIgnoreCase);
            
            for (int i = 0; i < sugerencias.size(); i++) {
                System.out.println("   " + (i + 1) + ". " + sugerencias.get(i));
            }
        }
    }
    
    /**
     * Busca si una palabra existe en el trie
     */
    private static void buscarPalabra() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║           BÚSQUEDA DE PALABRA                      ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.print("\nIngrese la palabra a buscar: ");
        String palabra = scanner.nextLine().trim();
        
        if (palabra.isEmpty()) {
            System.out.println("\n❌ No se puede buscar una palabra vacía.");
            return;
        }
        
        boolean existe = trie.buscar(palabra);
        
        System.out.println("\n" + "─".repeat(55));
        if (existe) {
            System.out.println("✅ La palabra '" + palabra + "' SÍ existe en el trie.");
        } else {
            System.out.println("❌ La palabra '" + palabra + "' NO existe en el trie.");
            
            // Mostrar sugerencias similares
            List<String> sugerencias = trie.autocompletar(palabra);
            if (!sugerencias.isEmpty()) {
                System.out.println("\n💡 ¿Quizás quisiste decir alguna de estas?");
                sugerencias.sort(String::compareToIgnoreCase);
                int limite = Math.min(5, sugerencias.size());
                for (int i = 0; i < limite; i++) {
                    System.out.println("   • " + sugerencias.get(i));
                }
            }
        }
    }
    
    /**
     * Elimina una palabra del trie
     */
    private static void eliminarPalabra() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║              ELIMINAR PALABRA                      ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        System.out.print("\nIngrese la palabra a eliminar: ");
        String palabra = scanner.nextLine().trim();
        
        if (palabra.isEmpty()) {
            System.out.println("\n❌ No se puede eliminar una palabra vacía.");
            return;
        }
        
        boolean eliminada = trie.eliminar(palabra);
        
        System.out.println("\n" + "─".repeat(55));
        if (eliminada) {
            System.out.println("✅ Palabra '" + palabra + "' eliminada correctamente.");
        } else {
            System.out.println("❌ La palabra '" + palabra + "' no existe en el trie.");
        }
        
        System.out.println("   Total de palabras restantes: " + trie.contarPalabras());
    }
    
    /**
     * Muestra todas las palabras almacenadas en el trie
     */
    private static void mostrarTodasLasPalabras() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║          TODAS LAS PALABRAS DEL TRIE               ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");
        
        if (trie.estaVacio()) {
            System.out.println("⚠️  El trie está vacío. No hay palabras almacenadas.");
            return;
        }
        
        trie.imprimirTodasLasPalabras();
    }
    
    /**
     * Muestra estadísticas del trie
     */
    private static void mostrarEstadisticas() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║              ESTADÍSTICAS DEL TRIE                  ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");
        
        int totalPalabras = trie.contarPalabras();
        boolean vacio = trie.estaVacio();
        
        System.out.println("   Total de palabras almacenadas: " + totalPalabras);
        System.out.println("   Estado del trie: " + (vacio ? "Vacío" : "Con palabras"));
        
        if (!vacio && totalPalabras > 0) {
            List<String> todas = trie.obtenerTodasLasPalabras();
            todas.sort(String::compareToIgnoreCase);
            
            String primera = todas.get(0);
            String ultima = todas.get(todas.size() - 1);
            
            System.out.println("   Primera palabra (alfabético): " + primera);
            System.out.println("   Última palabra (alfabético): " + ultima);
        }
    }
    
    /**
     * Limpia el trie eliminando todas las palabras
     */
    private static void limpiarTrie() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║              LIMPIAR TRIE                          ║");
        System.out.println("╚═══════════════════════════════════════════════════╝");
        
        if (trie.estaVacio()) {
            System.out.println("\n⚠️  El trie ya está vacío.");
            return;
        }
        
        System.out.print("\n⚠️  ¿Está seguro que desea eliminar todas las palabras? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
        
        if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("yes") || confirmacion.equals("y")) {
            int cantidadAntes = trie.contarPalabras();
            List<String> palabras = trie.obtenerTodasLasPalabras();
            
            for (String palabra : palabras) {
                trie.eliminar(palabra);
            }
            
            System.out.println("\n✅ Se eliminaron " + cantidadAntes + " palabra(s) del trie.");
            System.out.println("   El trie ahora está vacío.");
        } else {
            System.out.println("\n❌ Operación cancelada. El trie no fue modificado.");
        }
    }
}

