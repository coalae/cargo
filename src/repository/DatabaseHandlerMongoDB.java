package repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class DatabaseHandlerMongoDB {
	private static final String db ="mongodb://raiz3r:dbkzlxlq1@ds141351.mlab.com:41351/isedatabase";
	private static DatabaseHandlerMongoDB instance=null;
	private DatabaseHandlerMongoDB(){}
	public void erstellen(){
		MongoClientURI uri = new MongoClientURI(db);
		MongoClient mongoClient = new MongoClient(uri);
		
	}
	
}
