package repository;

import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class DatabaseHandlerMongoDB {
	private static DatabaseHandlerMongoDB instance=null;
	private DB db;
	private DatabaseHandlerMongoDB(){
		db=erstellen();
	}
	private DB erstellen(){
		char[] pass= new char[9];
		pass[0]='d';pass[1]='b';pass[2]='k';pass[3]='z';pass[4]='l';pass[5]='x';pass[6]='l';pass[7]='q';pass[8]='1';
		MongoCredential cres= MongoCredential.createCredential("imseDatabaseHandler", "isedatabase", pass);
		MongoClient client = new MongoClient(new ServerAddress("ds141351.mlab.com:41351"),Arrays.asList(cres));
		return db = client.getDB("isedatabase");
	}
	public DatabaseHandlerMongoDB getInstance(){
		if(instance==null){
			instance=new DatabaseHandlerMongoDB();
		}
		return instance;
	}
}
