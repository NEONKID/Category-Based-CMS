package xyz.neonkid.cms.modules.post.domain.valueObjects

import org.valiktor.functions.isNull
import org.valiktor.functions.isWebsite
import org.valiktor.validate

/**
 * Created by Neon K.I.D on 1/1/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class Thumbnail(val value: String?) {
    init {
        validate(this) {
            validate(Thumbnail::value).isNull().isWebsite()
        }
    }
}