package com.mongodb.spring.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.spring.model.Employee;

/**
 * Service for processing {@link Employee} objects. Uses Spring's
 * {@link MongoTemplate} to perform CRUD operations.
 * <p>
 * For a complete reference to MongoDB see http://www.mongodb.org/
 * <p>
 * For a complete reference to Spring Data MongoDB see
 * http://www.springsource.org/spring-data
 * 
 * 
 */
@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired(required = true)
	private MongoTemplate mongoTemplate;
	//
	// @Autowired
	// private MongoOperations mongoOperations;
	protected static Logger logger = Logger.getLogger("service");

	/**
	 * Retrieves all Employees
	 */
	public List<Employee> listEmployeess() {
		logger.debug("Retrieving all Employees");
		MongoOperations mongoOperation = (MongoOperations) mongoTemplate;
		// List<Employee> employees = mongoTemplate.find(query, Employee.class);
		// Execute the query and find all matching entries
		List<Employee> employees = mongoOperation.findAll(Employee.class);
		return employees;
	}

	/**
	 * Retrieves a single Employee
	 */
	public Employee getEmployee(String empid) {
		MongoOperations mongoOperation = (MongoOperations) mongoTemplate;
		logger.debug("Retrieving an existing Employee");
		// Find an entry where empid matches the id
		Query searchUserQuery = new Query(Criteria.where("empId").is(empid));
		Employee savedUser = mongoOperation.findOne(searchUserQuery, Employee.class);
		return savedUser;
	}

	/**
	 * Adds a new Employee
	 */
	public Boolean addEmployee(Employee employee) {
		logger.debug("Adding a new employee");
		MongoOperations mongoOperation = (MongoOperations) mongoTemplate;
		try {
			if (employee.getEmpId() != null && employee.getEmpId() != "") {
				// Find an entry where empid matches the id
				employee.setEmpId(UUID.randomUUID().toString());
				// Insert to db
				mongoTemplate.save(employee);
			} else {
				// Set a new value to the empid property first since it's blank
				employee.setEmpId(UUID.randomUUID().toString());
				// Insert to db
				mongoTemplate.save(employee);
			}
			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to add new employee", e);
			return false;
		}
	}

	/**
	 * Deletes an existing employee
	 */
	public Boolean deleteEmployee(String empid) {
		logger.debug("Deleting existing employee");
		try {
			// Find an entry where pid matches the id
			Query query = new Query(where("empId").is(empid));
			// Run the query and delete the entry
			mongoTemplate.remove(query);
			//
			// // Find an entry where empid matches the id
			// DBObject query = new BasicDBObject();
			// query.put("empId", empid);
			// // Run the query and delete the entry
			// mongoTemplate.getDb().getCollection("employee").findAndRemove(query);
			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to delete new employee", e);
			return false;
		}
	}

	/**
	 * Edits an existing employee
	 */
	public Boolean edit(Employee employee) {
		logger.debug("Editing existing employee");
		try {
			// Find an entry where empid matches the id
			Query query = new Query(where("empId").is(employee.getEmpId()));
			// Declare an Update object.
			// This matches the update modifiers available in MongoDB
			Update update = new Update();
			update.set("Employee Name", employee.getEmpName());
			mongoTemplate.updateMulti(query, update, "collectionNam");
			update.set("empAddress", employee.getEmpAddress());
			// mongoTemplate.updateMulti(query, update, "collectionNam");
			update.set("salary", employee.getSalary());
			// mongoTemplate.updateMulti(query, update, "collectionNam");
			update.set("Employee Age", employee.getEmpAge());
			// mongoTemplate.updateMulti(query, update, "collectionNam");
			return true;
		} catch (Exception e) {
			logger.error("An error has occurred while trying to edit existing employee", e);
			return false;
		}
	}
}
