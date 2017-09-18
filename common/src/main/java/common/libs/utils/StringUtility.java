package common.libs.utils;

public final class StringUtility {
    public static String join(String[] str,String del){
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<str.length;i++){
            sb.append(str[i]);
            if(i<str.length-1){ sb.append(del); }
        }
        return sb.toString();
    }

    public static String chomp(String str){
        if(str.endsWith("\r\n")){ return str.substring(0,str.length()-2); }
        if(str.endsWith("\r") || str.endsWith("\n")){ return str.substring(0,str.length()-1); }
        return str;
    }

    /**
     * Return whether the non-null text arg starts with any of the prefix
     * values.
     *
     * @param text
     * @param prefixes
     * @return boolean
     */
    public static boolean startsWithAny(String text, String... prefixes) {

        for (String prefix : prefixes) {
            if (text.startsWith(prefix)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns whether the non-null text arg matches any of the test values.
     *
     * @param text
     * @param tests
     * @return boolean
     */
    public static boolean isAnyOf(String text, String... tests) {

        for (String test : tests) {
            if (text.equals(test)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns true if the value arg is either null, empty, or full of
     * whitespace characters. More efficient that calling
     * (string).trim().length() == 0
     *
     * @param value
     * @return <code>true</code> if the value is empty, <code>false</code>
     *         otherwise.
     */
    public static boolean isEmpty(String value) {

        if (value == null || "".equals(value)) {
            return true;
        }

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     *
     * @param value String
     * @return boolean
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /**
     *
     * @param original String
     * @param oldString String
     * @param newString String
     * @return String
     */
    public static String replaceString(final String original, final String oldString, final String newString) {
        int index = original.indexOf(oldString);
        if (index < 0) {
            return original;
        } else {
            final String replace = newString == null ? "" : newString;
            final StringBuilder buf = new StringBuilder(Math.max(16, original.length() + replace.length()));
            int last = 0;
            while (index != -1) {
                buf.append(original.substring(last, index));
                buf.append(replace);
                last = index + oldString.length();
                index = original.indexOf(oldString, last);
            }
            buf.append(original.substring(last));
            return buf.toString();
        }
    }

    /**
     *
     * @param original String
     * @param oldChar char
     * @param newString String
     * @return String
     */
    public static String replaceString(final String original, char oldChar, final String newString) {
        int index = original.indexOf(oldChar);
        if (index < 0) {
            return original;
        } else {
            final String replace = newString == null ? "" : newString;
            final StringBuilder buf = new StringBuilder(Math.max(16, original.length() + replace.length()));
            int last = 0;
            while (index != -1) {
                buf.append(original.substring(last, index));
                buf.append(replace);
                last = index + 1;
                index = original.indexOf(oldChar, last);
            }
            buf.append(original.substring(last));
            return buf.toString();
        }
    }

    public static String stripHtml(String html){
        if (isEmpty(html)) return html;

        return html.replaceAll("\\<.*?\\>", "");
    }
}
