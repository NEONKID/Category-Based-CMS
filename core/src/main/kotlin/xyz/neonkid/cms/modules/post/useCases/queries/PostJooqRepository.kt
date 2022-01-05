package xyz.neonkid.cms.modules.post.useCases.queries

import org.jooq.DSLContext
import org.jooq.impl.DSL.*
import org.springframework.stereotype.Repository
import xyz.neonkid.cms.modules.post.useCases.exceptions.PostNotFoundException
import xyz.neonkid.cms.modules.post.useCases.queries.dto.PostDTO
import xyz.neonkid.cms.modules.post.useCases.queries.dto.SinglePostDTO
import xyz.neonkid.cms.tables.*

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Repository
class PostJooqRepository(private val ctx: DSLContext) : PostQueryRepository {
    private val post: Post = Post.POST
    private val postVirtualAuthor: PostVirtualAuthor = PostVirtualAuthor.POST_VIRTUAL_AUTHOR
    private val postCategory: PostCategory = PostCategory.POST_CATEGORY
    private val category: Category = Category.CATEGORY
    private val virtualAuthor: VirtualAuthor = VirtualAuthor.VIRTUAL_AUTHOR

    private val selectPost = jsonObject(
        key("id").value(post.ID),
        key("title").value(post.TITLE),
        key("body").value(post.BODY),
        key("thumbnail").value(post.THUMBNAIL),
        key("isPrivate").value(post.IS_PRIVATE),
        key("description").value(post.DESCRIPTION),
        key("published_at").value(post.PUBLISHED_AT),
        key("virtual_author").value(
            field(
                select(jsonObject(
                    key("id").value(virtualAuthor.ID),
                    key("name").value(virtualAuthor.NAME),
                    key("profile_image").value(virtualAuthor.PROFILE_IMAGE)
                )).from(virtualAuthor).join(postVirtualAuthor).on(virtualAuthor.ID.eq(postVirtualAuthor.VIRTUAL_AUTHOR_ID))
                    .where(postVirtualAuthor.POST_ID.eq(post.ID))
            )
        ),
        key("category").value(
            select(jsonObject(category.ID, category.NAME))
                .from(category).join(postCategory).on(category.ID.eq(postCategory.CATEGORY_ID))
                .where(postCategory.POST_ID.eq(post.ID))
        )
    )

    override fun fetchByTitle(title: String): PostDTO =
        ctx.select(selectPost)
            .from(post).where(post.TITLE.eq(title)).fetchOneInto(PostDTO::class.java) ?: throw PostNotFoundException()

    override fun fetchById(id: Long): PostDTO =
        ctx.select(selectPost)
            .from(post).where(post.ID.eq(id)).fetchOneInto(PostDTO::class.java) ?: throw PostNotFoundException()

    override fun fetchAll(): MutableList<SinglePostDTO> =
        ctx.select().from(post).fetch().into(SinglePostDTO::class.java)
}