package it.isislab.p2p.chat;

import java.io.IOException;

public class Example {
	
	

	public static void main(String[] args) throws IOException {
		
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
		
		PublishSubscribeImpl peer = new PublishSubscribeImpl(Integer.parseInt(args[1]), args[0], new MessageListenerImpl(Integer.parseInt(args[1])));

	}

}
