package br.com.rcp.gateway

import br.com.rcp.gateway.dto.AccountDTO
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.*
import org.keycloak.admin.client.resource.*
import org.keycloak.authorization.client.AuthzClient
import org.keycloak.authorization.client.Configuration
import org.keycloak.representations.idm.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.ws.rs.core.Response


@RequestMapping(value = ["/users"])
@RestController
class UserController {
	private val authServerUrl = "http://localhost:8050/auth"
	private val realm = "services"
	private val clientId = "service-gateway"
	private val role = "admin"
	private val clientSecret = "55b8dff2-49b6-46d0-825f-a77003e01c83"

	@PostMapping(path = ["/create"])
	fun createUser(@RequestBody userDTO: AccountDTO): ResponseEntity<*> {
		val keycloak: Keycloak = KeycloakBuilder.builder()
			.serverUrl(authServerUrl)
			.grantType(OAuth2Constants.PASSWORD)
			.realm("services")
			.clientId("service-gateway")
			.username("root@root.com")
			.password("root")
			.resteasyClient(ResteasyClientBuilder().connectionPoolSize(10).build()).build()
		keycloak.tokenManager().accessToken
		val user = UserRepresentation()
		user.setEnabled(true)
		user.setUsername(userDTO.username)
		user.setFirstName(userDTO.fullname)
		user.setLastName(userDTO.fullname)
		user.setEmail(userDTO.email)

		// Get realm
		val realmResource: RealmResource = keycloak.realm(realm)
		val usersRessource: UsersResource = realmResource.users()
		val response: Response = usersRessource.create(user)
		if (response.getStatus() == 201) {
			val userId: String = CreatedResponseUtil.getCreatedId(response)
			log.info("Created userId {}", userId)


			// create password credential
			val passwordCred = CredentialRepresentation()
			passwordCred.isTemporary = false
			passwordCred.type = CredentialRepresentation.PASSWORD
			passwordCred.value = userDTO.password
			val userResource: UserResource = usersRessource.get(userId)

			// Set password credential
			userResource.resetPassword(passwordCred)

			// Get realm role student
			val realmRoleUser: RoleRepresentation = realmResource.roles().get(role).toRepresentation()

			// Assign realm role student to user
			userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser))
		}
		return ResponseEntity.ok<Any>(userDTO)
	}

	@PostMapping(path = ["/signin"])
	fun signin(@RequestBody userDTO: AccountDTO): ResponseEntity<*> {
		val clientCredentials: MutableMap<String, Any> = HashMap()
		clientCredentials["secret"] = clientSecret
		clientCredentials["grant_type"] = "password"
		val configuration = Configuration(authServerUrl, realm, clientId, clientCredentials, null)
		val authzClient: AuthzClient = AuthzClient.create(configuration)
		val response = authzClient.obtainAccessToken(userDTO.email, userDTO.password)
		return ResponseEntity.ok(response)
	}

	@get:GetMapping(value = ["/public"])
	val name: String
		get() = "Hello, this api is not protected."

	@get:GetMapping(value = ["/protected-data"])
	val email: String
		get() = "Hello, this api is protected."

	companion object {
		private val log: Logger = LoggerFactory.getLogger(UserController::class.java)
	}
}
