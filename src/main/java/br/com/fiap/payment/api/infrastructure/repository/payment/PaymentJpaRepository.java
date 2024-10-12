package br.com.fiap.payment.api.infrastructure.repository.payment;

import br.com.fiap.payment.api.infrastructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {

}
