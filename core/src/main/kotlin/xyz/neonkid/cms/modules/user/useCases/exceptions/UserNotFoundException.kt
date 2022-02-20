package xyz.neonkid.cms.modules.user.useCases.exceptions

import org.springframework.http.HttpStatus
import xyz.neonkid.cms.common.errors.NKCMSException

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class UserNotFoundException(id: Long) : NKCMSException(HttpStatus.NOT_FOUND, "$id 사용자를 찾을 수 없습니다")