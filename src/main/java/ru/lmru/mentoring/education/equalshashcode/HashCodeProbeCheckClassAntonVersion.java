package ru.lmru.mentoring.education.equalshashcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.SneakyThrows;
import ru.lmru.mentoring.education.equalshashcode.data.TestEntry;

public class HashCodeProbeCheckClassAntonVersion {
  private final Integer probe = 1;
  private Integer capacity;
  private List<TestEntry<Key, String>> data;
  private Integer usedSlots = 0;
  private static final Integer USED_SLOTS_THRESHOLD_PERCENT = 60;

  public HashCodeProbeCheckClassAntonVersion() {
    this.capacity = 20;
    data = new ArrayList<>(this.capacity);
    init();
  }

  public HashCodeProbeCheckClassAntonVersion(int capacity) {
    this.capacity = capacity;
    data = new ArrayList<>(this.capacity);
    init();
  }

  public void init() {
    for (int i = 0; i < capacity; i++) {
      data.add(null);
    }
  }

  public int put(Object key, String value) {
    TestEntry entry = new TestEntry(key, value);
    int index = getIndex(key);
    return saveToIndex(index, entry);
  }

  public String get(String key) {
    return getByIndex(getIndex(key), key);
  }

  @SneakyThrows
  private int getIndex(Object key) {
    return key.hashCode() % capacity;
  }

  private int saveToIndex(int index, TestEntry<Key, String> entry) {
    tryToExtend();
    var i = index;
    var trackToSet = 0;
    usedSlots++;
    do {
      if (data.get(i) == null) {
        data.set(i, entry);
        return trackToSet;
      }
      if (data.get(i).getKey().equals(entry.getKey())) {
        data.set(i, entry);
        return trackToSet;
      }
      trackToSet++;
      i = (i + probe) % capacity;
    } while (i != index);
    throw new RuntimeException("All bucket is full");
  }


  private String getByIndex(int index, String key) {
    for (int i = index; i < capacity; i += probe) {
      if (key.equals(data.get(i).getKey())) {
        return data.get(i).getValue();
      }
    }
    return "";
  }

  private void tryToExtend() {
    if (usedSlots * 100 / capacity >= USED_SLOTS_THRESHOLD_PERCENT) {
      System.out.println("===========EXTENSION===========");
      int newCapacity = capacity << 1;
      var newMap = new HashCodeProbeCheckClassAntonVersion(newCapacity);
      data.stream()
          .filter(Objects::nonNull)
          .forEach(entry -> newMap.put(entry.getKey(), entry.getValue()));
      data = newMap.data;
      capacity = newMap.capacity;
      usedSlots = newMap.usedSlots;
    }
  }


  private static class Key {
    private final String a;

    Key(int i) {
      this.a = "kjdfadashdfpiashudlehrpiuqeriuvaspofnmpewoijf[qowmefoiwqjefqoijf;oiajsd;fkas;dofiuqewojfq;" +
               "wijef;oiwqjef;oqij" +
               "lkjasdfkjsad;fajs;dijfa;sidfjaskdhfliueqhrgiuqheworhf.kjqhfdapiushdfpaiushd" +
               ".kjfqghrphqpireuhgqluehf.kjshfdaiushdpf" +
               ";lksjdfpoijeqheoifqjwpeifhsdjf.akhdsfouahsdviuahrg;" +
               "hqeruhglqehrgkjqhdfiuashdifuahsdkjfahdslfhasldfhaslkjdfhalsiudh" + i;
    }

    @Override
    public String toString() {
      return a;
    }
  }

  static void printTime(long startTime, long count, int trackToSetSum) {
    long endTime = System.nanoTime();
    long duration = endTime - startTime;
    System.out.printf("%.6f%n", duration / count / 1000000.0);
//    System.out.printf("%.6f avg track to set %d %n", duration / count / 1000000.0, trackToSetSum / count);
  }

  public static void main(String[] args) {
    final int N = 1000;
    HashCodeProbeCheckClassAntonVersion test = new HashCodeProbeCheckClassAntonVersion();

    System.out.println("=======PUT=======");
    long startTime = System.nanoTime();
    var percentStep = 0.01;
    var capacityStep = Math.ceil(N * percentStep);
    var curPercent = percentStep;
    var trackToSetSum = 0;

    for (int i = 1; i <= N; i++) {
      trackToSetSum += test.put(new Key(i), "d;alksjdf;aksjdnkjcankj.nweifjqnwekjfnaldskjfnas,"
                                            +
                                            "mdfnaskjdnflaskjdnfkjsadnfas,mdnclajd"
                                            +
                                            "as;kdjfoqjewqkfu;ewifjpqoiwjef;ijoidsajf;aoijdskcah.kjhreviuqh;icas.,d;"
                                            + "fasdjf;aisjdfoi"
                                            +
                                            ";lajsdoifjapoiejrclkdjaoijdsfoiajsdlkfha;ewoihfoiqhew;oifhq;oiewhf;oihd;"
                                            + "isahv.kkhdviua"
                                            +
                                            "askdjfoaijsvoiaelkjas;doijf[aoijewflkjwe;fkhdsa;"
                                            + "hfakjdshfliuewhfianhdsfkjasnhdlfiuansd");
      if (i % capacityStep == 0) {
        System.out.print((int) (curPercent * 100) + "% ");
        printTime(startTime, (int) (N * percentStep), trackToSetSum);
        startTime = System.nanoTime();
        curPercent += percentStep;
      }
    }
  }

}