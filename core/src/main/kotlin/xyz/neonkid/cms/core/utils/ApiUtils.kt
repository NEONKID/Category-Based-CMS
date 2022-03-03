package xyz.neonkid.cms.core.utils

import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.springframework.http.HttpStatus

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class ApiError internal constructor(val message: String?, val status: HttpStatus) {
    internal constructor(throwable: Throwable, status: HttpStatus) : this(throwable.message, status)

    override fun toString() =
        ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("message", message)
            .append("status", status)
            .toString()
}

class ApiResult<T> (@get:JvmName("isSuccess") val success: Boolean, val response: T, val error: ApiError?) {
    override fun toString() =
        ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("success", success)
            .append("response", response)
            .append("error", error)
            .toString()
}

fun <T> success(response: T) = ApiResult(true, response, null)
fun error(throwable: Throwable, status: HttpStatus) = ApiResult(false, null, ApiError(throwable, status))
fun error(message: String?, status: HttpStatus) = ApiResult(false, null, ApiError(message, status))
