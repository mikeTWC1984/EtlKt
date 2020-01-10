package secret

import org.bouncycastle.*
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.security.Security
import java.util.*
import javax.crypto.*
import javax.crypto.spec.SecretKeySpec

fun encrypt(strToEncrypt: String, secret_key: String): String? {
    Security.addProvider(BouncyCastleProvider())
    var keyBytes: ByteArray

    try {
        keyBytes = secret_key.toByteArray(charset("UTF8"))
        val skey = SecretKeySpec(keyBytes, "AES")
        val input = strToEncrypt.toByteArray(charset("UTF8"))

        synchronized(Cipher::class.java) {
            val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
            cipher.init(Cipher.ENCRYPT_MODE, skey)

            val cipherText = ByteArray(cipher.getOutputSize(input.size))
            var ctLength = cipher.update(
                input, 0, input.size,
                cipherText, 0
            )
            ctLength += cipher.doFinal(cipherText, ctLength)
            return String(
                Base64.getEncoder().encode(cipherText)
            )
        }
    } catch (uee: UnsupportedEncodingException) {
        uee.printStackTrace()
    } catch (ibse: IllegalBlockSizeException) {
        ibse.printStackTrace()
    } catch (bpe: BadPaddingException) {
        bpe.printStackTrace()
    } catch (ike: InvalidKeyException) {
        ike.printStackTrace()
    } catch (nspe: NoSuchPaddingException) {
        nspe.printStackTrace()
    } catch (nsae: NoSuchAlgorithmException) {
        nsae.printStackTrace()
    } catch (e: ShortBufferException) {
        e.printStackTrace()
    }

    return null
}