package assign.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Accumulators;
import org.bson.Document;
import com.mongodb.client.MongoCursor;

public class filterData {
	private MongoCollection<Document> collection;
	List<Document> documentFilter = new ArrayList<Document>();

	public filterData(MongoCollection<Document> collection) {
		// TODO Auto-generated constructor stub
		this.collection = collection;
	}

	Block<Document> printBlock = new Block<Document>() {
		@Override
		public void apply(final Document document) {
			System.out.println(document.toJson());
		}
	};

	public void filterNumberlowthan24() {
		AggregateIterable<Document> iterableCollection = collection
				.aggregate(Arrays.asList(Aggregates.group("$personId", Accumulators.sum("sum_time", "$time"))));
		for (Document d : iterableCollection) {
			int countOfRecord = (int) d.get("sum_time");
			if (countOfRecord < 276) {
				String personid = (String) d.get("_id");
				Document query = new Document("personId", personid);
				collection.deleteMany(query);
			} else {
				Document doc = new Document("personId", (String) d.get("_id"));
				documentFilter.add(doc);
			}
		}
		return;
	}

	public void filterRepeatField() {
		// FindIterable<Document> findItrable1 = collection
		// .find(Filvalueters.and(Filters.eq("personId", "00000fe70dfd879c"),
		// Filters.eq("time", 20)));
		// MongoCursor<Document> mongoCursor1 = findItrable1.iterator();
		// int countOfCursor = 0;
		// while(mongoCursor1.hasNext()){
		// System.out.println(mongoCursor1.next());
		// countOfCursor++;
		// }
		// System.out.println(countOfCursor);
		int countOfCursor = 0;
		for (Document d : documentFilter) {
			for (int i = 0; i < 24; i++) {
				countOfCursor = 0;
				String personid = (String) d.get("personId");
				Document query1 = new Document("personId", personid).append("time", i);
				FindIterable<Document> findItrable = collection.find(query1);
				MongoCursor<Document> mongoCursor = findItrable.iterator();

				while (mongoCursor.hasNext()) {
					countOfCursor++;
					mongoCursor.next();
				}
				
				if (countOfCursor == 0) {
					Document query = new Document("personId", personid);
					collection.deleteMany(query);
				}else if(countOfCursor > 1){
					for(int j = 0;j<countOfCursor-1;j++){
						Document query = new Document("personId", personid).append("time", i);
						collection.deleteOne(query);
					}
				}
			}
		}
		
		
		return;
	}

}