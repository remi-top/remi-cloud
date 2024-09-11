package ai.remi.comm.email.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    /**
     * 收件人，多个收件人用“,”隔开
     */
    private String to;
    /**
     * 抄送人，多个抄送人用“,”隔开
     */
    private String cc;
    /**
     * 密送人，多个密送人用“,”隔开
     */
    private String bcc;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 图片资源路径
     */
    private String rscPath;
    /**
     * 图片资源ID（CID）
     */
    private String rscId;
    /**
     * 附件路径
     */
    private String[] filePaths;
    /**
     * 邮件模板地址
     */
    private String template;
    /**
     * 邮件模板变量
     */
    private Map<String, Object> variable;

}
