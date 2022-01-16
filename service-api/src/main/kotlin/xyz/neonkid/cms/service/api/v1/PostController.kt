package xyz.neonkid.cms.service.api.v1

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.neonkid.cms.modules.post.useCases.queries.PostQueryRepository

/**
 * Created by Neon K.I.D on 1/16/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@RestController
@RequestMapping("/v1/posts")
class PostController(
    private val postQueryRepository: PostQueryRepository
) {

}