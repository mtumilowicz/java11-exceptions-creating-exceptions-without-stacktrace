[![Build Status](https://travis-ci.com/mtumilowicz/java11-creating-exceptions-without-stacktrace.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-creating-exceptions-without-stacktrace)

# java11-exceptions-creating-exceptions-without-stacktrace

# preface
* it could be valuable to take a look at: https://github.com/mtumilowicz/java11-exceptions-throwing-exceptions-is-expensive
* we could create exception without stacktrace by:
    * using dedicated constructor
        ```
        protected Exception(String message, Throwable cause,
                            boolean enableSuppression,
                            boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
        ```
    * overriding `fillInStackTrace()`
        ```
        public synchronized Throwable fillInStackTrace() {
            if (stackTrace != null ||
                backtrace != null /* Out of protocol state */ ) {
                fillInStackTrace(0);
                stackTrace = UNASSIGNED_STACK;
            }
            return this;
        }
        
        private native Throwable fillInStackTrace(int dummy);
        ```
# project description
We will show how to create exception without stacktrace:
1. constructor approach
    ```
    class ExceptionWithoutStackTrace extends RuntimeException {
        ExceptionWithoutStackTrace() {
            super(null, null, false, false);
        }
    }
    ```
    and test:
    ```
    var exception = new ExceptionWithoutStackTrace();
    
    assertEquals(0, exception.getStackTrace().length);
    ```
1. `fillInStackTrace` approach
    ```
    class ExceptionWithOverriddenFillInStackTrace extends RuntimeException {
    
        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }
    ```
    and test:
    ```
    var exception = new ExceptionWithOverriddenFillInStackTrace();
    
    assertEquals(0, exception.getStackTrace().length);
    ```
