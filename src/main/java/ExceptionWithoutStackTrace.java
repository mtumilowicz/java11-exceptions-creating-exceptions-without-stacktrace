/**
 * Created by mtumilowicz on 2019-03-02.
 */
class ExceptionWithoutStackTrace extends RuntimeException {
    ExceptionWithoutStackTrace() {
        super(null, null, false, false);
    }
}
