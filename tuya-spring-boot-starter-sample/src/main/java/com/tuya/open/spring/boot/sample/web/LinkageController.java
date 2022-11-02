package com.tuya.open.spring.boot.sample.web;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.ability.common.Page;
import com.tuya.connector.open.ability.linkage.connector.LinkageConnector;
import com.tuya.connector.open.ability.linkage.model.request.LinkageAddRequest;
import com.tuya.connector.open.ability.linkage.model.request.LinkageModifyRequest;
import com.tuya.connector.open.ability.linkage.model.response.LinkageAddResponse;
import com.tuya.connector.open.ability.linkage.model.response.LinkageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    Boolean deleteRule(@PathVariable("id") String ruleId){
        return linkageConnector.deleteRule(ruleId);
    }

    @PutMapping("/{id}")
    Boolean modifyRule(@PathVariable("id") String ruleId, @RequestBody LinkageModifyRequest linkageModifyRequest){
        return linkageConnector.modifyRule(ruleId,linkageModifyRequest);
    }

    @GetMapping("/{id}")
    LinkageResponse getRule(@PathVariable("id") String ruleId) {
        return linkageConnector.getRule(ruleId);
    }

    @POST("/{id}/actions/enable")
    Boolean enableRule(@PathVariable("id") String ruleId){
        return linkageConnector.enableRule(ruleId);
    }

    @POST("/{id}/actions/disable")
    Boolean disableRule(@PathVariable("id") String ruleId){
        return linkageConnector.disableRule(ruleId);
    }

    @GetMapping
    Page<LinkageResponse> listRules(@RequestParam("page_size") Integer pageSize,
                                    @RequestParam("page_no") Integer pageNo,
                                    @RequestParam("type") String type) {
        return linkageConnector.listRules(type, pageNo, pageSize);
    }
}
