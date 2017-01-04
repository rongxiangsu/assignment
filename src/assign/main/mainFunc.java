package assign.main;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import assign.mongo.readData;
import assign.mongo.filterData;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

public class mainFunc {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 27017;
		String dbName = "db3";
		String collectionName = "trajectory";

		MongoClient mongoClient = new MongoClient(host, port);
		MongoDatabase database = mongoClient.getDatabase(dbName);

		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(collectionName);
			collection.drop();

			readData readThread1 = new readData("/home/wushi/workspace/mongoTest/data", collection);
			readThread1.run();
			System.out.println("原始记录数量：" + collection.count());
			filterData filterdata = new filterData(collection);
			filterdata.filterNumberlowthan24();
			filterdata.filterRepeatField();

//			FindIterable<Document> findIterable = collection.find();
//			MongoCursor<Document> mongoCursor = findIterable.iterator();
//			while (mongoCursor.hasNext()) {
//				System.out.println(mongoCursor.next());
//			}
			System.out.println("清洗后记录数量：" + collection.count());

		}
	}
}