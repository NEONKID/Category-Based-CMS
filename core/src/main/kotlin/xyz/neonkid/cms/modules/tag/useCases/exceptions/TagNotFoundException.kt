package xyz.neonkid.cms.modules.tag.useCases.exceptions

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
class TagNotFoundException(val name: String) : RuntimeException("$name 을 찾을 수 없습니다")