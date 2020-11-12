package space.harbour.java.hw4;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

public class Movie implements Jsonable {
    String title;
    int year;
    String released;
    int runtime;
    String[] genres;
    Director director;
    Writer[] writers;
    Actor[] actors;
    String plot;
    String[] languages;
    String[] countries;
    String awards;
    String poster;
    Rating[] ratings;

    public static class Director implements Jsonable {
        String name;

        @Override
        public String toString() {
            return "Director{"
                    + "name='" + name + '\''
                    + '}';
        }

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("Name", name)
                    .build();
        }

        @Override
        public String toJsonString() {
            return this.toJsonObject().toString();
        }
    }

    public static class Writer implements Jsonable {
        String name;
        String type;

        @Override
        public String toString() {
            return "Writer{"
                    + "name='" + name + '\''
                    + ", type='" + type + '\''
                    + '}';
        }

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("Name", name)
                    .add("Type", type)
                    .build();
        }

        @Override
        public String toJsonString() {
            return this.toJsonObject().toString();
        }
    }

    public static class Actor implements Jsonable {
        String name;
        String as;

        @Override
        public String toString() {
            return "Actor{"
                    + "name='" + name + '\''
                    + ", as='" + as + '\''
                    + '}';
        }

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("Name", name)
                    .add("As", as)
                    .build();
        }

        @Override
        public String toJsonString() {
            return this.toJsonObject().toString();
        }
    }

    public static class Rating implements Jsonable {
        String source;
        String value;
        int votes;

        @Override
        public String toString() {
            return "Rating{"
                    + "source='" + source + '\''
                    + ", value='" + value + '\''
                    + ", votes='" + votes + '\''
                    + '}';
        }

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("Source", source)
                    .add("Value", value)
                    .add("Votes", votes)
                    .build();
        }

        @Override
        public String toJsonString() {
            return this.toJsonObject().toString();
        }
    }

    @Override
    public JsonObject toJsonObject() {
        JsonArrayBuilder genresBuilder = Json.createArrayBuilder();
        for (String genre : genres) {
            genresBuilder.add(genre);
        }

        JsonArrayBuilder writersBuilder = Json.createArrayBuilder();
        for (Writer writer : writers) {
            writersBuilder.add(writer.toJsonObject());
        }

        JsonArrayBuilder actorsBuilder = Json.createArrayBuilder();
        for (Actor actor : actors) {
            actorsBuilder.add(actor.toJsonObject());
        }

        JsonArrayBuilder languagesBuilder = Json.createArrayBuilder();
        for (String lang : languages) {
            languagesBuilder.add(lang);
        }

        JsonArrayBuilder countriesBuilder = Json.createArrayBuilder();
        for (String country : countries) {
            countriesBuilder.add(country);
        }

        JsonArrayBuilder ratingsBuilder = Json.createArrayBuilder();
        for (Rating rating : ratings) {
            ratingsBuilder.add(rating.toJsonObject());
        }

        return Json.createObjectBuilder()
                .add("Title", title)
                .add("Year", year)
                .add("Released", released)
                .add("Runtime", runtime)
                .add("Genres", genresBuilder.build())
                .add("Director", director.toJsonObject())
                .add("Writers", writersBuilder.build())
                .add("Actors", actorsBuilder.build())
                .add("Plot", plot)
                .add("Languages", languagesBuilder.build())
                .add("Countries", countriesBuilder.build())
                .add("Awards", awards)
                .add("Poster", poster)
                .add("Ratings", ratingsBuilder.build())
                .build();
    }

    @Override
    public String toJsonString() {
        return this.toJsonObject().toString();
    }

    @Override
    public String toString() {
        return "Movie{"
                + "title='" + title + '\''
                + ", year='" + year + '\''
                + ", released='" + released + '\''
                + ", runtime='" + runtime + '\''
                + ", genres=" + Arrays.toString(genres)
                + ", director=" + director.toString()
                + ", writers=" + Arrays.toString(writers)
                + ", actors=" + Arrays.toString(actors)
                + ", plot='" + plot + '\''
                + ", languages=" + Arrays.toString(languages)
                + ", countries=" + Arrays.toString(countries)
                + ", awards='" + awards + '\''
                + ", poster='" + poster + '\''
                + ", ratings=" + Arrays.toString(ratings)
                + '}';
    }

    public void readFromJsonFile(String filename) throws FileNotFoundException {
        FileInputStream in = new FileInputStream("./src/main/java/space/harbour/java/hw4/"
                + filename);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        JsonReader reader = Json.createReader(bufferedInputStream);
        JsonObject jsonObject = reader.readObject();
        this.title = jsonObject.getString("Title");
        this.year = jsonObject.getInt("Year");
        this.released = jsonObject.getString("Released");
        this.runtime = jsonObject.getInt("Runtime");

        JsonArray jsonGenresArray = jsonObject.getJsonArray("Genres");
        this.genres = new String[jsonGenresArray.size()];
        for (int i = 0; i < this.genres.length; i++) {
            this.genres[i] = jsonGenresArray.get(i).toString();
        }

        JsonObject jsonDirector = jsonObject.getJsonObject("Director");
        this.director = new Director();
        this.director.name = jsonDirector.getString("Name");

        JsonArray jsonWritersArray = jsonObject.getJsonArray("Writers");
        this.writers = new Writer[jsonWritersArray.size()];
        for (int i = 0; i < this.writers.length; i++) {
            this.writers[i] = new Writer();
            this.writers[i].name = jsonWritersArray.getJsonObject(i).getString("Name");
            this.writers[i].type = jsonWritersArray.getJsonObject(i).getString("Type");
        }

        JsonArray jsonActorsArray = jsonObject.getJsonArray("Actors");
        this.actors = new Actor[jsonActorsArray.size()];
        for (int i = 0; i < this.actors.length; i++) {
            this.actors[i] = new Actor();
            this.actors[i].name = jsonActorsArray.getJsonObject(i).getString("Name");
            this.actors[i].as = jsonActorsArray.getJsonObject(i).getString("As");
        }

        this.plot = jsonObject.getString("Plot");

        JsonArray jsonLanguagesArray = jsonObject.getJsonArray("Languages");
        this.languages = new String[jsonLanguagesArray.size()];
        for (int i = 0; i < this.languages.length; i++) {
            this.languages[i] = jsonLanguagesArray.get(i).toString();
        }

        JsonArray jsonCountriesArray = jsonObject.getJsonArray("Countries");
        this.countries = new String[jsonCountriesArray.size()];
        for (int i = 0; i < this.countries.length; i++) {
            this.countries[i] = jsonCountriesArray.get(i).toString();
        }

        this.awards = jsonObject.getString("Awards");
        this.poster = jsonObject.getString("Poster");

        JsonArray jsonRatingsArray = jsonObject.getJsonArray("Ratings");
        this.ratings = new Rating[jsonRatingsArray.size()];
        for (int i = 0; i < this.ratings.length; i++) {
            this.ratings[i] = new Rating();
            this.ratings[i].source = jsonRatingsArray.getJsonObject(i).getString("Source");
            this.ratings[i].value = jsonRatingsArray.getJsonObject(i).getString("Value");
            this.ratings[i].votes = jsonRatingsArray.getJsonObject(i).getInt("Votes", 0);
        }
    }

    public void writeToJsonFile(String filename) throws FileNotFoundException {
        FileOutputStream out = new FileOutputStream("./src/main/java/space/harbour/java/hw4/"
                + filename);
        JsonWriter writer = Json.createWriter(out);
        writer.writeObject(this.toJsonObject());
    }

    public static void main(String[] args) {
        Movie movie = new Movie();
        try {
            // Deserialize
            String filename = "BladeRunner.json";
            movie.readFromJsonFile(filename);
            System.out.println("Reading from BladeRunner.json: \n"
                    + movie.toString() + "\n--------");

            // Serialize
            movie.writeToJsonFile("NewBladeRunner.json");
            System.out.println("Writing to NewBladeRunner.json ...");

            movie.readFromJsonFile("NewBladeRunner.json");
            System.out.println("Reading from NewBladeRunner.json: \n"
                    + movie.toString() + "\n--------");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
