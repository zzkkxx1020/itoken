package com.funtl.itoken.service.sso.service.consumer.fallback;

import com.funtl.itoken.common.constants.HttpStatusConstants;
import com.funtl.itoken.common.dto.BaseResult;
import com.funtl.itoken.common.hystrix.Fallback;
import com.funtl.itoken.common.utils.MapperUtils;
import com.funtl.itoken.service.sso.service.consumer.RedisService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

/**
 * 调用服务时需要的熔断器
 */
@Component
public class RedisServiceFallback implements RedisService {
    @Override
    public String put(String key, String value, int seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}
