package xyz.neonkid.cms.modules.category.useCases.exceptions

import org.springframework.http.HttpStatus
import xyz.neonkid.cms.common.errors.NKCMSException

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class CategoryNotFoundException(val id: Long) : NKCMSException(HttpStatus.NOT_FOUND, "$id 번 카테고리를 찾을 수 없습니다")