package br.com.fiap.payment.api.application.repository;

import br.com.fiap.payment.api.application.dto.PaymentDTO;

public interface PaymentRepository {

  PaymentDTO save(PaymentDTO paymentDTO);

}
