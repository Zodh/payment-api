package br.com.fiap.payment.api.infrastructure.repository.payment;

import br.com.fiap.payment.api.application.dto.PaymentDTO;
import br.com.fiap.payment.api.application.repository.PaymentRepository;
import br.com.fiap.payment.api.infrastructure.entity.PaymentEntity;
import br.com.fiap.payment.api.infrastructure.mapper.PaymentMapperInfra;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

  private final PaymentJpaRepository paymentJpaRepository;
  private final PaymentMapperInfra paymentMapperInfra;

  @Override
  public Optional<PaymentDTO> getById(Long id) {
    return paymentJpaRepository.findById(id).map(paymentMapperInfra::toDTO);
  }

  @Override
  public PaymentDTO save(PaymentDTO paymentDTO) {
    PaymentEntity pe = paymentMapperInfra.toData(paymentDTO);
    PaymentEntity persistedPe = paymentJpaRepository.save(pe);
    return paymentMapperInfra.toDTO(persistedPe);
  }

}
