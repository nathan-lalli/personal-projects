package POS_PD;

public class AuthorizedPayemnt extends Payment 
{

	private String authorizationCode;

	public String getAuthorizationCode() 
	{
		return this.authorizationCode;
	}

	public void setAuthorizationCode(String authorizationCode) 
	{
		this.authorizationCode = authorizationCode;
	}

	/**
	 * Tells whether or not the payment is authorized.
	 */
	public Boolean isAuthorized() 
	{
		boolean authorized = false;
		if(authorizationCode.equals(getAuthorizationCode()))
		{
			authorized = true;
		}
		return authorized;
	}

	/**
	 * Boolean to count the payment as cash.
	 */
	public Boolean countsAsCash() 
	{
		return false;
	}

}