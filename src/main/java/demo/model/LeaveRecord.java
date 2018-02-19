package demo.model;

import java.io.*;
import java.util.*;

import javax.persistence.*;

import org.springframework.data.domain.*;

@Entity
@Table(name = "demo_order")
public class LeaveRecord implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String empId;
    private String type;
    private String startDate;
    private String endDate;
    private String status;
    
//    @OneToOne(cascade = CascadeType.PERSIST , fetch=FetchType.EAGER)
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
    
    protected LeaveRecord() {}

    public LeaveRecord(String empId, String type, String start, String end, String status) {
    	this.empId=empId;
    	this.type=type;
    	this.startDate=start;
    	this.endDate=end;
    	this.status=status;
    }

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}