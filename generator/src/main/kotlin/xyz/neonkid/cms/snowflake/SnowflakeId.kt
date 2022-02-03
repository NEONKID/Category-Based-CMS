package xyz.neonkid.cms.snowflake

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface SnowflakeId {
    val workerId: Long
    val snowflake: Snowflake
    fun nextId(): Long
}