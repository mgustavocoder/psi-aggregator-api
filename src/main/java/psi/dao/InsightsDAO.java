package psi.dao;

import com.mongodb.*;
import psi.model.Insights;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class InsightsDAO {

    private static final String DATABASE = "psi";
    private static final String COLLECTION = "insights";
    private DBCollection dbcollection;

    public InsightsDAO(){
        MongoClient mongoClient = DBConnector.Companion.getInstance().getMongoClient();
        DB database = mongoClient.getDB(DATABASE);
        dbcollection = database.getCollection(COLLECTION);
    }

    public void create(Insights insights){
        DBObject dbObject = Insights.Companion.adaptToDbObject(insights);

        BasicDBObject searchQuery = new BasicDBObject()
                .append("url", insights.getUrl())
                .append("strategy", insights.getStrategy());

        if(dbcollection.findAndModify(searchQuery, dbObject) == null){
            dbcollection.insert(dbObject);
        }
    }

    public List<Insights> read(String url, String strategy){
        DBObject dbObject = Insights.Companion.adaptToDbObject(url, strategy);
        return StreamSupport
                .stream(dbcollection.find(dbObject).spliterator(), true)
                .map(Insights::new).collect(Collectors.toList());
    }

}
