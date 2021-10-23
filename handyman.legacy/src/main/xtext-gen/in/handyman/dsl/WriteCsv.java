/**
 * generated by Xtext 2.25.0
 */
package in.handyman.dsl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Write Csv</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link in.handyman.dsl.WriteCsv#getSource <em>Source</em>}</li>
 *   <li>{@link in.handyman.dsl.WriteCsv#getTo <em>To</em>}</li>
 *   <li>{@link in.handyman.dsl.WriteCsv#getDelim <em>Delim</em>}</li>
 *   <li>{@link in.handyman.dsl.WriteCsv#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see in.handyman.dsl.DslPackage#getWriteCsv()
 * @model
 * @generated
 */
public interface WriteCsv extends Action
{
  /**
   * Returns the value of the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' attribute.
   * @see #setSource(String)
   * @see in.handyman.dsl.DslPackage#getWriteCsv_Source()
   * @model
   * @generated
   */
  String getSource();

  /**
   * Sets the value of the '{@link in.handyman.dsl.WriteCsv#getSource <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' attribute.
   * @see #getSource()
   * @generated
   */
  void setSource(String value);

  /**
   * Returns the value of the '<em><b>To</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>To</em>' attribute.
   * @see #setTo(String)
   * @see in.handyman.dsl.DslPackage#getWriteCsv_To()
   * @model
   * @generated
   */
  String getTo();

  /**
   * Sets the value of the '{@link in.handyman.dsl.WriteCsv#getTo <em>To</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>To</em>' attribute.
   * @see #getTo()
   * @generated
   */
  void setTo(String value);

  /**
   * Returns the value of the '<em><b>Delim</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Delim</em>' attribute.
   * @see #setDelim(String)
   * @see in.handyman.dsl.DslPackage#getWriteCsv_Delim()
   * @model
   * @generated
   */
  String getDelim();

  /**
   * Sets the value of the '{@link in.handyman.dsl.WriteCsv#getDelim <em>Delim</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Delim</em>' attribute.
   * @see #getDelim()
   * @generated
   */
  void setDelim(String value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(String)
   * @see in.handyman.dsl.DslPackage#getWriteCsv_Value()
   * @model
   * @generated
   */
  String getValue();

  /**
   * Sets the value of the '{@link in.handyman.dsl.WriteCsv#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(String value);

} // WriteCsv
