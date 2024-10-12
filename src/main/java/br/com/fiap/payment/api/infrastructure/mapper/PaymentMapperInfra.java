package br.com.fiap.payment.api.infrastructure.mapper;

import br.com.fiap.payment.api.application.dto.PaymentDTO;
import br.com.fiap.payment.api.infrastructure.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentMapperInfra {

  PaymentEntity toData(PaymentDTO dto);
  PaymentDTO toDTO(PaymentEntity entity);

}
