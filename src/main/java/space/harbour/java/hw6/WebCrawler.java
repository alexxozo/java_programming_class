package space.harbour.java.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WebCrawler {
    public static final ConcurrentLinkedQueue<URL> toVisit = new ConcurrentLinkedQueue<>();
    public static final CopyOnWriteArraySet<URL> visited = new CopyOnWriteArraySet<>();

    public static class UrlVisitor implements Runnable {
        public static String getContentOfWebPage(URL url) {
            final StringBuilder content = new StringBuilder();

            try (InputStream is = url.openConnection().getInputStream();
                    InputStreamReader in = new InputStreamReader(is, "UTF-8");
                    BufferedReader br = new BufferedReader(in); ) {
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    content.append(inputLine);
                }
            } catch (IOException e) {
                System.out.println("Failed to retrieve content of " + url.toString());
            }
            return content.toString();
        }

        @Override
        public void run() {
            synchronized (toVisit) {
                synchronized (visited) {
                    AtomicReference<URL> url = new AtomicReference<>(toVisit.poll());
                    visited.add(url.get());

                    AtomicReference<String> content =
                            new AtomicReference<>(
                                    getContentOfWebPage(url.get()));
                    String regex = "\\b(https?|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]"
                            + "*[-a-zA-Z0-9+&@#/%=~_|]";
                    Pattern regexPattern = Pattern.compile(regex);
                    Matcher regexMatcher = regexPattern.matcher(content.get());
                    while (regexMatcher.find()) {
                        AtomicReference<String> link =
                                new AtomicReference<>(regexMatcher.group());
                        try {
                            AtomicReference<URL> newLinkUrl
                                    = new AtomicReference<>(
                                            new URL(link.get()));
                            if (!toVisit.contains(newLinkUrl.get())
                                    && !visited.contains(newLinkUrl.get())) {
                                toVisit.add(newLinkUrl.get());
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        toVisit.add(new URL("https://alexxozo.github.io/cinemaProject/filme-noi.html"));

        ExecutorService executorService = Executors.newCachedThreadPool();
        while (!toVisit.isEmpty()) {
            executorService.submit(new UrlVisitor());
        }

        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
                System.out.println(visited);
                System.out.println(toVisit);
            }

        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


}
