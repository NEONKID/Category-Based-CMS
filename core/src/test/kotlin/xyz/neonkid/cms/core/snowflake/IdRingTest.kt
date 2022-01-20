package xyz.neonkid.cms.core.snowflake

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class IdRingTest {
    @Test
    internal fun `시퀀스가 최댓값에 도달하면 Timestamp는 증가한다`() {
        val snowflake = Snowflake(timeBits = 30, workerBits = 30)
        val epochSecond = snowflake.spec().epochSecond
        val sequenceNumberPerSecond = snowflake.spec().sequenceNumberPerSecond.toInt()
        val idRing = IdRing(workerId = 1, snowflake = snowflake, ringBits = 1, timeGen = { epochSecond })

        // the previous sequence is used to initialize
        // 1 << 1 = 2
        val startSequence = 2

        val ids = sequence {
            while (true) yield(idRing.nextId())
        }.take(sequenceNumberPerSecond + 1 - startSequence).toList()

        ids.dropLast(1).forEach {
            assertThat(snowflake.getDeltaSeconds(it)).isEqualTo(0)
        }
        assertThat(snowflake.getDeltaSeconds(ids.last())).isEqualTo(1)
    }

    @Test
    internal fun `오직 고유한 ID를 생성한다`() {
        val snowflake = Snowflake(timeBits = 30, workerBits = 26)
        val worker = IdRing(1, ringBits = 6, snowflake = snowflake)
        val n = 200000
        val distinct = (1..n).map { worker.nextId() }.distinct()

        assertThat(distinct.size).isEqualTo(n)
        val exception = assertThrows<IllegalArgumentException> {
            IdRing(1, ringBits = 7, snowflake = snowflake)
        }

        assertThat(exception.message).isEqualTo("ringBits[7] can't be greater or equal to timeBits[7]")
    }

    @Test
    internal fun `시간이 거꾸로 간다 하더라도 일관성을 유지해야 한다`() {
        class StaticTimeWorker {
            val snowflake = Snowflake()
            var time = snowflake.spec().epochSecond + 1
            private val worker = IdRing(0, timeGen = { time })
            fun nextId() = worker.nextId()
        }

        // the previous sequence is used to initialize
        // 1 << 9 = 512
        val startSequence = 512L

        val worker = StaticTimeWorker()

        // reported at https://github.com/twitter/snowflake/issues/6
        // first we generate 2 ids with the same time, so that we get the sequence to 1
        val id1 = worker.nextId()
        assertThat(worker.snowflake.getDeltaSeconds(id1)).isEqualTo(1)
        assertThat(worker.snowflake.getSequence(id1)).isEqualTo(startSequence)

        val id2 = worker.nextId()
        assertThat(worker.snowflake.getDeltaSeconds(id2)).isEqualTo(1)
        assertThat(worker.snowflake.getSequence(id2)).isEqualTo(startSequence + 1L)

        // then we set the time backwards
        worker.time = worker.snowflake.spec().epochSecond

        val id3 = worker.nextId()
        assertThat(worker.snowflake.getDeltaSeconds(id3)).isEqualTo(1)
        assertThat(worker.snowflake.getSequence(id3)).isEqualTo(startSequence + 2L)
    }
}