package com.tuya.connector.open.ability.device.model.response;

import lombok.Data;

@Data
public class DoorLockPasswordTicketResponse {

    /**
     * The ID of the temporary key.
     */
    private String ticket_id;

    /**
     * The temporary key. It can be used after decryption with AES based on the accessKey
     * that is issued by the Cloud Development Platform.
     */
    private String ticket_key;

    /**
     * Remaining validity period.
     */
    private Long expire_time;
}
