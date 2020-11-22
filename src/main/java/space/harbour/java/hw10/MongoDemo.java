package space.harbour.java.hw10;

import com.mongodb.BasicDBObject;
import java.util.Random;
import java.util.function.Function;
import org.bson.Document;

public class MongoDemo {

    public static void main(String[] args) {
        MongoExecutor executor = new MongoExecutor();
        Random rand = new Random();
        Document movie = new Document("title", "BladeRunner")
                .append("type", "Action")
                .append("runtime", "120")
                .append("director", "Alex Simion")
                .append("year", "2010");
        executor.execStoreMovie(movie);

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("title", "BladeRunner");
        Function<Document, String> handler = s -> String.valueOf(s);
        String result = (String) executor.execFindOne("movies", searchQuery, handler);
        System.out.println(result);

        executor.execDelMovie(movie);
    }

}
