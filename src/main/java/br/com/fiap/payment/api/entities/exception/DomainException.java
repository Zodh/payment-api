package br.com.fiap.payment.api.entities.exception;

public class DomainException extends RuntimeException {

  private String example;

  public DomainException(String message) {
    super(message);
  }

  public DomainException(String message, String example) {
    super(message);
    this.example = example;
  }
}
