package demo.ui;

import java.util.*;

import org.springframework.beans.factory.annotation.*;

import com.vaadin.annotations.*;
import com.vaadin.data.util.*;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.*;
import com.vaadin.ui.*;

import demo.model.*;
import demo.model.repo.*;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	LeaveRepository repo;
	Grid grid;

	@Autowired
	public VaadinUI(LeaveRepository repo) {
	    this.repo = repo;
	    this.grid = new Grid();
	}

	@Override
	protected void init(VaadinRequest request) {
	    setContent(grid);
	    grid.setHeight("90%");
	    grid.setWidth("90%");
	    listCustomers();
	}

	private void listCustomers() {
		List<LeaveRecord> leaves = new ArrayList<>();
		for (LeaveRecord leave : repo.findAll()) {
			leaves.add(leave);
		}
		
		BeanItemContainer container = new BeanItemContainer(LeaveRecord.class, leaves);
		System.out.println(container.getContainerPropertyIds()); 
		
	    grid.setContainerDataSource(container);
		//System.out.println(grid.getColumns());
	    grid.setColumnOrder("id","empId","type","startDate","endDate","comments","status");
		
	}
	
}
