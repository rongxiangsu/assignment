package assign.mongo;

import com.mongodb.BasicDBObject;

public class constructDoc {  
    public static BasicDBObject construct(String[] fields)  
    {  
//        int fieldsLen=fields.length;  
        BasicDBObject result=null;  
        
        String objectid = fields[0];
        int time = commonFunc.stringToInt(fields[1]);
        String longitude = fields[2];
        String latitude = fields[3];
                                           
        result=new BasicDBObject("objectid",objectid)
                                .append("time", time)  
                                .append("longitude", longitude)  
                                .append("latitude", latitude);  
        return result;  
    }  
}  