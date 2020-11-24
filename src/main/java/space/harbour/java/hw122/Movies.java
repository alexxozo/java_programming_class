package space.harbour.java.hw122;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Function;
import freemarker.template.Configuration;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.eclipse.jetty.http.HttpStatus;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;

/**
 * A simple CRUD example showing how to create, get, update and delete movie resources.
 */
public class Movies {

    public Movies() throws UnknownHostException {
    }

    public static void main(String[] args) {
        final Gson gson = new Gson();
        MovieExecutor movieExecutor = new MovieExecutor();

        movieExecutor.insertMovie(new Movie("Superman", 4, 2014,
                150, new String[]{"adventure", "action"}, "Spilberg",
                new String[]{"Alex", "George"}, "https://www.washingtonpost.com/graphics/2019/entertainment/oscar-nominees-movie-poster-design/img/black-panther-web.jpg"));

        movieExecutor.insertMovie(new Movie("Batman Returns", 10, 2015,
                150, new String[]{"comedy", "action"}, "Spilberg",
                new String[]{"Christan Bale", "George"}, "https://www.washingtonpost.com/graphics/2019/entertainment/oscar-nominees-movie-poster-design/img/black-panther-web.jpg"));


        movieExecutor.insertMovie(new Movie("Family Guy", 9, 2010,
                150, new String[]{"comedy", "action"}, "Spilberg",
                new String[]{"Christan Bale", "George"}, "https://www.washingtonpost.com/graphics/2019/entertainment/oscar-nominees-movie-poster-design/img/black-panther-web.jpg"));


        staticFileLocation("public");

        post("/movies", (request, response) -> {
            Movie movie = gson.fromJson(request.body(), Movie.class);
            movieExecutor.insertMovie(movie);
            return "Successfuly added!";
        });

        // Gets the movie resource for the provided id
        get("/movies/:id", (request, response) -> {
            String id = request.params(":id");
            Function<Document, Movie> handler = doc -> gson.fromJson(doc.toJson(), Movie.class);
            Movie movie = movieExecutor.getMovieById(id, handler);

            if (movie == null) {
                response.status(HttpStatus.NOT_FOUND_404);
                return "Movie not found";
            }
            if (clientAcceptsHtml(request)) {
                Map<String, Object> movieMap = new HashMap<>();
                movieMap.put("movie", movie);
                return render(movieMap, "movie.ftl");
            } else if (clientAcceptsJson(request))
                return gson.toJson(movie);

            return null;
        });

        // Updates the movie resource for the provided id with new information
        // author and title are sent in the request body as x-www-urlencoded values e.g. author=Foo&title=Bar
        // you get them by using request.queryParams("valuename")
        put("/movies/:id", (request, response) -> {
            String id = request.params(":id");

            // Update fields (just use title for example)
            BasicDBObject newData = new BasicDBObject();
            String newTitle = request.queryParams("title");

            Function<Document, Movie> handler = doc -> gson.fromJson(doc.toJson(), Movie.class);
            Movie movie = movieExecutor.getMovieById(id, handler);

            if (movie == null) {
                response.status(HttpStatus.NOT_FOUND_404);
                return "Movie not found";
            }
            if (newTitle != null) {
                newData.put("title", newTitle);
                movieExecutor.updateMovieById(id, newData, handler);
            }
            return "Movie with id '" + id + "' updated";
        });

        // Deletes the movie resource for the provided id
        delete("/movies/:id", (request, response) -> {
            String id = request.params(":id");
            Function<Document, Movie> handler = doc -> gson.fromJson(doc.toJson(), Movie.class);
            Movie movie = movieExecutor.getMovieById(id, handler);
            if (movie == null) {
                response.status(HttpStatus.NOT_FOUND_404);
                return "Movie not found";
            }
            movieExecutor.deleteMovieById(id);
            return "Movie with id '" + id + "' deleted";
        });

        // Gets all available movie resources
        get("/movies", (request, response) -> {
            Function<List<Movie>, List<Movie>> handler = x -> x;
            List<Movie> movies = movieExecutor.getMovies(handler);
            if (clientAcceptsHtml(request)) {
                Map<String, Object> moviesMap = new HashMap<>();
                moviesMap.put("movies", movies);
                System.out.println(moviesMap);
                return render(moviesMap, "movies.ftl");
            } else if (clientAcceptsJson(request))
                return gson.toJson(movies);

            return null;
        });
    }

    public static String render(Map values, String template) {
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Movies.class, "/spark.template.freemarker/");
        freeMarkerEngine.setConfiguration(configuration);

        return freeMarkerEngine.render(new ModelAndView(values, template));
    }

    public static boolean clientAcceptsHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }

    public static boolean clientAcceptsJson(Request request) {
        String accept = request.headers("Accept");
        return accept != null && (accept.contains("application/json") || accept.contains("*/*"));
    }

    public static class Movie {
        public Movie(String title, double rating, int year, int runtime, String[] genres, String director, String[] actors, String image) {
            this.title = title;
            this.rating = rating;
            this.year = year;
            this.runtime = runtime;
            this.genres = genres;
            this.director = director;
            this.actors = actors;
            this.image = image;
        }

        public ObjectId get_id() {
            return _id;
        }

        public void set_id(ObjectId _id) {
            this._id = _id;
        }

        public ObjectId _id;
        public String title;
        public double rating;
        public int year;
        public int runtime;
        public String[] genres;
        public String director;
        public String[] actors;
        public String image;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getRuntime() {
            return runtime;
        }

        public void setRuntime(int runtime) {
            this.runtime = runtime;
        }

        public String[] getGenres() {
            return genres;
        }

        public void setGenres(String[] genres) {
            this.genres = genres;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String[] getActors() {
            return actors;
        }

        public void setActors(String[] actors) {
            this.actors = actors;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return "Movie{" +
                    "title='" + title + '\'' +
                    ", rating=" + rating +
                    ", year=" + year +
                    ", runtime=" + runtime +
                    ", genres=" + Arrays.toString(genres) +
                    ", director='" + director + '\'' +
                    ", actors=" + Arrays.toString(actors) +
                    ", image='" + image + '\'' +
                    '}';
        }
    }
}