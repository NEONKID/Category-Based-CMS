package xyz.neonkid.cms.modules.tag.useCases.queries.dto

data class PublicSingleTagDTO (
    val name: String,
    val recentlyPublished: Boolean,
    val publicPostCount: Int
)