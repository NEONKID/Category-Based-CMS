package xyz.neonkid.cms.manager.core.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import xyz.neonkid.cms.manager.core.utils.exceptions.JwtExpiredException
import xyz.neonkid.cms.manager.core.utils.exceptions.JwtSignatureException
import xyz.neonkid.cms.manager.core.utils.exceptions.JwtTokenDecodeException
import java.util.Date

/**
 * Created by Neon K.I.D on 2/19/22
 * Blog : https://blog.neonkid.xyz
 * GitHub : https://github.com/NEONKID
 */
@Component
class JwtTokenUtils {
    @Value("\${spring.jwt.algorithm}") private val algorithm = "HS256"
    @Value("\${spring.jwt.secret}") private val secret = "secret-key"

    fun encode(payload: Map<String, Any>, seconds: Int) = Jwts.builder()
        .setHeader(makeHeader())
        .setClaims(convertMapToClaims(payload))
        .setIssuedAt(Date())
        .setExpiration(makeExpiration(seconds))
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact()

    fun encode(payload: Map<String, Any>) = Jwts.builder()
        .setHeader(makeHeader())
        .setClaims(convertMapToClaims(payload))
        .setIssuedAt(Date())
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact()

    fun decode(token: String): Claims {
        verify(token)
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
    }

    fun verify(token: String) {
        try {
            Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).body
        } catch (ex: SignatureException) {
            throw JwtSignatureException()
        } catch (ex: ExpiredJwtException) {
            throw JwtExpiredException()
        } catch (ex: JwtException) {
            throw JwtTokenDecodeException()
        }
    }

    fun getPayload(token: String): JwtTokenPayload {
        val claims = decode(token)
        val userId = claims[JwtTokenPayloadKeySet.USER_ID.toString()].toString().toInt()
        return JwtTokenPayload(userId.toLong(), false)
    }

    private fun makeHeader() = mapOf(
        "typ" to "JWT",
        "alg" to algorithm
    )

    private fun makeExpiration(seconds: Int) = Date(System.currentTimeMillis() + seconds * 1000L)

    private fun convertMapToClaims(payload: Map<String, Any>) = payload.map {
        it.key to it.value
    }.toMap()
}

data class JwtTokenPayload(
    val userId: Long,
    val refresh: Boolean
)

enum class JwtTokenPayloadKeySet {
    USER_ID, REFRESH;

    override fun toString() = name.lowercase()
}