package br.com.fiap.payment.api.application.usecase;

import br.com.fiap.payment.api.application.dto.PaymentDTO;
import br.com.fiap.payment.api.application.exception.NotFoundException;
import br.com.fiap.payment.api.application.mapper.PaymentMapperApp;
import br.com.fiap.payment.api.application.repository.PaymentRepository;
import br.com.fiap.payment.api.entities.payment.Payment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MakePaymentUseCase {

  private final PaymentRepository paymentRepository;
  private final PaymentMapperApp paymentMapper;

  public PaymentDTO pay(Long paymentId) {
    Payment payment = paymentRepository.getById(paymentId)
        .map(paymentMapper::toDomain)
        .orElseThrow(() -> new NotFoundException("payment", paymentId));
    String result = payment.pay();
    PaymentDTO validPayment = paymentMapper.toDTO(payment);
    PaymentDTO updatedPayment = paymentRepository.save(validPayment);
    updatedPayment.setPaymentMessage(result);
    return updatedPayment;
  }

}
