package com.tuya.connector.open.ability.acl.base;

import org.springframework.stereotype.Component;

@Component
public class AclProviderSelectorImpl implements AclProviderSelector {

    public static final String IDAAS = "idaas";
    public static final String DEFAULT = "default";

    /**
     * @param projectCode
     * @return
     */
    @Override
    public String selectProvider(String projectCode) {
        //todo
        return IDAAS;
    }
}
