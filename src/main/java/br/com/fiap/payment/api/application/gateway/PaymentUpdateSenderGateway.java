package br.com.fiap.payment.api.application.gateway;

import br.com.fiap.payment.api.application.dto.PaymentDTO;

public interface PaymentUpdateSenderGateway {

  void sendPaymentUpdate(PaymentDTO paymentDTO);

}
