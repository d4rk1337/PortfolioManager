package net.d4rk.portfoliomanager.util.security

import org.apache.commons.codec.binary.Hex
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class SignatureGenerator {

    companion object {

        /**
         * Create a signature for Binance API calls with data string and API key
         */
        fun createSignature(key: String, data: String): String {
            val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA256")

            val hmacSha256 = Mac.getInstance("HmacSHA256")
            hmacSha256.init(secretKey)

            return Hex.encodeHexString(hmacSha256.doFinal(data.toByteArray()))
        }
    }

}