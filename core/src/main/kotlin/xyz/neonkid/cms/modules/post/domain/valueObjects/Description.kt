package xyz.neonkid.cms.modules.post.domain.valueObjects

import org.valiktor.functions.hasSize
import org.valiktor.functions.isNull
import org.valiktor.validate

/**
 * Created by Neon K.I.D on 1/1/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class Description(val value: String) {
    init {
        validate(this) {
            validate(Description::value).hasSize(min = 0, max = 1024)
        }
    }
}