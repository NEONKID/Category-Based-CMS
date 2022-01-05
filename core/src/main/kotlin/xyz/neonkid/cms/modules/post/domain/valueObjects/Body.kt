package xyz.neonkid.cms.modules.post.domain.valueObjects

import org.valiktor.validate

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class Body(val value: String) {
    init {
        validate(this) {
            validate(Body::value)
        }
    }
}