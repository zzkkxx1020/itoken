package com.funtl.itoken.web.admin.service.fallback;

import com.funtl.itoken.web.admin.service.RedisService;
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
