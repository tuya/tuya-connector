package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 2:22 PM
 */
@Data
public class IdaasCreateSpaceRequest {

   private String spaceGroup;

   private String spaceCode;

   private String spaceName;

   private String remark;

   private Integer authentication;

   private List<String> ownerList;

   private Integer sync;

   private Integer openApply;
}
