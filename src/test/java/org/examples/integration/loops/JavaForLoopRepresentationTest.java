package org.examples.integration.loops;

import org.junit.jupiter.api.Test;

/**
 * Tests that Java `for` loop is properly detected
 *
 * <p>
 * Expected result:
 * Trace contains a loop with 3 iterations.
 */
public class JavaForLoopRepresentationTest {
    public Object escape = null;

    @Test
    public void operation() {
        escape = "START";
        for (int i = 1; i <= 3; i++) {
            Object a = i;
            escape = a.toString();
        }
        escape = "END";
    }
}
