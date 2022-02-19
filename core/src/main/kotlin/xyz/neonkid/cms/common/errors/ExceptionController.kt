package xyz.neonkid.cms.common.errors

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import xyz.neonkid.cms.core.utils.ApiError

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
@RestControllerAdvice
class ExceptionController {
    @ExceptionHandler(NKCMSException::class)
    fun handleNKCMSException(ex: NKCMSException) = ResponseEntity(ApiError(ex, ex.status), ex.status)
}