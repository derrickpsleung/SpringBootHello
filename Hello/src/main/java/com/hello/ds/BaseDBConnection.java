package com.hello.ds;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

@Deprecated
public class BaseDBConnection {
	

	public static void main(String[] args) {
		MongoClientURI uri = new MongoClientURI("mongodb+srv://derrickmongo:nosqlmongodb@clusterhello-mk3qk.mongodb.net/hello");


		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase database = mongoClient.getDatabase("hello");
		System.out.println(mongoClient.toString());
		System.out.println(database.toString());
		
		mongoClient.close();
	}
}
