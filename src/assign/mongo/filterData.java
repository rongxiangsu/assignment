package assign.mongo;

import java.util.Arrays;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Accumulators;
import org.bson.Document;

public class filterData{
	private MongoCollection<Document>  collection;
	
	public filterData(MongoCollection<Document>  collection) {
		// TODO Auto-generated constructor stub
		this.collection = collection;
	}


	Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };
	
	public AggregateIterable<Document> filterNumberlowthan24(){
		AggregateIterable<Document> countCollection = collection.aggregate(
			      Arrays.asList(
			              Aggregates.group("$personId", Accumulators.sum("count", 1))
			      )
			);
		countCollection.toCollection();
		System.out.println(countCollection);
		return countCollection;
	}
	
}