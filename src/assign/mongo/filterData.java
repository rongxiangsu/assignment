package assign.mongo;
import java.util.List;
import com.mongodb.DB;
import java.util.ArrayList; 
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class filterData{
	
	public void filterMissingTimeData(DB db,String COLLECTION_NAME){
		BasicDBObject inQuery = new BasicDBObject();
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(4);
		list.add(5);
		inQuery.put("time", new BasicDBObject("$in", list));
		DBCollection collection = db.getCollection(COLLECTION_NAME);
		DBCursor cursor = collection.find(inQuery);
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		return;
	}
	
}