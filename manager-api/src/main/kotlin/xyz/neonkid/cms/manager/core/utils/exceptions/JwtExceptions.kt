package xyz.neonkid.cms.manager.core.utils.exceptions

import org.springframework.http.HttpStatus
import xyz.neonkid.cms.common.errors.NKCMSException

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
class JwtExpiredException : NKCMSException(HttpStatus.FORBIDDEN, "expired token")
class JwtSignatureException : NKCMSException(HttpStatus.FORBIDDEN, "jwt signature exception")
class JwtTokenDecodeException : NKCMSException(HttpStatus.BAD_REQUEST, "invalid jwt token")