package assign.main;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import assign.mongo.readData;
import assign.mongo.filterData;
import org.bson.Document;

public class mainFunc {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 27017;
		String dbName = "db3";
		String collectionName = "trajectory";

		MongoClient mongoClient = new MongoClient(host,port);
		MongoDatabase database = mongoClient.getDatabase(dbName);
		
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(collectionName);
			collection.drop();
			
			readData readThread1 = new readData("/home/wushi/workspace/mongoTest/data", collection);
			readThread1.run();
			
			filterData filterdata = new filterData(collection);
//			filterdata.filterNumberlowthan24();

		}
	}
}