package program;

public class LinkTest {
    public static void main(String[] args) {
        Link link = new Link();
        link.readFile("/Users/sohailchutani/IdeaProjects/Project3_Release/src/main/resources/test.csv");
//        link.printStudents();
        link.generateChart();
//        System.out.println();
//        link.updateFile();
//        System.out.println();
//        System.out.println();

        // link.deleteStudent("3");
//        link.printStudents();
    }
}

/*
* public class DummyData {
    public static Object[][] sampleStudents() {
        return new Object[][] {
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
            {1, "John", 3.75, "Edit", "Delete"},
            {2, "Martha", 3.1, "Edit", "Delete"},
            {3, "Max", 3, "Edit", "Delete"},
            {4, "Carolina", 3.9, "Edit", "Delete"},
        };
    }
}*/