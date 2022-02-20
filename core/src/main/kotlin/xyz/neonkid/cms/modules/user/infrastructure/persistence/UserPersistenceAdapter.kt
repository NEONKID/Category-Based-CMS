package xyz.neonkid.cms.modules.user.infrastructure.persistence

import org.springframework.stereotype.Service
import xyz.neonkid.cms.common.interfaces.PersistenceAdapter
import xyz.neonkid.cms.modules.user.domain.aggregate.User
import xyz.neonkid.cms.modules.user.domain.aggregate.UserId
import xyz.neonkid.cms.modules.user.useCases.exceptions.UserNotFoundException
import xyz.neonkid.cms.persistence.user.UserRepository

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
class UserPersistenceAdapter(private val userRepository: UserRepository) : PersistenceAdapter<User, UserId> {
    override fun findById(id: UserId): User {
        val result = userRepository.findById(id.value).orElseThrow { UserNotFoundException(id.value) }
        return UserMapper.mapToDomainEntity(result)
    }

    override fun insert(domain: User): User {
        val result = userRepository.insert(UserMapper.mapToJdbcEntity(domain))
        return UserMapper.mapToDomainEntity(result)
    }

    override fun update(domain: User): User {
        val result = userRepository.update(UserMapper.mapToJdbcEntity(domain))
        return UserMapper.mapToDomainEntity(result)
    }

    override fun deleteById(id: UserId) {
        userRepository.deleteById(id.value)
    }
}