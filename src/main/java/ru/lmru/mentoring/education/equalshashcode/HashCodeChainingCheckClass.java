package ru.lmru.mentoring.education.equalshashcode;

import lombok.SneakyThrows;
import ru.lmru.mentoring.education.equalshashcode.data.TestEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HashCodeChainingCheckClass {
    private Integer capacity = 10000;
    private List<List<TestEntry<Object, String>>> data = new ArrayList<>(capacity);
    private Integer usedSlots = 0;
    private static final Integer USED_SLOTS_THRESHOLD_PERCENT = 60;

    public HashCodeChainingCheckClass() {
        init(data, capacity);
    }

    private void init(List<List<TestEntry<Object, String>>> data, Integer capacity) {
        for (int i = 0; i < capacity; i++) {
            data.add(i, null);
        }
    }

    private void tryToExtend() {
        if (usedSlots * 100 / capacity >= USED_SLOTS_THRESHOLD_PERCENT) {
            System.out.println("===========EXTENSION===========");
            // TODO: зачем используется сдвиг?
            int newCapacity = capacity << 1;
            List<List<TestEntry<Object, String>>> newData = new ArrayList<>(newCapacity);
            init(newData, newCapacity);
            data.stream().filter(Objects::nonNull)
                .forEach(slot -> {
                    slot.forEach(entry -> {
                        put(newData, newCapacity, entry.getKey(), entry.getValue());
                    });
                });
            data = newData;
            capacity = newCapacity;
        }
    }
    // TODO изза того что ты делаешь все в одном обьекте у тебя начали появляться вторые функции для роботы со старым и новым массивом.
    // вот тут как раз имеет смысл уже делать полноценную обьектную модель. те работать только через методы.
    // есть идети как это сделать?
    public TestEntry put(Object key, String value) {
        tryToExtend();
        TestEntry entry = new TestEntry(key, value);
        int index = getIndex(key);
        saveToIndex(index, entry);
        return entry;
    }

    public TestEntry put(List<List<TestEntry<Object, String>>> data, Integer capacity, Object key, String value) {
        TestEntry entry = new TestEntry(key, value);
        int index = getIndex(capacity, key);
        saveToIndex(data, index, entry);
        return entry;
    }

    @SneakyThrows
    private int getIndex(Object key) {
        return key.hashCode() % capacity;
    }

    @SneakyThrows
    private int getIndex(Integer capacity, Object key) {
        return key.hashCode() % capacity;
    }

    private void saveToIndex(int index, TestEntry entry) {
        if (data.get(index) == null) {
            data.set(index, new ArrayList<>(List.<TestEntry<Object, String>>of(entry)));
            usedSlots += 1;
            return;
        }
        if (data.get(index) != null) {
            data.get(index).add(entry);
        }
    }

    private void saveToIndex(List<List<TestEntry<Object, String>>> data, int index, TestEntry entry) {
        if (data.get(index) == null) {
            data.set(index, new ArrayList<>(List.<TestEntry<Object, String>>of(entry)));
            return;
        }
        if (data.get(index) != null) {
            data.get(index).add(entry);
        }
    }

//    public String getValue(String key) {
//        int index = getIndex(key);
//        return getByIndex(index, key);
//    }
//
//    private String getByIndex(int index, String key) {
//        List<TestEntry> indexEntries = data.get(index);
//        for (TestEntry indexEntry : indexEntries) {
//            if (indexEntry.getKey().equals(key)) {
//                return indexEntry.getValue();
//            }
//        }
//        System.out.printf("sorry, can't find your element with key:%s\n", key);
//        return "";
//    }

    static class Key {
        private final String a;

        Key(int i) {
            this.a = "kjdfadashdfpiashudlehrpiuqeriuvaspofnmpewoijf[qowmefoiwqjefqoijf;oiajsd;fkas;dofiuqewojfq;" +
                    "wijef;oiwqjef;oqij" +
                    "lkjasdfkjsad;fajs;dijfa;sidfjaskdhfliueqhrgiuqheworhf.kjqhfdapiushdfpaiushd" +
                    ".kjfqghrphqpireuhgqluehf.kjshfdaiushdpf" +
                    ";lksjdfpoijeqheoifqjwpeifhsdjf.akhdsfouahsdviuahrg;" +
                    "hqeruhglqehrgkjqhdfiuashdifuahsdkjfahdslfhasldfhaslkjdfhalsiudh" + i;
        }
    }

    public static void main(String[] args) {
        HashCodeChainingCheckClass test = new HashCodeChainingCheckClass();
        for (int i = 1; i <= 999; i++) {
            test.put(new Key(i), "value: " + i);
        }
        for (int i = 1; i <= 999; i++) {
            test.put(new Key(i), null);
        }

        System.out.println("=======PUT=======");
        long startTime = System.nanoTime();

        int valuesCount = 50000;
        for (int i = 1; i <= valuesCount; i++) {
            test.put(new EqualsCheckClass.Key(i), "d;alksjdf;aksjdnkjcankj.nweifjqnwekjfnaldskjfnas," +
                    "mdfnaskjdnflaskjdnfkjsadnfas,mdnclajd" +
                    "as;kdjfoqjewqkfu;ewifjpqoiwjef;ijoidsajf;aoijdskcah.kjhreviuqh;icas.,d;fasdjf;aisjdfoi" +
                    ";lajsdoifjapoiejrclkdjaoijdsfoiajsdlkfha;ewoihfoiqhew;oifhq;oiewhf;oihd;isahv.kkhdviua" +
                    "askdjfoaijsvoiaelkjas;doijf[aoijewflkjwe;fkhdsa;hfakjdshfliuewhfianhdsfkjasnhdlfiuansd");
            if (i * 100 % valuesCount == 0) {
//                System.out.println(i*100/capacity");
                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                System.out.printf("%s%n", duration / valuesCount);
            }
        }
    }
}
