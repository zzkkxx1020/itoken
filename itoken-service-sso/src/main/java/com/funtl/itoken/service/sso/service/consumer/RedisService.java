package com.funtl.itoken.service.sso.service.consumer;

import com.funtl.itoken.service.sso.service.consumer.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 通过feign调用其他服务
 *
 */
@FeignClient(value = "itoken-service-redis",fallback = RedisServiceFallback.class)
public interface RedisService {

    /**
     * redis的put方法
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    @RequestMapping(value = "put",method = RequestMethod.POST)
    public String put(@RequestParam(value = "key") String key,
                      @RequestParam(value = "value") String value,
                      @RequestParam(value = "seconds") int seconds);

    /**
     * redis的get方法
     * @param key
     * @return
     */
    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String get(@RequestParam(value = "key") String key);

}
