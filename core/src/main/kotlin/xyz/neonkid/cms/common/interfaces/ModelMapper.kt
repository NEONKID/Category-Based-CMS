package xyz.neonkid.cms.common.interfaces

/**
 * Created by Neon K.I.D on 1/1/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface ModelMapper<D, P> {
    fun mapToDomainEntity(model: P): D
    fun mapToJdbcEntity(model: D): P
}