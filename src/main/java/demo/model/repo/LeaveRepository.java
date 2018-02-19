package demo.model.repo;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.*;

import demo.model.*;

public interface LeaveRepository extends JpaRepository<LeaveRecord, Long> {

	List<LeaveRecord> findByEmpId(String empId);
	List<LeaveRecord> findByStatus(String lastName);
}