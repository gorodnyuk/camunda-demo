package uk.gorodny.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.connect.Connectors;
import org.camunda.connect.httpclient.HttpConnector;
import org.camunda.connect.httpclient.HttpRequest;
import org.camunda.connect.httpclient.HttpResponse;
import org.springframework.stereotype.Component;
import uk.gorodny.domain.Client;

import java.util.HashMap;
import java.util.Map;

@Component
public class GetClientDelegate implements JavaDelegate {

    private final String url;

    public GetClientDelegate(String url) {
        this.url = url;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Client client = null;

        HttpConnector httpConnector = Connectors.getConnector(HttpConnector.ID);
        HttpRequest request = httpConnector.createRequest()
                .url(url)
                .get();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-type", "application/json");

        request.setRequestParameter("headers", headers);

        HttpResponse response = request.execute();
        if (response.getStatusCode() == 200 || !response.getResponse().isEmpty()) {
            // Нужен маппинг на клиента
        }
        response.close();

        delegateExecution.setVariable("client", client);
    }
}
