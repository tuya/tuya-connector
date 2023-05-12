package com.tuya.connector.open.ability.device.connector;

import com.tuya.connector.api.annotations.*;
import com.tuya.connector.api.model.Result;
import com.tuya.connector.open.ability.common.AbilityPage;
import com.tuya.connector.open.ability.device.model.request.*;
import com.tuya.connector.open.ability.device.model.response.*;

import java.util.List;

/**
 * <p> TODO
 *
 * @author @author qiufeng.yu@tuya.com
 * @since 2021/4/1 10:10 下午
 */
public interface DeviceConnector {

    /**
     * delete device
     *
     * @param deviceId
     * @return
     */
    @DELETE("/v1.0/iot-03/devices/{device_id}")
    Boolean deleteDevice(@Path(("device_id")) String deviceId);

    /**
     * delete devices
     *
     * @param deviceIds
     * @return
     */
    @DELETE("/v1.0/iot-03/devices")
    Boolean deleteDevices(@Query("device_ids") String deviceIds);

    /**
     * modify device
     *
     * @param deviceId
     * @param request
     * @return
     */
    @PUT("/v1.0/iot-03/devices/{device_id}")
    Boolean modifyDevice(@Path("device_id") String deviceId, @Body DeviceModifyRequest request);

    /**
     * select device by deviceId
     *
     * @param deviceId
     * @return
     */
    @GET("/v1.0/iot-03/devices/{device_id}")
    Devices.Device selectDevice(@Path("device_id") String deviceId);

    /**
     * select device by deviceIds
     *
     * @param deviceIds
     * @return
     */
    @GET("/v1.0/iot-03/devices")
    Devices selectDevices(@Query("device_ids") String deviceIds);

    /**
     * select device status by deviceId
     *
     * @param deviceId
     * @return
     */
    @GET("/v1.0/iot-03/devices/{device_id}/status")
    List<DeviceStatuses.DeviceStatus> selectDeviceStatus(@Path("device_id") String deviceId);

    /**
     * select device status by deviceIds
     *
     * @param deviceIds
     * @return
     */
    @GET("/v1.0/iot-03/devices/status")
    List<DeviceStatuses> selectDeviceStatuses(@Query("device_ids") String deviceIds);

    /**
     * select device function
     *
     * @param deviceId
     * @return
     */
    @GET("/v1.0/iot-03/devices/{device_id}/functions")
    DeviceSpecification selectDeviceFunctions(@Path("device_id") String deviceId);

    /**
     * select device specification
     *
     * @param deviceId
     * @return
     */
    @GET("/v1.0/iot-03/devices/{device_id}/specification")
    DeviceSpecification selectDeviceSpecification(@Path("device_id") String deviceId);

    /**
     * select device commands
     *
     * @param deviceId
     * @param request
     * @return
     */
    @POST("/v1.0/iot-03/devices/{device_id}/commands")
    Boolean commandDevice(@Path("device_id") String deviceId, @Body DeviceCommandRequest request);

    /**
     * select device logs
     *
     * @param deviceId
     * @param startTime
     * @param endTime
     * @param lastRowKey
     * @param eventTypes
     * @param pageSize
     * @return
     */
    @GET("/v1.0/iot-03/devices/{device_id}/logs")
    AbilityPage<DeviceStatusLogResultRsp> deviceEventLog(@Path("device_id") String deviceId,
                                                         @Query("start_time") Long startTime,
                                                         @Query("end_time") Long endTime,
                                                         @Query("last_row_key") String lastRowKey,
                                                         @Query("event_types") String eventTypes,
                                                         @Query("size") Integer pageSize);

    /**
     * select device report-logs
     *
     * @param deviceId
     * @param startTime
     * @param endTime
     * @param lastRowKey
     * @param codes
     * @param pageSize
     * @return
     */
    @GET("/v1.0/iot-03/devices/{device_id}/report-logs")
    AbilityPage<DeviceStatusLogResultRsp> deviceStatusLog(@Path("device_id") String deviceId,
                                                          @Query("start_time") Long startTime,
                                                          @Query("end_time") Long endTime,
                                                          @Query("last_row_key") String lastRowKey,
                                                          @Query("codes") String codes,
                                                          @Query("size") Integer pageSize);

    @POST("/v1.0/devices/{device_id}/door-lock/password-ticket")
    DoorLockPasswordTicketResponse doorLockPasswordTicket(@Path("device_id") String deviceId);

    @POST("/v2.0/devices/{device_id}/door-lock/temp-password")
    DoorLockTempPasswordResponse doorLockTempPassword(@Path("device_id") String deviceId,
                                                      @Body DoorLockTempPasswordRequest doorLockTempPasswordRequest);

    @DELETE("/v1.0/devices/{device_id}/door-lock/temp-passwords/{password_id}")
    Result deleteTempPassword(@Path("device_id") String deviceId, @Path("password_id") String password_id);

    @POST("/v1.0/devices/{device_id}/door-lock/password-free/open-door")
    Result openDoor(@Path("device_id") String deviceId, @Body DoorLockFreePasswordOpenDoorRequest doorLockFreePasswordOpenDoorRequest);

    @PUT("/v1.0/devices/{device_id}/door-lock/temp-passwords/{password_id}/modify-password")
    Result modifyTempPassword(@Path("device_id") String deviceId, @Path("password_id") String password_id,
                              @Body DoorLockTempPasswordRequest doorLockTempPasswordRequest);

}
