package main.java.com.Kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


public class KafkaProduce {
	
	private static Properties properties;
	
	public KafkaProduce(String url){
		properties = new Properties();
		properties.put("bootstrap.servers", url);
		properties.put("producer.type", "sync");
		properties.put("request.required.acks", "1");
		properties.put("serializer.class", "kafka.serializer.DefaultEncoder");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("bak.partitioner.class", "kafka.producer.DefaultPartitioner");
		properties.put("bak.key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("bak.value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	}
 
 
	/**
	 * @Title: sendMessage
	 * @Description: ����һ����Ϣ
	 */
	public void sendMessage(String topic, String key, String value) {
		// ʵ����produce
		KafkaProducer<String, String> kp = new KafkaProducer<String, String>(properties);
 
		// ��Ϣ��װ
		ProducerRecord<String, String> pr = new ProducerRecord<String, String>(topic, key, value);
 
		// ��������
		kp.send(pr, new Callback() {
			// �ص�����
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if (null != exception) {
					System.out.println("��¼��offset��:" + metadata.offset());
					System.out.println(exception.getMessage() + exception);
				}
			}
		});
 
		// �ر�produce
		kp.close();
	}
}
