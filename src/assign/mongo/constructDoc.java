package assign.mongo;

import org.bson.Document;

public class constructDoc {  
    public static Document construct(String[] fields)  
    {  
    	Document result=null;  
        
        String objectid = fields[0];
        int time = commonFunc.stringToInt(fields[1]);
        String longitude = fields[2];
        String latitude = fields[3];
                                           
        result=new Document("personId",objectid)
                                .append("time", time)  
                                .append("longitude", longitude)  
                                .append("latitude", latitude);  
        return result;  
    }  
}  