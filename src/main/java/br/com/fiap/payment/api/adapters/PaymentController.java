package br.com.fiap.payment.api.adapters;

import br.com.fiap.payment.api.adapters.dto.PaymentRequest;
import br.com.fiap.payment.api.adapters.gateway.QrCodeGeneratorGateway;
import br.com.fiap.payment.api.adapters.gateway.ServerUrlGeneratorGateway;
import br.com.fiap.payment.api.application.dto.PaymentDTO;
import br.com.fiap.payment.api.application.usecase.CreatePaymentUseCase;
import java.awt.image.BufferedImage;

public class PaymentController {

  private final CreatePaymentUseCase createPaymentUseCase;
  private final QrCodeGeneratorGateway qrCodeGeneratorGateway;
  private final ServerUrlGeneratorGateway serverUrlGeneratorGateway;

  public PaymentController(CreatePaymentUseCase createPaymentUseCase,
      QrCodeGeneratorGateway qrCodeGeneratorGateway,
      ServerUrlGeneratorGateway serverUrlGeneratorGateway) {
    this.createPaymentUseCase = createPaymentUseCase;
    this.qrCodeGeneratorGateway = qrCodeGeneratorGateway;
    this.serverUrlGeneratorGateway = serverUrlGeneratorGateway;
  }

  public BufferedImage create(PaymentRequest request) {
    PaymentDTO paymentDTO = PaymentDTO.builder()
        .externalPaymentId(request.getExternalPaymentId())
        .externalProductId(request.getExternalProductId())
        .amount(request.getAmount())
        .currency(request.getCurrency())
        .description(request.getDescription())
        .dueAt(request.getDueAt())
        .callbackUrl(request.getCallbackUrl())
        .build();
    PaymentDTO result = createPaymentUseCase.create(paymentDTO);
    String url = serverUrlGeneratorGateway.generate(result.getId());
    return qrCodeGeneratorGateway.generate(url);
  }

}
