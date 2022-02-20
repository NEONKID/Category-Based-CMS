package xyz.neonkid.cms.modules.user.useCases.commands

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.user.domain.aggregate.UserId
import xyz.neonkid.cms.modules.user.infrastructure.persistence.UserPersistenceAdapter

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class DeleteUserUseCase(
    private val userPersistenceAdapter: UserPersistenceAdapter
) : UseCase<DeleteUserCommand, Unit> {
    override fun invoke(command: DeleteUserCommand) {
        userPersistenceAdapter.deleteById(command.userId)
    }
}

data class DeleteUserCommand(val userId: UserId)