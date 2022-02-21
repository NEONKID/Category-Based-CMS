package xyz.neonkid.cms.core.resolver

import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import xyz.neonkid.cms.core.interceptor.AuthenticationException
import xyz.neonkid.cms.manager.core.utils.JwtTokenUtils

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Component
class CurrentUserArgumentResolver(
    private val jwtTokenUtils: JwtTokenUtils
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter) = parameter.hasParameterAnnotation(CurrentUser::class.java)

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): UserInfo? {
        val authorization = webRequest.getHeader("Authorization") ?: throw AuthenticationException()
        val token = authorization.split(" ")[1]
        val payload = jwtTokenUtils.getPayload(token)

        return UserInfo(payload.userId)
    }
}