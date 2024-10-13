package br.com.fiap.payment.api.application.exception;

public class NotFoundException extends RuntimeException {

  private final String subject;
  private final Object identifier;

  public NotFoundException(String subject, Object identifier) {
    super(String.format("The %s with identifier %s was not found!", subject, identifier));
    this.subject = subject;
    this.identifier = identifier;
  }

}
