package logic;

import junit.framework.TestCase;

import java.util.Objects;

public class PlayerTest extends TestCase {

    Player p;
    public void setUp() throws Exception {
        p  = new Player(0);
        super.setUp();
    }

    public void testHashCode() {
        assertEquals(Objects.hash(0), p.hashCode());
    }
}