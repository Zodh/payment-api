package br.com.fiap.payment.api.entities.payment;

import br.com.fiap.payment.api.entities.exception.DomainException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Payment {

  private Long id;
  private Long externalPaymentId;
  private Long externalProductId;
  private BigDecimal amount;
  private String currency;
  private PaymentStatusEnum status;
  private LocalDateTime createdAt;
  private String description;
  private LocalDateTime dueAt;
  private String callbackUrl;

  public Payment(Long externalPaymentId, BigDecimal amount, String currency, Long externalProductId) {
    validate(externalPaymentId, amount, currency, externalProductId);
    this.externalPaymentId = externalPaymentId;
    this.amount = amount;
    this.currency = currency;
    this.externalProductId = externalProductId;
    this.status = PaymentStatusEnum.PENDING;
  }

  public Payment(Long id, Long externalPaymentId, Long externalProductId, BigDecimal amount,
      String currency, LocalDateTime createdAt, String description, LocalDateTime dueAt, String callbackUrl) {
    validate(externalPaymentId, amount, currency, externalProductId);
    this.id = id;
    this.externalPaymentId = externalPaymentId;
    this.externalProductId = externalProductId;
    this.amount = amount;
    this.currency = currency;
    this.status = PaymentStatusEnum.PENDING;
    this.createdAt = createdAt;
    this.description = description;
    this.dueAt = dueAt;
    this.callbackUrl = callbackUrl;
  }

  private static void validate(Long externalPaymentId, BigDecimal amount, String currency,
      Long externalProductId) {
    if (Objects.isNull(externalPaymentId) || externalPaymentId <= 0) {
      throw new DomainException("O identificador do pagamento externo não pode ser nulo ou menor ou igual a zero!", "137");
    }
    if (Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new DomainException("O valor do pagamento não pode ser nulo ou menor ou igual a zero!", BigDecimal.valueOf(10.0).toString());
    }
    if (StringUtils.isBlank(currency)) {
      throw new DomainException("A moeda fiduciária do pagamento não pode ser nula ou vazia!", "BRL");
    }
    if (Objects.isNull(externalProductId) || externalProductId <= 0) {
      throw new DomainException("O identificador do produto comercializado não pode ser nulo ou menor ou igual a zero!", "892");
    }
  }


}
