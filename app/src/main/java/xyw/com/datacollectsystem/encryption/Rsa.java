package xyw.com.datacollectsystem.encryption;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;

import javax.crypto.Cipher;

/**
 * Created by 31429 on 2017/10/12.
 */

public class Rsa {
    public static PrivateKey getPrivateKeySame() throws Exception {
        byte[] ary_m = new byte[]{(byte) 203, 85, 89, 65, 119, 86, 86, 52, 7,
                39, (byte) 158, 94, (byte) 174, 86, (byte) 197, 31, 53, 76,
                (byte) 135, 126, 17, (byte) 140, (byte) 209, (byte) 132, 99,
                (byte) 173, (byte) 199, (byte) 173, 28, 119, (byte) 212,
                (byte) 239, (byte) 244, (byte) 252, 7, 122, 100, 62,
                (byte) 219, 93, 80, 29, (byte) 149, 14, (byte) 148, (byte) 209,
                127, (byte) 143, 5, 70, 30, (byte) 253, (byte) 148, (byte) 191,
                67, (byte) 137, 13, 62, (byte) 187, 99, 115, 36, (byte) 253,
                28, 101, 92, 93, 67, (byte) 249, 118, (byte) 146, 32, 48, 72,
                74, (byte) 192, 115, 68, 105, (byte) 128, (byte) 207, 41, 87,
                10, 52, (byte) 255, (byte) 192, 116, (byte) 138, (byte) 133,
                (byte) 245, (byte) 233, (byte) 212, (byte) 159, 88, (byte) 167,
                75, 83, (byte) 129, (byte) 173, 65, 18, (byte) 128, 57, 0, 120,
                52, (byte) 135, (byte) 197, (byte) 205, 127, (byte) 254, 15,
                (byte) 169, 6, (byte) 200, 58, (byte) 153, (byte) 218, 110, 57,
                (byte) 245, 81, (byte) 183, (byte) 215, (byte) 174, 73, 65};
        byte[] ary_private = new byte[]{(byte) 132, 92, (byte) 172,
                (byte) 219, (byte) 166, (byte) 249, (byte) 182, (byte) 231,
                (byte) 235, 120, 104, (byte) 233, (byte) 173, (byte) 135,
                (byte) 162, (byte) 142, 108, 61, (byte) 195, 61, (byte) 215,
                (byte) 226, (byte) 217, 72, (byte) 219, 50, 118, (byte) 247,
                67, (byte) 190, (byte) 230, 8, (byte) 198, 107, (byte) 129,
                (byte) 218, (byte) 180, (byte) 131, 14, 125, (byte) 177,
                (byte) 247, 111, (byte) 222, (byte) 172, 21, (byte) 172, 121,
                (byte) 199, (byte) 175, 27, (byte) 225, 107, 1, 14, (byte) 207,
                (byte) 154, (byte) 165, (byte) 169, (byte) 138, 85, (byte) 156,
                80, 127, 15, 15, 98, 3, 124, (byte) 180, (byte) 143,
                (byte) 206, (byte) 142, 108, (byte) 155, (byte) 236,
                (byte) 186, 66, (byte) 135, (byte) 161, (byte) 155, 14,
                (byte) 238, 57, (byte) 229, (byte) 254, (byte) 249, (byte) 221,
                40, (byte) 212, (byte) 230, (byte) 162, 60, (byte) 241, 53,
                (byte) 153, 103, (byte) 252, 115, 69, (byte) 155, 20,
                (byte) 130, (byte) 133, 97, 30, 102, 69, 86, (byte) 252,
                (byte) 227, (byte) 144, (byte) 160, (byte) 230, (byte) 231,
                (byte) 217, (byte) 233, (byte) 149, 105, 12, (byte) 224,
                (byte) 199, 103, 82, (byte) 225, (byte) 227, 26, 33};
        BigInteger m = new BigInteger(1, ary_m);

        BigInteger e = new BigInteger(1, ary_private);

        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

    public static byte[] getRc2Key() throws Exception {
        int iRsa[] = {1, 20, 248, 108, 132, 40, 115, 11, 68, 66, 58, 171, 43,
                69, 26, 36, 156, 126, 86, 74, 253, 192, 157, 252, 49, 204, 66,
                186, 146, 35, 123, 93, 197, 93, 64, 129, 80, 1, 212, 67, 97,
                10, 114, 183, 218, 251, 175, 96, 228, 17, 228, 30, 13, 62, 133,
                25, 75, 111, 37, 17, 180, 120, 182, 198, 213, 28, 233, 186,
                143, 111, 23, 175, 254, 173, 254, 143, 197, 55, 39, 136, 96,
                140, 91, 21, 37, 221, 50, 72, 28, 206, 19, 219, 185, 129, 44,
                231, 132, 243, 172, 246, 155, 253, 86, 109, 186, 132, 225, 113,
                160, 216, 143, 209, 35, 129, 236, 42, 200, 13, 187, 149, 173,
                253, 108, 68, 210, 58, 74, 173};
        byte bRsa[] = new byte[iRsa.length];
        for (int i = 0; i < iRsa.length; i++) {
            bRsa[i] = (byte) iRsa[i];
        }
        PrivateKey privateKey = getPrivateKeySame();
        Cipher cipher2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher2.init(Cipher.DECRYPT_MODE, privateKey);
        // byte[] deBytes = cipher2.doFinal(bRsa);
        byte[] deBytes = cipher2.doFinal(bRsa);
        return deBytes;
    }
}
