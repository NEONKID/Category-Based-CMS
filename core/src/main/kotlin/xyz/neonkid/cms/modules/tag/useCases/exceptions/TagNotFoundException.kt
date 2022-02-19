package xyz.neonkid.cms.modules.tag.useCases.exceptions

import org.springframework.http.HttpStatus
import xyz.neonkid.cms.common.errors.NKCMSException

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class TagNotFoundException(val name: String) : NKCMSException(HttpStatus.NOT_FOUND, "$name 을 찾을 수 없습니다")