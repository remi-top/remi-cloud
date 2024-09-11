package ai.remi.comm.core.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum YesNoEnum implements TypeEnum{

    NO(0, "否"),

    YES(1, "是");

    @JsonValue
    private Integer code;

    private String desc;
}
