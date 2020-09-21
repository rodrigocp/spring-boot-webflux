package br.com.rcp.gateway.domains

import br.com.rcp.gateway.apis.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.*
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class AccountDetailsService(@Autowired private var client: AccountService): UserDetailsService {
	override fun loadUserByUsername(username: String?): UserDetails {
		val	login	= username 				?: throw UsernameNotFoundException("Empty username!")
		val	account	= client.find(login) 	?: throw UsernameNotFoundException("Account not found!")

		return object: UserDetails {
			override fun getUsername()				: String									= account.username ?: ""
			override fun getPassword()				: String									= account.password ?: ""
			override fun isEnabled()				: Boolean									= account.enabled
			override fun isAccountNonLocked()		: Boolean									= account.enabled
			override fun isAccountNonExpired()		: Boolean									= account.enabled
			override fun isCredentialsNonExpired()	: Boolean									= account.enabled
			override fun getAuthorities()			: MutableCollection<out GrantedAuthority>	= account.roles?.stream()?.map { SimpleGrantedAuthority(it) }?.collect(Collectors.toList()) as? MutableList<GrantedAuthority> ?: mutableListOf()
		}
	}
}