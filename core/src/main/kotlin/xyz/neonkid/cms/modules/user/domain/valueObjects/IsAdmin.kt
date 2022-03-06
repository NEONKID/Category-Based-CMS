package xyz.neonkid.cms.modules.user.domain.valueObjects

import org.valiktor.functions.isNotNull
import org.valiktor.validate

/**
 * Created by Neon K.I.D on 3/6/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class IsAdmin(val value: Boolean) {
    init {
        validate(this) {
            validate(IsAdmin::value).isNotNull()
        }
    }
}
