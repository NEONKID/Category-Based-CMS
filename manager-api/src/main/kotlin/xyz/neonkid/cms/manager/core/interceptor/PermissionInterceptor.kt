package xyz.neonkid.cms.manager.core.interceptor

import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import xyz.neonkid.cms.manager.core.utils.JwtTokenUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Component
class PermissionInterceptor(
    private val jwtTokenUtils: JwtTokenUtils
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod)
            return true

        val permission = handler.getMethodAnnotation(Permission::class.java) ?: return true
        if (permission.role == PermissionRole.MEMBER)
            return true

        val token = extractJwtTokenFromHeader(request)
        val payload = jwtTokenUtils.getPayload(token)

        if (permission.role == PermissionRole.ADMIN)
            return true

        throw Exception()
    }

    private fun extractJwtTokenFromHeader(request: HttpServletRequest): String {
        val authorization = request.getHeader("Authorization") ?: throw Exception()

        try {
            return authorization.split(" ")[1]
        } catch (ex: Exception) {
            throw Exception()
        }
    }
}