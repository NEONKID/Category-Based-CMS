package xyz.neonkid.cms.core

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import xyz.neonkid.cms.core.jdbc.JdbcConfig

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Configuration
@ComponentScan(basePackages = ["xyz.neonkid.cms.modules"])
@EnableJdbcAuditing
@EnableJdbcRepositories(value = ["xyz.neonkid.cms.core", "xyz.neonkid.cms.persistence"])
@Import(JdbcConfig::class)
class CoreConfig