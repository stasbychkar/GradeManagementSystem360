import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class MainFrameTest {

    @Test
    public void testMainFrameCreated() {
        MainFrame.link = new Link();
        MainFrame frame = new MainFrame();
        assertNotNull(frame);
    }

    @Test
    public void testTitleIsSet() {
        MainFrame.link = new Link();
        MainFrame frame = new MainFrame();
        assertEquals("Grade Manager", frame.getTitle());
    }

    @Test
    public void testWindowSize() {
        MainFrame.link = new Link();
        MainFrame frame = new MainFrame();
        assertEquals(800, frame.getWidth());
        assertEquals(600, frame.getHeight());
    }

    @Test
    public void testStudentTableExists() {
        MainFrame.link = new Link();
        MainFrame frame = new MainFrame();
        assertNotNull(frame.studentTable);
    }

    @Test
    public void testTableModelExists() {
        MainFrame.link = new Link();
        MainFrame frame = new MainFrame();
        assertNotNull(frame.tableModel);
    }

    @Test
    public void testEmptyFields() {
        MainFrame.ValidationResult result = MainFrame.validateStudentFields("", "", "");
        assertNotNull(result.error);
        assertEquals("All fields are required.", result.error);
    }

    @Test
    public void testInvalidIDText() {
        MainFrame.ValidationResult result = MainFrame.validateStudentFields("abc", "Alice", "3.5");
        assertNotNull(result.error);
        assertTrue(result.error.contains("ID must be a valid number."));
    }

    @Test
    public void testNegativeID() {
        MainFrame.ValidationResult result = MainFrame.validateStudentFields("-5", "Alice", "3.5");
        assertNotNull(result.error);
        assertTrue(result.error.contains("ID must be a positive integer."));
    }

    @Test
    public void testInvalidGPAFormat() {
        MainFrame.ValidationResult result = MainFrame.validateStudentFields("101", "Alice", "xyz");
        assertNotNull(result.error);
        assertTrue(result.error.contains("GPA must be a valid decimal number."));
    }

    @Test
    public void testOutOfRangeGPA() {
        MainFrame.ValidationResult result = MainFrame.validateStudentFields("101", "Alice", "4.5");
        assertNotNull(result.error);
        assertTrue(result.error.contains("GPA must be between 0.0 and 4.0."));
    }

    @Test
    public void testValidInput() {
        MainFrame.ValidationResult result = MainFrame.validateStudentFields("123", "Alice", "3.7");
        assertNull(result.error);
        assertEquals(123, result.id);
        assertEquals(3.7, result.gpa, 0.0001);
    }

    @Test
    public void testEdgeCaseGPAZero() {
        MainFrame.ValidationResult result = MainFrame.validateStudentFields("1", "Bob", "0.0");
        assertNull(result.error);
        assertEquals(0.0, result.gpa, 0.0001);
    }

    @Test
    public void testEdgeCaseGPAFour() {
        MainFrame.ValidationResult result = MainFrame.validateStudentFields("2", "Bob", "4.0");
        assertNull(result.error);
        assertEquals(4.0, result.gpa, 0.0001);
    }
}