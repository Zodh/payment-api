package br.com.fiap.payment.api.infrastructure.web;

import br.com.fiap.payment.api.adapters.PaymentController;
import br.com.fiap.payment.api.adapters.dto.CreatePaymentRequest;
import br.com.fiap.payment.api.adapters.gateway.PaymentNotificationSenderGateway;
import br.com.fiap.payment.api.adapters.gateway.QrCodeGeneratorGateway;
import br.com.fiap.payment.api.adapters.gateway.ServerUrlGeneratorGateway;
import br.com.fiap.payment.api.application.mapper.PaymentMapperApp;
import br.com.fiap.payment.api.application.mapper.PaymentMapperAppImpl;
import br.com.fiap.payment.api.application.repository.PaymentRepository;
import br.com.fiap.payment.api.application.usecase.CreatePaymentUseCase;
import br.com.fiap.payment.api.application.usecase.MakePaymentUseCase;
import java.awt.image.BufferedImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentApi {

  private final PaymentController paymentController;

  @Autowired
  public PaymentApi(PaymentRepository paymentRepository,
      QrCodeGeneratorGateway qrCodeGeneratorGateway,
      ServerUrlGeneratorGateway serverUrlGeneratorGateway, PaymentNotificationSenderGateway paymentNotificationSenderGateway,
      PaymentMapperApp paymentMapperApp) {
    CreatePaymentUseCase createPaymentUseCase = new CreatePaymentUseCase(
        paymentRepository, new PaymentMapperAppImpl()
    );
    MakePaymentUseCase makePaymentUseCase = new MakePaymentUseCase(
        paymentRepository,
        paymentMapperApp
    );
    this.paymentController = new PaymentController(createPaymentUseCase, qrCodeGeneratorGateway, serverUrlGeneratorGateway, makePaymentUseCase, paymentNotificationSenderGateway);
  }

  @PostMapping(produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<BufferedImage> create(@RequestBody CreatePaymentRequest request) {
    BufferedImage response = paymentController.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<String> pay(@PathVariable Long id) {
    return ResponseEntity.ok(paymentController.pay(id));
  }


}
