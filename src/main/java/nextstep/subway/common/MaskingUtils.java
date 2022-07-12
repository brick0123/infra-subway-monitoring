package nextstep.subway.common;

public abstract class MaskingUtils {

    private static final String MASKING = "******";

    public static String half(final String str) {
        return str.substring(0, str.length() / 2) + MASKING;
    }

    public static String all(final String str) {
        return MASKING;
    }
}
