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
			//������
	    	KafkaProduce kp = new KafkaProduce(url);
	        //����һ����Ϣ
	    	kp.sendMessage(topic, "key", "{\"id\":\"1234312\",\"startDate\":\"20181009\",\"endDate\":\"20181009\",\"custname\":\"�ŷ�\",\"custid\":\"100000001\"}");
	    	
	        //������
	    	KafkaCustomer kc = new KafkaCustomer(url);
	        //����ָ��topic������
	    	kc.getMessage(topic);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}

	
}
