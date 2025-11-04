import java.util.*;
import java.time.Duration;
import java.time.Instant;

public class ComparacionArboles {
    
    public static void main(String[] args) {
        System.out.println("=== COMPARACIÓN: TU IMPLEMENTACIÓN vs TREEMAP ===\n");
        
        // Crear datos de prueba
        String[] titulos = {
            "El Quijote", "Cien Años de Soledad", "Pedro Páramo", "Rayuela", 
            "Don Juan Tenorio", "La Celestina", "El Aleph", "Ficciones",
            "1984", "Orgullo y Prejuicio", "Crimen y Castigo", "Ulises",
            "En busca del tiempo perdido", "El Gran Gatsby", "Matar a un ruiseñor"
        };
        
        // 1. Comparación de rendimiento
        comparacionRendimiento(titulos);
        
        // 2. Comparación de funcionalidades
        comparacionFuncionalidades(titulos);
        
        // 3. Casos de uso específicos
        casosDeUsoEspecificos(titulos);
        
        // 4. Ventajas y desventajas
        ventajasDesventajas();
    }
    
    public static void comparacionRendimiento(String[] titulos) {
        System.out.println("1. COMPARACIÓN DE RENDIMIENTO");
        System.out.println("=============================");
        
        // Tu implementación
        BookNode root = new BookNode(titulos[0], "Autor", 1, 100);
        Instant inicio = Instant.now();
        
        for (int i = 1; i < titulos.length; i++) {
            root.insert(root, titulos[i], "Autor", i + 1, 100);
        }
        
        Instant fin = Instant.now();
        Duration duracionTuImplementacion = Duration.between(inicio, fin);
        
        // TreeMap
        TreeMap<String, BookNode> treeMap = new TreeMap<>();
        inicio = Instant.now();
        
        for (int i = 0; i < titulos.length; i++) {
            treeMap.put(titulos[i], new BookNode(titulos[i], "Autor", i + 1, 100));
        }
        
        fin = Instant.now();
        Duration duracionTreeMap = Duration.between(inicio, fin);
        
        System.out.println("Tiempo de inserción de " + titulos.length + " elementos:");
        System.out.println("• Tu implementación: " + duracionTuImplementacion.toNanos() + " nanosegundos");
        System.out.println("• TreeMap: " + duracionTreeMap.toNanos() + " nanosegundos");
        
        // Búsqueda
        String tituloBuscado = titulos[titulos.length / 2];
        
        inicio = Instant.now();
        BookNode resultado1 = root.search(root, tituloBuscado);
        fin = Instant.now();
        Duration duracionBusquedaTu = Duration.between(inicio, fin);
        
        inicio = Instant.now();
        BookNode resultado2 = treeMap.get(tituloBuscado);
        fin = Instant.now();
        Duration duracionBusquedaTreeMap = Duration.between(inicio, fin);
        
        System.out.println("\nTiempo de búsqueda:");
        System.out.println("• Tu implementación: " + duracionBusquedaTu.toNanos() + " nanosegundos");
        System.out.println("• TreeMap: " + duracionBusquedaTreeMap.toNanos() + " nanosegundos");
        
        System.out.println();
    }
    
    public static void comparacionFuncionalidades(String[] titulos) {
        System.out.println("2. COMPARACIÓN DE FUNCIONALIDADES");
        System.out.println("=================================");
        
        // Tu implementación
        BookNode root = new BookNode(titulos[0], "Autor", 1, 100);
        for (int i = 1; i < titulos.length; i++) {
            root.insert(root, titulos[i], "Autor", i + 1, 100);
        }
        
        // TreeMap
        TreeMap<String, BookNode> treeMap = new TreeMap<>();
        for (int i = 0; i < titulos.length; i++) {
            treeMap.put(titulos[i], new BookNode(titulos[i], "Autor", i + 1, 100));
        }
        
        System.out.println("--- Tu implementación ---");
        System.out.println("• Búsqueda: ✓");
        System.out.println("• Inserción: ✓");
        System.out.println("• Recorrido in-order: ✓");
        System.out.println("• Recorrido post-order: ✓");
        System.out.println("• Recorrido level-order: ✓");
        System.out.println("• Impresión gráfica: ✓");
        System.out.println("• Eliminación: ✗");
        System.out.println("• Búsqueda por rango: ✗");
        System.out.println("• Navegación (siguiente/anterior): ✗");
        System.out.println("• Orden automático: ✓");
        
        System.out.println("\n--- TreeMap ---");
        System.out.println("• Búsqueda: ✓");
        System.out.println("• Inserción: ✓");
        System.out.println("• Eliminación: ✓");
        System.out.println("• Búsqueda por rango: ✓");
        System.out.println("• Navegación (siguiente/anterior): ✓");
        System.out.println("• Orden automático: ✓");
        System.out.println("• Primer/último elemento: ✓");
        System.out.println("• Submapas: ✓");
        System.out.println("• Recorridos personalizados: ✓");
        System.out.println("• Thread-safe: ✗ (pero tiene ConcurrentSkipListMap)");
        
        System.out.println();
    }
    
    public static void casosDeUsoEspecificos(String[] titulos) {
        System.out.println("3. CASOS DE USO ESPECÍFICOS");
        System.out.println("===========================");
        
        // Tu implementación - Casos donde es mejor
        System.out.println("--- Tu implementación es mejor para: ---");
        System.out.println("• Aprendizaje de estructuras de datos");
        System.out.println("• Control total sobre la implementación");
        System.out.println("• Personalización específica del comportamiento");
        System.out.println("• Entender cómo funcionan los árboles binarios");
        System.out.println("• Proyectos académicos o de investigación");
        
        // TreeMap - Casos donde es mejor
        System.out.println("\n--- TreeMap es mejor para: ---");
        System.out.println("• Aplicaciones de producción");
        System.out.println("• Necesidad de operaciones avanzadas (rangos, navegación)");
        System.out.println("• Rendimiento optimizado y probado");
        System.out.println("• Integración con otras colecciones de Java");
        System.out.println("• Aplicaciones que requieren eliminación de elementos");
        
        // Ejemplo práctico: Sistema de biblioteca
        System.out.println("\n--- Ejemplo: Sistema de Biblioteca ---");
        
        // Con tu implementación
        BookNode bibliotecaCustom = new BookNode("El Quijote", "Cervantes", 1, 800);
        bibliotecaCustom.insert(bibliotecaCustom, "Cien Años de Soledad", "García Márquez", 2, 400);
        bibliotecaCustom.insert(bibliotecaCustom, "Pedro Páramo", "Rulfo", 3, 200);
        
        System.out.println("Tu implementación:");
        bibliotecaCustom.printTree(bibliotecaCustom);
        
        // Con TreeMap
        TreeMap<String, BookNode> bibliotecaTreeMap = new TreeMap<>();
        bibliotecaTreeMap.put("El Quijote", new BookNode("El Quijote", "Cervantes", 1, 800));
        bibliotecaTreeMap.put("Cien Años de Soledad", new BookNode("Cien Años de Soledad", "García Márquez", 2, 400));
        bibliotecaTreeMap.put("Pedro Páramo", new BookNode("Pedro Páramo", "Rulfo", 3, 200));
        
        System.out.println("\nTreeMap:");
        bibliotecaTreeMap.forEach((titulo, libro) -> 
            System.out.println("• " + titulo + " - " + libro.author));
        
        // Operaciones avanzadas con TreeMap
        System.out.println("\nOperaciones avanzadas con TreeMap:");
        System.out.println("• Libros entre 'C' y 'P': " + 
            bibliotecaTreeMap.subMap("C", "P").keySet());
        System.out.println("• Primer libro: " + bibliotecaTreeMap.firstKey());
        System.out.println("• Último libro: " + bibliotecaTreeMap.lastKey());
        
        System.out.println();
    }
    
    public static void ventajasDesventajas() {
        System.out.println("4. VENTAJAS Y DESVENTAJAS");
        System.out.println("=========================");
        
        System.out.println("--- Tu implementación ---");
        System.out.println("VENTAJAS:");
        System.out.println("• Control total sobre la implementación");
        System.out.println("• Excelente para aprendizaje");
        System.out.println("• Puedes personalizar cualquier aspecto");
        System.out.println("• Entiendes exactamente cómo funciona");
        System.out.println("• No dependes de librerías externas");
        
        System.out.println("\nDESVENTAJAS:");
        System.out.println("• Más código para mantener");
        System.out.println("• Posibles bugs en la implementación");
        System.out.println("• Menos funcionalidades avanzadas");
        System.out.println("• No optimizado para rendimiento");
        System.out.println("• Falta de operaciones como eliminación");
        
        System.out.println("\n--- TreeMap ---");
        System.out.println("VENTAJAS:");
        System.out.println("• Implementación probada y optimizada");
        System.out.println("• Muchas funcionalidades avanzadas");
        System.out.println("• Excelente rendimiento");
        System.out.println("• Integración perfecta con Java Collections");
        System.out.println("• Operaciones de rango y navegación");
        System.out.println("• Thread-safe con ConcurrentSkipListMap");
        
        System.out.println("\nDESVENTAJAS:");
        System.out.println("• Menos control sobre la implementación");
        System.out.println("• Puede ser excesivo para casos simples");
        System.out.println("• Dependencia de la JVM");
        
        System.out.println("\n--- RECOMENDACIÓN ---");
        System.out.println("• Para aprendizaje: Usa tu implementación");
        System.out.println("• Para proyectos reales: Usa TreeMap");
        System.out.println("• Para casos específicos: Combina ambos");
        System.out.println("• Para rendimiento crítico: Mide y compara");
        
        System.out.println();
    }
}

