package it.isislab.p2p.chat;



public interface PublishSubscribe {

	public boolean createTopic(String _topic_name);
	public boolean subscribetoTopic(String _topic_name);
	public boolean publishToTopic(String _topic_name, Object _obj);
	public boolean unsubscribeFromTopic(String _topic_name);
	public boolean leaveNetwork();
	
}
