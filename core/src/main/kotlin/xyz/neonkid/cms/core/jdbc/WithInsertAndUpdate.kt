package xyz.neonkid.cms.core.jdbc

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface WithInsertAndUpdate<T> {
    fun insert(t: T): T
    fun update(t: T): T
}