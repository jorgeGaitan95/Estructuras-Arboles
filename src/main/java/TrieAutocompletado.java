import java.util.*;

/**
 * Clase que implementa un Trie (árbol de prefijos) para autocompletado
 * Permite insertar palabras y buscar todas las palabras que empiezan con un prefijo dado
 */
public class TrieAutocompletado {
    private TrieNode raiz;
    
    /**
     * Constructor que inicializa un trie vacío
     */
    public TrieAutocompletado() {
        this.raiz = new TrieNode();
    }
    
    /**
     * Inserta una palabra en el trie
     * @param palabra La palabra a insertar
     */
    public void insertar(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return;
        }
        
        TrieNode actual = raiz;
        palabra = palabra.toLowerCase(); // Normalizar a minúsculas
        
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            
            // Si el carácter no existe en los hijos, crear un nuevo nodo
            if (!actual.tieneHijo(caracter)) {
                TrieNode nuevoNodo = new TrieNode();
                actual.agregarHijo(caracter, nuevoNodo);
            }
            
            // Avanzar al siguiente nodo
            actual = actual.getHijo(caracter);
        }
        
        // Marcar el último nodo como fin de palabra
        actual.setEsFinDePalabra(true);
        actual.setPalabraCompleta(palabra);
    }
    
    /**
     * Busca una palabra completa en el trie
     * @param palabra La palabra a buscar
     * @return true si la palabra existe, false en caso contrario
     */
    public boolean buscar(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }
        
        TrieNode nodo = buscarNodo(palabra);
        return nodo != null && nodo.esFinDePalabra();
    }
    
    /**
     * Busca el nodo correspondiente a un prefijo
     * @param prefijo El prefijo a buscar
     * @return El nodo correspondiente al final del prefijo, o null si no existe
     */
    private TrieNode buscarNodo(String prefijo) {
        if (prefijo == null || prefijo.isEmpty()) {
            return raiz;
        }
        
        TrieNode actual = raiz;
        prefijo = prefijo.toLowerCase();
        
        for (int i = 0; i < prefijo.length(); i++) {
            char caracter = prefijo.charAt(i);
            
            if (!actual.tieneHijo(caracter)) {
                return null;
            }
            
            actual = actual.getHijo(caracter);
        }
        
        return actual;
    }
    
    /**
     * Obtiene todas las palabras que empiezan con un prefijo dado (autocompletado)
     * @param prefijo El prefijo para autocompletar
     * @return Lista de palabras que empiezan con el prefijo
     */
    public List<String> autocompletar(String prefijo) {
        List<String> resultados = new ArrayList<>();
        
        if (prefijo == null || prefijo.isEmpty()) {
            // Si el prefijo está vacío, retornar todas las palabras
            obtenerTodasLasPalabras(raiz, "", resultados);
            return resultados;
        }
        
        // Buscar el nodo correspondiente al prefijo
        TrieNode nodoPrefijo = buscarNodo(prefijo);
        
        if (nodoPrefijo == null) {
            // El prefijo no existe en el trie
            return resultados;
        }
        
        // Si el prefijo en sí mismo es una palabra, agregarlo
        if (nodoPrefijo.esFinDePalabra()) {
            resultados.add(prefijo);
        }
        
        // Buscar todas las palabras que continúan desde este nodo
        obtenerTodasLasPalabras(nodoPrefijo, prefijo, resultados);
        
        return resultados;
    }
    
    /**
     * Método auxiliar recursivo para obtener todas las palabras desde un nodo
     * @param nodo El nodo desde donde empezar la búsqueda
     * @param prefijoActual El prefijo acumulado hasta este nodo
     * @param resultados La lista donde se acumulan los resultados
     */
    private void obtenerTodasLasPalabras(TrieNode nodo, String prefijoActual, List<String> resultados) {
        if (nodo == null) {
            return;
        }
        
        // Recorrer todos los hijos del nodo
        for (Map.Entry<Character, TrieNode> entrada : nodo.getChildren().entrySet()) {
            char caracter = entrada.getKey();
            TrieNode hijo = entrada.getValue();
            String nuevoPrefijo = prefijoActual + caracter;
            
            // Si este nodo marca el fin de una palabra, agregarlo a los resultados
            if (hijo.esFinDePalabra()) {
                resultados.add(nuevoPrefijo);
            }
            
            // Continuar recursivamente con los hijos
            obtenerTodasLasPalabras(hijo, nuevoPrefijo, resultados);
        }
    }
    
    /**
     * Elimina una palabra del trie
     * @param palabra La palabra a eliminar
     * @return true si la palabra fue eliminada, false si no existía
     */
    public boolean eliminar(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }
        
        return eliminarRecursivo(raiz, palabra.toLowerCase(), 0);
    }
    
    /**
     * Método auxiliar recursivo para eliminar una palabra
     */
    private boolean eliminarRecursivo(TrieNode nodo, String palabra, int indice) {
        if (nodo == null) {
            return false;
        }
        
        // Si hemos llegado al final de la palabra
        if (indice == palabra.length()) {
            // Si no es fin de palabra, la palabra no existe
            if (!nodo.esFinDePalabra()) {
                return false;
            }
            
            // Marcar que ya no es fin de palabra
            nodo.setEsFinDePalabra(false);
            nodo.setPalabraCompleta(null);
            
            // Si el nodo no tiene hijos, puede ser eliminado
            return nodo.getChildren().isEmpty();
        }
        
        char caracter = palabra.charAt(indice);
        TrieNode hijo = nodo.getHijo(caracter);
        
        if (hijo == null) {
            return false; // La palabra no existe
        }
        
        boolean debeEliminarHijo = eliminarRecursivo(hijo, palabra, indice + 1);
        
        // Si el hijo debe ser eliminado y ya no es fin de palabra
        if (debeEliminarHijo && !hijo.esFinDePalabra()) {
            nodo.getChildren().remove(caracter);
            // Si el nodo actual no tiene más hijos y no es fin de palabra, puede ser eliminado
            return nodo.getChildren().isEmpty() && !nodo.esFinDePalabra();
        }
        
        return false;
    }
    
    /**
     * Obtiene el número total de palabras almacenadas en el trie
     * @return El número de palabras
     */
    public int contarPalabras() {
        return contarPalabrasRecursivo(raiz);
    }
    
    /**
     * Método auxiliar recursivo para contar palabras
     */
    private int contarPalabrasRecursivo(TrieNode nodo) {
        if (nodo == null) {
            return 0;
        }
        
        int contador = nodo.esFinDePalabra() ? 1 : 0;
        
        for (TrieNode hijo : nodo.getChildren().values()) {
            contador += contarPalabrasRecursivo(hijo);
        }
        
        return contador;
    }
    
    /**
     * Verifica si el trie está vacío
     * @return true si no hay palabras, false en caso contrario
     */
    public boolean estaVacio() {
        return raiz.getChildren().isEmpty();
    }
    
    /**
     * Obtiene todas las palabras almacenadas en el trie
     * @return Lista con todas las palabras
     */
    public List<String> obtenerTodasLasPalabras() {
        List<String> todas = new ArrayList<>();
        obtenerTodasLasPalabras(raiz, "", todas);
        return todas;
    }
    
    /**
     * Imprime todas las palabras del trie en orden alfabético
     */
    public void imprimirTodasLasPalabras() {
        List<String> palabras = obtenerTodasLasPalabras();
        Collections.sort(palabras);
        System.out.println("Palabras en el trie (" + palabras.size() + "):");
        for (String palabra : palabras) {
            System.out.println("  - " + palabra);
        }
    }
}


