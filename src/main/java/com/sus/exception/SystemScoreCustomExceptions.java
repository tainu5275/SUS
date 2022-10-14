package com.sus.exception;

public class SystemScoreCustomExceptions {

    public static class CalculateScoreException extends BaseException {

        public CalculateScoreException(String errorMessage, int status) {
            super(errorMessage, status);
        }
    }

    public static class LocalizedSessionScoreException extends BaseException {

        public LocalizedSessionScoreException(String errorMessage, int status) {
            super(errorMessage, status);
        }
    }
}
