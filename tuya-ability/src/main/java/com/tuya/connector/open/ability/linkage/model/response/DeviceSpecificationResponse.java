package com.tuya.connector.open.ability.linkage.model.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DeviceSpecificationResponse
 *
 * @author jwsong
 * @since 2022/11/15 3:16 下午
 */
@Data
public class DeviceSpecificationResponse implements Serializable {

    private static final long serialVersionUID = 7266913098738141757L;
    private List<Function> functions;
    private List<Status> status;

    @Data
    public static class Function {
        private String code;
        private String name;
        private String type;
        private JSONObject values;
    }

    @Data
    public class Status {
        private String code;
        private String name;
        private String type;
        private JSONObject values;
        private List<String> comparator;
    }
}

