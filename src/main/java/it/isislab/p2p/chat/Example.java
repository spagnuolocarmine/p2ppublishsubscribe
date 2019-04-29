package it.isislab.p2p.chat;

import java.io.IOException;
/**
 * docker build --no-cache -t test  .
 * docker run -e MASTERIP="127.0.0.1" -e ID=4 test
 * @author carminespagnuolo
 *
 */
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
		
		System.out.println("Staring peer id: "+args[1]+" with master node: "+args[0]);

	}

}
