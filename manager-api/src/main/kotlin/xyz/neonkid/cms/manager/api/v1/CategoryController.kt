package xyz.neonkid.cms.manager.api.v1

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import xyz.neonkid.cms.core.utils.success
import xyz.neonkid.cms.modules.category.domain.aggregate.CategoryId
import xyz.neonkid.cms.modules.category.domain.valueObjects.Name
import xyz.neonkid.cms.modules.category.useCases.commands.*
import xyz.neonkid.cms.modules.category.useCases.queries.CategoryQueryRepository

/**
 * Created by Neon K.I.D on 1/4/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val categoryQueryRepository: CategoryQueryRepository
) {
    @GetMapping
    fun getCategoryAll() = success(categoryQueryRepository.fetchAll())

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long) = success(categoryQueryRepository.fetchById(id))

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCategory(@RequestBody request: CreateCategoryRequest) =
        success(createCategoryUseCase.invoke(CreateCategoryCommand(Name(request.name))).id)

    @PatchMapping("/{id}")
    fun updateCategory(@PathVariable id: Long) {

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(@PathVariable id: Long) {
        deleteCategoryUseCase.invoke(DeleteCategoryCommand(CategoryId(id)))
    }
}