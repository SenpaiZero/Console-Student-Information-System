
public class Admin {
	private String username;
	private String password;
	public Admin() {
		username = "admin";
		password = "admin";
	}
	
	public boolean isLogin(String username, String password) 
	{
		if("exit".matches(username+"|"+password)) 
		{
			System.out.println(" ====================================");
			System.out.println(" System have been closed");
			System.exit(0);
		}
		
		if(this.username.equals(username))
		{
			if(this.password.equals(password))
			{
				return true;
			}
		}
		System.out.println(" ====================================");
		System.out.println(" Enter the correct account. ");
		
		return false;
	}
}
