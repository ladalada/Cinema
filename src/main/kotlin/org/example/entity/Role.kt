package org.example.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "role")
class Role : GrantedAuthority {
    @Id
    var roleId: Long? = null
    var name: String? = null

    @Transient
    @ManyToMany(mappedBy = "roles")
    var users: Set<User>? = null

    constructor() {}

    constructor(id: Long?) {
        this.roleId = id
    }

    constructor(id: Long?, name: String?) {
        this.roleId = id
        this.name = name
    }

    override fun getAuthority(): String {
        return name!!
    }
}
