package theater.domein;

/**
 * Representeert klant.
 * @author Open Universiteit
 */
public class Klant {
  private int id;
  private String naam;
  private String telefoon;
  
  /**
   * Creeert klant.
   * @param id klantnummer
   * @param naam naam
   * @param telefoon telefoonnummer
   */
  public Klant(int id, String naam, String telefoon) {
    this.id = id;
    this.naam = naam;
    this.telefoon = telefoon;
  }

  /**
   * Geeft klantnummer.
   * @return klantnummer
   */
  public int getId() {
    return id;
  }

  /**
   * Geeft klantnaam.
   * @return naam
   */
  public String getNaam() {
    return naam;
  }

  /**
   * Geeft telefoonnummer.
   * @return telefoonnummer.
   */
  public String getTelefoon() {
    return telefoon;
  }

  /**
   * Geeft stringrepresentatie van klant.
   * @return stringrepresentatie.
   */
  public String toString() {
    return id + ", " + naam + ", " + telefoon;
  }

}
