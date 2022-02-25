package xyz.neonkid.cms.modules.user.useCases.exceptions

import org.springframework.http.HttpStatus
import xyz.neonkid.cms.common.errors.NKCMSException

/**
 * Created by Neon K.I.D on 2/20/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class UserNotFoundException : NKCMSException {
    constructor(id: Long) : super(HttpStatus.NOT_FOUND, "$id 유저를 찾을 수 없습니다.")
    constructor(email: String) : super(HttpStatus.NOT_FOUND, "$email 유저를 찾을 수 없습니다.")
}