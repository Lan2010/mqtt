package SpringBoot.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//消费者


@Component
@RabbitListener(queues = "hello")
public class SimpleRecv {
	
	@RabbitHandler
	    public void process(String message) {
	        System.out.println("[x] rev : " + message);
	    }


}
