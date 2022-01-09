package xyz.neonkid.cms.manager.api.v1

import org.springframework.web.bind.annotation.*
import xyz.neonkid.cms.core.utils.ApiResult
import xyz.neonkid.cms.core.utils.success
import xyz.neonkid.cms.modules.author.domain.aggregate.VirtualAuthorId
import xyz.neonkid.cms.modules.author.domain.valueObjects.Description
import xyz.neonkid.cms.modules.author.domain.valueObjects.Name
import xyz.neonkid.cms.modules.author.domain.valueObjects.ProfileImage
import xyz.neonkid.cms.modules.author.useCases.commands.CreateVirtualAuthorCommand
import xyz.neonkid.cms.modules.author.useCases.commands.CreateVirtualAuthorRequest
import xyz.neonkid.cms.modules.author.useCases.commands.CreateVirtualAuthorUseCase
import xyz.neonkid.cms.modules.author.useCases.queries.VirtualAuthorQueryRepository
import java.util.*

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@RestController
@RequestMapping("/v1/virtual_authors")
class VirtualAuthorController(
    private val createVirtualAuthorUseCase: CreateVirtualAuthorUseCase,
    private val virtualAuthorQueryRepository: VirtualAuthorQueryRepository
) {
    @GetMapping
    fun getVirtualAuthors() = success(virtualAuthorQueryRepository.fetchAll())

    @PostMapping
    fun addVirtualAuthor(@RequestBody request: CreateVirtualAuthorRequest): ApiResult<UUID> {
        val dto = createVirtualAuthorUseCase.invoke(CreateVirtualAuthorCommand(
            VirtualAuthorId.nextId(),
            Name(request.name),
            request.description?.let { Description(it) },
            request.profileImage?.let { ProfileImage(it) }
        ))

        return success(dto.id)
    }
}