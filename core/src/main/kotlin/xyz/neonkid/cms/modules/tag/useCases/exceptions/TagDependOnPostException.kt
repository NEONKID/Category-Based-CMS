package xyz.neonkid.cms.modules.tag.useCases.exceptions

import xyz.neonkid.cms.common.errors.ConflictException

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class TagDependOnPostException(val name: String) : ConflictException("$name 에 연결된 컨텐츠가 존재합니다")