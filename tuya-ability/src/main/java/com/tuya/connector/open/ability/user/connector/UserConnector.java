package com.tuya.connector.open.ability.user.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.ability.user.model.request.UserRequest;

/**
 * Description  TODO
 *
 * @author Emerson Gil
 * @date 2023/1/27
 */
public interface UserConnector {

    /**
     * delete user
     *
     * @param userId
     * @return
     */
    @DELETE("/v1.0/iot-02/users/{user_id}")
    Boolean deleteUser(@Path(("user_id")) String userId);

    /**
     * add user
     *
     * @param request
     * @return
     */
    @POST("/v1.0/iot-02/users")
    String addUser(@Body UserRequest request);


}
