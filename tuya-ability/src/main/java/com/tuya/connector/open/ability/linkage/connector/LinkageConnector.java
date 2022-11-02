package com.tuya.connector.open.ability.linkage.connector;

import com.tuya.connector.api.annotations.*;
import com.tuya.connector.open.ability.common.Page;
import com.tuya.connector.open.ability.linkage.model.request.LinkageAddRequest;
import com.tuya.connector.open.ability.linkage.model.request.LinkageModifyRequest;
import com.tuya.connector.open.ability.linkage.model.response.LinkageAddResponse;
import com.tuya.connector.open.ability.linkage.model.response.LinkageResponse;

/**
 * LinkageConnector
 *
 * @author jwsong
 * @since 2022/10/31 2:48 下午
 */
public interface LinkageConnector {

    /**
     * add linkage
     *
     * @param addRequest linkage content
     * @return response
     */
    @POST("/v1.0/iot-03/linkage-rule")
    LinkageAddResponse addRule(@Body LinkageAddRequest addRequest);

    /**
     * remove linkage
     *
     * @param ruleId rule id
     * @return true or false
     */
    @DELETE("/v1.0/iot-03/linkage-rule/{id}")
    Boolean deleteRule(@Path("id") String ruleId);

    /**
     * modify linkage
     *
     * @param ruleId               rule id
     * @param linkageModifyRequest linkage content
     * @return true or false
     */
    @PUT("/v1.0/iot-03/linkage-rule/{id}")
    Boolean modifyRule(@Path("id") String ruleId, @Body LinkageModifyRequest linkageModifyRequest);

    /**
     * get linkage
     *
     * @param ruleId rule id
     * @return linkage content
     */
    @GET("/v1.0/iot-03/linkage-rule/{id}")
    LinkageResponse getRule(@Path("id") String ruleId);

    /**
     * enable linkage
     *
     * @param ruleId rule id
     * @return true or false
     */
    @POST("/v1.0/iot-03/linkage-rule/{id}/actions/enable")
    Boolean enableRule(@Path("id") String ruleId);

    /**
     * disable linkage
     *
     * @param ruleId rule id
     * @return true or false
     */
    @POST("/v1.0/iot-03/linkage-rule/{id}/actions/disable")
    Boolean disableRule(@Path("id") String ruleId);

    /**
     * list linkage
     *
     * @param type     scene or automation
     * @param pageNo   page num
     * @param pageSize page size
     * @return response
     */
    @GET("/v1.0/iot-03/linkage-rule")
    Page<LinkageResponse> listRules(@Query("type") String type,
                                    @Query("page_no") Integer pageNo,
                                    @Query("page_size") Integer pageSize);

}
