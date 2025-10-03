public class Main {
    public static void main(String[] args) {
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
    }
}
