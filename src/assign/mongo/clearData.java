package assign.mongo;
import com.mongodb.BasicDBObject;

public class clearData{
	
	public BasicDBObject filterMissingTimeData(){
		BasicDBObject inQuery = new BasicDBObject();
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(4);
		list.add(5);
		inQuery.put("number", new BasicDBObject("$in", list));
		DBCursor cursor = collection.find(inQuery);
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
	
}