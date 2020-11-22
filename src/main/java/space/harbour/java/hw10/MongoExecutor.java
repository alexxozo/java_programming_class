package space.harbour.java.hw10;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.function.Function;
import org.bson.Document;

public class MongoExecutor {
    MongoClient client;
    MongoDatabase database;
    MongoClientURI uri;

    public MongoExecutor() {
        uri = new MongoClientURI("mongodb+srv://alex:19SAcpelnixmW8Yy"
                + "@cluster0.3pdbe.mongodb.net/movies?retryWrites=true&w=majority");
        client = new MongoClient(uri);
        database = client.getDatabase("movies");
    }

    public <T> T execFindOne(String collection,
                             BasicDBObject searchQuery,
                             Function<Document, T> handler) {
        MongoCollection<Document> mongoCollection = database.getCollection(collection);
        FindIterable<Document> result = mongoCollection.find(searchQuery);
        return handler.apply(result.first());
    }

    public void execStoreMovie(Document document) {
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        mongoCollection.insertOne(document);
        System.out.println("Succesfully stored!");
    }

    public void execDelMovie(Document document) {
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        mongoCollection.deleteOne(document);
        System.out.println("Succesfully deleted!");
    }

}

