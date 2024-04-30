package com.skillstorm.taxprep.server.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
  public UsernameAlreadyExistsException(String username) {
      super("Username '" + username + "' is already taken");
  }
}
