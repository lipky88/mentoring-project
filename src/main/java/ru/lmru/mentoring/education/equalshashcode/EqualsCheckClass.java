package ru.lmru.mentoring.education.equalshashcode;

import ru.lmru.mentoring.education.equalshashcode.data.TestEntry;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
public class EqualsCheckClass {
    public static final Integer capacity = 1000;
    private List<TestEntry<Object, String>> data = new ArrayList<>(capacity * 10);

    public EqualsCheckClass() {
        for (int i = 0; i < capacity; i++) {
            data.add(i, null);
        }
    }

    public TestEntry put(Object key, String value) {
        TestEntry<Object, String> entry = new TestEntry(key, value);
        for (int i = 0; i < capacity; i++) {
            if (data.get(i) == null) {
                data.set(i, entry);
                break;
            }
            if (data.get(i).getKey().equals(key)) {
                data.set(i, entry);
                break;
            }
        }

        return entry;
    }

    public String getValue(String key) {
        long startTime = System.nanoTime();

        String result = getByKey(key);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.printf("duration: %s%n", duration);
        return result;
    }

    private String getByKey(String key) {
        for (int i = 0; i < capacity; i++) {
            if (data.get(i).getKey().equals(key)) {
                return data.get(i).getValue();
            }
        }
        return format("sorry, can't find your element with key:%s\n", key);
    }

    public static void main(String[] args) {
        EqualsCheckClass test = new EqualsCheckClass();
        for (int i = 1; i <= 999; i++) {
            test.put(new Key(i), "value: "+ i);
        }
        for (int i = 1; i <= 999; i++) {
            test.data.set(i, null);
        }

        System.out.println("=======PUT=======");
        long startTime = System.nanoTime();

        for (int i = 1; i <= capacity; i++) {
            test.put(new Key(i), "d;alksjdf;aksjdnkjcankj.nweifjqnwekjfnaldskjfnas,mdfnaskjdnflaskjdnfkjsadnfas,mdnclajd"+
                    "as;kdjfoqjewqkfu;ewifjpqoiwjef;ijoidsajf;aoijdskcah.kjhreviuqh;icas.,d;fasdjf;aisjdfoi"+
                    ";lajsdoifjapoiejrclkdjaoijdsfoiajsdlkfha;ewoihfoiqhew;oifhq;oiewhf;oihd;isahv.kkhdviua"+
                    "askdjfoaijsvoiaelkjas;doijf[aoijewflkjwe;fkhdsa;hfakjdshfliuewhfianhdsfkjasnhdlfiuansd");
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.printf("%s%n", duration/capacity);
    }

    static class Key {
        private final String a;

        Key(int i) {
            this.a = "kjdfadashdfpiashudlehrpiuqeriuvaspofnmpewoijf[qowmefoiwqjefqoijf;oiajsd;fkas;dofiuqewojfq;wijef;oiwqjef;oqij" +
                    "lkjasdfkjsad;fajs;dijfa;sidfjaskdhfliueqhrgiuqheworhf.kjqhfdapiushdfpaiushd.kjfqghrphqpireuhgqluehf.kjshfdaiushdpf"+
                    ";lksjdfpoijeqheoifqjwpeifhsdjf.akhdsfouahsdviuahrg;hqeruhglqehrgkjqhdfiuashdifuahsdkjfahdslfhasldfhaslkjdfhalsiudh"+ i;
        }
    }
}