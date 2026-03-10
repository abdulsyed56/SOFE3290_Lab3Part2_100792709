package com.ontariotechu.sofe3980U;

public class Binary {
    private String value;

    public Binary() {
        this.value = "0";
    }

    public Binary(String value) {
        this.value = sanitize(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = sanitize(value);
    }

    private static String sanitize(String input) {
        if (input == null || input.isEmpty()) {
            return "0";
        }

        StringBuilder cleaned = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '0' || c == '1') {
                cleaned.append(c);
            } else {
                cleaned.append('0');
            }
        }

        String result = cleaned.toString().replaceFirst("^0+(?!$)", "");
        return result.isEmpty() ? "0" : result;
    }

    private static String padLeft(String s, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < len; i++) {
            sb.append('0');
        }
        sb.append(s);
        return sb.toString();
    }

    public static Binary add(Binary a, Binary b) {
        String x = a.getValue();
        String y = b.getValue();

        int maxLen = Math.max(x.length(), y.length());
        x = padLeft(x, maxLen);
        y = padLeft(y, maxLen);

        StringBuilder result = new StringBuilder();
        int carry = 0;

        for (int i = maxLen - 1; i >= 0; i--) {
            int bitA = x.charAt(i) - '0';
            int bitB = y.charAt(i) - '0';

            int sum = bitA + bitB + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }

        if (carry == 1) {
            result.append('1');
        }

        return new Binary(result.reverse().toString());
    }

    public static Binary multiply(Binary a, Binary b) {
        String x = a.getValue();
        String y = b.getValue();

        Binary result = new Binary("0");

        int shift = 0;
        for (int i = y.length() - 1; i >= 0; i--) {
            if (y.charAt(i) == '1') {
                StringBuilder partial = new StringBuilder(x);
                for (int j = 0; j < shift; j++) {
                    partial.append('0');
                }
                result = add(result, new Binary(partial.toString()));
            }
            shift++;
        }

        return result;
    }

    public static Binary and(Binary a, Binary b) {
        String x = a.getValue();
        String y = b.getValue();

        int maxLen = Math.max(x.length(), y.length());
        x = padLeft(x, maxLen);
        y = padLeft(y, maxLen);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            result.append((x.charAt(i) == '1' && y.charAt(i) == '1') ? '1' : '0');
        }

        return new Binary(result.toString());
    }

    public static Binary or(Binary a, Binary b) {
        String x = a.getValue();
        String y = b.getValue();

        int maxLen = Math.max(x.length(), y.length());
        x = padLeft(x, maxLen);
        y = padLeft(y, maxLen);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            result.append((x.charAt(i) == '1' || y.charAt(i) == '1') ? '1' : '0');
        }

        return new Binary(result.toString());
    }

    @Override
    public String toString() {
        return value;
    }
}
