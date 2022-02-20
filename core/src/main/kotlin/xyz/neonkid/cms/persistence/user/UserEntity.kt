package xyz.neonkid.cms.persistence.user

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.conversion.MutableAggregateChange
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.relational.core.mapping.event.BeforeDeleteCallback
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback
import xyz.neonkid.cms.snowflake.IdGenerator
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
    val nickname: String,
    val password: String,
    @CreatedDate @Column("created_at") var createdAt: LocalDateTime?
) {
    @LastModifiedDate @Column("updated_at") var updatedAt: LocalDateTime = LocalDateTime.MIN
    private set

    var deletedAt: LocalDateTime? = null
}

class BeforeSaveUserCallback: BeforeSaveCallback<UserEntity> {
    override fun onBeforeSave(aggregate: UserEntity, aggregateChange: MutableAggregateChange<UserEntity>): UserEntity {
        if (aggregate.id == 0L)
            aggregate.id = IdGenerator.nextId()

        return aggregate
    }
}