package space.harbour.java.hw7;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MovieTest {

    ArrayList<Movie> movies = new ArrayList<>();


    @Before
    public void setup() {
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
    }

    @Test
    public void testOne() {
        List<Movie> dramaMovies = movies.stream()
                .filter(m -> Arrays.asList(m.getGenres()).contains("drama"))
                .collect(Collectors.toList());
        assertTrue(dramaMovies.contains(movies.get(2)));
    }

    @Test
    public void testTwo() {
        List<Movie> actorMovies = movies.stream()
                .filter(m -> Arrays.asList(m.getActors()).contains("John"))
                .collect(Collectors.toList());
        System.out.println(actorMovies);

        assertTrue(actorMovies.contains(movies.get(2)));
    }

    @Test
    public void testThree() {
        List<Movie> directorMovies = movies.stream()
                .filter(m -> m.getDirector().equals("Vasilli"))
                .collect(Collectors.toList());

        assertTrue(directorMovies.contains(movies.get(1)));
    }

    @Test
    public void testFour() {
        ArrayList<Movie> testingMovies = new ArrayList<>();
        testingMovies.add(movies.get(1));
        testingMovies.add(movies.get(2));
        testingMovies.add(movies.get(0));
        movies.sort((o1, o2) -> (int) (o1.rating - o2.rating));
        assertEquals(testingMovies, movies);
    }

    @Test
    public void testFive() {
        ArrayList<Movie> testingMovies = new ArrayList<>();
        testingMovies.add(movies.get(0));
        testingMovies.add(movies.get(1));
        testingMovies.add(movies.get(2));
        movies.sort(Comparator.comparingInt(o -> o.year));
        assertEquals(testingMovies, movies);
    }

    @Test
    public void testSix() {
        ArrayList<Movie> testingMovies = new ArrayList<>();
        testingMovies.add(movies.get(2));
        testingMovies.add(movies.get(1));
        testingMovies.add(movies.get(0));
        movies.sort(Comparator.comparingInt(o -> o.runtime));
        assertEquals(testingMovies, movies);
    }

}