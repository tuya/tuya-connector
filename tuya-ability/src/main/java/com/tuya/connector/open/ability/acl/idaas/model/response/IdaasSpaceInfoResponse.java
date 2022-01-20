package com.tuya.connector.open.ability.acl.idaas.model.response;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 2:24 PM
 */
@Data
public class IdaasSpaceInfoResponse {

   private String spaceId;

   private String spaceGroup;

   private String spaceCode;

   private String spaceName;

   private String remark;

   private List<String> ownerList;

   private Long gmtCreate;

   private Long gmtModified;

   private Integer sync;

   private Integer openApply;
}
