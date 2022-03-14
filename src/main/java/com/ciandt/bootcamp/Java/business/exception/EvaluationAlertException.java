package com.ciandt.bootcamp.Java.business.exception;

import com.ciandt.bootcamp.Java.business.exception.base.AlertException;
import com.ciandt.bootcamp.Java.business.exception.base.ProblemKey;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;

public class EvaluationAlertException extends AlertException {

    private static final StatusType statusType = Status.PRECONDITION_FAILED;

    public EvaluationAlertException(ProblemKey problemKey) {
        super(statusType, problemKey);
    }

    public EvaluationAlertException(ProblemKey problemKey, Object... messageArgs) {
        super(statusType, problemKey, messageArgs);
    }
}
