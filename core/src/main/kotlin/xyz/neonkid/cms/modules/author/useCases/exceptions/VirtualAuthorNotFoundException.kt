package xyz.neonkid.cms.modules.author.useCases.exceptions

import xyz.neonkid.cms.common.errors.NotFoundException
import java.util.*

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class VirtualAuthorNotFoundException(val id: UUID) : NotFoundException("$id 의 가상 저자를 찾을 수 없습니다")