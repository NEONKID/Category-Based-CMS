package xyz.neonkid.cms.persistence.tag

import org.springframework.data.repository.CrudRepository
import xyz.neonkid.cms.core.jdbc.WithInsertAndUpdate

/**
 * Created by Neon K.I.D on 1/5/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface TagRepository : CrudRepository<TagEntity, String>, WithInsertAndUpdate<TagEntity>