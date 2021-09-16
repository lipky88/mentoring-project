package ru.lmru.mentoring.education.temp;

public class Polindrom {
    public boolean isPolindrom(String word) {
        int i = 0;
        int j = word.length()-1;
        while (i<j) {
            if(word.charAt(i)!=word.charAt(j)) {
                return false;
            }
            i += 1;
            j -= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        String test1 = "abcfba";
        String test2 = "abcdcba";

        Polindrom polindrom = new Polindrom();

        System.out.println(polindrom.isPolindrom(test1));
        System.out.println(polindrom.isPolindrom(test2));

    }
}
