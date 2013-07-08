package com.tsoj.web.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.Mongo;
import com.tsoj.web.entity.Solution;


@Repository("SolutionMongoDao")
public class SolutionMongoDao {
	private MongoOperations mongoOperation;
	
	public SolutionMongoDao() throws Exception {
		mongoOperation = new MongoTemplate(new Mongo(), "tsoj");
	}
	
	public void save(Solution solution) {
		mongoOperation.save(solution);
	}
	
	public void delete(Solution solution) {
		mongoOperation.remove(solution);
	}
	
	public Solution findById(int sid) {
		Solution solution = mongoOperation.findOne(
				new Query(Criteria.where("sid").is(sid)),
				Solution.class);
		return solution;
	}

	public List<Solution> findInRange(int from, int to) {
		Query query = new Query();
		query.with(new Sort(Sort.Direction.ASC, 
				(List<String>) new ArrayList<String>(Arrays.asList("ssbmttime"))
				));
		query.skip(from-1);
		query.limit(to-from);
		List<Solution> solutions = mongoOperation.find(
				query,
				Solution.class
				);
		return solutions;
	}
	
	public List<Solution> findInRangeByPid(int from, int to, int pid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("pid").is(pid));
		query.with(new Sort(Sort.Direction.ASC, 
				(List<String>) new ArrayList<String>(Arrays.asList("ssbmttime"))
				));
		
		query.skip(from-1);
		query.limit(to-from);
		List<Solution> solutions = mongoOperation.find(query, Solution.class);
		return solutions;
	}
	
	public long countByPid(int pid) {
		return 0;
	}
	
	public List<Solution> findInRangeByUid(int from, int to, int uid) {
		return null;
	}
	
	public long countByUid(int uid) {
		return 0;
	}

	public List<Solution> findInRangeByBothId(int from, int to, int uid, int pid) {
		return null;
	}

	public void update(Solution solution) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sid").is(solution.getSid()));
		Update update = Update.update("sresult", solution.getSresult())
				.set("smemory", solution.getSmemory())
				.set("stesttime", solution.getStesttime())
				.set("stime", solution.getStime());
		System.out.println("to be updated Result: " + solution.getSresult());
		mongoOperation.updateFirst(query, update, Solution.class);
		solution = this.mongoOperation.findOne(query, Solution.class);
		System.out.println("updated Result: " + solution.getSresult());
	}
}
