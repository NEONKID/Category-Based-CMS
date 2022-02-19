package xyz.neonkid.cms.manager.core.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import xyz.neonkid.cms.core.CoreConfig
import xyz.neonkid.cms.manager.core.interceptor.PermissionInterceptor
import xyz.neonkid.cms.manager.core.resolver.CurrentUserArgumentResolver

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
@Configuration
@Import(value = [CoreConfig::class])
class AppConfig (
    private val currentUserArgumentResolver: CurrentUserArgumentResolver,
    private val permissionInterceptor: PermissionInterceptor
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(currentUserArgumentResolver)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(permissionInterceptor)
    }
}