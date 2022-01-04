package xyz.neonkid.cms.common.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
abstract class BadRequestException(override val message: String) : RuntimeException()