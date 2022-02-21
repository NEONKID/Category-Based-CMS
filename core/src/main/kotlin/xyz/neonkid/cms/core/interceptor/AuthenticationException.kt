package xyz.neonkid.cms.core.interceptor

import org.springframework.http.HttpStatus
import xyz.neonkid.cms.common.errors.NKCMSException

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class AuthenticationException : NKCMSException(HttpStatus.UNAUTHORIZED, "not authenticated")