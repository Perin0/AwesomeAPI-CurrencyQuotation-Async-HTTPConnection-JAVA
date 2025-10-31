import org.json.JSONObject;
import org.json.JSONException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {

    //httpClient is imutable and reusable
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String token = dotenv.get("YOUR_API_TOKEN");
        if (token==null || token.isEmpty()) {
            System.out.println("Invalid token");
        }
        URI targetUri;
        try {
            targetUri = new URI("https://economia.awesomeapi.com.br/last/BRL-USD,EUR-BRL,BTC-BRL?token="+token);
        } catch (URISyntaxException e) {
            System.err.println("Syntax error in the URI: " + e.getMessage());
            return;
        }

        //httpClient is imutable and reusable
        HttpRequest request = HttpRequest.newBuilder()
                .uri(targetUri)
                .header("Accept", "application/json")
                .GET()
                .build();

        System.out.println(": " + targetUri);

        //send async request
        CompletableFuture<HttpResponse<String>> asyncResponseFuture = httpClient.sendAsync(
                request,
                HttpResponse.BodyHandlers.ofString() //body response as String
        );

        //callbacks
        CompletableFuture<Void> processingFuture = asyncResponseFuture.thenAccept(response -> {
            //block executed in a thread of HttpClient pool
            System.out.println("\n[CALLBACK] Request received: !");
            System.out.println("[CALLBACK] Status Code: " + response.statusCode());

            String body = response.body();
            
            try {
                //parse body into JSON object
                JSONObject root = new JSONObject(body);
                
                System.out.println("[CALLBACK] Parsed JSON content:");
                System.out.prinln(root.toString(4));
                
            } catch (JSONException e) {
                //if JSON is malformed or key doesn't exist
                System.err.println("[CALLBACK] Erro ao analisar o JSON: " + e.getMessage());
                System.err.println("[CALLBACK] Resposta recebida que causou o erro: " + body);
            }

        }).exceptionally(ex -> {
            //blcok executed if request (network, DNS, etc.) fails
            System.err.println("\n[CALLBACK] request failed: " + ex.getCause().getMessage());
            return null; // CompletableFuture<Void> returns null
        });

        //main still free
        System.out.println("...request sent. Main thread still free");

        // .join() blocks main thread until 'processingFuture' is complete.
        processingFuture.join();

        System.out.println("\nFinalized, finishing Main thread");
    }
}
