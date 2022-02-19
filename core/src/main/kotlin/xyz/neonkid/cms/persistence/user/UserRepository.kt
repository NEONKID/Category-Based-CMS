package xyz.neonkid.cms.persistence.user

import org.springframework.data.repository.CrudRepository
import xyz.neonkid.cms.core.jdbc.WithInsertAndUpdate

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
interface UserRepository : CrudRepository<UserEntity, Long>, WithInsertAndUpdate<UserEntity>