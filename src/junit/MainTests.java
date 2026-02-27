package junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTests {

    @Test
    public void testIsSpecial() {
        assertTrue(Code.isSpecial(0));
        assertTrue(Code.isSpecial(1));
        assertTrue(Code.isSpecial(2));
        assertTrue(Code.isSpecial(3));
        assertFalse(Code.isSpecial(4));
        assertTrue(Code.isSpecial(11));
        assertFalse(Code.isSpecial(15));
        assertTrue(Code.isSpecial(36));
        assertFalse(Code.isSpecial(37));
    }

    @Test
    public void testMode() {
        assertNull(Code.mode(null));
        assertNull(Code.mode(""));
        assertEquals(Character.valueOf('b'), Code.mode("abcb"));
        assertEquals(Character.valueOf('c'), Code.mode("cbbc"));
    }

    @Test
    public void testGetCharacterCount() {
        assertEquals(0, Code.getCharacterCount(null, 'a'));
        assertEquals(0, Code.getCharacterCount("", 'a'));
        assertEquals(2, Code.getCharacterCount("abba", 'a'));
        assertEquals(2, Code.getCharacterCount("abba", 'b'));
    }

    @Test
    public void testLongestStreak() {
        assertEquals(0, Code.longestStreak(""));
        assertEquals(1, Code.longestStreak("a"));
        assertEquals(4, Code.longestStreak("abbcccaaaad"));
        assertEquals(4, Code.longestStreak("abbcccaaaa"));
    }

    @Test
    public void testRemoveDuplicates() {
        assertArrayEquals(new int[]{1}, Code.removeDuplicates(new int[]{1, 1}));
        assertArrayEquals(new int[]{1, 2, 3}, Code.removeDuplicates(new int[]{1, 2, 1, 3, 2}));
    }

    @Test
    public void testSumIgnoringDuplicates() {
        assertEquals(1, Code.sumIgnoringDuplicates(new int[]{1, 1}));
        assertEquals(3, Code.sumIgnoringDuplicates(new int[]{1, 2, 1, 2}));
    }
}