package xyz.neonkid.cms.modules.user.useCases.commands

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.neonkid.cms.common.interfaces.UseCase
import xyz.neonkid.cms.core.utils.JwtTokenUtils
import xyz.neonkid.cms.modules.user.domain.valueObjects.Email
import xyz.neonkid.cms.modules.user.domain.valueObjects.Password
import xyz.neonkid.cms.modules.user.infrastructure.persistence.UserPersistenceAdapter
import xyz.neonkid.cms.modules.user.useCases.queries.dto.TokenDTO

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
@Service
@Transactional(readOnly = true)
class LoginUserUseCase(
    private val userPersistenceAdapter: UserPersistenceAdapter,
    private val jwtTokenUtils: JwtTokenUtils
) : UseCase<LoginUserCommand, TokenDTO> {
    override fun invoke(command: LoginUserCommand): TokenDTO {
        val user = userPersistenceAdapter.findByEmail(command.email)
        if (user.password != command.password)
            throw IllegalArgumentException("Password is incorrect")

        val accessToken = jwtTokenUtils.encode(
            mapOf("user_id" to user.id, "is_admin" to user.isAdmin, "refresh" to false),
            1800)
        val refreshToken = jwtTokenUtils.encode(
            mapOf("user_id" to user.id, "is_admin" to user.isAdmin, "refresh" to true),
            3600 * 24)

        return TokenDTO(accessToken, refreshToken)
    }
}

data class LoginUserCommand(val email: Email, val password: Password)
data class LoginUserRequest(val email: String, val password: String)