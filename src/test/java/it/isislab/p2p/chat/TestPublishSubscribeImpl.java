package it.isislab.p2p.chat;

import java.io.IOException;

public class TestPublishSubscribeImpl {

	public static void main(String[] args) throws Exception {
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
		
		try {
			PublishSubscribeImpl peer0 = new PublishSubscribeImpl(0, "127.0.0.1", new MessageListenerImpl(0));
			
			PublishSubscribeImpl peer1 = new PublishSubscribeImpl(1, "127.0.0.1", new MessageListenerImpl(1));
			
			PublishSubscribeImpl peer2 = new PublishSubscribeImpl(2, "127.0.0.1", new MessageListenerImpl(2));
			
			PublishSubscribeImpl peer3 = new PublishSubscribeImpl(3, "127.0.0.1", new MessageListenerImpl(3));
			
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
