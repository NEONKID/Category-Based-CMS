package xyz.neonkid.cms.modules.category.domain.aggregate

import org.valiktor.functions.isNotZero
import org.valiktor.functions.isNull
import org.valiktor.functions.isPositive
import org.valiktor.validate
import xyz.neonkid.cms.core.snowflake.IdGenerator

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
data class CategoryId(val value: Long) {
    init {
        validate(this) {
            validate(CategoryId::value).isNotZero().isPositive()
        }
    }

    companion object {
        fun nextId() = CategoryId(IdGenerator.nextId())
    }
}