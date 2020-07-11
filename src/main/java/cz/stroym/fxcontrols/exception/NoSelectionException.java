package cz.stroym.fxcontrols.exception;

public class NoSelectionException extends Exception{
  
  public NoSelectionException() {
    super();
  }
  
  public NoSelectionException(String message) {
    super(message);
  }
  
  public NoSelectionException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public NoSelectionException(Throwable cause) {
    super(cause);
  }
  
  protected NoSelectionException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
  
}
