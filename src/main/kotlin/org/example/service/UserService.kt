package org.example.service

import org.example.entity.Role
import org.example.entity.User
import org.example.repository.RoleRepository
import org.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class UserService : UserDetailsService {
    @PersistenceContext
    private val em: EntityManager? = null

    @Autowired
    var userRepository: UserRepository

    @Autowired
    var roleRepository: RoleRepository? = null

    @Autowired
    var bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @Autowired
    constructor(userRepository: UserRepository){
        this.userRepository = userRepository
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
    }

    fun findUserById(userId: Long): User? {
        val userFromDb: Optional<User?> = userRepository.findById(userId)
        return userFromDb.orElse(User())
    }

    fun findUserByName(userName: String): User? {
        return userRepository.findByUsername(userName)
    }

    fun allUsers(): List<User?> {
        return userRepository.findAll()
    }

    fun saveUser(user: User): Boolean {
        val userFromDB = userRepository.findByUsername(user.username)
        if (userFromDB != null) {
            return false
        }

        user.setRoles(Collections.singleton(Role(1L, "ROLE_USER")))
        user.setPassword(bCryptPasswordEncoder!!.encode(user.password))
        userRepository.save(user)
        return true
    }

    fun deleteRole(userId:Long, roleId:Long){
        val user = userRepository.findById(userId).get()
        val roles = user.getRoles()
        val new_roles = mutableSetOf<Role>()
        for ((i, el) in roles!!.withIndex()){
            if(el.roleId != roleId){
                new_roles.add(el)
            }
        }
        user.setRoles(new_roles)
        userRepository.save(user)
    }

    fun deleteUser(userId: Long): Boolean {
        if (userRepository.findById(userId).isPresent) {
            userRepository.deleteById(userId)
            return true
        }
        return false
    }

    fun addManager(user:User){
        user.setRole((Role(3L, "ROLE_MANAGER")))
        userRepository.save(user)
    }

    fun usergtList(idMin: Long?): List<User> {
        return em!!.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User::class.java)
                .setParameter("paramId", idMin).resultList
    }
}