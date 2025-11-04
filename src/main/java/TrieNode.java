import java.util.*;

/**
 * Clase que representa un nodo en un Trie (árbol de prefijos)
 * Cada nodo almacena un carácter y tiene referencias a sus hijos
 */
public class TrieNode {
    private Map<Character, TrieNode> children;
    private boolean esFinDePalabra;
    private String palabraCompleta; // Opcional: para almacenar la palabra completa cuando es fin de palabra
    
    /**
     * Constructor que inicializa un nodo vacío
     */
    public TrieNode() {
        this.children = new HashMap<>();
        this.esFinDePalabra = false;
        this.palabraCompleta = null;
    }
    
    /**
     * Obtiene el mapa de hijos del nodo
     */
    public Map<Character, TrieNode> getChildren() {
        return children;
    }
    
    /**
     * Verifica si este nodo marca el final de una palabra
     */
    public boolean esFinDePalabra() {
        return esFinDePalabra;
    }
    
    /**
     * Establece si este nodo marca el final de una palabra
     */
    public void setEsFinDePalabra(boolean esFinDePalabra) {
        this.esFinDePalabra = esFinDePalabra;
    }
    
    /**
     * Obtiene la palabra completa almacenada en este nodo
     */
    public String getPalabraCompleta() {
        return palabraCompleta;
    }
    
    /**
     * Establece la palabra completa en este nodo
     */
    public void setPalabraCompleta(String palabraCompleta) {
        this.palabraCompleta = palabraCompleta;
    }
    
    /**
     * Obtiene el nodo hijo para un carácter dado
     */
    public TrieNode getHijo(char caracter) {
        return children.get(caracter);
    }
    
    /**
     * Agrega un nodo hijo para un carácter dado
     */
    public void agregarHijo(char caracter, TrieNode nodo) {
        children.put(caracter, nodo);
    }
    
    /**
     * Verifica si existe un hijo para un carácter dado
     */
    public boolean tieneHijo(char caracter) {
        return children.containsKey(caracter);
    }
}


