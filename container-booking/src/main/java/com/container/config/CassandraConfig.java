package com.container.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

/**
 * Class CassandraConfig - Configuration class
 * @author santhoshkumardurairaj
 *
 */
@Configuration
@EnableReactiveCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration{

	/**
	 * cassandra keyspace
	 * param keySpace
	 */
	
	@Value("${spring.data.cassandra.keyspace-name}")
	private String keySpace;

	@Override
	protected String getKeyspaceName() {
		return keySpace;
	}
}
