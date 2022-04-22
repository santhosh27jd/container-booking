package com.container.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.container.model.Container;
import com.container.repository.ContainerRepository;

import reactor.core.publisher.Mono;

/**
 * Class ContainerService
 * @author santhoshkumardurairaj
 *
 */
@Service
public class ContainerService {
	
	/**
	 * ContainerRepository object injected
	 */
	@Autowired
	private ContainerRepository containerRepository;

	/**
	 * save
	 * @param container
	 * @return
	 */
	public Mono<Container> save(Container container){
		return containerRepository.save(container);
	}

	/**
	 * findByContainerTypeAndContainerSize
	 * @param containerType
	 * @param containerSize
	 * @return
	 */
	public Mono<Container> findByContainerTypeAndContainerSize(String containerType, int containerSize) {
		// TODO Auto-generated method stub
		return containerRepository.findByContainerTypeAndContainerSize(containerType, containerSize);
	}

	/**
	 * deleteById
	 * @param id
	 */
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		containerRepository.deleteById(id);
	}

}
