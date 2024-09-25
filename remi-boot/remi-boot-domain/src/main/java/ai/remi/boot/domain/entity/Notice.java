package ai.remi.boot.domain.entity;


import ai.remi.comm.domain.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 通知公告 NoticeDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_notice")
public class Notice extends BaseEntity {


    /**
     * 通知标题
     */
    private String noticeTitle;

    /**
     * 通知类型（1通知公告 2系统消息）
     */
    private Integer noticeType;

    /**
     * 通知图标
     */
    private String noticeIcon;

    /**
     * 通知内容
     */
    private String noticeContent;

    /**
     * 通知英文内容
     */
    private String noticeContentEn;

    /**
     * 链接地址
     */
    private String linkUrl;

    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;


}
