/**
 * generated by Xtext 2.25.0
 */
package in.handyman.dsl.impl;

import in.handyman.dsl.DslPackage;
import in.handyman.dsl.GooglecontactPUT;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Googlecontact PUT</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link in.handyman.dsl.impl.GooglecontactPUTImpl#getAccount <em>Account</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.GooglecontactPUTImpl#getPrivateKey <em>Private Key</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.GooglecontactPUTImpl#getPtwelveFile <em>Ptwelve File</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.GooglecontactPUTImpl#getProject <em>Project</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.GooglecontactPUTImpl#getImpersonatedUser <em>Impersonated User</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.GooglecontactPUTImpl#getDbSrc <em>Db Src</em>}</li>
 *   <li>{@link in.handyman.dsl.impl.GooglecontactPUTImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GooglecontactPUTImpl extends ActionImpl implements GooglecontactPUT
{
  /**
   * The default value of the '{@link #getAccount() <em>Account</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAccount()
   * @generated
   * @ordered
   */
  protected static final String ACCOUNT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAccount() <em>Account</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAccount()
   * @generated
   * @ordered
   */
  protected String account = ACCOUNT_EDEFAULT;

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
   * The default value of the '{@link #getPtwelveFile() <em>Ptwelve File</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPtwelveFile()
   * @generated
   * @ordered
   */
  protected static final String PTWELVE_FILE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPtwelveFile() <em>Ptwelve File</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPtwelveFile()
   * @generated
   * @ordered
   */
  protected String ptwelveFile = PTWELVE_FILE_EDEFAULT;

  /**
   * The default value of the '{@link #getProject() <em>Project</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProject()
   * @generated
   * @ordered
   */
  protected static final String PROJECT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getProject() <em>Project</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProject()
   * @generated
   * @ordered
   */
  protected String project = PROJECT_EDEFAULT;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GooglecontactPUTImpl()
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
    return DslPackage.Literals.GOOGLECONTACT_PUT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getAccount()
  {
    return account;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setAccount(String newAccount)
  {
    String oldAccount = account;
    account = newAccount;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.GOOGLECONTACT_PUT__ACCOUNT, oldAccount, account));
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
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.GOOGLECONTACT_PUT__PRIVATE_KEY, oldPrivateKey, privateKey));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getPtwelveFile()
  {
    return ptwelveFile;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setPtwelveFile(String newPtwelveFile)
  {
    String oldPtwelveFile = ptwelveFile;
    ptwelveFile = newPtwelveFile;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.GOOGLECONTACT_PUT__PTWELVE_FILE, oldPtwelveFile, ptwelveFile));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getProject()
  {
    return project;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setProject(String newProject)
  {
    String oldProject = project;
    project = newProject;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.GOOGLECONTACT_PUT__PROJECT, oldProject, project));
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
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.GOOGLECONTACT_PUT__IMPERSONATED_USER, oldImpersonatedUser, impersonatedUser));
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
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.GOOGLECONTACT_PUT__DB_SRC, oldDbSrc, dbSrc));
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
      eNotify(new ENotificationImpl(this, Notification.SET, DslPackage.GOOGLECONTACT_PUT__VALUE, oldValue, value));
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
      case DslPackage.GOOGLECONTACT_PUT__ACCOUNT:
        return getAccount();
      case DslPackage.GOOGLECONTACT_PUT__PRIVATE_KEY:
        return getPrivateKey();
      case DslPackage.GOOGLECONTACT_PUT__PTWELVE_FILE:
        return getPtwelveFile();
      case DslPackage.GOOGLECONTACT_PUT__PROJECT:
        return getProject();
      case DslPackage.GOOGLECONTACT_PUT__IMPERSONATED_USER:
        return getImpersonatedUser();
      case DslPackage.GOOGLECONTACT_PUT__DB_SRC:
        return getDbSrc();
      case DslPackage.GOOGLECONTACT_PUT__VALUE:
        return getValue();
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
      case DslPackage.GOOGLECONTACT_PUT__ACCOUNT:
        setAccount((String)newValue);
        return;
      case DslPackage.GOOGLECONTACT_PUT__PRIVATE_KEY:
        setPrivateKey((String)newValue);
        return;
      case DslPackage.GOOGLECONTACT_PUT__PTWELVE_FILE:
        setPtwelveFile((String)newValue);
        return;
      case DslPackage.GOOGLECONTACT_PUT__PROJECT:
        setProject((String)newValue);
        return;
      case DslPackage.GOOGLECONTACT_PUT__IMPERSONATED_USER:
        setImpersonatedUser((String)newValue);
        return;
      case DslPackage.GOOGLECONTACT_PUT__DB_SRC:
        setDbSrc((String)newValue);
        return;
      case DslPackage.GOOGLECONTACT_PUT__VALUE:
        setValue((String)newValue);
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
      case DslPackage.GOOGLECONTACT_PUT__ACCOUNT:
        setAccount(ACCOUNT_EDEFAULT);
        return;
      case DslPackage.GOOGLECONTACT_PUT__PRIVATE_KEY:
        setPrivateKey(PRIVATE_KEY_EDEFAULT);
        return;
      case DslPackage.GOOGLECONTACT_PUT__PTWELVE_FILE:
        setPtwelveFile(PTWELVE_FILE_EDEFAULT);
        return;
      case DslPackage.GOOGLECONTACT_PUT__PROJECT:
        setProject(PROJECT_EDEFAULT);
        return;
      case DslPackage.GOOGLECONTACT_PUT__IMPERSONATED_USER:
        setImpersonatedUser(IMPERSONATED_USER_EDEFAULT);
        return;
      case DslPackage.GOOGLECONTACT_PUT__DB_SRC:
        setDbSrc(DB_SRC_EDEFAULT);
        return;
      case DslPackage.GOOGLECONTACT_PUT__VALUE:
        setValue(VALUE_EDEFAULT);
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
      case DslPackage.GOOGLECONTACT_PUT__ACCOUNT:
        return ACCOUNT_EDEFAULT == null ? account != null : !ACCOUNT_EDEFAULT.equals(account);
      case DslPackage.GOOGLECONTACT_PUT__PRIVATE_KEY:
        return PRIVATE_KEY_EDEFAULT == null ? privateKey != null : !PRIVATE_KEY_EDEFAULT.equals(privateKey);
      case DslPackage.GOOGLECONTACT_PUT__PTWELVE_FILE:
        return PTWELVE_FILE_EDEFAULT == null ? ptwelveFile != null : !PTWELVE_FILE_EDEFAULT.equals(ptwelveFile);
      case DslPackage.GOOGLECONTACT_PUT__PROJECT:
        return PROJECT_EDEFAULT == null ? project != null : !PROJECT_EDEFAULT.equals(project);
      case DslPackage.GOOGLECONTACT_PUT__IMPERSONATED_USER:
        return IMPERSONATED_USER_EDEFAULT == null ? impersonatedUser != null : !IMPERSONATED_USER_EDEFAULT.equals(impersonatedUser);
      case DslPackage.GOOGLECONTACT_PUT__DB_SRC:
        return DB_SRC_EDEFAULT == null ? dbSrc != null : !DB_SRC_EDEFAULT.equals(dbSrc);
      case DslPackage.GOOGLECONTACT_PUT__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
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
    result.append(" (account: ");
    result.append(account);
    result.append(", privateKey: ");
    result.append(privateKey);
    result.append(", ptwelveFile: ");
    result.append(ptwelveFile);
    result.append(", project: ");
    result.append(project);
    result.append(", impersonatedUser: ");
    result.append(impersonatedUser);
    result.append(", dbSrc: ");
    result.append(dbSrc);
    result.append(", value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

} //GooglecontactPUTImpl
