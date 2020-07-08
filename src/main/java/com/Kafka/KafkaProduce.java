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
	 * @Description: 生产一个消息
	 */
	public void sendMessage(String topic, String key, String value) {
		// 实例化produce
		KafkaProducer<String, String> kp = new KafkaProducer<String, String>(properties);
 
		// 消息封装
		ProducerRecord<String, String> pr = new ProducerRecord<String, String>(topic, key, value);
 
		// 发送数据
		kp.send(pr, new Callback() {
			// 回调函数
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if (null != exception) {
					System.out.println("记录的offset在:" + metadata.offset());
					System.out.println(exception.getMessage() + exception);
				}
			}
		});
 
		// 关闭produce
		kp.close();
	}
}
