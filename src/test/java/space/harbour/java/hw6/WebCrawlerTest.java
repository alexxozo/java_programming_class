package space.harbour.java.hw6;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.Test;

public class WebCrawlerTest {

    //    @Test
    //    public void testGithubPagesOne() throws MalformedURLException {
    //        ExecutorService executorService = Executors.newCachedThreadPool();
    //        Future task = null;
    //        try {
    //            WebCrawler.toVisit.add(new URL("https://vasart.github.io/supreme-potato/"));
    //            while (!WebCrawler.toVisit.isEmpty()) {
    //                task = executorService.submit(new WebCrawler.UrlVisitor());
    //                task.get();
    //            }
    //            executorService.shutdown();
    //            assertEquals(4, WebCrawler.visited.size());
    //        } catch (InterruptedException | ExecutionException | MalformedURLException e) {
    //            e.printStackTrace();
    //        }
    //    }
    //
    //    @Test
    //    public void testGithubPagesTwo() throws MalformedURLException {
    //        ExecutorService executorService = Executors.newCachedThreadPool();
    //        Future task = null;
    //        try {
    //            WebCrawler.toVisit.add(new URL("https://thedatanomad.github.io/index.html"));
    //            while (!WebCrawler.toVisit.isEmpty()) {
    //                task = executorService.submit(new WebCrawler.UrlVisitor());
    //                task.get();
    //            }
    //            executorService.shutdown();
    //            assertEquals(8, WebCrawler.visited.size());
    //        } catch (InterruptedException | ExecutionException | MalformedURLException e) {
    //            e.printStackTrace();
    //        }
    //    }


    @Test (expected = MalformedURLException.class)
    public void testBadUrl() throws MalformedURLException {
        WebCrawler.toVisit.add(new URL("dsafs"));
    }

    @Test (expected = MalformedURLException.class)
    public void testNullUrl() throws MalformedURLException {
        WebCrawler.toVisit.add(new URL(null));
    }

}