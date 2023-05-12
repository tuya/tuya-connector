package com.tuya.connector.open.ability.user.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Description  TODO
 *
 * @author Emerson Gil
 * @date 2023/1/27
 */
@Data
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 155965236652121243L;
    private String username;
    private String password;
    private String country_code;
    private String creator;
    private String user_nick_name;

}
