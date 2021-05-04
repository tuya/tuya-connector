package com.tuya.connector.open.api.token;

import com.tuya.connector.api.token.Token;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/5 4:23 下午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TuyaToken implements Token {

    String access_token;
    Long expire_time;
    String refresh_token;
    String uid;

    @Override
    public String getAccessToken() {
        return access_token;
    }

    @Override
    public String getRefreshToken() {
        return refresh_token;
    }
}
