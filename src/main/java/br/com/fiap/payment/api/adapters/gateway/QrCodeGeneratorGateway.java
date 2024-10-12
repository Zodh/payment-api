package br.com.fiap.payment.api.adapters.gateway;

import java.awt.image.BufferedImage;

public interface QrCodeGeneratorGateway {

  BufferedImage generate(String content);

}
