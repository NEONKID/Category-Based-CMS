package xyz.neonkid.cms.modules.category.useCases.exceptions

import xyz.neonkid.cms.common.errors.NotFoundException

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class CategoryNotFoundException(val id: Long) : NotFoundException("$id 번 카테고리를 찾을 수 없습니다")