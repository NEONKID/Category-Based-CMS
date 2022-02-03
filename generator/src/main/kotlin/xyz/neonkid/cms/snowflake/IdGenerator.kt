package xyz.neonkid.cms.snowflake

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
object IdGenerator {
    private val idWorker = IdWorker(1)

    fun nextId(): Long {
        return idWorker.nextId()
    }
}