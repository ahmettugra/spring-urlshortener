package common.libs.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

public final class Randomizer {

    private static Random theRandomInstance = null;
    private static SecureRandom theSecureRandomInstance = null;
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static Random getRandomInstance() {
        if (theRandomInstance == null) {
            theRandomInstance = new Random();
        }
        return theRandomInstance;
    }

    private static SecureRandom getSecureRandomInstance() {
        if (theSecureRandomInstance == null) {
            theSecureRandomInstance = new SecureRandom();
        }
        return theSecureRandomInstance;
    }

    public static boolean nextBoolean() {
        return getRandomInstance().nextBoolean();
    }

    public static boolean nextBoolean(double probability) {
        return nextDouble() < probability;
    }

    public static int nextInt() {
        return getRandomInstance().nextInt();
    }

    public static int nextInt(int n) {
        return getRandomInstance().nextInt(n);
    }

    /* Return a nubmer between min and max, inclusive. */
    public static int nextInt(int min, int max) {
        return min + getRandomInstance().nextInt(max - min + 1);
    }

    public static double nextDouble() {
        return getRandomInstance().nextDouble();
    }

    public static double nextDouble(double min, double max) {
        return min + (max - min) * getRandomInstance().nextDouble();
    }

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(getSecureRandomInstance().nextInt(AB.length())));
        return sb.toString();
    }

    public static String nextSessionId() {
        return new BigInteger(130, getSecureRandomInstance()).toString(32);
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
