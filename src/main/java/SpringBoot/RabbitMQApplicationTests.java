package SpringBoot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class RabbitMQApplicationTests {
	
	@Autowired
	 private HelloSender helloSender;
	
	@Test
	public void testSimpleQueue(){
		helloSender.send();
	}
	
}
