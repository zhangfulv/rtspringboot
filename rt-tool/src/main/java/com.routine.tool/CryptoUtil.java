package com.routine.tool;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;

/**
 * @ClassName CryptoUtil 加解密工具包
 * @DESCRIPTION TODO 依赖  hutool-crypto
 * <dependency>
 *       <groupId>cn.hutool</groupId>
 *       <artifactId>hutool-crypto</artifactId>
 *       <version>5.5.4</version>
 * </dependency>
 * @Author Mr.zf, link:282734967@qq.com
 * @Date 2020/12/17 9:51
 */
public class CryptoUtil {
    private CryptoUtil(){}
    private volatile  static CryptoUtil _INSTANCE = null;
    /**
    * desc: model 和 padding 是选择加密方式，可以不动
    */
    private final String MODEL = "CBC";
    private final String PADDING = "PKCS5Padding";
    /**
    * desc: aes_key 和 iv  是随机生成的。
    */
    private final String AES_KEY = "kyMzk2MjItMWE0NS00MDQ5LWI4NDEtYz";
    private final String IV = "BjNWQ3MGEtMTg0Yi";
    private final AES hutoolAes = new AES(MODEL,PADDING,AES_KEY.getBytes(),IV.getBytes());
    public static CryptoUtil getInstance(){
        CryptoUtil ins = _INSTANCE;
        if(ins == null){
            synchronized (CryptoUtil.class){
                if(ins == null){
                    ins =_INSTANCE = new CryptoUtil();
                }

            }
        }
        return ins;
    }


    /**
     * @Description   aes 加密
     * @param data 加密原数据
     * @return  加密后的数据
     * @Author Mr.zf.link:282734967@qq.com
     * @Date 2020-12-17 10:01
     * @Update
     **/
    public String AesEncryptHex(String data){
        String s = hutoolAes.encryptHex(data);
        return s;
    }
     /**
      * @Description   aes解密
      * @param encryptData 需要解密的数据
      * @return  解密后的数据
      * @Author Mr.zf.link:282734967@qq.com
      * @Date 2020-12-17 10:02
      * @Update
      **/
    public String AesDecrypt(String encryptData){
        String s = hutoolAes.decryptStr(encryptData);
        return s;
    }


    public String Base64Encrypt(String data){
        String s = Base64.encode(data);
        return s;
    }

    public String Base64Decrypt(String encryptData){
        String s = Base64.decodeStr(encryptData);
        return s;
    }
    public String MD5Encrypt(String data){
        return MD5.create().digestHex(data);
    }
    public static void main(String[] args) {
        String aesKey = "kyMzk2MjItMWE0NS00MDQ5LWI4NDEtYz";
        String iv = "BjNWQ3MGEtMTg0Yi";
        AES aes = new AES("CBC","PKCS5Padding",aesKey.getBytes());
        aes.setIv(iv.getBytes());
        String str = "NHml2ITn3wmxYTEQ2dzkYA==";
        byte[] contentDecByBase64 =  contentDecByBase64 = Base64.decode(str);
        String s1 = aes.decryptStr(contentDecByBase64);
        System.err.println(s1);


        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MWYzMDQyNjQ4ZjU0NjVjOTc1ZTg0YjAzYTljMTg3NyJ9.xaQu6c1T9Kdo6usIneu4RMqOwjw3FDeEUTPujc-6ZlU";
        byte[] encrypt = aes.encrypt(jwt.getBytes());
        String s = CryptoUtil.getInstance().AesEncryptHex(jwt);
        String encode = Base64.encode(s);
        System.out.println("encode = [" + encode + "]");
    }
}
