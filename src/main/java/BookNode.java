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


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


}
