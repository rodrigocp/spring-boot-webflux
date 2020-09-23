package br.com.rcp.gateway

import br.com.rcp.gateway.apis.AccountServiceAPI
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.annotation.Bean
import reactivefeign.webclient.WebReactiveFeign


@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
class Gateway {
	@Bean
	fun accountServiceAPI() : AccountServiceAPI {
		return WebReactiveFeign  //WebClient based reactive feign
			//JettyReactiveFeign //Jetty http client based
			//Java11ReactiveFeign //Java 11 http client based
			.builder<AccountServiceAPI>()
			.target(AccountServiceAPI::class.java, "http://localhost:8070")
	}

//	@Bean
//	fun accountServiceAPI() : AccountServiceAPI {
//		return CloudReactiveFeign.builder<AccountServiceAPI>(WebReactiveFeign.builder<AccountServiceAPI>())
//			.setLoadBalancerCommandFactory {
//				LoadBalancerCommand.builder<Any>()
//					.withLoadBalancer(AbstractLoadBalancer::class.java.cast(getNamedLoadBalancer("account-service")))
//					.withRetryHandler(DefaultLoadBalancerRetryHandler(1, 1, true))
//					.build()
//			}
//			.target(AccountServiceAPI::class.java, "http://account-service/")
//	}
}

fun main(args: Array<String>) {
	runApplication<Gateway>(*args)
}
