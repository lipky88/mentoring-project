package ru.lmru.mentoring.education.equalshashcode.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestEntry<K,V> {
    private K key;
    private V value;
}
