package xyz.neonkid.cms.common.interfaces

/**
 * Created by Neon K.I.D on 1/1/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface UseCase<in C, out T> {
    fun invoke(command: C): T
}