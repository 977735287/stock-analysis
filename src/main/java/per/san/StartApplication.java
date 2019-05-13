package per.san;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * description: 启动类
 *
 * @author sanchar
 * @date 12/5/2018 14:58
 * lastUpdateBy: sanchar
 * lastUpdateDate: 12/5/2018
 */
@MapperScan(basePackages = {"per.san.*.mapper", "per.san.*.mapper.*"})
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
