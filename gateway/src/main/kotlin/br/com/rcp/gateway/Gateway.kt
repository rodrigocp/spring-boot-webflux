package br.com.rcp.gateway

import br.com.rcp.gateway.apis.AccountServiceAPI
import br.com.rcp.gateway.apis.SessionServiceAPI
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import reactivefeign.webclient.WebReactiveFeign


@SpringBootApplication(exclude = [MongoReactiveAutoConfiguration::class, MongoReactiveDataAutoConfiguration::class])
@EnableDiscoveryClient
class Gateway {
	@Bean
	fun locator(builder: RouteLocatorBuilder): RouteLocator {
		return builder.routes()
			.route("account-service") { it.path("/api/users/**")	.filters { p -> p.rewritePath("^/api", "") }.uri("lb://account-service") }
			.route("session-service") { it.path("/api/sessions/**")	.filters { p -> p.rewritePath("^/api", "") }.uri("lb://session-service") }
			.build()
	}

	@Bean
	fun accountServiceAPI() : AccountServiceAPI {
		return WebReactiveFeign.builder<AccountServiceAPI>().target(AccountServiceAPI::class.java, "http://localhost:8070")
	}

	@Bean
	fun sessionServiceAPI() : SessionServiceAPI {
		return WebReactiveFeign.builder<SessionServiceAPI>().target(SessionServiceAPI::class.java, "http://localhost:8060")
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
//			.target(AccountServiceAPI::class.java, "http://account-service")
//	}
}

fun main(args: Array<String>) {
	runApplication<Gateway>(*args)
}
