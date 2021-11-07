package com.server.onlineup.common.constant;

public enum AuthenticationEnum implements BaseEnum {
    //OTP Error
    WRONG("OTP is not correct", "wrong"),
    OTP_SUCCESS("Correct OTP!", "success"),
    TIME_OUT("Your OTP is expired", "fail"),
    NOT_FOUND("Not found!", "otp_not_found"),
    END_OF_TRY("You have no attempt left", "fail"),
    SEND_OTP_SUCCESS("Sent OTP!", "sent_otp"),

    //Email
    INVALID_EMAIL("Invalid email format", "invalid_email"),
    EXISTING_EMAIL("This email is already in use", "existing_email"),

    //Facebook
    FACEBOOK_FAIL("Something go wrong with your account", "facebook_fail"),
    FACEBOOK_SUCCESS("Accept facebook account", "facebook_success"),
    CREATE_ACCOUNT_SUCCESS("Create account success!", "create_account_success"),
    LOGIN_SUCCESS("Login successfully!", "login_success"),
    LOGIN_FAIL("Incorrect email or password", "login_fail"),

    // Token
    DUPLICATE_TOKEN("OTP Token and User Token can not go together", "duplicate_token"),
    EXPIRED_TOKEN("Token is expired", "expired_token"),
    INVALID_SIGNATURE_TOKEN("Invalid JWT signature", "invalid_signature_tokenn"),
    INVALID_TOKEN("Invalid JWT token", "invalid_token"),
    UNSUPPORTED_TOKEN("Unsupported JWT token", "unsupported_token"),
    CLAIM_EMPTY_TOKEN("JWT claims string is empty", "claim_empty_token"),
    REQUEST_NOT_MATCH_TOKEN("Token is not used for this email", "request_not_match_token"),

    // Forget Password
    NOT_EXISTING_EMAIL_TO_RESET_PASS("Your email is not available to reset password", "not_exist_email"),
    WRONG_CONFIRM_PASSWORD("Confirm password unsuccessfully", "wrong_confirm_password"),
    CHANGE_PASS_SUCCESS("Change password successfully", "change_pass_success"),

    // Using Facebook
    FAIL_FACEBOOK_SIGN_IN("Something wrong with your facebook", "fail_facebook_sign_in"),

    // Update fcm_token
    GET_FCM_TOKEN_SUCCESS("Update fcm_token success", "get_fcm_token_success"),
    TOKEN_EMPTY_USER("Can not specify the user from jwt token", "token_empty_user");


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