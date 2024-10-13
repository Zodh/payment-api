package br.com.fiap.payment.api.application.dto;

import br.com.fiap.payment.api.entities.payment.PaymentStatusEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
public class PaymentDTO {

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
  private String paymentMessage;

  public String getPaymentMessage() {
    if (this.status == null || this.status != PaymentStatusEnum.PAID) {
      return "O pagamento não foi efetuado!";
    }
    return paymentMessage;
  }

}
