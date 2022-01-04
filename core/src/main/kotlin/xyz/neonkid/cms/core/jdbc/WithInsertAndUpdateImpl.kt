package xyz.neonkid.cms.core.jdbc

import org.springframework.data.jdbc.core.JdbcAggregateTemplate

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class WithInsertAndUpdateImpl<T>(private val template: JdbcAggregateTemplate) : WithInsertAndUpdate<T> {
    override fun insert(t: T): T = this.template.insert(t)
    override fun update(t: T): T = this.template.update(t)
}