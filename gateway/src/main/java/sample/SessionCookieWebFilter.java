package sample;

import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class SessionCookieWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {

        ServerHttpResponse response = serverWebExchange.getResponse();

        if(response.getCookies().getFirst("JSESSIONID") == null) {
            response.addCookie(ResponseCookie.from("JSESSIONID", UUID.randomUUID().toString()).build());
        }

        System.out.println("Cookie set");

        return webFilterChain.filter(serverWebExchange);
    }
}