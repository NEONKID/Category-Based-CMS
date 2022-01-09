package xyz.neonkid.cms.modules.author.domain.aggregate

import org.valiktor.validate
import java.util.*

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class VirtualAuthorId(val value: UUID) {
    init {
        validate(this) {
            validate(VirtualAuthorId::value)
        }
    }

    companion object {
        fun nextId() = VirtualAuthorId(UUID.randomUUID())
    }
}