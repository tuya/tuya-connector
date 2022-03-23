package com.tuya.connector.open.ability.acl.kunun.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.PUT;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.kunun.model.KunlunCursorPage;
import com.tuya.connector.open.ability.acl.kunun.model.KunlunPage;
import com.tuya.connector.open.ability.acl.kunun.model.request.KunlunAddUserRequest;
import com.tuya.connector.open.ability.acl.kunun.model.request.KunlunUpdateUserRequest;
import com.tuya.connector.open.ability.acl.kunun.model.request.KunlunUserResetPasswordRequest;
import com.tuya.connector.open.ability.acl.kunun.model.response.KunlunAddUserResponse;
import com.tuya.connector.open.ability.acl.kunun.model.response.KunlunMerUserInfoResponse;
import com.tuya.connector.open.ability.acl.kunun.model.response.KunlunUserInfoResponse;

/**
 * @author Mario
 * @since 2022/1/20 9:49 AM
 */
public interface KunlunUserConnector {

   @POST("/v1.0/iot-02/users")
   KunlunAddUserResponse addUser(@Body KunlunAddUserRequest req);

   @DELETE("/v1.0/iot-02/users/{user_id}")
   Boolean deleteUser(@Path("user_id") String userId);

   @PUT("/v1.1/iot-02/users/{user_id}")
   Boolean updateUser(@Path("user_id") String userId, @Body KunlunUpdateUserRequest req);

   @PUT("/v1.0/iot-02/users/reset-password")
   Boolean resetPassword(@Body KunlunUserResetPasswordRequest req);

   @GET("/v1.0/iot-02/users/{user_id}")
   KunlunUserInfoResponse queryByUid(@Path("user_id") String userId);

   @GET("/v1.1/iot-02/users")
   KunlunCursorPage<KunlunMerUserInfoResponse> cursorPage(@Query("user_ids") String userIds,
                                                          @Query("user_nick_name") String userNickName,
                                                          @Query("user_names") String userNames,
                                                          @Query("last_row_key") String lastRowKey,
                                                          @Query("page_size") Integer pageSize);

   @GET("/v1.1/iot-02/users/page")
   KunlunPage<KunlunMerUserInfoResponse> page(@Query("user_ids") String userIds,
                                              @Query("user_nick_name") String userNickName,
                                              @Query("user_names") String userNames,
                                              @Query("page_no") Integer pageNo,
                                              @Query("page_size") Integer pageSize);
}
