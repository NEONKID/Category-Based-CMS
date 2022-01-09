package xyz.neonkid.cms.manager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import xyz.neonkid.cms.core.CoreConfig

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Import(value = [CoreConfig::class])
@SpringBootApplication
class CMSManagerApplication

fun main(args: Array<String>) {
    runApplication<CMSManagerApplication>(*args)
}