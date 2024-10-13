package br.com.fiap.payment.api.infrastructure.gateway;

import br.com.fiap.payment.api.adapters.gateway.PaymentNotificationSenderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpNotificationSender implements PaymentNotificationSenderGateway {

  @Override
  public void send(String message, String callbackUrl) {
    RestTemplate rest = new RestTemplate();
    try {
      rest.getForObject(callbackUrl, Void.class);
    } catch (HttpStatusCodeException e) {
      e.printStackTrace();
    }
  }

}
