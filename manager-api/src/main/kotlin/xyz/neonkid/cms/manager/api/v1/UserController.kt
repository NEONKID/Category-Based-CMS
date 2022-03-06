package xyz.neonkid.cms.manager.api.v1

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import xyz.neonkid.cms.core.utils.success
import xyz.neonkid.cms.core.interceptor.Permission
import xyz.neonkid.cms.core.interceptor.PermissionRole
import xyz.neonkid.cms.core.resolver.CurrentUser
import xyz.neonkid.cms.core.resolver.UserInfo
import xyz.neonkid.cms.modules.user.domain.aggregate.UserId
import xyz.neonkid.cms.modules.user.domain.valueObjects.Email
import xyz.neonkid.cms.modules.user.domain.valueObjects.NickName
import xyz.neonkid.cms.modules.user.domain.valueObjects.Password
import xyz.neonkid.cms.modules.user.useCases.commands.*
import xyz.neonkid.cms.modules.user.useCases.queries.UserQueryRepository

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
@RestController
@RequestMapping("/v1/users")
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val assignAdminUseCase: AssignAdminUseCase,
    private val assignMemberUseCase: AssignMemberUseCase,

    private val userQueryRepository: UserQueryRepository
) {
    @Permission(PermissionRole.ADMIN)
    @GetMapping
    fun getAllUsers(@CurrentUser userInfo: UserInfo) = success(userQueryRepository.fetchAll())

    @PostMapping("/login")
    fun loginUser(@RequestBody req: LoginUserRequest) =
        success(loginUserUseCase.invoke(
            LoginUserCommand(
                Email(req.email),
                Password(req.password)
            ))
        )

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
    @PatchMapping("{userId}/admin")
    fun assignAdmin(@PathVariable userId: Long) {
        success(assignAdminUseCase.invoke(AssignAdminCommand(UserId(userId))))
    }

    @Permission(PermissionRole.ADMIN)
    @PatchMapping("{userId}/member")
    fun assignMember(@PathVariable userId: Long) {
        success(assignMemberUseCase.invoke(AssignMemberCommand(UserId(userId))))
    }

    @Permission(PermissionRole.ADMIN)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long, @CurrentUser userInfo: UserInfo) =
        deleteUserUseCase.invoke(DeleteUserCommand(UserId(id)))
}