package gwTest;

import static org.junit.Assert.*;


import main.java.com.Ent.ToolEnt;
import main.java.com.tools.util.ObjUtil;
import redis.clients.jedis.Jedis;

import org.junit.Test;

public class GwTest {

	@Test
	public void test1() {
		System.out.println("will test 1");
		ToolEnt te1 = new ToolEnt();
		String rtn = te1.test1();
		System.out.println("rtn:" + rtn);
	}

	@Test
	public void test_redis() {
		System.out.println("will test 2");
		//Jedis jd = new Jedis("127.0.0.1");
		Jedis jd = new Jedis("120.27.95.182");
		System.out.println("rtn:" + jd.ping());
		//jd.set("gwkey", "gw test redis1");
		System.out.println("rtn:" + jd.get("gwkey"));
	}
}
