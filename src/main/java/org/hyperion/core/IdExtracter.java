package org.hyperion.core;



import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IdExtracter {

    public IdExtracter() {

    }

    public Optional<String> extractRxCuiForDrug(String drugId, String idType){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("idtype", idType);
        parameters.put("id", drugId);

        String response = "";


        try(CloseableHttpClient client = HttpClients.createDefault()) {

            URIBuilder uri = new URIBuilder(ApiUrls.URL_API_RXCUI);
            uri.setParameter("idtype", idType).setParameter("id", drugId);
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
