package xyz.neonkid.cms.common.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
abstract class NotFoundException(override val message: String) : Exception()