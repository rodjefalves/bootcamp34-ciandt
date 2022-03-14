package com.ciandt.bootcamp.Java.business.exception;

import com.ciandt.bootcamp.Java.business.exception.base.AlertException;
import com.ciandt.bootcamp.Java.business.exception.base.ProblemKey;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class NotFoundAlertException extends AlertException {

    private static final StatusType statusType = Status.NOT_FOUND;

    public NotFoundAlertException(ProblemKey problemKey) {
        super(statusType, problemKey);
    }

    public NotFoundAlertException(ProblemKey problemKey, Object... messageArgs) {
        super(statusType, problemKey, messageArgs);
    }

}
