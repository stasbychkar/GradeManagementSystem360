public class LinkTest {
    public static void main(String[] args) {
        Link link = new Link();
        link.readFile("test.csv");
        link.printStudents();
        System.out.println();
        link.updateFile();
        System.out.println();
        System.out.println();

       // link.deleteStudent("3");
        link.printStudents();
    }
}
