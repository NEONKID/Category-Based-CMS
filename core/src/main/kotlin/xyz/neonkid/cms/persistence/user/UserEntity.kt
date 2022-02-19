package xyz.neonkid.cms.persistence.user

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * Github : https://github.com/NEONKID
 */
@Table("users")
data class UserEntity (
    @Id var id: Long,
    val email: String,
    val nickname: String
) {
    @CreatedDate @Column("created_at") private lateinit var createdAt: LocalDateTime
    @LastModifiedDate @Column("updated_at") private lateinit var updatedAt: LocalDateTime
}
