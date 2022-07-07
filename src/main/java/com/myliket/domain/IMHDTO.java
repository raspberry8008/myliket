package kr.co.imh.domain;

public class IMHDTO {
	private String userId;
	private String userPassword;
	private String userAuthkey;
	private String userAuth;
	private String userName;
	private String userNickname;
	private String userPhone;
	private String userImage;
	private String userMarketing;
	private String userCoupon;
	private int userPoint;
	private String userGrade;
	private int userTotalPrice;
	private String userJoindate;
	private String userState;
	private String userDropdate;
	private String userBlacklist;
	private String userAddress;
	
	public IMHDTO() {}
	public IMHDTO(String userId, String userPassword, String userAuthkey, String userAuth, String userName,
			String userNickname, String userPhone, String userImage, String userMarketing, String userCoupon,
			int userPoint, String userGrade, int userTotalPrice, String userJoindate, String userState,
			String userDropdate, String userBlacklist, String userAddress) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userAuthkey = userAuthkey;
		this.userAuth = userAuth;
		this.userName = userName;
		this.userNickname = userNickname;
		this.userPhone = userPhone;
		this.userImage = userImage;
		this.userMarketing = userMarketing;
		this.userCoupon = userCoupon;
		this.userPoint = userPoint;
		this.userGrade = userGrade;
		this.userTotalPrice = userTotalPrice;
		this.userJoindate = userJoindate;
		this.userState = userState;
		this.userDropdate = userDropdate;
		this.userBlacklist = userBlacklist;
		this.userAddress = userAddress;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserAuthkey() {
		return userAuthkey;
	}
	public void setUserAuthkey(String userAuthkey) {
		this.userAuthkey = userAuthkey;
	}
	public String getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getUserMarketing() {
		return userMarketing;
	}
	public void setUserMarketing(String userMarketing) {
		this.userMarketing = userMarketing;
	}
	public String getUserCoupon() {
		return userCoupon;
	}
	public void setUserCoupon(String userCoupon) {
		this.userCoupon = userCoupon;
	}
	public int getUserPoint() {
		return userPoint;
	}
	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}
	public String getUserGrade() {
		return userGrade;
	}
	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	public int getUserTotalPrice() {
		return userTotalPrice;
	}
	public void setUserTotalPrice(int userTotalPrice) {
		this.userTotalPrice = userTotalPrice;
	}
	public String getUserJoindate() {
		return userJoindate;
	}
	public void setUserJoindate(String userJoindate) {
		this.userJoindate = userJoindate;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getUserDropdate() {
		return userDropdate;
	}
	public void setUserDropdate(String userDropdate) {
		this.userDropdate = userDropdate;
	}
	public String getUserBlacklist() {
		return userBlacklist;
	}
	public void setUserBlacklist(String userBlacklist) {
		this.userBlacklist = userBlacklist;
	}
	public String getUserAddress() {
		return this.userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	@Override
	public String toString() {
		return "IMHDTO [userId=" + userId + ", userPassword=" + userPassword + ", userAuthkey=" + userAuthkey
				+ ", userAuth=" + userAuth + ", userName=" + userName + ", userNickname=" + userNickname
				+ ", userPhone=" + userPhone + ", userImage=" + userImage + ", userMarketing=" + userMarketing
				+ ", userCoupon=" + userCoupon + ", userPoint=" + userPoint + ", userGrade=" + userGrade
				+ ", userTotalPrice=" + userTotalPrice + ", userJoindate=" + userJoindate + ", userState=" + userState
				+ ", userDropdate=" + userDropdate + ", userBlacklist=" + userBlacklist + ", userAddress=" + userAddress
				+ ", userAddress1=" + "]";
	}
	
}
