package space.harbour.java.hw6;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class WebCrawlerTest {

    @Test
    public void testGithubPagesOne() throws MalformedURLException {
        WebCrawler.toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));

        ExecutorService executorService = Executors.newCachedThreadPool();
        while (!WebCrawler.toVisit.isEmpty()) {
            executorService.submit(new WebCrawler.UrlVisitor());
        }

        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
                assertEquals(5, WebCrawler.visited.size());
            }

        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testGithubPagesTwo() throws MalformedURLException {
        WebCrawler.toVisit.add(new URL("https://thedatanomad.github.io/index.html"));

        ExecutorService executorService = Executors.newCachedThreadPool();
        while (!WebCrawler.toVisit.isEmpty()) {
            executorService.submit(new WebCrawler.UrlVisitor());
        }

        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
                System.out.println(WebCrawler.visited);
                assertEquals(7, WebCrawler.visited.size());
            }

        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


    @Test (expected = MalformedURLException.class)
    public void testBadUrl() throws MalformedURLException {
        WebCrawler.toVisit.add(new URL("dsafs"));
    }

    @Test (expected = MalformedURLException.class)
    public void testNullUrl() throws MalformedURLException {
        WebCrawler.toVisit.add(new URL(null));
    }

}