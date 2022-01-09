package xyz.neonkid.cms.manager.api.v1

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import xyz.neonkid.cms.core.utils.*
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.post.domain.aggregate.PostId
import xyz.neonkid.cms.modules.post.domain.valueObjects.*
import xyz.neonkid.cms.modules.post.useCases.commands.*
import xyz.neonkid.cms.modules.post.useCases.queries.PostQueryRepository
import xyz.neonkid.cms.modules.tag.domain.aggregate.TagId
import java.util.*
import java.util.stream.Collectors

/**
 * Created by Neon K.I.D on 12/31/21
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@RestController
@RequestMapping("/v1/posts")
class PostController(
    private val createPostUseCase: CreatePostUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val savePostUseCase: SavePostUseCase,

    private val changeCategoryUseCase: ChangeCategoryUseCase,
    private val removeCategoryUseCase: RemoveCategoryUseCase,
    private val changeVirtualAuthorUseCase: ChangeVirtualAuthorUseCase,
    private val removeVirtualAuthorUseCase: RemoveVirtualAuthorUseCase,

    private val postQueryRepository: PostQueryRepository
) {
    @GetMapping
    fun getAllPosts() = success(postQueryRepository.fetchAll())

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long) = success(postQueryRepository.fetchById(id))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addPost(@RequestBody request: CreatePostRequest): ApiResult<Long> {
        val dto = createPostUseCase.invoke(CreatePostCommand(
            title = Title(request.title),
            body = request.body?.let { Body(it) },
            description = request.description?.let { Description(it) },
            thumbnail = request.thumbnail?.let { Thumbnail(it) },
            publishedAt = request.publishedAt?.let { ContentDateTime(it) },
            categoryId = request.categoryId?.let { CategoryId(it) },
            virtualAuthorId = request.virtualAuthorId?.let { VirtualAuthorId(it) },
            isPrivate = IsPrivate(request.isPrivate),
            tags = request.tags.let { it.stream().map { TagId(it) }.collect(Collectors.toSet()) }
        ))
        return success(dto.id)
    }

    @PatchMapping("/{id}")
    fun savePost(@PathVariable id: Long, @RequestBody request: SavePostRequest) =
        success(savePostUseCase.invoke(
            SavePostCommand(
                id = PostId(id),
                title = request.title?.let { Title(it) },
                body = request.body?.let { Body(it) },
                description = request.description?.let { Description(it) },
                thumbnail = request.thumbnail?.let { Thumbnail(it) },
                publishedAt = request.publishedAt?.let { ContentDateTime(it) })
        ))

    @PatchMapping("/{id}/category/{categoryId}")
    fun changeCategory(@PathVariable id: Long, @PathVariable categoryId: Long): ApiResult<String> {
        changeCategoryUseCase.invoke(
            ChangeCategoryCommand(postId = PostId(id), categoryId = CategoryId(categoryId))
        )
        return success("OK")
    }

    @PatchMapping("/{id}/category")
    fun removeCategory(@PathVariable id: Long): ApiResult<String> {
        removeCategoryUseCase.invoke(RemoveCategoryCommand(PostId(id)))
        return success("OK")
    }

    @PatchMapping("/{id}/virtual_author/{authorId}")
    fun changeVirtualAuthor(@PathVariable id: Long, @PathVariable authorId: UUID): ApiResult<String> {
        changeVirtualAuthorUseCase.invoke(ChangeVirtualAuthorCommand(
            postId = PostId(id), virtualAuthorId = VirtualAuthorId(authorId)
        ))
        return success("OK")
    }

    @PatchMapping("/{id}/virtual_author")
    fun removeVirtualAuthor(@PathVariable id: Long): ApiResult<String> {
        removeVirtualAuthorUseCase.invoke(RemoveVirtualAuthorCommand(PostId(id)))
        return success("OK")
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(@PathVariable id: Long) {
        deletePostUseCase.invoke(DeletePostCommand(PostId(id)))
    }
}