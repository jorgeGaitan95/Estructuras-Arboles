import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Ejemplo de Árbol Binario (BookNode) ===\n");
        BookNode bookNode = new BookNode("DFE","jorge",1,10);

        bookNode.insert(bookNode,"CFA","jorge",1,10);
        bookNode.insert(bookNode,"CEA","jorge",1,10);
        bookNode.insert(bookNode,"CBE","jorge",1,10);
        bookNode.insert(bookNode,"CEB","jorge",1,10);
        bookNode.insert(bookNode,"CFB","jorge",1,10);
        bookNode.insert(bookNode,"GMO","jorge",1,10);
        bookNode.insert(bookNode,"GAU","jorge",1,10);
        bookNode.insert(bookNode,"GPL","jorge",1,10);
        bookNode.insert(bookNode,"GPA","jorge",1,10);
        bookNode.insert(bookNode,"GPZ","jorge",1,10);

        bookNode.levelOrder(bookNode);
        BookNode result = bookNode.search(bookNode,"CFB");
        System.out.println(result.title);
        bookNode.printPostOrder(bookNode);

        bookNode.printTree(bookNode);
        
        System.out.println("\n\n=== Ejemplo de Trie de Autocompletado ===\n");
        
        // Crear una instancia del trie
        TrieAutocompletado trie = new TrieAutocompletado();
        
        // Insertar palabras de ejemplo
        System.out.println("Insertando palabras...");
        trie.insertar("casa");
        trie.insertar("casamiento");
        trie.insertar("casero");
        trie.insertar("casa");
        trie.insertar("casita");
        trie.insertar("carro");
        trie.insertar("carrito");
        trie.insertar("carta");
        trie.insertar("cartel");
        trie.insertar("perro");
        trie.insertar("pera");
        trie.insertar("periscopio");
        trie.insertar("programacion");
        trie.insertar("programa");
        trie.insertar("progreso");
        
        System.out.println("Total de palabras insertadas: " + trie.contarPalabras());
        System.out.println();
        
        // Mostrar todas las palabras
        trie.imprimirTodasLasPalabras();
        System.out.println();
        
        // Ejemplos de autocompletado
        System.out.println("=== Autocompletado con prefijo 'cas' ===");
        List<String> sugerencias1 = trie.autocompletar("cas");
        System.out.println("Sugerencias: " + sugerencias1);
        System.out.println();
        
        System.out.println("=== Autocompletado con prefijo 'car' ===");
        List<String> sugerencias2 = trie.autocompletar("car");
        System.out.println("Sugerencias: " + sugerencias2);
        System.out.println();
        
        System.out.println("=== Autocompletado con prefijo 'per' ===");
        List<String> sugerencias3 = trie.autocompletar("per");
        System.out.println("Sugerencias: " + sugerencias3);
        System.out.println();
        
        System.out.println("=== Autocompletado con prefijo 'prog' ===");
        List<String> sugerencias4 = trie.autocompletar("prog");
        System.out.println("Sugerencias: " + sugerencias4);
        System.out.println();
        
        // Buscar palabras
        System.out.println("=== Búsqueda de palabras ===");
        System.out.println("¿Existe 'casa'? " + trie.buscar("casa"));
        System.out.println("¿Existe 'casita'? " + trie.buscar("casita"));
        System.out.println("¿Existe 'casilla'? " + trie.buscar("casilla"));
        System.out.println();
        
        // Eliminar una palabra
        System.out.println("=== Eliminando 'casita' ===");
        boolean eliminada = trie.eliminar("casita");
        System.out.println("¿Fue eliminada? " + eliminada);
        System.out.println("¿Existe 'casita' después? " + trie.buscar("casita"));
        System.out.println("Autocompletado con 'cas' después de eliminar: " + trie.autocompletar("cas"));
    }
}
