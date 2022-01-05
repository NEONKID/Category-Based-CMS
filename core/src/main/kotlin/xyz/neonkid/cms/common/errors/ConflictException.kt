package xyz.neonkid.cms.common.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@ResponseStatus(HttpStatus.CONFLICT)
abstract class ConflictException(override val message: String) : Exception()