package it.isislab.p2p.chat;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestPublishSubscribeImpl {

	protected PublishSubscribeImpl peer0, peer1, peer2, peer3;
	
	public TestPublishSubscribeImpl() throws Exception{
		class MessageListenerImpl implements MessageListener{
			int peerid;
			public MessageListenerImpl(int peerid)
			{
				this.peerid=peerid;
			}
			public Object parseMessage(Object obj) {
				System.out.println(peerid+"] (Direct Message Received) "+obj);
				return "success";
			}
			
		}
		 peer0 = new PublishSubscribeImpl(0, "127.0.0.1", new MessageListenerImpl(0));	
		 peer1 = new PublishSubscribeImpl(1, "127.0.0.1", new MessageListenerImpl(1));
		 peer2 = new PublishSubscribeImpl(2, "127.0.0.1", new MessageListenerImpl(2));
		 peer3 = new PublishSubscribeImpl(3, "127.0.0.1", new MessageListenerImpl(3));
		
	}
	@Test
	void testCaseCreateTopic(TestInfo testInfo){
		peer1.createTopic("Alice");
		assertTrue(peer2.subscribetoTopic("Alice"));
	}
	@Test
	void testCasePublishToTopic(TestInfo testInfo){
		assertTrue(peer3.publishToTopic("Alice", "peer 0 send on topic Alice!"));
	}
	//TODO to remove it!
	void testCaseGeneral(TestInfo testInfo){
	
		try {
			
			peer1.createTopic("Alice");
			peer1.subscribetoTopic("Alice");
			peer2.subscribetoTopic("Alice");
			peer3.subscribetoTopic("Alice");
			
			peer1.createTopic("Bob");
			peer1.subscribetoTopic("Bob");
			peer2.subscribetoTopic("Bob");
						
			
			peer0.publishToTopic("Alice", "peer 0 send on topic Alice!");
			
			peer2.unsubscribeFromTopic("Alice");
			
			peer2.leaveNetwork();
			
			peer0.publishToTopic("Alice", "peer 0 send on topic Alice!");
			peer0.publishToTopic("Alice", "peer 0 send on topic Alice!");
			
			System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
