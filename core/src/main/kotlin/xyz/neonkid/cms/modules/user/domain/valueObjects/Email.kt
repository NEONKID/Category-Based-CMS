package xyz.neonkid.cms.modules.user.domain.valueObjects

import org.valiktor.functions.isEmail
import org.valiktor.validate

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
data class Email (val value: String) {
    init {
        validate(this) {
            validate(Email::value).isEmail()
        }
    }
}