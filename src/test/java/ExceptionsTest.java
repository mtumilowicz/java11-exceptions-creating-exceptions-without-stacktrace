import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mtumilowicz on 2019-03-02.
 */
public class ExceptionsTest {
    @Test
    public void no_stacktrace_exceptionWithoutStackTrace() {
        var exception = new ExceptionWithoutStackTrace();

        assertEquals(0, exception.getStackTrace().length);
    }

    @Test
    public void no_stacktrace_exceptionWithOverriddenFillInStackTrace() {
        var exception = new ExceptionWithOverriddenFillInStackTrace();

        assertEquals(0, exception.getStackTrace().length);
    }
}
