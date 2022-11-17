package URLShortenerUlvis;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.apache.commons.validator.routines.UrlValidator;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter URL to shorten: ");
        String input_url = sc.nextLine();
        if(!UrlValidator.getInstance().isValid(input_url)){
            System.out.println("Invalid URL");
            System.exit(1);
        }
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://ulvis.net/api.php?url=" + input_url))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Shortened URL:" + getResponse.body());

    }
}
