package theater.data;

/**
 * Exception-klasse voor Theaterapplicatie
 */

public class TheaterException extends Exception {
  private static final long serialVersionUID = 1L;

  public TheaterException() {
    super();
  }

  public TheaterException(String s) {
    super(s);
  }
}