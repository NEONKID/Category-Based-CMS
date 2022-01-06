package xyz.neonkid.cms.modules.category.domain.valueObjects

import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotBlank
import org.valiktor.validate

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class Name(val value: String) {
    init {
        validate(this) {
            validate(Name::value).isNotBlank().hasSize(min = 1, max = 255)
        }
    }
}
