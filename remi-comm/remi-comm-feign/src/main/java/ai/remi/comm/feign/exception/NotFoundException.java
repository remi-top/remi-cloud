package ai.remi.comm.feign.exception;

import ai.remi.comm.core.constant.Constants;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Remi
 * @email remi@dianjiu.cc
 * @desc NotFoundException
 */
@Data
@NoArgsConstructor
public class NotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String message;

    public NotFoundException(String msg) {
        super(msg);
        this.code = Constants.ERROR;
    }

    public NotFoundException(String msg, Exception e) {
        super(msg + " not found,because of" + e.getMessage());
    }
}
