package org.examples.integration.loops;

import org.junit.jupiter.api.Test;

/**
 * Tests that Java `do { } while` loop is properly detected
 *
 * Expected result:
 * Trace contains a loop with 3 iterations.
 */
public class JavaDoLoopRepresentationTest {
    public Object escape = null;

    @Test
    public void operation() {
        int i = 1;
        escape = "START";
        do {
            Object a = i;
            escape = a.toString();
            i++;
        } while (i <= 3);
        escape = "END";
    }
}
