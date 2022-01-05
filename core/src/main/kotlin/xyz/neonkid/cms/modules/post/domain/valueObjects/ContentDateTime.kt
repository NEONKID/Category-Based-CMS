package xyz.neonkid.cms.modules.post.domain.valueObjects

import org.valiktor.validate
import java.time.LocalDateTime

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class ContentDateTime(val value: LocalDateTime) {
    init {
        validate(this) {
            validate(ContentDateTime::value)
        }
    }
}