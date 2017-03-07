# serge-raft
raft协议--java版  研发中……

造轮子系列

serge-raft技术详解：


1、采用固定节点数量，目前没做动态增减服务器。


	不采用动态增减是因为其中有一些问题还没有好的解决方案，如下：
	如果设定集群最小机器数为2，那么6台机器启动之后，服务器在线检测节点。很难在启动之后第一时间得到所有的在线机器，这就会造成，
	每台机器可能得到了不确定的在线机器。然后在这些分裂的小集群中选出多个 leader。
	因此启动之后还要进行预热，让集群达到平衡。  想不到还有什么更好的办法来避免预热。
	
	
2、客户端采用和zk相同的访问方式，即客户端需要知道服务端ip列表，在其中一个出现问题以后，立刻切换到下一个ip。


	之所以没有采用固定ip访问方式，是因为不想再引入注册中心：
	如服务器A是leader，则向外提供服务的便是A主机。当A宕机以后B选为leader，此时向外提供主机的ip即发生变化，如何让外界感知？
	1、采用注册中心，把leader的ip注册到注册中心，由注册中心统一负责调用
	2、采用客户端轮询ip列表方式，此方式需要服务端做额外的处理，即非leader收到请求以后，需要转发到leader。
