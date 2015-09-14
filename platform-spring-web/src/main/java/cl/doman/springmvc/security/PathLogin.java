package cl.doman.springmvc.security;





public class PathLogin {
/*implements CustomLogin{
	private String key;
	private CoreUser user;
	
	public PathLogin(String mail, String key) throws QueryException{
		this.key = key;
		
		CoreUserQuery query = new CoreUserQuery();
		user = query.getByMail(mail);
	}
	
	@Override
	public CoreUser getUser() {
		return user;
	}

	@Override
	public boolean check() {
		CoreUserRecovery userPassRecovery = user.getCoreUserRecovery();
		return userPassRecovery!=null && key.equals(userPassRecovery.getPassword());
	}
	*/
	/*
	public void a(User user) throws QueryException{
		UserCoreQuery query = new UserCoreQuery();
		user.setStatus(UserStatus.AVAILABLE);
		UserPassRecovery userPassRecovery = user.getUserPassRecovery();
		query.update(user);
		UserPassRecoveryQuery userPassRecoveryQuery = new UserPassRecoveryQuery();
		userPassRecoveryQuery.delete(userPassRecovery);
	}
	*/
}
