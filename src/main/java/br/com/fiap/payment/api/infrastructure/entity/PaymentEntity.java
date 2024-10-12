package br.com.fiap.payment.api.infrastructure.entity;

import br.com.fiap.payment.api.entities.payment.PaymentStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "external_payment_id")
  private Long externalPaymentId;

  @Column(name = "external_product_id")
  private Long externalProductId;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "currency")
  private String currency;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "status")
  private PaymentStatusEnum status;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "payer_id")
  private Long payerId;

  @Column(name = "receiver_id")
  private Long receiverId;

  @Column(name = "payment_description")
  private String description;

  @Column(name = "due_at")
  private LocalDateTime dueAt;

  @Column(name = "callback_url")
  private String callbackUrl;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }

}
