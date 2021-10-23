/**
 * generated by Xtext 2.25.0
 */
package in.handyman.dsl.impl;

import in.handyman.dsl.DslPackage;
import in.handyman.dsl.SendMail;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Send Mail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link in.handyman.dsl.impl.SendMailImpl#getPrivateKey <em>Private Key</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.SendMailImpl#getImpersonatedUser <em>Impersonated User</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.SendMailImpl#getDbSrc <em>Db Src</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.SendMailImpl#getValue <em>Value</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.SendMailImpl#getDryrunMail <em>Dryrun Mail</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SendMailImpl extends ActionImpl implements SendMail
{
  /**
   * The default value of the '{@link #getPrivateKey() <em>Private Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrivateKey()
   * @generated
   * @ordered
   */
  protected static final String PRIVATE_KEY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPrivateKey() <em>Private Key</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrivateKey()
   * @generated
   * @ordered
   */
  protected String privateKey = PRIVATE_KEY_EDEFAULT;

  /**
   * The default value of the '{@link #getImpersonatedUser() <em>Impersonated User</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImpersonatedUser()
   * @generated
   * @ordered
   */
  protected static final String IMPERSONATED_USER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getImpersonatedUser() <em>Impersonated User</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImpersonatedUser()
   * @generated
   * @ordered
   */
  protected String impersonatedUser = IMPERSONATED_USER_EDEFAULT;

  /**
   * The default value of the '{@link #getDbSrc() <em>Db Src</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbSrc()
   * @generated
   * @ordered
   */
  protected static final String DB_SRC_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDbSrc() <em>Db Src</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDbSrc()
   * @generated
   * @ordered
   */
  protected String dbSrc = DB_SRC_EDEFAULT;

  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final String VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected String value = VALUE_EDEFAULT;

  /**
   * The default value of the '{@link #getDryrunMail() <em>Dryrun Mail</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDryrunMail()
   * @generated
   * @ordered
   */
  protected static final String DRYRUN_MAIL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDryrunMail() <em>Dryrun Mail</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDryrunMail()
   * @generated
   * @ordered
   */
  protected String dryrunMail = DRYRUN_MAIL_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SendMailImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return DslPackage.Literals.SEND_MAIL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getPrivateKey()
  {
    return privateKey;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setPrivateKey(String newPrivateKey)
  {
    String oldPrivateKey = privateKey;
    privateKey = newPrivateKey;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.SEND_MAIL__PRIVATE_KEY, oldPrivateKey, privateKey));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getImpersonatedUser()
  {
    return impersonatedUser;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setImpersonatedUser(String newImpersonatedUser)
  {
    String oldImpersonatedUser = impersonatedUser;
    impersonatedUser = newImpersonatedUser;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.SEND_MAIL__IMPERSONATED_USER, oldImpersonatedUser, impersonatedUser));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getDbSrc()
  {
    return dbSrc;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setDbSrc(String newDbSrc)
  {
    String oldDbSrc = dbSrc;
    dbSrc = newDbSrc;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.SEND_MAIL__DB_SRC, oldDbSrc, dbSrc));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setValue(String newValue)
  {
    String oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.SEND_MAIL__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getDryrunMail()
  {
    return dryrunMail;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setDryrunMail(String newDryrunMail)
  {
    String oldDryrunMail = dryrunMail;
    dryrunMail = newDryrunMail;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.SEND_MAIL__DRYRUN_MAIL, oldDryrunMail, dryrunMail));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case DslPackage.SEND_MAIL__PRIVATE_KEY:
        return getPrivateKey();
      case DslPackage.SEND_MAIL__IMPERSONATED_USER:
        return getImpersonatedUser();
      case DslPackage.SEND_MAIL__DB_SRC:
        return getDbSrc();
      case DslPackage.SEND_MAIL__VALUE:
        return getValue();
      case DslPackage.SEND_MAIL__DRYRUN_MAIL:
        return getDryrunMail();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case DslPackage.SEND_MAIL__PRIVATE_KEY:
        setPrivateKey((String)newValue);
        return;
      case DslPackage.SEND_MAIL__IMPERSONATED_USER:
        setImpersonatedUser((String)newValue);
        return;
      case DslPackage.SEND_MAIL__DB_SRC:
        setDbSrc((String)newValue);
        return;
      case DslPackage.SEND_MAIL__VALUE:
        setValue((String)newValue);
        return;
      case DslPackage.SEND_MAIL__DRYRUN_MAIL:
        setDryrunMail((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case DslPackage.SEND_MAIL__PRIVATE_KEY:
        setPrivateKey(PRIVATE_KEY_EDEFAULT);
        return;
      case DslPackage.SEND_MAIL__IMPERSONATED_USER:
        setImpersonatedUser(IMPERSONATED_USER_EDEFAULT);
        return;
      case DslPackage.SEND_MAIL__DB_SRC:
        setDbSrc(DB_SRC_EDEFAULT);
        return;
      case DslPackage.SEND_MAIL__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case DslPackage.SEND_MAIL__DRYRUN_MAIL:
        setDryrunMail(DRYRUN_MAIL_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case DslPackage.SEND_MAIL__PRIVATE_KEY:
        return PRIVATE_KEY_EDEFAULT == null ? privateKey != null : !PRIVATE_KEY_EDEFAULT.equals(privateKey);
      case DslPackage.SEND_MAIL__IMPERSONATED_USER:
        return IMPERSONATED_USER_EDEFAULT == null ? impersonatedUser != null : !IMPERSONATED_USER_EDEFAULT.equals(impersonatedUser);
      case DslPackage.SEND_MAIL__DB_SRC:
        return DB_SRC_EDEFAULT == null ? dbSrc != null : !DB_SRC_EDEFAULT.equals(dbSrc);
      case DslPackage.SEND_MAIL__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case DslPackage.SEND_MAIL__DRYRUN_MAIL:
        return DRYRUN_MAIL_EDEFAULT == null ? dryrunMail != null : !DRYRUN_MAIL_EDEFAULT.equals(dryrunMail);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (privateKey: ");
    result.append(privateKey);
    result.append(", impersonatedUser: ");
    result.append(impersonatedUser);
    result.append(", dbSrc: ");
    result.append(dbSrc);
    result.append(", value: ");
    result.append(value);
    result.append(", dryrunMail: ");
    result.append(dryrunMail);
    result.append(')');
    return result.toString();
  }

} //SendMailImpl
