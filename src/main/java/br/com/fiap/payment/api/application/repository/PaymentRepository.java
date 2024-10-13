package br.com.fiap.payment.api.application.repository;

import br.com.fiap.payment.api.application.dto.PaymentDTO;
import java.util.Optional;

public interface PaymentRepository {

  Optional<PaymentDTO> getById(Long id);
  PaymentDTO save(PaymentDTO paymentDTO);

}
