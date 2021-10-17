package demo.test.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otp")
public class OTPEntity {
    private static byte DEFAULT_RETRY_TIME = 3;

    @Id
    private String email;

    private String otp;

    private long createTime;

    private byte timeRetry;

    public OTPEntity() {
    }

    public OTPEntity(String email, String otp, long createTime) {
        this.email = email;
        this.otp = otp;
        this.createTime = createTime;
        this.timeRetry = DEFAULT_RETRY_TIME;
    }

    public void decreaseRetryTime() {
        this.timeRetry--;
    }

    public byte getTimeRetry() {
        return this.timeRetry;
    }

    public String getOTP() {
        return this.otp;
    }

    public long getCreateTime() {
        return this.createTime;
    }
}