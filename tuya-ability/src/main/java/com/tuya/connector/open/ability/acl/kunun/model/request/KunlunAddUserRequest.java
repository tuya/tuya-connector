package com.tuya.connector.open.ability.acl.kunun.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/20 10:08 AM
 */
@Data
public class KunlunAddUserRequest {

    private String username;

    private String password;

    private String countryCode;

    private String creator;

    private String userNickName;
}
