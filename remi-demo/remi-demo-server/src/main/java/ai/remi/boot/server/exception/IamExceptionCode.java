package ai.remi.boot.server.exception;

import ai.remi.comm.exception.enums.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IamExceptionCode implements ExceptionCode {

    /**
     * 业务异常code
     */
    INVENTORY_CHECK_FAIL("B2006", "库存不足"),
    QUERY_ORDER_FAIL("B2007", "查询订单失败"),
    ORDER_STATUS_EXCEPTION("B2008", "订单状态不合法"),
    ORDER_PRODUCT_EXCEPTION("B2010", "查询商品失败"),
    ORDER_SHIPPING_RATE_PLAN_EXCEPTION("B2011", "邮费方案不存在"),
    ORDER_VARIANTS_EXCEPTION("B2012", "库存扣减异常"),
    PROMOTION_CODE_EXCEPTION("B2013", "优惠码错误"),
    CUSTOMER_DISABLE("B2014", "用户被禁用"),
    ;


    private String code;

    private String key;
}
