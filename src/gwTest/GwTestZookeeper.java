package gwTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import org.junit.Test;

public class GwTestZookeeper implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

	@Test
	public void test_zookeeper() {
		System.out.println("will test 3");
		try {
			String path = "/willdata";
			zk = new ZooKeeper("127.0.0.1:2181", 5000, //
					new GwTestZookeeper());
			//�ȴ�zk���ӳɹ���֪ͨ
	        try {
				connectedSemaphore.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //��ȡpathĿ¼�ڵ���������ݣ���ע��Ĭ�ϵļ�����
			try {
				System.out.println(new String(zk.getData(path, true, stat)));
				
				createNewZoo(zk);
				
		        Thread.sleep(Integer.MAX_VALUE);
			} catch (KeeperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

	}

	private void createNewZoo(ZooKeeper zk) {
		// TODO Auto-generated method stub
		try {
			Stat stat = zk.exists("/willdata2", true);
			if(stat == null)
				zk.create("/willdata2", ("������Ǹ����").getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			
			stat = zk.setData("/willdata2", ("���ǳ��򽨵�").getBytes(), -1);
			
			
		} catch (KeeperException e) {

		} catch (InterruptedException e) {

		}
		
	}

	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		if(KeeperState.SyncConnected == event.getState()){
			if (EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == EventType.NodeDataChanged) {  //zkĿ¼�ڵ����ݱ仯֪ͨ�¼�
                try {
                    System.out.println(event.getPath() + "�������޸ģ���ֵΪ��" + new String(zk.getData(event.getPath(), true, stat)));
                } 
                catch (Exception e) 
                {
                	
                }
            }
            else if (event.getType()==EventType.NodeDeleted){
            	try {
                	System.out.println(event.getPath()+" deleted");
					System.out.println(zk.exists(event.getPath(), true));
				} catch (Exception e) {

				}
            }
		}
	}
}
