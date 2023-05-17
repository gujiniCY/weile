package run.weile.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import run.weile.common.security.annotation.EnableCustomConfig;
import run.weile.common.security.annotation.EnableRyFeignClients;
import run.weile.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 物流模块
 *
 * @author songhaozhi
 * @since 2023/5/15
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class WeiLeLogisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeiLeLogisticsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  物流模块启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}
