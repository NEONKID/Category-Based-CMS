package xyz.neonkid.cms.modules.user.domain.valueObjects

import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotNull
import org.valiktor.validate

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class Password(val value: String) {
    init {
        validate(this) {
            validate(Password::value).isNotNull().hasSize(64, 255)
        }
    }
}
