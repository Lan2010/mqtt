package SpringBoot.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * MQTT生产端
 * 
 * @author Administrator
 *
 */
@Configuration
public class MqttOutboundConfiguration {
	@Autowired
	private MqttProperties mqttProperties;

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		String[] serverURIs = mqttProperties.getOutbound().getUrls().split(",");
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setServerURIs(serverURIs);
		factory.setCleanSession(false);
		return factory;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel")
	public MessageHandler mqttOutbound() {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(mqttProperties.getOutbound().getClientId(),
				mqttClientFactory());
		messageHandler.setAsync(true);
		messageHandler.setDefaultTopic(mqttProperties.getOutbound().getTopic());
		return messageHandler;
	}

	@Bean
	public MessageChannel mqttOutboundChannel() {
		return new DirectChannel();
	}

}
