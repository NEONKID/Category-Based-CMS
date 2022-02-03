package xyz.neonkid.cms.modules.author.useCases.queries

import org.jooq.DSLContext
import org.jooq.impl.DSL.*;
import org.springframework.stereotype.Repository
import xyz.neonkid.cms.generator.StandalonePostgresDatabase
import xyz.neonkid.cms.modules.author.useCases.exceptions.VirtualAuthorNotFoundException
import xyz.neonkid.cms.modules.author.useCases.queries.dto.SingleVirtualAuthorDTO
import xyz.neonkid.cms.modules.author.useCases.queries.dto.VirtualAuthorDTO
import xyz.neonkid.cms.modules.post.useCases.queries.dto.PostDTO
import xyz.neonkid.cms.tables.VirtualAuthor
import xyz.neonkid.cms.tables.VirtualAuthorPost
import java.util.*

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Repository
class VirtualAuthorJooqRepository(private val ctx: DSLContext) : VirtualAuthorQueryRepository {
    private val virtualAuthor: VirtualAuthor = VirtualAuthor.VIRTUAL_AUTHOR
    private val virtualAuthorPost: VirtualAuthorPost = VirtualAuthorPost.VIRTUAL_AUTHOR_POST

    private val allVirtualAuthor = jsonObject(
        key("id").value(virtualAuthor.ID),
        key("name").value(virtualAuthor.NAME),
        key("profile_image").value(virtualAuthor.PROFILE_IMAGE),
        key("post_count").value(
            field(
                select(count()).from(virtualAuthor).join(virtualAuthorPost).on(virtualAuthor.ID.eq(virtualAuthorPost.VIRTUAL_AUTHOR_ID))
                    .where(virtualAuthorPost.VIRTUAL_AUTHOR_ID.eq(virtualAuthor.ID))
            )
        )
    )

    override fun fetchAll(): MutableList<SingleVirtualAuthorDTO> =
        ctx.select(allVirtualAuthor).from(virtualAuthor).fetch().into(SingleVirtualAuthorDTO::class.java)

    override fun fetchById(id: UUID) =
        ctx.select(allVirtualAuthor).from(virtualAuthor).where(virtualAuthor.ID.eq(id.toString()))
            .fetchOneInto(VirtualAuthorDTO::class.java) ?: throw VirtualAuthorNotFoundException(id)
}