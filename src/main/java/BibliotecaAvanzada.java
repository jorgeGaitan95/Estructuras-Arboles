import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class BibliotecaAvanzada {
    
    public static void main(String[] args) {
        System.out.println("=== BIBLIOTECA AVANZADA CON TREEMAP ===\n");
        
        // Crear biblioteca con TreeMap
        TreeMap<String, BookNode> biblioteca = new TreeMap<>();
        
        // Agregar libros
        biblioteca.put("El Quijote", new BookNode("El Quijote", "Cervantes", 1, 800));
        biblioteca.put("Cien Años de Soledad", new BookNode("Cien Años de Soledad", "García Márquez", 2, 400));
        biblioteca.put("Pedro Páramo", new BookNode("Pedro Páramo", "Rulfo", 3, 200));
        biblioteca.put("Rayuela", new BookNode("Rayuela", "Cortázar", 4, 600));
        biblioteca.put("Don Juan Tenorio", new BookNode("Don Juan Tenorio", "Zorrilla", 5, 300));
        biblioteca.put("La Celestina", new BookNode("La Celestina", "Rojas", 6, 250));
        biblioteca.put("El Aleph", new BookNode("El Aleph", "Borges", 7, 180));
        biblioteca.put("Ficciones", new BookNode("Ficciones", "Borges", 8, 220));
        
        // 1. Búsqueda y navegación
        ejemploBusquedaNavegacion(biblioteca);
        
        // 2. Análisis de la biblioteca
        ejemploAnalisisBiblioteca(biblioteca);
        
        // 3. Sistema de préstamos
        ejemploSistemaPrestamos(biblioteca);
        
        // 4. Recomendaciones inteligentes
        ejemploRecomendaciones(biblioteca);
        
        // 5. Estadísticas avanzadas
        ejemploEstadisticasAvanzadas(biblioteca);
    }
    
    public static void ejemploBusquedaNavegacion(TreeMap<String, BookNode> biblioteca) {
        System.out.println("1. BÚSQUEDA Y NAVEGACIÓN");
        System.out.println("========================");
        
        // Mostrar todos los libros ordenados
        System.out.println("Libros en orden alfabético:");
        biblioteca.forEach((titulo, libro) -> 
            System.out.println("• " + titulo + " - " + libro.author + " (" + libro.numberOfPages + " páginas)"));
        
        // Navegación
        System.out.println("\n--- Navegación ---");
        System.out.println("Primer libro: " + biblioteca.firstKey());
        System.out.println("Último libro: " + biblioteca.lastKey());
        
        // Buscar libros que empiecen con cierta letra
        String letra = "E";
        SortedMap<String, BookNode> librosConE = biblioteca.subMap(letra, letra + Character.MAX_VALUE);
        System.out.println("\nLibros que empiezan con '" + letra + "':");
        librosConE.forEach((titulo, libro) -> 
            System.out.println("• " + titulo));
        
        // Buscar el libro más cercano a un título
        String tituloBuscado = "Don";
        String libroCercano = biblioteca.ceilingKey(tituloBuscado);
        System.out.println("\nLibro más cercano a '" + tituloBuscado + "': " + libroCercano);
        
        System.out.println();
    }
    
    public static void ejemploAnalisisBiblioteca(TreeMap<String, BookNode> biblioteca) {
        System.out.println("2. ANÁLISIS DE LA BIBLIOTECA");
        System.out.println("============================");
        
        // Análisis por autor
        Map<String, List<BookNode>> porAutor = biblioteca.values().stream()
            .collect(Collectors.groupingBy(libro -> libro.author));
        
        System.out.println("Libros por autor:");
        porAutor.forEach((autor, libros) -> {
            System.out.println("\n" + autor + " (" + libros.size() + " libros):");
            libros.forEach(libro -> 
                System.out.println("  • " + libro.title + " (" + libro.numberOfPages + " páginas)"));
        });
        
        // Análisis por longitud
        System.out.println("\n--- Análisis por longitud ---");
        TreeMap<Integer, List<BookNode>> porLongitud = new TreeMap<>();
        biblioteca.values().forEach(libro -> {
            porLongitud.computeIfAbsent(libro.numberOfPages, k -> new ArrayList<>()).add(libro);
        });
        
        System.out.println("Libros por número de páginas:");
        porLongitud.forEach((paginas, libros) -> {
            System.out.println(paginas + " páginas: " + 
                libros.stream().map(l -> l.title).collect(Collectors.joining(", ")));
        });
        
        // Libros más largos y más cortos
        BookNode libroMasLargo = biblioteca.values().stream()
            .max(Comparator.comparing(l -> l.numberOfPages)).orElse(null);
        BookNode libroMasCorto = biblioteca.values().stream()
            .min(Comparator.comparing(l -> l.numberOfPages)).orElse(null);
        
        System.out.println("\nLibro más largo: " + libroMasLargo.title + " (" + libroMasLargo.numberOfPages + " páginas)");
        System.out.println("Libro más corto: " + libroMasCorto.title + " (" + libroMasCorto.numberOfPages + " páginas)");
        
        System.out.println();
    }
    
    public static void ejemploSistemaPrestamos(TreeMap<String, BookNode> biblioteca) {
        System.out.println("3. SISTEMA DE PRÉSTAMOS");
        System.out.println("======================");
        
        // TreeMap para préstamos (fecha -> lista de libros prestados)
        TreeMap<LocalDate, List<String>> prestamos = new TreeMap<>();
        
        LocalDate hoy = LocalDate.now();
        prestamos.put(hoy, Arrays.asList("El Quijote", "Cien Años de Soledad"));
        prestamos.put(hoy.plusDays(1), Arrays.asList("Pedro Páramo"));
        prestamos.put(hoy.plusDays(2), Arrays.asList("Rayuela", "Don Juan Tenorio"));
        prestamos.put(hoy.plusDays(3), Arrays.asList("La Celestina"));
        
        System.out.println("Préstamos programados:");
        prestamos.forEach((fecha, libros) -> {
            System.out.println(fecha + ": " + String.join(", ", libros));
        });
        
        // Buscar préstamos en un rango de fechas
        LocalDate inicio = hoy;
        LocalDate fin = hoy.plusDays(2);
        
        System.out.println("\nPréstamos entre " + inicio + " y " + fin + ":");
        prestamos.subMap(inicio, fin.plusDays(1)).forEach((fecha, libros) -> {
            System.out.println(fecha + ": " + String.join(", ", libros));
        });
        
        // Verificar disponibilidad de un libro
        String libroSolicitado = "El Quijote";
        boolean disponible = prestamos.values().stream()
            .noneMatch(lista -> lista.contains(libroSolicitado));
        
        System.out.println("\n¿Está disponible '" + libroSolicitado + "'? " + (disponible ? "Sí" : "No"));
        
        System.out.println();
    }
    
    public static void ejemploRecomendaciones(TreeMap<String, BookNode> biblioteca) {
        System.out.println("4. RECOMENDACIONES INTELIGENTES");
        System.out.println("===============================");
        
        // TreeMap para puntuaciones de libros
        TreeMap<String, Double> puntuaciones = new TreeMap<>();
        puntuaciones.put("El Quijote", 4.8);
        puntuaciones.put("Cien Años de Soledad", 4.9);
        puntuaciones.put("Pedro Páramo", 4.7);
        puntuaciones.put("Rayuela", 4.6);
        puntuaciones.put("Don Juan Tenorio", 4.2);
        puntuaciones.put("La Celestina", 4.4);
        puntuaciones.put("El Aleph", 4.9);
        puntuaciones.put("Ficciones", 4.8);
        
        // Recomendaciones por puntuación
        System.out.println("Libros ordenados por puntuación:");
        puntuaciones.descendingMap().forEach((titulo, puntuacion) -> {
            BookNode libro = biblioteca.get(titulo);
            System.out.println("★ " + puntuacion + " - " + titulo + " (" + libro.author + ")");
        });
        
        // Recomendaciones por autor
        String autorFavorito = "Borges";
        System.out.println("\nRecomendaciones del autor '" + autorFavorito + "':");
        biblioteca.values().stream()
            .filter(libro -> libro.author.equals(autorFavorito))
            .forEach(libro -> {
                double puntuacion = puntuaciones.get(libro.title);
                System.out.println("★ " + puntuacion + " - " + libro.title);
            });
        
        // Recomendaciones por longitud similar
        String libroReferencia = "Pedro Páramo";
        BookNode libroRef = biblioteca.get(libroReferencia);
        int paginasRef = libroRef.numberOfPages;
        
        System.out.println("\nLibros de longitud similar a '" + libroReferencia + "' (" + paginasRef + " páginas):");
        biblioteca.values().stream()
            .filter(libro -> Math.abs(libro.numberOfPages - paginasRef) <= 50)
            .filter(libro -> !libro.title.equals(libroReferencia))
            .forEach(libro -> {
                double puntuacion = puntuaciones.get(libro.title);
                System.out.println("★ " + puntuacion + " - " + libro.title + " (" + libro.numberOfPages + " páginas)");
            });
        
        System.out.println();
    }
    
    public static void ejemploEstadisticasAvanzadas(TreeMap<String, BookNode> biblioteca) {
        System.out.println("5. ESTADÍSTICAS AVANZADAS");
        System.out.println("=========================");
        
        // Estadísticas generales
        int totalLibros = biblioteca.size();
        int totalPaginas = biblioteca.values().stream().mapToInt(l -> l.numberOfPages).sum();
        double promedioPaginas = (double) totalPaginas / totalLibros;
        
        System.out.println("Estadísticas generales:");
        System.out.println("• Total de libros: " + totalLibros);
        System.out.println("• Total de páginas: " + totalPaginas);
        System.out.println("• Promedio de páginas por libro: " + String.format("%.1f", promedioPaginas));
        
        // Distribución por rangos de páginas
        System.out.println("\nDistribución por rangos de páginas:");
        Map<String, Long> distribucion = biblioteca.values().stream()
            .collect(Collectors.groupingBy(libro -> {
                int paginas = libro.numberOfPages;
                if (paginas < 200) return "Corto (<200)";
                else if (paginas < 400) return "Mediano (200-399)";
                else if (paginas < 600) return "Largo (400-599)";
                else return "Muy largo (600+)";
            }, Collectors.counting()));
        
        distribucion.forEach((rango, cantidad) -> 
            System.out.println("• " + rango + ": " + cantidad + " libros"));
        
        // Top 3 autores más prolíficos
        System.out.println("\nTop 3 autores más prolíficos:");
        biblioteca.values().stream()
            .collect(Collectors.groupingBy(libro -> libro.author, Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(3)
            .forEach(entry -> 
                System.out.println("• " + entry.getKey() + ": " + entry.getValue() + " libros"));
        
        // Análisis de títulos
        System.out.println("\nAnálisis de títulos:");
        int titulosConArticulo = (int) biblioteca.keySet().stream()
            .filter(titulo -> titulo.startsWith("El ") || titulo.startsWith("La ") || titulo.startsWith("Los ") || titulo.startsWith("Las "))
            .count();
        
        System.out.println("• Títulos que empiezan con artículo: " + titulosConArticulo);
        System.out.println("• Título más largo: " + biblioteca.keySet().stream()
            .max(Comparator.comparing(String::length)).orElse(""));
        System.out.println("• Título más corto: " + biblioteca.keySet().stream()
            .min(Comparator.comparing(String::length)).orElse(""));
        
        System.out.println();
    }
}
