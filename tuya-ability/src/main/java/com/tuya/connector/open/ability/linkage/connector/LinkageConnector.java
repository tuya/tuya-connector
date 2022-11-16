package com.tuya.connector.open.ability.linkage.connector;

import com.tuya.connector.api.annotations.*;
import com.tuya.connector.open.ability.common.Page;
import com.tuya.connector.open.ability.linkage.model.request.LinkageAddRequest;
import com.tuya.connector.open.ability.linkage.model.request.LinkageModifyRequest;
import com.tuya.connector.open.ability.linkage.model.response.DeviceSpecificationResponse;
import com.tuya.connector.open.ability.linkage.model.response.LinkageAddResponse;
import com.tuya.connector.open.ability.linkage.model.response.LinkageListResponse;
import com.tuya.connector.open.ability.linkage.model.response.LinkageResponse;
import org.springframework.beans.factory.annotation.Qualifier;

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
     * @param ids rule id
     * @return true or false
     */
    @DELETE("/v1.0/iot-03/linkage-rule")
    Boolean deleteRule(@Query("ids") String ids);

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
     * @param ids rule id
     * @return true or false
     */
    @PUT("/v1.0/iot-03/linkage-rule/actions/enable")
    Boolean enableRule(@Query("ids") String ids);

    /**
     * disable linkage
     *
     * @param ids rule id
     * @return true or false
     */
    @PUT("/v1.0/iot-03/linkage-rule/actions/disable")
    Boolean disableRule(@Query("ids") String ids);

    /**
     * list linkage
     *
     * @param type     scene or automation
     * @param pageNo   page num
     * @param pageSize page size
     * @return response
     */
    @GET("/v1.0/iot-03/linkage-rule")
    Page<LinkageListResponse> listRules(@Query("type") String type,
                                    @Query("page_no") Integer pageNo,
                                    @Query("page_size") Integer pageSize,
                                    @Query("space_id") String spaceId);

    /**
     * 获取设备支持的指令集
     * @param deviceId 设备id
     * @return
     */
    @GET("/v1.0/iot-03/linkage-rule/devices/{device_id}/specifications")
    DeviceSpecificationResponse getDeviceLinkageSpec(@Path("device_id") String deviceId);

}
