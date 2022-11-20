package uk.gorodny.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gorodny.wrapper.Wrapper;

@Component
public class CalculateMonthlyPaymentDelegate implements JavaDelegate {

    private final Integer monthCount;

    public CalculateMonthlyPaymentDelegate(@Value("${application.loan.monthCount}") Integer monthCount) {
        this.monthCount = monthCount;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Double sum = Wrapper.getDouble(delegateExecution.getVariable("sum"));
        Double rate = Wrapper.getDouble(delegateExecution.getVariable("rate"));
        Double monthlyPayment = null;
        if (sum != null && rate != null) {
            monthlyPayment = sum + (sum * (rate / 100) * monthCount / 12);
        }
        delegateExecution.setVariable("monthlyPayment", monthlyPayment);
    }
}
