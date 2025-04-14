package org.examples.integration.loops;

import org.junit.jupiter.api.Test;

/**
 * Tests that Java `while` loop is properly detected
 *
 * Expected result:
 * Trace contains a loop with 3 iterations.
 */
public class JavaWhileLoopRepresentationTest {
    public Object escape = null;

    @Test
    public void operation() {
        int i = 1;
        escape = "START";
        while (i <= 3) {
            Object a = i;
            escape = a.toString();
            i++;
        }
        escape = "END";
    }
}
