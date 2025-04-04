package soomin.park.sajamart.global.error.excpetion;

import lombok.Getter;
import soomin.park.sajamart.global.error.ErrorCode;

@Getter
public class BusinessBaseException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessBaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessBaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
