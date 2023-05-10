package run.weile.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import run.weile.common.security.annotation.EnableCustomConfig;
import run.weile.common.security.annotation.EnableRyFeignClients;
import run.weile.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 用户模块
 *
 * @author songhaozhi
 * @since 2023/5/10
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class WeiLeUserApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(WeiLeUserApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  用户模块启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}
