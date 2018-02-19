package demo;

import java.util.*;

import org.codehaus.jackson.map.*;
import org.slf4j.*;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.security.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import demo.event.*;
import demo.model.*;
import demo.model.repo.*;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@RestController
@RequestMapping("/leaves")
public class LeaveService {
	private static final Logger log = LoggerFactory.getLogger(LeaveService.class);

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	LeaveRepository leaveRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LeaveRecord>> findAll(@RequestParam(name="customerid", required=false) String customerId, 
													@RequestParam(name="status", required=false) String status){
		List<LeaveRecord> orders = null;
		
		if(customerId != null){
			orders = leaveRepository.findByEmpId(customerId);
		}else{
			orders = (List<LeaveRecord>)leaveRepository.findAll();
		}
		
//		for (Order order : results) {
//			orders.add(order);
//		}
//		orders.forEach((ord) -> System.out.println(ord.toString()));
		return new ResponseEntity<List<LeaveRecord>>(orders,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LeaveRecord> findById(@PathVariable Long id){
		LeaveRecord order = leaveRepository.findOne(id);
		return new ResponseEntity<LeaveRecord>(order,HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LeaveRecord> saveLeave(@RequestBody LeaveRecord leave) throws Exception {
		
		if(leave.getId() != null){
			return new ResponseEntity<LeaveRecord>(leave,HttpStatus.BAD_REQUEST);
		}
//		if(leave.getCustomer() != null && leave.getCustomer().getId() != null){
//			Customer cust = customerRepository.findOne(leave.getCustomer().getId());
//			leave.setCustomer(cust);
//		}
		
		leave = leaveRepository.save(leave);
		log.info("Leave saved in the database :: Leave Id == "+leave.getId());
		
		return new ResponseEntity<LeaveRecord>(leave,HttpStatus.OK);
	}
//	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<LeaveRecord> updateOrder(@RequestBody LeaveRecord order) throws Exception {
//		if(order.getId() == null){
//			return new ResponseEntity<LeaveRecord>(order,HttpStatus.BAD_REQUEST);
//		}
//	
//		order = orderRepository.save(order);
//		
//		order = orderRepository.save(order);
//		return new ResponseEntity<LeaveRecord>(order,HttpStatus.OK);
//	}
//	
}
