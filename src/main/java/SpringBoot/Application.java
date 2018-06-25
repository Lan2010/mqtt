package SpringBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@MapperScan("SpringBoot.mapper")
@SpringBootApplication
@EnableSwagger2
public class Application 
{
    public static void main( String[] args )
    {
      SpringApplication.run(Application.class,args);

    }
}
