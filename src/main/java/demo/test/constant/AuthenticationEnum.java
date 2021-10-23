package demo.test.constant;

public enum AuthenticationEnum {
    WRONG("OTP is not correct", "wrong"),
    SUCCESS("Correct OTP!", "success"),
    TIME_OUT("Your OTP is expired", "fail"),
    NOT_FOUND("Not found!", "otp_not_found"),
    END_OF_TRY("You have no attempt left", "fail"),
    INVALID_EMAIL("Something wrong with your email", "fail"),
    SEND_OTP_SUCCESS("Sent OTP!", "sent_otp"),
    EXISTING_EMAIL("This email is already in use", "existing_email");

    AuthenticationEnum(String desc, String descCode) {
        this.desc = desc;
        this.descCode = descCode;
    }

    private String desc;
    private String descCode;
    private String extra;

    public String getDesc() {
        return this.desc;
    }

    public String getDescCode() {
        return this.descCode;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}