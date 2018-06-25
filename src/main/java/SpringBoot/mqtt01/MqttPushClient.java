package SpringBoot.mqtt01;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttPushClient {
	 private MqttClient client;

	    private static volatile MqttPushClient mqttPushClient = null;

	    public static MqttPushClient getInstance(){

	        if(null == mqttPushClient){
	            synchronized (MqttPushClient.class){
	                if(null == mqttPushClient){
	                    mqttPushClient = new MqttPushClient();
	                }
	            }

	        }
	        return mqttPushClient;

	    }

	    private MqttPushClient() {
	        connect();
	    }

	    private void connect(){
	        try {
	            client = new MqttClient(PropertiesUtil.MQTT_HOST, PropertiesUtil.MQTT_CLIENTID, new MemoryPersistence());
	            MqttConnectOptions options = new MqttConnectOptions();
	            options.setCleanSession(false);
	            options.setUserName(PropertiesUtil.MQTT_USER_NAME);
	            options.setPassword(PropertiesUtil.MQTT_PASSWORD.toCharArray());
	            options.setConnectionTimeout(PropertiesUtil.MQTT_TIMEOUT);
	            options.setKeepAliveInterval(PropertiesUtil.MQTT_KEEP_ALIVE);
	            try {
	                client.setCallback(new PushCallback());
	                client.connect(options);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * 发布，默认qos为0，非持久化
	     * @param topic
	     * @param pushMessage
	     */
	    public void publish(String topic,PushPayload pushMessage){
	        publish(0, false, topic, pushMessage);
	    }

	    /**
	     * 发布
	     * @param qos
	     * @param retained
	     * @param topic
	     * @param pushMessage
	     */
	    public void publish(int qos,boolean retained,String topic,PushPayload pushMessage){
	        MqttMessage message = new MqttMessage();
	        message.setQos(qos);
	        message.setRetained(retained);
	        message.setPayload(pushMessage.toString().getBytes());
	        MqttTopic mTopic = client.getTopic(topic);
	        if(null == mTopic){
	            System.out.println("topic not exist");
	        }
	        MqttDeliveryToken token;
	        try {
	            token = mTopic.publish(message);
	            token.waitForCompletion();
	        } catch (MqttPersistenceException e) {
	            e.printStackTrace();
	        } catch (MqttException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * 订阅某个主题，qos默认为0
	     * @param topic
	     */
	    public void subscribe(String topic){
	        subscribe(topic,0);
	    }

	    /**
	     * 订阅某个主题
	     * @param topic
	     * @param qos
	     */
	    public void subscribe(String topic,int qos){
	        try {
	            client.subscribe(topic, qos);
	        } catch (MqttException e) {
	            e.printStackTrace();
	        }
	    }


	    public static void main(String[] args) throws Exception {
	        String kdTopic = "good";
	        PushPayload pushMessage = PushPayload.getPushPayloadBuider().setMobile("15345715326")
	                 .setContent("designModel")
	                .bulid();
	        MqttPushClient.getInstance().publish(0, false, kdTopic, pushMessage);
	    }
}
