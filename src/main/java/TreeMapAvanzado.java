import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TreeMapAvanzado {
    
    public static void main(String[] args) {
        System.out.println("=== FUNCIONES AVANZADAS DE TREEMAP ===\n");
        
        // 1. TreeMap con Comparator personalizado
        ejemploComparatorPersonalizado();
        
        // 2. Navegación avanzada
        ejemploNavegacionAvanzada();
        
        // 3. Operaciones de rango
        ejemploOperacionesRango();
        
        // 4. TreeMap anidado (estructura compleja)
        ejemploTreeMapAnidado();
        
        // 5. Sistema de cache con TreeMap
        ejemploCacheConTreeMap();
        
        // 6. Análisis de datos con TreeMap
        ejemploAnalisisDatos();
        
        // 7. TreeMap con objetos complejos
        ejemploObjetosComplejos();
    }
    
    // 1. TreeMap con Comparator personalizado
    public static void ejemploComparatorPersonalizado() {
        System.out.println("1. TREEMAP CON COMPARATOR PERSONALIZADO");
        System.out.println("=====================================");
        
        // Ordenar por longitud de string (más corto primero)
        TreeMap<String, Integer> porLongitud = new TreeMap<>(
            Comparator.comparing(String::length).thenComparing(String::compareTo)
        );
        
        porLongitud.put("Java", 1);
        porLongitud.put("Python", 2);
        porLongitud.put("C++", 3);
        porLongitud.put("JavaScript", 4);
        porLongitud.put("Go", 5);
        
        System.out.println("Ordenado por longitud de nombre:");
        porLongitud.forEach((lenguaje, id) -> 
            System.out.println(lenguaje + " (longitud: " + lenguaje.length() + ") -> ID: " + id));
        
        // Ordenar números en orden descendente
        TreeMap<Integer, String> numerosDesc = new TreeMap<>(Collections.reverseOrder());
        numerosDesc.put(100, "Cien");
        numerosDesc.put(50, "Cincuenta");
        numerosDesc.put(200, "Doscientos");
        numerosDesc.put(25, "Veinticinco");
        
        System.out.println("\nNúmeros en orden descendente:");
        numerosDesc.forEach((num, texto) -> 
            System.out.println(num + " -> " + texto));
        
        System.out.println();
    }
    
    // 2. Navegación avanzada
    public static void ejemploNavegacionAvanzada() {
        System.out.println("2. NAVEGACIÓN AVANZADA");
        System.out.println("=====================");
        
        TreeMap<String, String> ciudades = new TreeMap<>();
        ciudades.put("Bogotá", "Colombia");
        ciudades.put("Madrid", "España");
        ciudades.put("París", "Francia");
        ciudades.put("Londres", "Reino Unido");
        ciudades.put("Tokio", "Japón");
        ciudades.put("Nueva York", "Estados Unidos");
        ciudades.put("Sídney", "Australia");
        
        System.out.println("Todas las ciudades:");
        ciudades.forEach((ciudad, pais) -> System.out.println(ciudad + " -> " + pais));
        
        // Navegación con NavigableMap
        NavigableMap<String, String> navMap = ciudades;
        
        System.out.println("\n--- Navegación ---");
        System.out.println("Primera ciudad: " + navMap.firstKey());
        System.out.println("Última ciudad: " + navMap.lastKey());
        
        // Obtener ciudad anterior y siguiente
        String ciudadActual = "Madrid";
        System.out.println("\nCiudad actual: " + ciudadActual);
        System.out.println("Ciudad anterior: " + navMap.lowerKey(ciudadActual));
        System.out.println("Ciudad siguiente: " + navMap.higherKey(ciudadActual));
        
        // Obtener la ciudad más cercana (floor/ceiling)
        System.out.println("\nCiudad más cercana <= 'M': " + navMap.floorKey("M"));
        System.out.println("Ciudad más cercana >= 'M': " + navMap.ceilingKey("M"));
        
        // Obtener y remover primer/último elemento
        Map.Entry<String, String> primera = navMap.pollFirstEntry();
        Map.Entry<String, String> ultima = navMap.pollLastEntry();
        
        System.out.println("\nPrimera ciudad removida: " + primera.getKey() + " -> " + primera.getValue());
        System.out.println("Última ciudad removida: " + ultima.getKey() + " -> " + ultima.getValue());
        
        System.out.println();
    }
    
    // 3. Operaciones de rango
    public static void ejemploOperacionesRango() {
        System.out.println("3. OPERACIONES DE RANGO");
        System.out.println("======================");
        
        TreeMap<Integer, String> calificaciones = new TreeMap<>();
        for (int i = 1; i <= 20; i++) {
            calificaciones.put(i, "Estudiante " + i);
        }
        
        System.out.println("Todas las calificaciones:");
        calificaciones.forEach((cal, estudiante) -> 
            System.out.print(cal + " "));
        System.out.println();
        
        // Rangos inclusivos y exclusivos
        System.out.println("\n--- Rangos ---");
        System.out.println("Calificaciones >= 10: " + calificaciones.tailMap(10).keySet());
        System.out.println("Calificaciones < 15: " + calificaciones.headMap(15).keySet());
        System.out.println("Calificaciones entre 8 y 12 (inclusive): " + 
            calificaciones.subMap(8, true, 12, true).keySet());
        System.out.println("Calificaciones entre 5 y 10 (exclusive): " + 
            calificaciones.subMap(5, false, 10, false).keySet());
        
        // Contar elementos en rango
        int aprobados = calificaciones.tailMap(10).size();
        int reprobados = calificaciones.headMap(10).size();
        
        System.out.println("\nEstadísticas:");
        System.out.println("Aprobados (>=10): " + aprobados);
        System.out.println("Reprobados (<10): " + reprobados);
        
        System.out.println();
    }
    
    // 4. TreeMap anidado (estructura compleja)
    public static void ejemploTreeMapAnidado() {
        System.out.println("4. TREEMAP ANIDADO (ESTRUCTURA COMPLEJA)");
        System.out.println("=======================================");
        
        // TreeMap de departamentos -> TreeMap de empleados
        TreeMap<String, TreeMap<String, Double>> empresa = new TreeMap<>();
        
        // Departamento de IT
        TreeMap<String, Double> it = new TreeMap<>();
        it.put("Ana García", 5000.0);
        it.put("Carlos López", 4500.0);
        it.put("Beatriz Ruiz", 5200.0);
        empresa.put("IT", it);
        
        // Departamento de Ventas
        TreeMap<String, Double> ventas = new TreeMap<>();
        ventas.put("Diego Martín", 4000.0);
        ventas.put("Elena Torres", 4200.0);
        ventas.put("Fernando Silva", 3800.0);
        empresa.put("Ventas", ventas);
        
        // Departamento de Marketing
        TreeMap<String, Double> marketing = new TreeMap<>();
        marketing.put("Gabriela Vega", 4100.0);
        marketing.put("Héctor Ramos", 4300.0);
        empresa.put("Marketing", marketing);
        
        System.out.println("Estructura de la empresa:");
        empresa.forEach((departamento, empleados) -> {
            System.out.println("\n" + departamento + ":");
            empleados.forEach((empleado, salario) -> 
                System.out.println("  " + empleado + ": $" + salario));
        });
        
        // Buscar empleado con mayor salario en cada departamento
        System.out.println("\n--- Empleado con mayor salario por departamento ---");
        empresa.forEach((departamento, empleados) -> {
            Map.Entry<String, Double> mejor = empleados.lastEntry();
            System.out.println(departamento + ": " + mejor.getKey() + " ($" + mejor.getValue() + ")");
        });
        
        System.out.println();
    }
    
    // 5. Sistema de cache con TreeMap
    public static void ejemploCacheConTreeMap() {
        System.out.println("5. SISTEMA DE CACHE CON TREEMAP");
        System.out.println("==============================");
        
        // Cache con timestamp de acceso
        TreeMap<Long, String> cache = new TreeMap<>();
        long tiempoActual = System.currentTimeMillis();
        
        // Simular acceso a datos
        cache.put(tiempoActual, "Datos A");
        cache.put(tiempoActual + 1000, "Datos B");
        cache.put(tiempoActual + 2000, "Datos C");
        cache.put(tiempoActual + 3000, "Datos D");
        
        System.out.println("Cache actual:");
        cache.forEach((timestamp, datos) -> 
            System.out.println(new Date(timestamp) + " -> " + datos));
        
        // Limpiar cache antiguo (más de 2 segundos)
        long tiempoLimite = tiempoActual + 2000;
        SortedMap<Long, String> cacheAntiguo = cache.headMap(tiempoLimite);
        
        System.out.println("\nDatos a limpiar del cache:");
        cacheAntiguo.forEach((timestamp, datos) -> 
            System.out.println(new Date(timestamp) + " -> " + datos));
        
        cacheAntiguo.clear();
        
        System.out.println("\nCache después de limpieza:");
        cache.forEach((timestamp, datos) -> 
            System.out.println(new Date(timestamp) + " -> " + datos));
        
        System.out.println();
    }
    
    // 6. Análisis de datos con TreeMap
    public static void ejemploAnalisisDatos() {
        System.out.println("6. ANÁLISIS DE DATOS CON TREEMAP");
        System.out.println("===============================");
        
        // Simular ventas por mes
        TreeMap<String, Integer> ventasPorMes = new TreeMap<>();
        ventasPorMes.put("2024-01", 150);
        ventasPorMes.put("2024-02", 180);
        ventasPorMes.put("2024-03", 220);
        ventasPorMes.put("2024-04", 190);
        ventasPorMes.put("2024-05", 250);
        ventasPorMes.put("2024-06", 280);
        
        System.out.println("Ventas por mes:");
        ventasPorMes.forEach((mes, ventas) -> 
            System.out.println(mes + ": " + ventas + " unidades"));
        
        // Análisis estadístico
        int totalVentas = ventasPorMes.values().stream().mapToInt(Integer::intValue).sum();
        double promedio = (double) totalVentas / ventasPorMes.size();
        
        System.out.println("\n--- Estadísticas ---");
        System.out.println("Total de ventas: " + totalVentas);
        System.out.println("Promedio mensual: " + String.format("%.2f", promedio));
        System.out.println("Mejor mes: " + ventasPorMes.lastEntry().getKey() + 
            " (" + ventasPorMes.lastEntry().getValue() + " unidades)");
        System.out.println("Peor mes: " + ventasPorMes.firstEntry().getKey() + 
            " (" + ventasPorMes.firstEntry().getValue() + " unidades)");
        
        // Trimestre con mejor rendimiento
        System.out.println("\n--- Análisis por trimestre ---");
        String[] trimestres = {"Q1", "Q2", "Q3", "Q4"};
        int trimestre = 1;
        int sumaTrimestre = 0;
        int contador = 0;
        
        for (Map.Entry<String, Integer> entrada : ventasPorMes.entrySet()) {
            sumaTrimestre += entrada.getValue();
            contador++;
            
            if (contador == 3) {
                System.out.println("Trimestre " + trimestre + ": " + sumaTrimestre + " unidades");
                trimestre++;
                sumaTrimestre = 0;
                contador = 0;
            }
        }
        
        System.out.println();
    }
    
    // 7. TreeMap con objetos complejos
    public static void ejemploObjetosComplejos() {
        System.out.println("7. TREEMAP CON OBJETOS COMPLEJOS");
        System.out.println("===============================");
        
        // TreeMap con objetos Persona como clave
        TreeMap<Persona, String> agenda = new TreeMap<>();
        
        agenda.put(new Persona("Ana", 25), "ana@email.com");
        agenda.put(new Persona("Carlos", 30), "carlos@email.com");
        agenda.put(new Persona("Beatriz", 28), "beatriz@email.com");
        agenda.put(new Persona("Diego", 35), "diego@email.com");
        
        System.out.println("Agenda ordenada por edad:");
        agenda.forEach((persona, email) -> 
            System.out.println(persona.getNombre() + " (" + persona.getEdad() + " años) -> " + email));
        
        // Buscar personas en rango de edad
        Persona inicio = new Persona("", 25);
        Persona fin = new Persona("", 30);
        
        System.out.println("\nPersonas entre 25 y 30 años:");
        agenda.subMap(inicio, fin).forEach((persona, email) -> 
            System.out.println(persona.getNombre() + " (" + persona.getEdad() + " años)"));
        
        System.out.println();
    }
    
    // Clase auxiliar para el ejemplo de objetos complejos
    static class Persona implements Comparable<Persona> {
        private String nombre;
        private int edad;
        
        public Persona(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }
        
        public String getNombre() { return nombre; }
        public int getEdad() { return edad; }
        
        @Override
        public int compareTo(Persona otra) {
            return Integer.compare(this.edad, otra.edad);
        }
        
        @Override
        public String toString() {
            return nombre + " (" + edad + ")";
        }
    }
}
