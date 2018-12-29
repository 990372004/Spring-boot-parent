# Spring-boot  2.0   SpringBoot
本项目将会陆续上传一些spring boot   相关的配置与集成的框架 

 如有不懂欢迎联系本人QQ：990372004 本人如有时间可为其解读
 

## Spring-boot-email 
 发送邮件基本操作 可单独作为项目运行，也可以jar包形式加入其他项目中运行 

## Spring-boot-kafka 
 Kafka is a distributed,partitioned,replicated commit logservice。
 
 1、它提供了类似于JMS的特性，但是在设计实现上完全不同，此外它并不是JMS规范的实现。
 
 2、kafka对消息保存时根据Topic进行归类，发送消息者成为Producer,消息接受者成为Consumer,此外kafka集群
      有多个kafka实例组成，每个实例(server)成为broker。
      
3、无论是kafka集群，还是producer和consumer都依赖于zookeeper来保证系统可用性集群保存一些meta信息。
	  配置：由于kafka依赖于zookeeper ，故先需要安装zookeeper ，然后安装kafka，集成配置端引用
	  
## Spring-boot-mybatis
1、mybatis-spring-boot-starter 1.3.2 mybatis支持包

2、druid-spring-boot-starter 1.1  druid数据源

3、pagehelper 5 分页插件 

4、mysql-connector-java 8  数据库连接包

5、log4j 1.2  日志

## Spring-boot-redis
1、简介：redis是一个key-value存储系统。和Memcached类似，它支持存储的value类型相对更多，包括string(字符串)、
	list(链表)、set(集合)、zset(sorted set --有序集合)和hash（哈希类型）。redis属于NOSQL，属于缓存技术，区别于传
	 统的mysql、oracle等持久化工具。redis是非关系型数据库，是缓存工具，运行速度快
	 
2、spring-boot-starter-data-redis + jedis 2.9 + jackson-annotations 2.9.8









