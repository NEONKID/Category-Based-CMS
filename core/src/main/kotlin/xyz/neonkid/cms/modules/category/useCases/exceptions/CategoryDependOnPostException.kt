package xyz.neonkid.cms.modules.category.useCases.exceptions

import xyz.neonkid.cms.common.errors.ConflictException

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class CategoryDependOnPostException(val id: Long) : ConflictException("$id 카테고리에 속한 컨텐츠가 존재합니다")