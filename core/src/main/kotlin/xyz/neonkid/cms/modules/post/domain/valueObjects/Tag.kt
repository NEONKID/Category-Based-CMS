package xyz.neonkid.cms.modules.post.domain.valueObjects

import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

/**
 * Created by Neon K.I.D on 1/1/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class Tag(val value: String) {
    init {
        validate(this) {
            validate(Tag::value).isNotBlank().hasSize(min = 1, max = 255)
        }
    }
}