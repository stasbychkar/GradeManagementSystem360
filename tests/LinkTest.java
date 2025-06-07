import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class LinkTest {
    @Test
    public void testLinkCreation() {
        Link link = new Link();
        assertNotNull(link);
    }

    @Test
    public void testInitialStudentListIsEmpty() {
        Link link = new Link();
        assertTrue(link.students.isEmpty());
    }   

    @Test
    public void testGenerateChartRuns() {
        Link link = new Link();
        assertDoesNotThrow(() -> link.generateChart());
    }
}
