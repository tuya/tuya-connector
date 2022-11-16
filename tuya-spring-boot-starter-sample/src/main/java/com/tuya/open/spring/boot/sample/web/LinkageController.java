package com.tuya.open.spring.boot.sample.web;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.ability.common.Page;
import com.tuya.connector.open.ability.linkage.connector.LinkageConnector;
import com.tuya.connector.open.ability.linkage.model.request.LinkageAddRequest;
import com.tuya.connector.open.ability.linkage.model.request.LinkageModifyRequest;
import com.tuya.connector.open.ability.linkage.model.response.DeviceSpecificationResponse;
import com.tuya.connector.open.ability.linkage.model.response.LinkageAddResponse;
import com.tuya.connector.open.ability.linkage.model.response.LinkageListResponse;
import com.tuya.connector.open.ability.linkage.model.response.LinkageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.GET;

/**
 * LinkageController
 *
 * @author jwsong
 * @since 2022/11/1 2:22 下午
 */
@RestController
@RequestMapping("/linkage")
public class LinkageController {

    @Autowired
    private LinkageConnector linkageConnector;

    @PostMapping
    LinkageAddResponse addRule(@RequestBody LinkageAddRequest addRequest){
        return linkageConnector.addRule(addRequest);
    }

    @DeleteMapping()
    Boolean deleteRule(@RequestParam("ids") String ruleIds){
        return linkageConnector.deleteRule(ruleIds);
    }

    @PutMapping("/{id}")
    Boolean modifyRule(@PathVariable("id") String ruleId, @RequestBody LinkageModifyRequest linkageModifyRequest){
        return linkageConnector.modifyRule(ruleId,linkageModifyRequest);
    }

    @GetMapping("/{id}")
    @GET
    LinkageResponse getRule(@PathVariable("id") String ruleId) {
        return linkageConnector.getRule(ruleId);
    }

    @PostMapping("/actions/enable")
    Boolean enableRule(@RequestParam("ids") String ruleIds){
        return linkageConnector.enableRule(ruleIds);
    }

    @PostMapping("/actions/disable")
    Boolean disableRule(@PathVariable("id") String ruleId){
        return linkageConnector.disableRule(ruleId);
    }

    @GetMapping
    Page<LinkageListResponse> listRules(@RequestParam(value = "page_size",defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                                    @RequestParam(value = "type",required = false) String type,
                                    @RequestParam("space_id") String spaceId) {
        return linkageConnector.listRules(type, pageNo, pageSize,spaceId);
    }

    @GetMapping("devices/{device_id}/specifications")
    DeviceSpecificationResponse getDeviceLinkageSpec(@PathVariable("device_id") String deviceId) {
        return linkageConnector.getDeviceLinkageSpec(deviceId);
    }
}
