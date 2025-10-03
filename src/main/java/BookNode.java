import javax.swing.*;

public class BookNode {
    String title;
    String author;
    int id;
    int numberOfPages;


    BookNode left;
    BookNode right;


    public BookNode(String title, String author, int id, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.numberOfPages = numberOfPages;
        left = null;
        right = null;
    }

    public BookNode insert (BookNode root, String title, String author,int id, int numberOfPages) {
        if (root == null) {
            return new  BookNode(title, author, id, numberOfPages);
        }

        if (title.compareTo(root.title) < 0) {
            root.left = insert(root.left, title, author, id, numberOfPages);
        } else if (title.compareTo(root.title) > 0) {
            root.right = insert(root.right, title, author, id, numberOfPages);
        }

        return root;
    }


    public BookNode search (BookNode root, String title){
        if (root == null) {
            return null;
        }

        if (root.title.equals(title)) {
            return root;
        } else if(title.compareTo(root.title) < 0) {
            return search(root.left, title);
        }
        else {
            return search(root.right, title);
        }
    }

    public void printInOrder(BookNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.println(root.title);
            printInOrder(root.right);
        }
    }

    public void printPostOrder(BookNode root) {
        if (root != null) {
            printPostOrder(root.right);
            System.out.println(root.title);
            printPostOrder(root.left);
        }
    }

    public void levelOrder(BookNode root) {
        if (root != null) {
            System.out.println(root.title);
            levelOrder(root.left);
            levelOrder(root.right);
        }
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Imprime el árbol gráficamente con indentación y relaciones
     * @param root Nodo raíz del árbol
     * @param prefix Prefijo para la indentación
     * @param isLast Indica si es el último nodo en su nivel
     */
    public void printTreeGraphically(BookNode root, String prefix, boolean isLast) {
        if (root == null) {
            return;
        }

        // Imprimir el nodo actual
        System.out.print(prefix);
        if (isLast) {
            System.out.print("└── ");
            prefix += "    ";
        } else {
            System.out.print("├── ");
            prefix += "│   ";
        }
        
        // Mostrar información del nodo
        System.out.println("[" + root.id + "] " + root.title + " (" + root.author + ")");
        
        // Determinar si hay hijos
        boolean hasLeft = root.left != null;
        boolean hasRight = root.right != null;
        
        // Imprimir hijo izquierdo
        if (hasLeft) {
            printTreeGraphically(root.left, prefix, !hasRight);
        }
        
        // Imprimir hijo derecho
        if (hasRight) {
            printTreeGraphically(root.right, prefix, true);
        }
    }

    /**
     * Método público para imprimir el árbol desde la raíz
     * @param root Nodo raíz del árbol
     */
    public void printTree(BookNode root) {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        
        System.out.println("Estructura del árbol:");
        System.out.println("└── [" + root.title + "] " + ") [RAÍZ]");
        
        String prefix = "";
        boolean hasLeft = root.left != null;
        boolean hasRight = root.right != null;
        
        if (hasLeft) {
            printTreeGraphically(root.left, prefix, !hasRight);
        }
        
        if (hasRight) {
            printTreeGraphically(root.right, prefix, true);
        }
    }


}
