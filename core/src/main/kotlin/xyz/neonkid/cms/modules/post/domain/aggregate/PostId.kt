package xyz.neonkid.cms.modules.post.domain.aggregate

import org.valiktor.functions.isNotZero
import org.valiktor.functions.isPositive
import org.valiktor.validate
import xyz.neonkid.cms.core.snowflake.IdGenerator

/**
 * Created by Neon K.I.D on 1/1/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class PostId (val value: Long) {
    init {
        validate(this) {
            validate(PostId::value).isNotZero().isPositive()
        }
    }

    companion object {
        fun nextId() = PostId(IdGenerator.nextId())
    }
}