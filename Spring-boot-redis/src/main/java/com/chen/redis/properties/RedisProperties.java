package com.chen.redis.properties;

import java.time.Duration;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@PropertySource("classpath:application-redis.properties")
public class RedisProperties {
	/** Redis数据库索引（默认为0） */
	private int database;
	/** Redis服务器地址 */
	private String host;
	/** Redis服务器连接端口 */
	private int port;
	/** Redis服务器连接密码（默认为空） */
	private String password;
	/** 连接超时时间（毫秒） */
	private int timeout;
	private Sentinel sentinel;
	private Cluster cluster;
	private final Jedis jedis = new Jedis();
	private final Lettuce lettuce = new Lettuce();

	/** Pool properties.（连接池的配置信息） */
	public static class Pool {
		/** 连接池中的最大空闲连接 */
		private int maxIdle = 8;
		/** 连接池中的最小空闲连接 */
		private int minIdle = 0;
		/** 连接池最大连接数（使用负值表示没有限制） */
		private int maxActive = 8;
		/** 连接池最大阻塞等待时间（使用负值表示没有限制） */
		private Duration maxWait = Duration.ofMillis(-1);
		/** 
		 * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		 * 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个 
		 * */
		private boolean testOnBorrow = true;

		public int getMaxIdle() {
			return maxIdle;
		}

		public void setMaxIdle(int maxIdle) {
			this.maxIdle = maxIdle;
		}

		public int getMinIdle() {
			return minIdle;
		}

		public void setMinIdle(int minIdle) {
			this.minIdle = minIdle;
		}

		public int getMaxActive() {
			return maxActive;
		}

		public void setMaxActive(int maxActive) {
			this.maxActive = maxActive;
		}

		public Duration getMaxWait() {
			return maxWait;
		}

		public void setMaxWait(Duration maxWait) {
			this.maxWait = maxWait;
		}

		public boolean isTestOnBorrow() {
			return testOnBorrow;
		}

		public void setTestOnBorrow(boolean testOnBorrow) {
			this.testOnBorrow = testOnBorrow;
		}

	}

	/**
	 * 集群配置信息
	 * 
	 * @author chen
	 * @date 2018-12-28 05:20:58
	 */
	public static class Cluster {
		private List<String> nodes;
		private Integer maxRedirects;

		public List<String> getNodes() {
			return nodes;
		}

		public void setNodes(List<String> nodes) {
			this.nodes = nodes;
		}

		public Integer getMaxRedirects() {
			return maxRedirects;
		}

		public void setMaxRedirects(Integer maxRedirects) {
			this.maxRedirects = maxRedirects;
		}

	}

	/** 哨兵属性信息 */
	public static class Sentinel {
		private String master;
		private List<String> nodes;

		public String getMaster() {
			return master;
		}

		public void setMaster(String master) {
			this.master = master;
		}

		public List<String> getNodes() {
			return nodes;
		}

		public void setNodes(List<String> nodes) {
			this.nodes = nodes;
		}

	}

	/**
	 * redis的客户端jedis
	 * 
	 * @author chen
	 * @date 2018-12-28 05:22:31
	 */
	public static class Jedis {
		private Pool pool;

		public Pool getPool() {
			return pool;
		}

		public void setPool(Pool pool) {
			this.pool = pool;
		}

	}

	public static class Lettuce {
		private Duration shutdownTimeout = Duration.ofMillis(100);
		private Pool pool;

		public Duration getShutdownTimeout() {
			return shutdownTimeout;
		}

		public void setShutdownTimeout(Duration shutdownTimeout) {
			this.shutdownTimeout = shutdownTimeout;
		}

		public Pool getPool() {
			return pool;
		}

		public void setPool(Pool pool) {
			this.pool = pool;
		}

	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public Sentinel getSentinel() {
		return sentinel;
	}

	public void setSentinel(Sentinel sentinel) {
		this.sentinel = sentinel;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public Jedis getJedis() {
		return jedis;
	}

	public Lettuce getLettuce() {
		return lettuce;
	}

}