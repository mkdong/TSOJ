package com.tsoj.web.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository("AvatarDao")
public class AvatarDao {
	
	private MongoTemplate mongoTemplate;
	
	public AvatarDao() {
		try {
			mongoTemplate = new MongoTemplate(new Mongo(), "tsoj");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save(byte bytes[], String fileName) {
		DB db = mongoTemplate.getDb();
		GridFS fs = new GridFS(db, "avatar");
		GridFSInputFile inputFile;
		fs.remove(fileName);
		inputFile = fs.createFile(bytes);
		inputFile.setFilename(fileName);
		inputFile.save();
	}
	
	public byte[] findOne(String fileName) {
		DB db = mongoTemplate.getDb();
		GridFS fs = new GridFS(db, "avatar");
		InputStream is;
		ByteArrayOutputStream bais;
	
		GridFSDBFile dbFile= fs.findOne(fileName);
		is = dbFile.getInputStream();
		bais = new ByteArrayOutputStream();
		byte bytes[] =new byte[(int) dbFile.getLength()];
		int count = -1;
        try {
			while((count = is.read(bytes)) != -1) {  
			    bais.write(bytes, 0, count); 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			bais.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return bais.toByteArray();
	}
}
