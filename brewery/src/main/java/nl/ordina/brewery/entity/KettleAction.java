package nl.ordina.brewery.entity;

/**
 * Action for a <code>Kettle</code>.
 */
public interface KettleAction {

  /**
   * Exectute the action on the kettle
   *
   * @param kettle the kettle
   */
  void executeFor(Kettle kettle);
}

