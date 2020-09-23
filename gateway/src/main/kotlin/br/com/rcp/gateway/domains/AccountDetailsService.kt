package br.com.rcp.gateway.domains

import br.com.rcp.gateway.apis.AccountServiceAPI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AccountDetailsService(@Autowired private var client: AccountServiceAPI): UserDetailsService {
	override fun loadUserByUsername(username: String?): UserDetails {
		TODO("Implement method")
//		return CoroutineScope(Dispatchers.Unconfined).async {
//			val	login	= username 				?: throw UsernameNotFoundException("Empty username!")
//			val	account	= client.find(login) 	?: throw UsernameNotFoundException("Account not found!")
//
//			return@async object: UserDetails {
//				override fun getUsername()				: String									= account.username ?: ""
//				override fun getPassword()				: String									= account.password ?: ""
//				override fun isEnabled()				: Boolean									= account.enabled
//				override fun isAccountNonLocked()		: Boolean									= account.enabled
//				override fun isAccountNonExpired()		: Boolean									= account.enabled
//				override fun isCredentialsNonExpired()	: Boolean									= account.enabled
//				override fun getAuthorities()			: MutableCollection<out GrantedAuthority>	= account.roles?.stream()?.map { SimpleGrantedAuthority(it) }?.collect(Collectors.toList()) as? MutableList<GrantedAuthority> ?: mutableListOf()
//			}
//		}.getCompleted()
	}
}