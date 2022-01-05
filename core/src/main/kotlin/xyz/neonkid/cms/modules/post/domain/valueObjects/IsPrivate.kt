package xyz.neonkid.cms.modules.post.domain.valueObjects

import org.valiktor.validate

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class IsPrivate(val value: Boolean = true) {
    init {
        validate(this) {
            validate(IsPrivate::value)
        }
    }
}