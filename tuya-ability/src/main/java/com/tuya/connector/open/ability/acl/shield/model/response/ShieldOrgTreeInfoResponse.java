package com.tuya.connector.open.ability.acl.shield.model.response;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 3:25 PM
 */
@Data
public class ShieldOrgTreeInfoResponse {

   private String organizationId;

   private String organizationName;

   private String parentId;

   private String remark;

   private String fullPath;

   private Long gmtCreate;

   private Long gmtModified;

   private List<ShieldOrgTreeInfoResponse> childOrganizations;
}
