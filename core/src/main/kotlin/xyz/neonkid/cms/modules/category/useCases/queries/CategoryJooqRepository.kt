package xyz.neonkid.cms.modules.category.useCases.queries

import org.jooq.DSLContext
import org.jooq.impl.DSL.*;
import org.springframework.stereotype.Repository
import xyz.neonkid.cms.modules.category.useCases.exceptions.CategoryNotFoundException
import xyz.neonkid.cms.modules.category.useCases.queries.dto.CategoryDTO
import xyz.neonkid.cms.modules.category.useCases.queries.dto.PublicCategoryDTO
import xyz.neonkid.cms.modules.category.useCases.queries.dto.PublicSingleCategoryDTO
import xyz.neonkid.cms.modules.category.useCases.queries.dto.SingleCategoryDTO
import xyz.neonkid.cms.tables.Category
import xyz.neonkid.cms.tables.CategoryPost
import xyz.neonkid.cms.tables.Post

/**
 * Created by Neon K.I.D on 1/3/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Repository
class CategoryJooqRepository(private val ctx: DSLContext) : CategoryQueryRepository {
    private val category: Category = Category.CATEGORY
    private val categoryPost: CategoryPost = CategoryPost.CATEGORY_POST
    private val post: Post = Post.POST

    private val selectCategory = jsonObject(
        key("id").value(category.ID),
        key("name").value(category.NAME),
        key("posts").value(
            field(select(
                jsonArrayAgg(jsonObject(
                    key("id").value(post.ID),
                    key("title").value(post.TITLE),
                    key("thumbnail").value(post.THUMBNAIL),
                    key("description").value(post.DESCRIPTION)
                ))
            ).from(post).join(categoryPost).on(post.ID.eq(categoryPost.POST_ID))
                .where(categoryPost.CATEGORY_ID.eq(category.ID)))
        )
    )

    private val allCategory = jsonObject(
        key("id").value(category.ID),
        key("name").value(category.NAME),
        key("post_count").value(
            field(select(count()).from(post).join(categoryPost).on(post.ID.eq(categoryPost.POST_ID))
                .where(categoryPost.CATEGORY_ID.eq(category.ID)))
        ),
        key("public_post_count").value(
            field(select(count()).from(post).join(categoryPost).on(post.ID.eq(categoryPost.POST_ID))
                .where(categoryPost.CATEGORY_ID.eq(category.ID)).and(post.IS_PRIVATE.eq(false)))
        )
    )

    private val selectPublicCategory = jsonObject(
        key("id").value(category.ID),
        key("name").value(category.NAME),
        key("public_posts").value(
            field(select(
                jsonArrayAgg(jsonObject(
                    key("id").value(post.ID),
                    key("title").value(post.TITLE),
                    key("thumbnail").value(post.THUMBNAIL),
                    key("description").value(post.DESCRIPTION)
                ))
            ).from(post).join(categoryPost).on(post.ID.eq(categoryPost.POST_ID))
                .where(categoryPost.CATEGORY_ID.eq(category.ID)).and(post.IS_PRIVATE.eq(false)))
        )
    )

    private val allPublicCategory = jsonObject(
        key("id").value(category.ID),
        key("name").value(category.NAME),
        key("public_post_count").value(
            field(select(count()).from(post).join(categoryPost).on(post.ID.eq(categoryPost.POST_ID))
                .where(categoryPost.CATEGORY_ID.eq(category.ID)).and(post.IS_PRIVATE.eq(false)))
        )
    )

    override fun fetchById(id: Long): CategoryDTO =
        ctx.select(selectCategory).from(category)
            .where(category.ID.eq(id)).fetchOneInto(CategoryDTO::class.java) ?: throw CategoryNotFoundException(id)

    override fun fetchPublicById(id: Long): PublicCategoryDTO =
        ctx.select(selectPublicCategory).from(category)
            .where(category.ID.eq(id)).fetchOneInto(PublicCategoryDTO::class.java) ?: throw CategoryNotFoundException(id)

    override fun fetchAll(): MutableList<SingleCategoryDTO> =
        ctx.select(allCategory).from(category).fetch().into(SingleCategoryDTO::class.java)

    override fun fetchPublicAll(): MutableList<PublicSingleCategoryDTO> =
        ctx.select(allPublicCategory).from(category).fetch().into(PublicSingleCategoryDTO::class.java)
}