package com.tuya.connector.open.ability.acl.idaas.model.response;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 9:54 AM
 */
@Data
public class IdaasOrgTreeInfoResponse {

   private String code;

   private String name;

   private String parentCode;

   private String remark;

   private String fullPath;

   private Integer status;

   private Long gmtCreate;

   private Long gmtModified;

   private List<String> childOrganizations;

   private String spaceId;
}
