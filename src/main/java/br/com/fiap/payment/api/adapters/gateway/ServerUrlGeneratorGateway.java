package br.com.fiap.payment.api.adapters.gateway;

public interface ServerUrlGeneratorGateway {

  String generate(Long paymentId);

}
