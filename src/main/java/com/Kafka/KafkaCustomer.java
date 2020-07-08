package main.java.com.Kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaCustomer {
	
	private static Properties properties;
	private long SIZE = 100;
	
	KafkaConsumer<String, String> consumer;
	
	public KafkaCustomer(String url){
		properties = new Properties();
		properties.put("bootstrap.servers", url);
		properties.put("zookeeper.connect", url);
		properties.put("group.id", "willDemo");
		properties.put("zookeeper.session.timeout.ms", "4000");
		properties.put("zookeeper.sync.time.ms", "200");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("auto.offset.reset", "earliest");
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	}
 
 
	/**
	 * @Title: getMessage
	 * @Description: 消费一个消息
	 */
	public void getMessage(String topic) {
		consumer = new KafkaConsumer<String, String>(properties);
		// 订阅主题
		consumer.subscribe(Collections.singletonList(topic));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(SIZE);
			for (ConsumerRecord<String, String> record : records) {
				System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
				System.out.println();
			}
		}
	}
 
	public void closeConsumer() {
		consumer.close();

	}
}
