 
package org.eclipse.jetty.demo;

import http.ManyRequests;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mcaikovs
 */
public class MyMain {

    public static void main(String[] args) throws Exception {
        Main main = new Main(8080);

        List<String> URLS = Arrays.asList("http://localhost:8080",
                 "http://localhost:8080/test/dump.jsp",
                 "http://localhost:8080/test/bean1.jsp",
                 "http://localhost:8080/test/tag.jsp",
                 "http://localhost:8080/test/tag2.jsp",
                 "http://localhost:8080/test/tagfile.jsp",
                 "http://localhost:8080/test/expr.jsp?A=1",
                 "http://localhost:8080/test/jstl.jsp",
                 "http://localhost:8080/test/foo/",
                 "http://localhost:8080/date/");

        List<Long> timesForRequests = new LinkedList<>();
        List<String> allContents=new LinkedList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i <200; i++) {
            main.start();
            long requestStart = System.currentTimeMillis();
            String contents = new ManyRequests().requestManyUrls(URLS, 50).get();
            allContents.add(contents);
            timesForRequests.add(System.currentTimeMillis() - requestStart);
          
            main.stop();
            main.waitForInterrupt();
        }
 
        System.out.println("loaded characters: "+allContents.stream().collect(Collectors.joining()).length());
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("total: " + ((System.currentTimeMillis() - start) / 1000));
        System.out.println("total for requests: " + timesForRequests.stream().mapToLong(n -> n).sum()/1000);
    }
}
