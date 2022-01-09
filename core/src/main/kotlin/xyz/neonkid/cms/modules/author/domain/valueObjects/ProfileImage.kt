package xyz.neonkid.cms.modules.author.domain.valueObjects

import org.valiktor.functions.isWebsite
import org.valiktor.validate

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class ProfileImage (val value: String) {
    init {
        validate(this) {
            validate(ProfileImage::value).isWebsite()
        }
    }
}