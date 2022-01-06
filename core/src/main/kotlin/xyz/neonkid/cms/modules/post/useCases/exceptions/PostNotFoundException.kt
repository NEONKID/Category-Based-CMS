package xyz.neonkid.cms.modules.post.useCases.exceptions

import xyz.neonkid.cms.common.errors.NotFoundException

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class PostNotFoundException(val id: Long) : NotFoundException("$id 번 컨텐츠를 찾을 수 없습니다")