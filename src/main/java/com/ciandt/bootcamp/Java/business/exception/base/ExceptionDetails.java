package com.ciandt.bootcamp.Java.business.exception.base;

public class ExceptionDetails {

    private String title;
    private Integer status;
    private String message;

    private ExceptionDetails() {

    }

    public String getTitle() {
        return title;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public static final class ExceptionDetailsBuilder {
        private String title;
        private Integer status;
        private String message;

        private ExceptionDetailsBuilder() {
        }

        public static ExceptionDetailsBuilder newBuilder() {
            return new ExceptionDetailsBuilder();
        }

        public ExceptionDetailsBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ExceptionDetailsBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public ExceptionDetailsBuilder detail(String detail) {
            return this;
        }

        public ExceptionDetailsBuilder timestamp(long timestamp) {
            return this;
        }

        public ExceptionDetailsBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ExceptionDetails build() {
            ExceptionDetails exceptionDetails = new ExceptionDetails();
            exceptionDetails.title = this.title;
            exceptionDetails.message = this.message;
            exceptionDetails.status = this.status;
            return exceptionDetails;
        }
    }
}
