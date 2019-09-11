import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest{

    @Test
    void testgetStatusIcon(){
        assertEquals("[\u2718]", new Deadline("CG2027 Assignment", "9/9/2019 1200").getStatusIcon());
    }

    @Test
    void testToString() {
        assertEquals("[T][\u2718] CG2027 Assignment", new Todo("CG2027 Assignment").toString());
        assertEquals("[D][\u2718] CG2027 Assignment (by: 9/9/2019 1200)",
                new Deadline("CG2027 Assignment", "9/9/2019 1200").toString());
        assertEquals("[E][\u2718] CG2027 Assignment (at: 9/9/2019 1200)",
                new Event("CG2027 Assignment", "9/9/2019 1200").toString());
    }
}