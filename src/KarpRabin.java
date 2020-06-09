public class KarpRabin {
    public int strStr(String haystack, String needle) {

        if (haystack == null || needle == null) return 0;
        if (haystack.length() == 1 || needle.length() == 1) return 0;
        long mod = (long) Math.pow(2,31);
        long hash_ref = 0;
        long hash_curr = 0;
        int i;
        for (i = 0; i < needle.length(); i++) {
            hash_ref = ((hash_ref * 26) + charToInt(needle.charAt(i))) % mod;
            hash_curr = ((hash_curr * 26) + charToInt(haystack.charAt(i))) % mod;
        }

        if (hash_ref == hash_curr) return i - needle.length();

        long deletePower = 1;
        for (int j = 1; j <= needle.length(); j++) {
            deletePower *= 26;
        }

        for (i = 1; i < haystack.length() - needle.length() + 1; i++) {
            char removeChar = haystack.charAt(i - 1);
            int convertedInt = charToInt(removeChar);
            long deleteAmt = convertedInt * deletePower;
            int newAddition = charToInt(haystack.charAt(i + needle.length() - 1));

            hash_curr = hash_curr * 26;
            hash_curr = ((hash_curr - deleteAmt) + newAddition) % mod;

            if (hash_curr == hash_ref) return i;
        }

        return -1;
    }

    public int charToInt(char c) {
        return (int) c - (int) 'a';
    }
}
