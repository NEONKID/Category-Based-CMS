package xyz.neonkid.cms.modules.post.infrastructure.persistence

import org.springframework.stereotype.Service
import xyz.neonkid.cms.common.interfaces.PersistenceAdapter
import xyz.neonkid.cms.modules.post.domain.aggregate.Post
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.useCases.exceptions.PostNotFoundException
import xyz.neonkid.cms.persistence.post.PostRepository

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Service
class PostPersistenceAdapter(private val postRepository: PostRepository) : PersistenceAdapter<Post, PostId> {
    override fun findById(id: PostId): Post {
        val result = postRepository.findById(id.value).orElseThrow { PostNotFoundException(id.value) }
        return PostMapper.mapToDomainEntity(result)
    }

    override fun insert(domain: Post): Post {
        val result = postRepository.insert(PostMapper.mapToJdbcEntity(domain))
        return PostMapper.mapToDomainEntity(result)
    }

    override fun update(domain: Post): Post {
        val result = postRepository.update(PostMapper.mapToJdbcEntity(domain))
        return PostMapper.mapToDomainEntity(result)
    }

    override fun deleteById(id: PostId) {
        postRepository.deleteById(id.value)
    }
}