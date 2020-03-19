package top.jjmaps.utils;

import java.security.MessageDigest;

public class MD5Utils {

    private static final String ENCRYPTION_SOURCE = "365KKKKK^&*$$#@FFF996";

    public static String buildMD5Code(String sourceMessage) {
        StringBuilder result = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] tmpsource = sourceMessage.getBytes();
            md.update(tmpsource);
            tmpsource = md.digest();
            for (byte aTmpsource : tmpsource) {
                String tmp = Integer.toHexString(aTmpsource & 0xFF);
                if (tmp.length() == 1)
                    result.append("0").append(tmp);
                else
                    result.append(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String getEncryption() {
        return buildMD5Code(ENCRYPTION_SOURCE);
    }

    public static void main(String[] args) {
        System.out.println(MD5Utils.buildMD5Code("timg (1)"));
    }
}
