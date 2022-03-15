package com.ciandt.bootcamp.Java.business.exception.base;

import lombok.Getter;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;

public class AlertException extends AbstractThrowableProblem {

    @Getter
    private final ProblemKey problemKey;

    @Getter
    private Object[] messageArgs;

    public AlertException(StatusType statusType, ProblemKey problemKey) {
        super(null, null, statusType);
        this.problemKey = problemKey;
    }

    public AlertException(StatusType statusType ,ProblemKey problemKey, Object... messageArgs) {
        super(null, null, statusType);
        this.problemKey = problemKey;
        this.messageArgs = messageArgs;
    }

}
