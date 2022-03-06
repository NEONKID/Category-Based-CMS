package xyz.neonkid.cms.modules.user.useCases.commands

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.modules.user.domain.aggregate.UserId
import xyz.neonkid.cms.modules.user.domain.valueObjects.IsAdmin
import xyz.neonkid.cms.modules.user.infrastructure.persistence.UserPersistenceAdapter
import xyz.neonkid.cms.modules.user.useCases.queries.UserQueryRepository

/**
 * Created by Neon K.I.D on 3/6/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
@Transactional
class AssignMemberUseCase(
    private val userPersistenceAdapter: UserPersistenceAdapter
): UseCase<AssignMemberCommand, Unit> {
    override fun invoke(command: AssignMemberCommand) {
        val user = userPersistenceAdapter.findById(command.userId)
        user.isAdmin = IsAdmin(false)

        userPersistenceAdapter.update(user)
    }
}

data class AssignMemberCommand(val userId: UserId)