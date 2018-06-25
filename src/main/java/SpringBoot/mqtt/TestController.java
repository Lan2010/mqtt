package SpringBoot.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private  MqttGateway mqttGateway;

    @RequestMapping("/sendMqtt")
    public String sendMqtt(String  sendData){
    	System.out.println("进入sendMqtt-------");
        mqttGateway.sendToMqtt(sendData,"hello");
        return "OK";
    }
    
}
