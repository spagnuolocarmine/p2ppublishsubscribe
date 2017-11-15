package it.isislab.p2p.chat;

import java.io.IOException;

public class TestPublishSubscribeImpl {

	public static void main(String[] args) {
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
			
			peer1.createTopic("diletta");
			peer1.subscribetoTopic("diletta");
			peer2.subscribetoTopic("diletta");
			peer3.subscribetoTopic("diletta");
			
			peer1.createTopic("bestia");
			peer1.subscribetoTopic("bestia");
			peer2.subscribetoTopic("bestia");
						
			
			peer0.publishToTopic("diletta", "peer 0 send on topic diletta!");
			
			peer2.unsubscribeFromTopic("diletta");
			
			
			peer2.leaveNetwork();
			
			peer0.publishToTopic("diletta", "peer 0 send on topic diletta!");
			peer0.publishToTopic("diletta", "peer 0 send on topic diletta!");
			
//			peer1.publishToTopic("bestia", "peer 1 send on topic bestia!");
//			
//			peer2.subscribetoTopic("bestia2");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
