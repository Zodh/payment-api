package br.com.fiap.payment.api.application.usecase;

import br.com.fiap.payment.api.application.dto.PaymentDTO;
import br.com.fiap.payment.api.application.mapper.PaymentMapperApp;
import br.com.fiap.payment.api.application.repository.PaymentRepository;
import br.com.fiap.payment.api.entities.payment.Payment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePaymentUseCase {

  private final PaymentRepository paymentRepository;
  private final PaymentMapperApp paymentMapper;

  public PaymentDTO create(PaymentDTO paymentDTO) {
    Payment payment = new Payment(
        paymentDTO.getExternalPaymentId(),
        paymentDTO.getAmount(),
        paymentDTO.getCurrency(),
        paymentDTO.getExternalProductId(),
        paymentDTO.getDueAt()
    );
    PaymentDTO validPayment = paymentMapper.toDTO(payment);
    validPayment.setCallbackUrl(paymentDTO.getCallbackUrl());
    validPayment.setDescription(paymentDTO.getDescription());
    return paymentRepository.save(validPayment);
  }

}
