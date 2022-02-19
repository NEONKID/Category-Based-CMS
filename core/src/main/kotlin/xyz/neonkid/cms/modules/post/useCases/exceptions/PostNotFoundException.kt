package xyz.neonkid.cms.modules.post.useCases.exceptions

import org.springframework.http.HttpStatus
import xyz.neonkid.cms.common.errors.NKCMSException

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class PostNotFoundException : NKCMSException {
    constructor(id: Long) : super(HttpStatus.NOT_FOUND, "$id 번 컨텐츠를 찾을 수 없습니다")
    constructor(title: String) : super(HttpStatus.NOT_FOUND, "$title 컨텐츠를 찾을 수 없습니다")
}