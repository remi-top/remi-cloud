package ai.remi.comm.email.service;

import ai.remi.comm.email.config.EmailProperties;
import ai.remi.comm.email.domain.Email;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Component
public class EmailService {

    /**
     * 邮件配置
     */
    @Resource
    private EmailProperties emailProperties;

    /**
     * 邮件执行
     */
    @Resource
    private JavaMailSender mailSender;

    /**
     * thymeleaf
     */
    @Resource
    private TemplateEngine templateEngine;

    /**
     * freemarker
     */
    @Resource
    public Configuration configuration;


    /**
     * 发送文本邮件
     */
    public void sendTextMail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom(emailProperties.getFromName());
            message.setTo(email.getTo());
            message.setCc(email.getCc());
            message.setBcc(email.getBcc());
            message.setSubject(email.getSubject());
            message.setText(email.getContent());
            mailSender.send(message);
            log.info("发送基础邮件成功！");
        } catch (MailException e) {
            log.error("发送基础邮件异常：" + e);
        }
    }

    /**
     * 发送html邮件
     */
    public void sendHtmlMail(Email email) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailProperties.getFromName());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);
            // 检查附件数组是否为空
            String[] filePaths = email.getFilePaths();
            if (checkFilePaths(filePaths)) {
                for (String filePath : filePaths) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                    helper.addAttachment(fileName, file);
                }
            }
            mailSender.send(message);
            log.info("发送html邮件成功");
        } catch (MessagingException e) {
            log.error("发送html邮件异常:" + e);
        }
    }

    /**
     * 发送正文是静态资源（图片）的邮件
     */
    public void sendInlineMail(Email email) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailProperties.getFromName());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);
            // 嵌入静态资源
            FileSystemResource res = new FileSystemResource(new File(email.getRscPath()));
            helper.addInline(email.getRscId(), res);
            // 检查附件数组是否为空
            String[] filePaths = email.getFilePaths();
            if (checkFilePaths(filePaths)) {
                for (String filePath : filePaths) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                    helper.addAttachment(fileName, file);
                }
            }
            mailSender.send(message);
            log.info("发送嵌入图片的邮件成功！");
        } catch (MessagingException e) {
            log.error("发送嵌入图片的邮件异常：" + e);
        }
    }

    /**
     * 发送带附件的邮件
     */
    public void sendAttachmentsMail(Email email) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailProperties.getFromName());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);
            // 检查附件数组是否为空
            String[] filePaths = email.getFilePaths();
            if (checkFilePaths(filePaths)) {
                for (String filePath : filePaths) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                    helper.addAttachment(fileName, file);
                }
            }
            mailSender.send(message);
            log.info("发送带附件的邮件成功！");
        } catch (MessagingException e) {
            log.error("发送带附件的邮件异常：" + e);
        }
    }

    public void sendThymeleafMail(Email email) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailProperties.getFromName());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);
            // 设置邮件正文
            Context context = new Context();
            context.setVariables(email.getVariable());
            String htmlContent = templateEngine.process(email.getTemplate(), context);
            // 检查附件数组是否为空
            String[] filePaths = email.getFilePaths();
            if (checkFilePaths(filePaths)) {
                for (String filePath : filePaths) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                    helper.addAttachment("瑞米平台:" + fileName, file);
                }
            }
            // 第二个参数设置为true表示HTML格式
            helper.setText(htmlContent, true);
            mailSender.send(message);
            log.info("发送Thymeleaf模板邮件成功！");
        } catch (MessagingException e) {
            log.error("发送Thymeleaf模板邮件异常：" + e);
        }
    }

    public void sendFreemarkerMail(Email email) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailProperties.getFromName());
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getContent(), true);
            // 设置邮件正文
            Template template = configuration.getTemplate(email.getTemplate() + ".flt");
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, email.getVariable());
            // 检查附件数组是否为空
            String[] filePaths = email.getFilePaths();
            if (checkFilePaths(filePaths)) {
                for (String filePath : filePaths) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                    helper.addAttachment("瑞米平台:" + fileName, file);
                }
            }
            // 第二个参数设置为true表示HTML格式
            helper.setText(htmlContent, true);
            mailSender.send(message);
            log.info("发送Freemarker模板邮件成功！");
        } catch (Exception e) {
            log.error("发送Freemarker模板邮件异常：" + e);
        }
    }

    public static boolean checkFilePaths(String[] array) {
        if (array == null) {
            log.info("附件数组为null");
            return false;
        } else if (array.length == 0) {
            log.info("附件数组为空");
            return false;
        } else {
            log.info("附件数组不为空且不为null");
            return true;
        }
    }
}
