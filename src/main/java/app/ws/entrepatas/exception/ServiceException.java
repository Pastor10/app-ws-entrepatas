package app.ws.entrepatas.exception;


import app.ws.entrepatas.enums.ErrorCode;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException{
    private String code;
    private String message;

    public ServiceException(String code, String message) {
        super(message);
        this.code=code;
        this.message=message;
    }

    public ServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }
}
