package xyz.neonkid.cms.modules.user.domain.valueObjects

import org.valiktor.validate
import java.time.LocalDateTime

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class UserDateTime(val value: LocalDateTime?) {
    init {
        validate(this) {
            validate(UserDateTime::value)
        }
    }
}