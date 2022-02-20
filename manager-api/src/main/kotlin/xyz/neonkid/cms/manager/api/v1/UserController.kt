package xyz.neonkid.cms.manager.api.v1

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import xyz.neonkid.cms.core.utils.success
import xyz.neonkid.cms.manager.core.interceptor.Permission
import xyz.neonkid.cms.manager.core.interceptor.PermissionRole
import xyz.neonkid.cms.manager.core.resolver.CurrentUser
import xyz.neonkid.cms.manager.core.resolver.UserInfo
import xyz.neonkid.cms.modules.user.domain.aggregate.UserId
import xyz.neonkid.cms.modules.user.domain.valueObjects.Email
import xyz.neonkid.cms.modules.user.domain.valueObjects.NickName
import xyz.neonkid.cms.modules.user.domain.valueObjects.Password
import xyz.neonkid.cms.modules.user.useCases.commands.*
import xyz.neonkid.cms.modules.user.useCases.queries.UserQueryRepository

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@RestController
@RequestMapping("/v1/users")
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,

    private val userQueryRepository: UserQueryRepository
) {
    @Permission(PermissionRole.ADMIN)
    @GetMapping
    fun getAllUsers(@CurrentUser userInfo: UserInfo) = success(userQueryRepository.fetchAll())

    @Permission(PermissionRole.ADMIN)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody req: CreateUserRequest, @CurrentUser userInfo: UserInfo) =
        success(createUserUseCase.invoke(
            CreateUserCommand(
                Email(req.email),
                NickName(req.nickname),
                Password(req.password)
            ))
        )

    @Permission(PermissionRole.ADMIN)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long, @CurrentUser userInfo: UserInfo) =
        deleteUserUseCase.invoke(DeleteUserCommand(UserId(id)))
}