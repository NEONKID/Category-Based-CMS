package xyz.neonkid.cms.modules.tag.useCases.queries

import org.jooq.DSLContext
import org.jooq.impl.DSL.*;
import org.springframework.stereotype.Repository
import xyz.neonkid.cms.modules.tag.useCases.exceptions.TagNotFoundException
import xyz.neonkid.cms.modules.tag.useCases.queries.dto.SingleTagDTO
import xyz.neonkid.cms.modules.tag.useCases.queries.dto.TagDTO
import xyz.neonkid.cms.tables.Post
import xyz.neonkid.cms.tables.Tag
import xyz.neonkid.cms.tables.TagPost

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Repository
class TagJooqRepository(private val ctx: DSLContext): TagQueryRepository {
    private val tag: Tag = Tag.TAG
    private val tagPost: TagPost = TagPost.TAG_POST
    private val post: Post = Post.POST

    private val selectTag = jsonObject(
        key("name").value(tag.NAME),
        key("posts").value(
            field(select(
                jsonArrayAgg(jsonObject(
                    key("id").value(post.ID),
                    key("title").value(post.TITLE),
                    key("thumbnail").value(post.THUMBNAIL),
                    key("description").value(post.DESCRIPTION)
                ))
            ).from(post).join(tagPost).on(post.ID.eq(tagPost.POST_ID))
                .where(tagPost.TAG_NAME.eq(tag.NAME)))
        )
    )

    private val allTag = jsonObject(
        key("name").value(tag.NAME),
        key("post_count").value(
            field(select(count()).from(post).join(tagPost).on(post.ID.eq(tagPost.POST_ID))
                .where(tagPost.TAG_NAME.eq(tag.NAME)))
        ),
        key("public_post_count").value(
            field(select(count()).from(post).join(tagPost).on(post.ID.eq(tagPost.POST_ID))
                .where(tagPost.TAG_NAME.eq(tag.NAME)).and(post.IS_PRIVATE.eq(false)))
        )
    )

    private val allPublicTag = jsonObject(
        key("name").value(tag.NAME),
        key("public_post_count").value(
            field(select(count()).from(post).join(tagPost).on(post.ID.eq(tagPost.POST_ID))
                .where(tagPost.TAG_NAME.eq(tag.NAME)).and(post.IS_PRIVATE.eq(false)))
        )
    )

    override fun fetchById(name: String): TagDTO =
        ctx.select(selectTag).from(tag)
            .where(tag.NAME.eq(name)).fetchOneInto(TagDTO::class.java) ?: throw TagNotFoundException(name)

    override fun fetchAll(): MutableList<SingleTagDTO> =
        ctx.select(allTag).from(tag).fetch().into(SingleTagDTO::class.java)

    override fun fetchPublicAll(): MutableList<SingleTagDTO> =
        ctx.select(allPublicTag).from(tag).fetch().into(SingleTagDTO::class.java)
}