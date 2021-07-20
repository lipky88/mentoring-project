package ru.lmru.mentoring.education.equalshashcode;

import java.util.Objects;
import lombok.SneakyThrows;
import ru.lmru.mentoring.education.equalshashcode.data.TestEntry;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class HashCodeChainingCheckClassAntonVersion {
  private Integer capacity;
  private List<List<TestEntry<String, String>>> data;
  private Integer usedSlots = 0;
  private static final Integer USED_SLOTS_THRESHOLD_PERCENT = 60;

  public HashCodeChainingCheckClassAntonVersion() {
    this.capacity = 20;
    data = new ArrayList<>(this.capacity);
    init();
  }

  public HashCodeChainingCheckClassAntonVersion(int capacity) {
    this.capacity = capacity;
    data = new ArrayList<>(this.capacity);
    init();
  }

  public void init() {
    for (int i = 0; i < capacity; i++) {
      data.add(i, null);
    }
  }

  public TestEntry put(String key, String value) {
    return saveToIndex(getIndex(key),
                       new TestEntry(key, value));
  }

  public String get(String key) {
    return getByIndex(getIndex(key), key);
  }

  @SneakyThrows
  private int getIndex(String key) {
    return key.hashCode() % capacity;
  }

  private TestEntry saveToIndex(int index, TestEntry entry) {
    tryToExtend();
    if (data.get(index) == null) {
      data.remove(index);
      data.add(index, new ArrayList<>(List.<TestEntry<String, String>>of(entry)));
      usedSlots++;
    } else {
      data.get(index).add(entry);
    }
    return entry;
  }

  private String getByIndex(int index, String key) {
    List<TestEntry<String, String>> indexEntries = data.get(index);
    for (TestEntry<String, String> indexEntry : indexEntries) {
      if (indexEntry.getKey().equals(key)) {
        return indexEntry.getValue();
      }
    }
    System.out.printf("sorry, can't find your element with key:%s\n", key);
    return "";
  }

  private void tryToExtend() {
    if (usedSlots * 100 / capacity >= USED_SLOTS_THRESHOLD_PERCENT) {
      System.out.println("===========EXTENSION===========");
      int newCapacity = capacity << 1;
      var newMap = new HashCodeChainingCheckClassAntonVersion(newCapacity);
      data.stream()
          .filter(Objects::nonNull)
          .forEach(slot -> slot
              .forEach(entry -> newMap.put(entry.getKey(), entry.getValue())));
      data = newMap.data;
      capacity = newMap.capacity;
      usedSlots = newMap.usedSlots;
    }
  }


  public static void main(String[] args) {
    final int N = 1000;
    HashCodeChainingCheckClassAntonVersion test = new HashCodeChainingCheckClassAntonVersion();
    System.out.println("=======PUT=======");
    for (int i = 1; i <= N; i++) {
      String number = Integer.toString(i);
      test.put(number, format("value: %s", number));
    }
    System.out.println("=======GET=======");
    for (int i = 1; i <= N; i++) {
      String number = Integer.toString(i);
      test.get(number);
    }
  }
}
