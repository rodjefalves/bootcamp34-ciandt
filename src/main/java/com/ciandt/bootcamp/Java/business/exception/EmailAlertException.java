package com.ciandt.bootcamp.Java.business.exception;

import com.ciandt.bootcamp.Java.business.exception.base.AlertException;
import com.ciandt.bootcamp.Java.business.exception.base.ProblemKey;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class EmailAlertException extends AlertException {

    private static final StatusType statusType = Status.BAD_REQUEST;

    public EmailAlertException(ProblemKey problemKey) {
        super(statusType, problemKey);
    }

    public EmailAlertException(ProblemKey problemKey, Object... messageArgs) {
        super(statusType, problemKey, messageArgs);
    }

}
