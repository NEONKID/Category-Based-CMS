package xyz.neonkid.cms.common.errors

import org.springframework.http.HttpStatus

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
abstract class NKCMSException(val status: HttpStatus, override val message: String) : RuntimeException()