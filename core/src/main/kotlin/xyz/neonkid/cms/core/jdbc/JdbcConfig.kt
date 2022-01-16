package xyz.neonkid.cms.core.jdbc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import xyz.neonkid.cms.persistence.author.BeforeSaveAuthorCallback
import xyz.neonkid.cms.persistence.post.BeforeSavePostCallback

/**
 * Created by Neon K.I.D on 1/8/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Configuration
class JdbcConfig : AbstractJdbcConfiguration() {
    @Bean
    fun beforeSaveAuthorCallback() = BeforeSaveAuthorCallback()

    @Bean
    fun beforeSavePostCallback() = BeforeSavePostCallback()
}