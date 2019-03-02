/**
 * Created by mtumilowicz on 2019-03-02.
 */
class ExceptionWithOverriddenFillInStackTrace extends RuntimeException {

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
