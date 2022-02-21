package xyz.neonkid.cms.core.interceptor

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Permission (
    val role: PermissionRole = PermissionRole.MEMBER
)