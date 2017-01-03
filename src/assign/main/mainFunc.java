package assign.main;

import com.mongodb.DB;
import assign.mongo.connectMongo;
import assign.mongo.readData;

public class mainFunc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ip = "localhost";
		int port = 27017;
		String dbName = "db2";
		String collectionName = "movie";
		connectMongo M = new connectMongo(ip, port, dbName);
		DB db = M.connect();

		if (db != null) {
			readData readThread1 = new readData("/home/wushi/workspace/mongoTest/data", db, collectionName);
			readThread1.run();
			
			
			
			M.listLocationCollectionDocuments(dbName,collectionName);

			// Thread thread1 = new Thread(readThread1);
			// thread1.start();
			// readData readThread2=new
			// readData("/home/wushi/workspace/mongoTest/data",mydatabase);
			// new Thread(readThread2).start();

		}
	}
}