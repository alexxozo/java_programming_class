package space.harbour.java.hw7;

import java.util.*;
import java.util.stream.Collectors;

public class Movie
{
    public String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.rating, rating) == 0 &&
                year == movie.year &&
                runtime == movie.runtime &&
                title.equals(movie.title) &&
                Arrays.equals(genres, movie.genres) &&
                director.equals(movie.director) &&
                Arrays.equals(actors, movie.actors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, rating, year, runtime, director);
        result = 31 * result + Arrays.hashCode(genres);
        result = 31 * result + Arrays.hashCode(actors);
        return result;
    }

    public double rating;
    public int year;
    public int runtime;
    public String[] genres;
    public String director;
    public String[] actors;

    public Movie(String title, double rating, int year,
                 int runtime, String[] genres, String director, String[] actors)
    {
        this.title = title;
        this.rating = rating;
        this.year = year;
        this.runtime = runtime;
        this.genres = genres;
        this.director = director;
        this.actors = actors;
    }

    @Override
    public String toString()
    {
        return "Movies{" + "title='" + title + '\''
                + ", rating=" + rating
                + ", year=" + year + ", runtime=" + runtime
                + ", genres=" + Arrays.toString(genres)
                + ", director='" + director + '\''
                + ", actors=" + Arrays.toString(actors)
                + '}';
    }

    public double getRating()
    {
        return rating;
    }

    public String getTitle()
    {
        return title;
    }

    public int getRuntime()
    {
        return runtime;
    }

    public int getYear()
    {
        return year;
    }

    public String getDirector()
    {
        return director;
    }

    public String[] getActors()
    {
        return actors;
    }

    public String[] getGenres()
    {
        return genres;
    }

    public static void main(String[] args)
    {
        ArrayList<Movie> movies = new ArrayList<>();

        Movie movie = new Movie("Movie Title 1", 9, 2010,
                210, new String[]{"adventure", "action"}, "Robert",
                new String[]{"Alex", "Alejandro"});
        movies.add(movie);

        Movie movie2 = new Movie("Movie Title 2", 4, 2014,
                150, new String[]{"adventure", "action"}, "Vasilli",
                new String[]{"Alex", "George"});
        movies.add(movie2);

        Movie movie3 = new Movie("Movie Title 3", 6, 2018,
                90, new String[]{"adventure", "action", "drama"}, "Mihai",
                new String[]{"John", "Jimmy"});
        movies.add(movie3);

        // Sort Movie Rating
        movies.sort((o1, o2) -> (int) (o1.rating - o2.rating));
        System.out.println(movies);

        // Sort Movie Release Year
        movies.sort(Comparator.comparingInt(o -> o.year));
        System.out.println(movies);

        // Sort Movie Runtime
        movies.sort(Comparator.comparingInt(o -> o.runtime));
        System.out.println(movies);

        // Filter by genre
        List<Movie> dramaMovies = movies.stream()
                    .filter(m -> Arrays.asList(m.getGenres()).contains("drama"))
                    .collect(Collectors.toList());
        System.out.println(dramaMovies);

        // Filter by actor
        List<Movie> actorMovies = movies.stream()
                .filter(m -> Arrays.asList(m.getActors()).contains("John"))
                .collect(Collectors.toList());
        System.out.println(actorMovies);

        // Filter by director
        List<Movie> directorMovies = movies.stream()
                .filter(m -> m.getDirector().equals("Vasilli"))
                .collect(Collectors.toList());
        System.out.println(directorMovies);
    }
}