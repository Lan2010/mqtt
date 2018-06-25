package SpringBoot.mqtt;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class Env {
	// 加载YML格式自定义配置文件  
    @Bean  
    public static PropertySourcesPlaceholderConfigurer properties() {  
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();  
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();  
        yaml.setResources(new ClassPathResource("mqtt.yml"));//File引入  
        configurer.setProperties(yaml.getObject());  
        return configurer;  
    }  
}
