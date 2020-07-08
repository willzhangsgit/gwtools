package gwTest;

import org.junit.Test;

import main.java.com.Kafka.KafkaCustomer;
import main.java.com.Kafka.KafkaProduce;

public class GwTestKafka {

	private String url = "127.0.0.1:9092";
	private String topic = "willDemo";


	@Test
	public void test_kafka() {
		System.out.println("will test kafka");
		try {
			//生产者
	    	KafkaProduce kp = new KafkaProduce(url);
	        //生产一条消息
	    	kp.sendMessage(topic, "key", "{\"id\":\"1234312\",\"startDate\":\"20181009\",\"endDate\":\"20181009\",\"custname\":\"张飞\",\"custid\":\"100000001\"}");
	    	
	        //消费者
	    	KafkaCustomer kc = new KafkaCustomer(url);
	        //消费指定topic的数据
	    	kc.getMessage(topic);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}

	
}
