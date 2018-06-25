package SpringBoot.mqtt01;

import org.junit.Test;

public class TestDemo {
	@Test
	public void test() {

	   PushPayload pushPayload = PushPayload.getPushPayloadBuider().setContent("test")
	         .setMobile("119")
	         .setType("2018")
	         .bulid();
//	   mqttClientComponent.push("yes",pushPayload);

	}
}
