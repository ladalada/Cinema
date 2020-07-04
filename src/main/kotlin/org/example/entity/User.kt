package org.example.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.Size
import kotlin.jvm.Transient

@Entity
@Table(name = "users")
class User : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Size(min = 2, message = "Не меньше 5 знаков")
    private var username: String? = null

    @Size(min = 2, message = "Не меньше 5 знаков")
    private var password: String? = null

    @Transient
    private var passwordConfirm: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    private var roles :MutableSet<Role>?= mutableSetOf<Role>()

    constructor(){}

    constructor(id: Long?, username: String?, password: String?, passwordConfirm: String?, roles: MutableSet<Role>?) {
        this.id = id
        this.username = username
        this.password = password
        this.roles = roles
    }


    override fun getUsername(): String {
        return username!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return this!!.roles!!
    }

    override fun getPassword(): String {
        return password!!
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun setPasswordConfirm(password: String?) {
        this.passwordConfirm = password
    }

    fun getPasswordConfirm(): String? {
        return passwordConfirm
    }

    fun setRole(role:Role) {
        this.roles!!.add(role)
    }

    fun getRoles(): MutableSet<Role>? {
        return this.roles
    }

    fun setRoles(role: MutableSet<Role>) {
        this.roles = role
    }

}