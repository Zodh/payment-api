package br.com.fiap.payment.api.infrastructure.gateway;

import br.com.fiap.payment.api.adapters.gateway.ServerUrlGeneratorGateway;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentUrlGeneratorGatewayImpl implements ServerUrlGeneratorGateway {

  @Value("${server.port}")
  private int port;

  @Override
  public String generate(Long paymentId) {
    return getLocalIPv4() + ":" + port + "/payments/" + paymentId;
  }

  private String getLocalIPv4() {
    try {
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      while (interfaces.hasMoreElements()) {
        NetworkInterface networkInterface = interfaces.nextElement();

        // Ignora interfaces de loopback e interfaces que nao estao ativas
        if (networkInterface.isLoopback() || !networkInterface.isUp()) {
          continue;
        }

        Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
        while (addresses.hasMoreElements()) {
          InetAddress addr = addresses.nextElement();

          // Verifica se o endereco e IPv4
          if (addr.getHostAddress().indexOf(":") == -1) {
            return addr.getHostAddress();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null; // Retorna null se nao for encontrado nenhum IPv4
  }
}
