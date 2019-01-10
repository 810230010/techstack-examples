package basic;

import org.junit.Test;

import static org.junit.Assert.*;

public class TryCatchFinallyTest {

    @Test
    public void tryCatchDemo1() {
        assertEquals("world", TryCatchFinally.tryCatchDemo1());
    }

    @Test
    public void tryCatchDemo2() {
        assertEquals("hello", TryCatchFinally.tryCatchDemo2());
    }

    @Test
    public void tryCatchDemo3() {
        assertEquals("hello", TryCatchFinally.tryCatchDemo3());
    }

    @Test
    public void tryCatchDemo4() {
        assertEquals("world", TryCatchFinally.tryCatchDemo4());
    }

    @Test
    public void tryCatchDemo5() {
        assertEquals("world", TryCatchFinally.tryCatchDemo5());
    }
}