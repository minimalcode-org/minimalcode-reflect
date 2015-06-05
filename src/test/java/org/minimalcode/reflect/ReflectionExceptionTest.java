package org.minimalcode.reflect;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReflectionExceptionTest {

    @Test
    public void testReflectionInit() {
        final String message = "foo";

        ReflectionException e = new ReflectionException(message, new RuntimeException());
        assertEquals(message, e.getMessage());
        assertTrue(e.getCause() instanceof RuntimeException);
    }
}