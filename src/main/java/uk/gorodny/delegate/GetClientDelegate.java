package uk.gorodny.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class GetClientDelegate implements JavaDelegate {

    private final String uri;

    public GetClientDelegate(String uri) {
        this.uri = uri;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    }
}
