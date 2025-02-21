package com.paulomlr.carteira_digital.core.ports;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WalletCodeUtils {

    private static final SecureRandom RANDOM = new SecureRandom();

    private WalletCodeUtils() {
    }

    public static String generateCode() {
        return IntStream.range(0, 4) // 4 groups
                .mapToObj(i -> String.format("%04d", RANDOM.nextInt(10000))) // Generates a 4-digit number
                .collect(Collectors.joining(""));
    }

    public static String formatCode(String rawCode) {
        return rawCode.replaceAll("(.{4})", "$1-").replaceAll("-$", "");
    }

    public static String removeFormatting(String code){
        return code.replace("-", "");
    }
}
