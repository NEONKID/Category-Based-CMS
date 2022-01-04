package xyz.neonkid.cms.common.interfaces

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface PersistenceAdapter<D, I> {
    fun findById(id: I): D
    fun insert(domain: D): D
    fun update(domain: D): D
    fun deleteById(id: I)
}