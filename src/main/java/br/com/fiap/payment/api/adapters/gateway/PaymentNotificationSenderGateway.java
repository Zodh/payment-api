package br.com.fiap.payment.api.adapters.gateway;

public interface PaymentNotificationSenderGateway {

  void send(String message, String callbackUrl);

}
