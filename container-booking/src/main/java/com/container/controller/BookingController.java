package com.container.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.container.model.Booking;
import com.container.model.Container;
import com.container.service.BookingService;
import com.container.service.ContainerService;
import com.container.model.Availabe;
import com.container.model.ContainerType;
import com.container.model.Response;
import com.container.exception.BookingException;
import com.container.exception.ParameterException;
import com.container.exception.ServiceException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Class BookingController
 * 
 * @author santhoshkumardurairaj
 *
 */

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	/**
	 * BookingService is injected
	 */
	@Autowired
	private BookingService bookingService;

	/**
	 * ContainerService is injected
	 */
	@Autowired
	private ContainerService containerService;

	/**
	 * URL from application yml
	 */
	@Value("${url}")
	private String URL;

	/**
	 * RestController injected
	 */

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Booking container method
	 * 
	 * @param booking details
	 * @return Reference ID
	 */
	@PostMapping
	public Response bookContainer(@RequestBody Booking booking) {
		Response response = new Response();
		try {
			// VALIDATING PARAM
			if ((booking.getContainerSize() == 20 || booking.getContainerSize() == 40)
					&& (booking.getContainerType().equals(ContainerType.DRY.toString())
							|| booking.getContainerType().equals(ContainerType.REEFER.toString()))
					&& (booking.getOrigin().length() >= 5 && booking.getOrigin().length() <= 20)
					&& (booking.getDestination().length() >= 5 && booking.getDestination().length() <= 20)
					&& (booking.getQuantity() >= 1 && booking.getQuantity() <= 100)) {
				Flux<Booking> flux = bookingService.findAll();
				if (null != flux) {
					if (flux.hasElements().share().block()) {
						Mono<Booking> mono = flux.last();
						Mono<Integer> id = mono.map(Booking::getBookingRefId);
						Integer counter = id.share().block();
						Integer newValue = counter + 1;
						booking.setBookingRefId(newValue);
					}
				}
				// CHECK CONTAINER IS AVAILABLE
				Mono<Container> containerMono = containerService
						.findByContainerTypeAndContainerSize(booking.getContainerType(), booking.getContainerSize());
				Container availCont = containerMono.share().block();
				if (availCont != null) {
					int quan = availCont.getQuantity() - booking.getQuantity();
					if (quan >= 1) {
						availCont.setQuantity(quan);
						containerService.deleteById(availCont.getId());
						containerService.save(availCont);
					} else {
						throw new BookingException();
					}
				}
				// BOOK THE CONTAINER
				Mono<Booking> bookingDetail = bookingService.save(booking);
				Mono<Integer> refId = bookingDetail.map(Booking::getBookingRefId);
				response.setBookingRefId(refId.share().block());
				return response;
			} else {
				throw new ParameterException();
			}
		} catch (Exception ex) {
			if (ex instanceof ParameterException) {
				throw new ParameterException();
			} else if (ex instanceof BookingException) {
				// HANDLING BOOKING EXCEPTION
				throw new BookingException();
			} else {
				// HANDLING SERVICE EXCEPTION
				throw new ServiceException();
			}
		}
	}

	/**
	 * Loading Container from Request for testing
	 * 
	 * @param container
	 * @return
	 */
	@PostMapping("/loadcontainer")
	public Mono<Container> loadContainer(@RequestBody Container container) {
		try {
			if ((container.getContainerSize() == 20 || container.getContainerSize() == 40)
					&& (container.getContainerType().equals(ContainerType.DRY.toString())
							|| container.getContainerType().equals(ContainerType.REEFER.toString()))
					&& (container.getQuantity() >= 1 && container.getQuantity() <= 100)) {
				Mono<Container> containerMono = containerService.findByContainerTypeAndContainerSize(
						container.getContainerType(), container.getContainerSize());
				Container availCont = containerMono.share().block();
				if (availCont != null) {
					int quan = availCont.getQuantity();
					int total = quan + container.getQuantity();
					container.setId(availCont.getId());
					container.setQuantity(total);
					containerService.deleteById(availCont.getId());
					return containerService.save(container);
				} else {
					return containerService.save(container);
				}

			} else {
				throw new ParameterException();
			}

		} catch (Exception ex) {
			if (ex instanceof ParameterException) {
				throw new ParameterException();
			} else if (ex instanceof BookingException) {
				// HANDLING BOOKING EXCEPTION
				throw new BookingException();
			} else {
				// HANDLING SERVICE EXCEPTION
				throw new ServiceException();
			}
		}
	}

	/**
	 * 
	 * method used for testing purpose
	 * 
	 * @param booking
	 * @return
	 */
	@PostMapping("/checkAndBook")
	public Response checkAvailability(@RequestBody Booking booking) {
		Response res = new Response();
		try {
			Mono<Container> containerMono = containerService
					.findByContainerTypeAndContainerSize(booking.getContainerType(), booking.getContainerSize());
			if (containerMono != null) {
				Container availCont = containerMono.share().block();
				if (availCont != null) {
					// Calling External API
					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
					HttpEntity<Booking> entity = new HttpEntity<Booking>(booking, httpHeaders);
					String respon = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
					res.setBookingRefId(Integer.parseInt(respon));
				} else {
					res.setBookingRefId(0);
				}

			} else {
				// No container
				res.setBookingRefId(0);
				throw new BookingException();
			}
			return res;
		} catch (Exception ex) {
			if (ex instanceof ParameterException) {
				throw new ParameterException();
			} else if (ex instanceof BookingException) {
				// HANDLING BOOKING EXCEPTION
				throw new BookingException();
			} else {
				// HANDLING SERVICE EXCEPTION
				throw new ServiceException();
			}
		}

	}

	/**
	 * For Testing purpose
	 * 
	 * @return
	 */
	@PostMapping("/checkAvailable")
	public Availabe get(@RequestBody Booking booking) {
		try {
			Availabe ava = new Availabe();
			Mono<Container> containerMono = containerService
					.findByContainerTypeAndContainerSize(booking.getContainerType(), booking.getContainerSize());
			if (containerMono != null) {
				Container availCont = containerMono.share().block();
				if(availCont != null) {
					int quan = availCont.getQuantity();
					ava.setAvailableSpace(quan);
				}
			}else {
				ava.setAvailableSpace(0);
			}
			return ava;
		} catch (Exception ex) {
			throw new ServiceException();
		}
	}

}
