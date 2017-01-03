package assign.mongo;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class connectMongo {

	private Mongo m = null;
	private DB db;
	private String ip;
	private int port;
	private String dbName;

	public connectMongo(String ip, int port, String dbName) {
		this.ip = ip;
		this.port = port;
		this.dbName = dbName;
	}

	// connect to MongoDB
	public DB connect() {
		try {
			m = new Mongo(ip, port);
			db = m.getDB(dbName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return db;
	}

	// list all collections in database
	public void listAllCollections(String DATABASE_Name) {
		if (m != null) {
			db = m.getDB(DATABASE_Name);
			Set<String> collections = db.getCollectionNames();
			for (String c : collections) {
				System.out.println("Collection: " + c);
			}
		} else {
			System.out.println("Please connect to MongoDB!");
		}
	}

	// list all documents in collection
	public void listLocationCollectionDocuments(String DATABASE_NAME, String COLLECTION_NAME) {
		if (m != null) {
			db = m.getDB(DATABASE_NAME);
			// get a collection form database
			DBCollection collection = db.getCollection(COLLECTION_NAME);
			DBCursor cur = collection.find();
			while (cur.hasNext()) {
				System.out.println("Document: " + cur.next());
			}
		} else {
			System.out.println("Please connect to MongoDB!");
		}
	}

	// insert a document
	public void insertDocument(String DATABASE_NAME, String COLLECTION_NAME) {
		if (m != null) {
			db = m.getDB(DATABASE_NAME);
			DBCollection collection = db.getCollection(COLLECTION_NAME);
			BasicDBObject doc = new BasicDBObject();
			doc.put("name", "book1");
			doc.put("by", "author1");
			collection.insert(doc);
		} else {
			System.out.println("Please connect to MongoDB!");
		}
	}

	// remove documents
	public void remove(String DATABASE_NAME, String COLLECTION_NAME) {
		if (m != null) {
			db = m.getDB(DATABASE_NAME);
			DBCollection collection = db.getCollection(COLLECTION_NAME);
			BasicDBObject cond = new BasicDBObject();
			cond.put("by", "author1");
			DBCursor cur = collection.find(cond);
			while (cur.hasNext()) {
				collection.remove(cur.next());
			}
		} else {
			System.out.println("Please connect to MongoDB!");
		}
	}

//	public static MongoClient getClient(String ip, int port) {
//		try {
//			return new MongoClient(ip, port);
//		} catch (Exception e) {
//			System.out.println("获取MongoClient失败，" + e.getMessage());
//			return null;
//		}
//	}
//
//	public static DB getDatabase(MongoClient mongoClient, String dbName) {
//		try {
//			return mongoClient.getDB(dbName);
//		} catch (Exception e) {
//			System.out.println("获取数据库失败," + e.getMessage());
//			return null;
//		}
//	}

}
