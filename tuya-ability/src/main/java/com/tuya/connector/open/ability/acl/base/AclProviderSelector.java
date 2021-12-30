package com.tuya.connector.open.ability.acl.base;

public interface AclProviderSelector {

    /**
     * default,idaas
     */
    String selectProvider(String projectCode);
}
