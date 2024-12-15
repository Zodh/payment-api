package br.com.fiap.payment.api.infrastructure.gateway;

import br.com.fiap.payment.api.adapters.gateway.ServerUrlGeneratorGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentUrlGeneratorGatewayImpl implements ServerUrlGeneratorGateway {

  @Value("${host.address}")
  private String host;
  @Value("${host.port.address}")
  private int port;

  @Override
  public String generate(Long paymentId) {
    return host + ":" + port + "/payments/" + paymentId;
  }

}
