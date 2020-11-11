package com.lzz.filter;

import cn.hutool.core.util.StrUtil;
import com.lzz.api.ResultCode;
import com.lzz.exception.ApiException;
import com.lzz.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author lzz
 * @version 1.0
 * @date 2020/10/19 10:20
 */

@Component
@Slf4j
public class AuthGlobalFilter  implements GlobalFilter, Ordered {


    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //防止 OPTIONS 请求直接放行
        if (request.getMethod().equals(HttpMethod.OPTIONS)) {
            return chain.filter(exchange);
        }
        String requestUrl = request.getPath().toString();
        if (requestUrl.split("/")[3].equals("api-docs")|| requestUrl.split("/")[4].equals("p")) {
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst("token");
        if (StrUtil.isEmpty(token) || !redisService.hasKey(token)){
            throw new ApiException(ResultCode.UNAUTHORIZED);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
