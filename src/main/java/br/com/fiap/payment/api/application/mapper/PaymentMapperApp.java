package br.com.fiap.payment.api.application.mapper;

import br.com.fiap.payment.api.application.dto.PaymentDTO;
import br.com.fiap.payment.api.entities.payment.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentMapperApp {

  PaymentDTO toDTO(Payment payment);

}
