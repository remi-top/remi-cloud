package ai.remi.comm.feign.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Remi
 * @email remi@dianjiu.cc
 * @desc BadRequestException
 */
@Data
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
