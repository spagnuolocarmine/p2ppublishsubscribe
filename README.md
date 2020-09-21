# Publish/Subscribe Paradigm on P2P Networks

A p2p Java library for Publish/Subscribe communication paradigm.

This project is developed and supported for the University of Salerno master class of Distributed Systems. It aims to show an example of the P2P framework/library [TomP2P](https://tomp2p.net/).

## Prerequisites of this example project

- Concurrent and Object-oriented programming fundamental (Threads and Observer pattern are required).
- Distributed System foundamental (Distributed Hash Tables, DHT is required).
- Java 7 or greater.
- Apache Maven.
- Eclipse (optional).


## What is Publish/Subscribe paradigm?

"_Publish and Subscribe is a well-established communications paradigm that allows any number of publishers to communicate with any number of subscribers asynchronously and anonymously via an event channel._" 

-- DavidHoulding page088 DrDobbsJournal July 2000

Publish-subscribe sytems is a distributed communication paradigm for f client-server or peer-to-peer architecture. Publish/subscribe messaging allows you to decouple the provider of information, from the consumers of that information. The sending application and receiving application do not need to know anything about each other for the information to be sent and received. Each participant in a pub/sub-based communication system can take on the role of a publisher or a subscriber of information. Publishers produce information,
referred in the literature as notifications (or notifications), which is consumed by subscribers. The main semantical characterization of pub/sub is in the way notifications flow from senders to receivers: receivers are not directly targeted from publisher, but rather they are indirectly addressed according to the content of notifications. That is, a subscriber expresses its interest by issuing subscriptions for specific notifications, independently from the publishers that produces them, and then it is asynchronously notified for
all notifications, submitted by any publisher, that match their subscription.

Different ways for specifying the notifications of interest have led to identifying different variants of the pub/sub paradigm. Several subscription models appear in the literature, characterized by different expressive powers.

![PB model](https://i-msdn.sec.s-msft.com/dynimg/IC141963.gif)

_*In this project we consider only the Topic-based Model.*_ In the topic-based model notifications are grouped in topics (or subjects) i.e., a subscriber declares its interest for a particular topic and will receive all notifications related to that topic. In other words, the filter _s.f_ of a subscription _s_ is simply the specification of a topic. Each topic corresponds to a logical
event channel ideally connecting each possible publisher to all interested subscribers. That is, there exists a static association between a channel and allits subscribers, then when a notification is published, the system does not
have to calculate all the receivers. 

## Project Structure

Usign Maven you can add the dependencies to TomP2P in the pom.xml file. 

```
<repositories>
    <repository>
        <id>tomp2p.net</id>
         <url>http://tomp2p.net/dev/mvn/</url>
     </repository>
</repositories>
<dependencies>
   <dependency>
     <groupId>net.tomp2p</groupId>
     <artifactId>tomp2p-all</artifactId>
      <version>5.0-Beta8</version>
   </dependency>
</dependencies>
```

The package ```src/main/java/it/isislab/p2p/chat/``` provides three Java classes: 

- _MessageListener_ a interface for listener of messages received by a peer.
- _PublishSubscribe_ a interface that defines the Publish/Subscribe communication paradigm.	
- _PublishSubscribeImpl_ an implementation of the _PublishSubscribe_ interface that exploits the TomP2P library.
- _Example_ an example REPL application of a peers network able to communicate using the publish/subscribe.

## Build your app in a Docker container

An example application is provided using Docker container, running on a local machine. See the Dockerfile, for the builing details.

First of all you can build your docker container:

```docker build --no-cache -t p2p-pp-client  .```

#### Start the master peer

After that you can start the master peer, in interactive mode (-i) and with two (-e) environment variables:

```docker run -i --name MASTER-PEER -e MASTERIP="127.0.0.1" -e ID=0 p2p-pp-client```

,the MASTERIP envirnoment variable is the master peer ip address and the ID environment variable is the unique id of your peer. Rember you have to run the master peer using the ID=0.

**Note that**: after the first launch, you can launch the master node using the following command: 
```docker start -i MASTER-PEER```.

#### Start a generic peer

When master is started you have to check the ip address of your container:

- Check the docker <container ID>: ```docker ps```
- Check the IP address: ```docker inspect <container ID>```

Now you can start your peers varying the unique peer id:

```docker run -i --name PEER-1 -e MASTERIP="172.17.0.2" -e ID=1 p2p-pp-client```

**Note that**: after the first launch, you can launch this peer node using the following command: 
```docker start -i PEER-1```.
