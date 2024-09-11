package ai.remi.comm.core.excel;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})//用此注解用在属性上。
@Retention(RetentionPolicy.RUNTIME)//注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
public @interface ExcelSelected {

    /**
     * 固定下拉内容
     */
    String[] source() default {};

    /**
     * 动态下拉内容
     */
    Class<? extends ExcelDynamicSelect>[] sourceClass() default {};

    /**
     * 设置下拉框的起始行，默认为第二行
     */
    int firstRow() default 1;

    /**
     * 设置下拉框的结束行，默认为最后一行
     */
    int lastRow() default 0x10000;
}
