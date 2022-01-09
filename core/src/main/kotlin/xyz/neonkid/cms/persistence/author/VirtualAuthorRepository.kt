package xyz.neonkid.cms.persistence.author

import org.springframework.data.repository.CrudRepository
import xyz.neonkid.cms.core.jdbc.WithInsertAndUpdate
import java.util.*

/**
 * Created by Neon K.I.D on 1/2/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface VirtualAuthorRepository : CrudRepository<VirtualAuthorEntity, UUID>, WithInsertAndUpdate<VirtualAuthorEntity>