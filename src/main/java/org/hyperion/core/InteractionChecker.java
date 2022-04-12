package org.hyperion.core;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

/*TODO: Check https://www.baeldung.com/java-http-request*/
public class InteractionChecker {
    public InteractionChecker() {

    }

    public Optional<String> getInteraction(String drugId1, String drugId2, String sourceId) {

        String parameterDrugs = drugId1+" "+drugId2;

        String response = "";

        try(CloseableHttpClient client = HttpClients.createDefault()) {
            URIBuilder uri = new URIBuilder(ApiUrls.URL_API_INTERACTIONS);
            uri.setParameter("rxcuis", parameterDrugs)

                    .setParameter("source", sourceId);
            HttpGet request = new HttpGet(uri.build());
            response = client.execute(request,httpResponse -> EntityUtils.toString(httpResponse.getEntity()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Optional.of(response);
    }
}
