package ai.remi.comm.web.config;

import io.github.mngsk.devicedetector.Detection;
import io.github.mngsk.devicedetector.DeviceDetector;
import io.github.mngsk.devicedetector.device.Device;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc user-agent解析器
 */
@Configuration
public class UserAgentConfig {

    /**
     * 初始化user-agent解析器
     */
    @Bean
    public DeviceDetector deviceDetector() {
        return new DeviceDetector.DeviceDetectorBuilder().build();
    }

    public static void main(String[] args) {
        DeviceDetector dd = new DeviceDetector.DeviceDetectorBuilder().build();
        String str = "Mozilla/5.0 (Linux; U; Android 8.0.0; zh-CN; BAC-AL00 Build/HUAWEIBAC-AL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 UCBrowser/11.9.4.974 UWS/2.13.1.48 Mobile Safari/537.36 AliApp(DingTalk/4.5.11) com.alibaba.android.rimet/10487439 Channel/227200 language/zh-CN";
        Detection detect = dd.detect(str);
        Device device = detect.getDevice().get();
        System.out.println(device.getModel().get());
    }
}
