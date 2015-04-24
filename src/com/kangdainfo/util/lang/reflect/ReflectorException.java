package com.kangdainfo.util.lang.reflect;

/**
 * Reflector exception class.
 * @author bear huang
 * @version 1.0
 */
public class ReflectorException extends Exception {
  public static final String ERROR_NULL_CLASS =
      "The handled class is null.";
  public static final String ERROR_NO_THIS_METHOD =
      "No public method of this field to call: ";
  public static final String ERROR_NO_THIS_FIELD =
      "No this field to access: ";
  public static final String ERROR_CANNOT_GET_FIELD_CHILD =
      "The field that you want to get child field is null :";
  public static final String ERROR_NO_ACCESSOR =
      "There is no accssor for this field: ";
  public static final String ERROR_NO_MUTATOR =
      "There is no mutator for this field: ";

  /**
   * Initial this exception class.
   */
  protected void init() {
  }

  /**
   * Constructor.
   * @param message Error message.
   */
  public ReflectorException(String message) {
    super(message);

    init();
  }

  /**
   * Constructor.
   * @param exception Throwable (other exception).
   */
  public ReflectorException(Throwable exception) {
    super(exception);

    init();
  }
}
