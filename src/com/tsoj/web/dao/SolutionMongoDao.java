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

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;
import com.tsoj.web.entity.Solution;


@Repository("SolutionMongoDao")
public class SolutionMongoDao {
	private MongoOperations mongoOperation;
	
	private class Sequence{
		public String _id;
		public int seq;
		public Sequence(String _id, int seq) {
			this._id = _id;
			this.seq = seq;
		}
	}
	
	public SolutionMongoDao() throws Exception {
		mongoOperation = new MongoTemplate(new Mongo(), "tsoj");
		if (!mongoOperation.collectionExists("counters")) {
			mongoOperation.createCollection("counters");
			mongoOperation.save(new Sequence("sid", 0));
		}
	}
	 
	public int getNextSequence() {
		Sequence seq = mongoOperation.findAndModify(
				Query.query(Criteria.where("_id").is("sid")),
				new Update().inc("seq", 1),
				Sequence.class);
		return seq.seq;
	}
	
	public void save(Solution solution) {
		solution.setSid(getNextSequence());
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
		query.with(new Sort(Sort.Direction.DESC, 
				(List<String>) new ArrayList<String>(Arrays.asList("sid"))
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
		query.with(new Sort(Sort.Direction.DESC, 
				(List<String>) new ArrayList<String>(Arrays.asList("sid"))
				));
		
		query.skip(from);
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
	
	public class UserRank {
		private String uid;
		private int ac;
		private int wa;
		private int tot;
		private int rank;
		
		public UserRank(String uid, int ac, int wa, int tot, int rank) {
			this.uid = uid;
			this.ac = ac;
			this.wa = wa;
			this.tot = tot;
			this.rank = rank;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public int getAc() {
			return ac;
		}
		public void setAc(int ac) {
			this.ac = ac;
		}
		public int getTot() {
			return tot;
		}
		public void setTot(int tot) {
			this.tot = tot;
		}
		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}
		public int getWa() {
			return wa;
		}
		public void setWa(int wa) {
			this.wa = wa;
		}
		
	}
	
	public List<UserRank> rank(int from, int to) {
		String map = "function() { " + 
				"if (this.sresult === 'AC') { " +
				" emit(this.uid, {ac:1, wa: 0, tot:1});" +
				"} else if (this.sresult === 'WA') {" +
				" emit(this.uid, {ac:0, wa: 1, tot:1});" +
				"} else {" +
				" emit(this.uid, {ac:0, wa: 0, tot:1});" +
				"}" +
			"}";
		String reduce = "function (key, values) {" +
				"var ac = 0;" +
				"var tot = 0;" +
				"var wa = 0;" +
				"for (var i=0; i<values.length; ++i) {" +
					"ac += values[i].ac;" +
					"wa += values[i].wa;" +
					"tot += values[i].tot;" +
    			"}" +
    			"return {ac: ac, wa: wa, tot: tot};" +
			"}";
		DBObject cmd = new BasicDBObject();
		cmd.put("mapreduce", "solution");
		cmd.put("map", map);
		cmd.put("reduce", reduce);
		cmd.put("verbose", "true");
		cmd.put("out", new BasicDBObject().append("replace", "rank"));
		//cmd.put("sort", new BasicDBObject().append("ac", -1).append("tot", 1));
		MapReduceOutput mapReduceOutput = mongoOperation.getCollection("solution").mapReduce(cmd);
		DBCollection results = mapReduceOutput.getOutputCollection();
		DBCursor cursor = results.find().sort(new BasicDBObject().append("value.ac", -1).append("value.tot", 1)).skip(from).limit(to-from);
		
		List<UserRank> res = new ArrayList<UserRank>();
		int rank = 0;
		while (cursor.hasNext()) {
			rank++;
			DBObject o = cursor.next();
			
			String id = (String) o.get("_id");
			DBObject dbo = (DBObject) o.get("value");
			
			System.out.println(dbo.toString());
			System.out.println(dbo.get("ac"));
			Double ac = (Double) dbo.get("ac");
			Double wa = (Double) dbo.get("wa");
			Double tot = (Double) dbo.get("tot");
			res.add(
					new UserRank(
							id,
							ac.intValue(),
							wa.intValue(),
							tot.intValue(),
							rank
					));
		}
		
		return res;
	}
}
