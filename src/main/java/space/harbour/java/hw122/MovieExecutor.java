package space.harbour.java.hw122;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Function;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MovieExecutor {
    MongoClient client;
    MongoDatabase database;
    MongoClientURI uri;
    String collection;

    public MovieExecutor() {
        uri = new MongoClientURI("mongodb+srv://alex:19SAcpelnixmW8Yy"
                + "@cluster0.3pdbe.mongodb.net/movies?retryWrites=true&w=majority");
//        uri = new MongoClientURI("mongodb://localhost:27017");
        client = new MongoClient(uri);
        database = client.getDatabase("movies");
        collection = "movies";
    }

    public List<Movies.Movie> getMovies(Function<List<Movies.Movie>, List<Movies.Movie>> handler) {
        MongoCollection<Document> mongoCollection = database.getCollection(collection);
        FindIterable<Document> result = mongoCollection.find();
        List<Movies.Movie> movies = new ArrayList<>();
        for (Document document : result) {
            movies.add(new Gson().fromJson(document.toJson(), Movies.Movie.class));
        }
        return handler.apply(movies);
    }

    public Movies.Movie getMovieById(String id, Function<Document, Movies.Movie> handler) {
        MongoCollection<Document> mongoCollection = database.getCollection(collection);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(id));
        FindIterable<Document> result = mongoCollection.find(searchQuery);
        return handler.apply(result.first());
    }

    public Movies.Movie updateMovieById(String id, BasicDBObject newData, Function<Document, Movies.Movie> handler) {
        MongoCollection<Document> mongoCollection = database.getCollection(collection);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(id));

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newData);

        mongoCollection.updateOne(searchQuery, updateObject);
        return handler.apply(mongoCollection.find(searchQuery).first());
    }

    public void insertMovie(Movies.Movie movie) {
        String json = new Gson().toJson(movie);
        Document doc = Document.parse(json);
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        mongoCollection.insertOne(doc);
        System.out.println("Succesfully stored!");
    }

    public void deleteMovieById(String id) {
        MongoCollection<Document> mongoCollection = database.getCollection("movies");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(id));
        mongoCollection.deleteOne(searchQuery);
        System.out.println("Succesfully deleted!");
    }

}
