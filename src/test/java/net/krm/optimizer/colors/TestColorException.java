package net.krm.optimizer.colors;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestColorException {

    @Test
    @SuppressWarnings("unused")
    public void testColorExceptionCS() {
        try {
            throw new ColorException(ColorErrorCode.WRONG_COLOR_STRING, "TEST_COLOR");
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.WRONG_COLOR_STRING, ex.getErrorCode());
        }
    }
}

