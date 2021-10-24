package demo.test.common.constant;

public enum AuthenticationEnum implements BaseEnum {
    WRONG("OTP is not correct", "wrong"),
    SUCCESS("Correct OTP!", "success"),
    TIME_OUT("Your OTP is expired", "fail"),
    NOT_FOUND("Not found!", "otp_not_found"),
    END_OF_TRY("You have no attempt left", "fail"),
    INVALID_EMAIL("Something wrong with your email", "fail"),
    SEND_OTP_SUCCESS("Sent OTP!", "sent_otp"),
    EXISTING_EMAIL("This email is already in use", "existing_email"),
    FACEBOOK_FAIL("Something go wrong with your account", "facebook_fail"),
    FACEBOOK_SUCCESS("Accept facebook account", "facebook_success"),
    USERNAME_ERROR("Something wrong with your user name", "error_username"),
    CONFIRM_PASSWORD_FAIL("Confirm password fail!", "wrong_confirm_password"),
    CREATE_ACCOUNT_SUCCESS("Create account success!", "create_account_success"),
    LOGIN_SUCCESS("Login successfully!", "login_success");

    AuthenticationEnum(String desc, String descCode) {
        this.desc = desc;
        this.code = descCode;
    }

    private String desc;
    private String code;
    private String extra;

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getExtra() {
        return this.extra;
    }
    
    @Override
    public void setExtra(String extra) {
        this.extra = extra;
    }
}