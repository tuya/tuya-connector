package com.tuya.connector.open.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Description: TODO
 *
 * @author Chyern
 * @since 2021/4/7
 */
public class Sha256Util {

    public static String encryption(String str) throws Exception{
        MessageDigest messageDigest;
        messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
        return byte2Hex(messageDigest.digest());
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuilder stringBuffer = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
