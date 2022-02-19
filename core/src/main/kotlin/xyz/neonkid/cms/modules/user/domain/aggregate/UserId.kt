package xyz.neonkid.cms.modules.user.domain.aggregate

import org.valiktor.functions.isPositiveOrZero
import org.valiktor.validate

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
data class UserId (val value: Long) {
    init {
        validate(this) {
            validate(UserId::value).isPositiveOrZero()
        }
    }
}