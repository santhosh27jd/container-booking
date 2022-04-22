package com.container.repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import com.container.model.Container;
import reactor.core.publisher.Mono;

/**
 * Class ContainerRepository
 * @author santhoshkumardurairaj
 *
 */
@Repository
public interface ContainerRepository extends ReactiveCassandraRepository<Container, Integer>{
	/**
	 * 
	 * @param containerType
	 * @param containerSize
	 * @return
	 */
	@AllowFiltering
	public Mono<Container> findByContainerTypeAndContainerSize(String containerType,Integer containerSize);
}
