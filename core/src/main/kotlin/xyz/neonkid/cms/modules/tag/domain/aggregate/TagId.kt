package xyz.neonkid.cms.modules.tag.domain.aggregate

import org.valiktor.functions.isNotBlank
import org.valiktor.validate

/**
 * Created by Neon K.I.D on 1/8/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class TagId(val value: String) {
    init {
        validate(this) {
            validate(TagId::value).isNotBlank()
        }
    }
}