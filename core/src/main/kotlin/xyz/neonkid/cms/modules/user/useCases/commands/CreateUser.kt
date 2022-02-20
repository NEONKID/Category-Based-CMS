package xyz.neonkid.cms.modules.user.useCases.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.user.domain.aggregate.User
import xyz.neonkid.cms.modules.user.domain.valueObjects.Email
import xyz.neonkid.cms.modules.user.domain.valueObjects.NickName
import xyz.neonkid.cms.modules.user.infrastructure.persistence.UserPersistenceAdapter
import xyz.neonkid.cms.modules.user.useCases.queries.UserQueryRepository
import xyz.neonkid.cms.modules.user.useCases.queries.dto.UserDTO

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class CreateUserUserCase(
    private val userPersistenceAdapter: UserPersistenceAdapter,
    private val userQueryRepository: UserQueryRepository,
    private val publisher: ApplicationEventPublisher
) : UseCase<CreateUserCommand, UserDTO> {
    override fun invoke(command: CreateUserCommand): UserDTO {
        val domain = User.newUser(command)
        val entity = userPersistenceAdapter.insert(domain)

        return userQueryRepository.fetchById(entity.id.value)
    }
}

data class CreateUserCommand (
    val email: Email,
    val nickname: NickName
)

data class CreateUserRequest (
    val email: String,
    val nickname: String
) {
    fun toCommand() = CreateUserCommand(Email(email), NickName(nickname))
}