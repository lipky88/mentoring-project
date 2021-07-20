package ru.lmru.mentoring.education.equalshashcode;

import lombok.SneakyThrows;
import ru.lmru.mentoring.education.equalshashcode.data.TestEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HashCodeProbeCheckClass {
    private final Integer probe = 1;
    private Integer capacity = 10000;
    private List<TestEntry<Object, String>> data = new ArrayList<>(capacity);
    private Integer usedSlots = 0;
    private static final Integer USED_SLOTS_THRESHOLD_PERCENT = 60;

    public HashCodeProbeCheckClass() {
        init(data, capacity);
    }

    private void init(List<TestEntry<Object, String>> data, Integer capacity) {
        for (int i = 0; i < capacity; i++) {
            data.add(i, null);
        }
    }

    private void tryToExtend() {
        if (usedSlots * 100 / capacity >= USED_SLOTS_THRESHOLD_PERCENT) {
            System.out.println("===========EXTENSION===========");
            int newCapacity = capacity << 1;
            List<TestEntry<Object, String>> newData = new ArrayList<>(newCapacity);
            init(newData, newCapacity);
            data.stream().filter(Objects::nonNull)
                .forEach(slot -> {
                    put(newData, newCapacity, slot.getKey(), slot.getValue());
                });
            data = newData;
            capacity = newCapacity;
        }
    }

    public TestEntry put(Object key, String value) {
        usedSlots += 1;
        tryToExtend();
        TestEntry entry = new TestEntry(key, value);
        int index = getIndex(key);
        saveToIndex(index, entry);
        return entry;
    }

    public TestEntry put(List<TestEntry<Object, String>> data, Integer capacity, Object key, String value) {
        TestEntry<Object, String> entry = new TestEntry(key, value);
        int index = getIndex(capacity, key);
        saveToIndex(data, capacity, index, entry);
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
            data.set(index, entry);
            return;
        }
        while (index + probe < capacity) {
            if (data.get(index) == null) {
                data.set(index, entry);
                return;
            }
            if (data.get(index).getKey().equals(entry.getKey())) {
                data.set(index, entry);
                return;
            }
            index += probe;
        }
    }

    private void saveToIndex(List<TestEntry<Object, String>> data, Integer capacity, int index, TestEntry entry) {
        if (data.get(index) == null) {
            data.set(index, entry);
            return;
        }
        while (index + probe < capacity) {
            if (data.get(index) == null) {
                data.set(index, entry);
                return;
            }
            if (data.get(index).getKey().equals(entry.getKey())) {
                data.set(index, entry);
                return;
            }
            index += probe;
        }
    }

//    public String getValue(String key) {
//        long startTime = System.nanoTime();
//
//        int index = getIndex(key);
//        String result = getByIndex(index, key);
//
//        long endTime = System.nanoTime();
//        long duration = endTime - startTime;
//        System.out.printf("duration: %s%n", duration);
//        return result;
//    }
//
//    private String getByIndex(int index, String key) {
//        for (int i = index; i < capacity; i += probe) {
//            if (key.equals(data.get(i).getKey())) {
//                if (i != index) {
////                    System.out.printf("collision get key: %s\n", key);
//                }
//                return data.get(i).getValue();
//            }
//        }
////        System.out.printf("sorry, can't find your element with key:%s\n", key);
//        return "";
//    }
//
//    private static int getCapacity(List<?> list) throws Exception {
//        Field dataField = ArrayList.class.getDeclaredField("elementData");
//        dataField.setAccessible(true);
//        return ((Object[]) dataField.get(list)).length;
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
        HashCodeProbeCheckClass test = new HashCodeProbeCheckClass();
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
