package ru.lmru.mentoring.education.referencevelue;

import lombok.SneakyThrows;
import org.springframework.util.SerializationUtils;
import ru.lmru.mentoring.education.equalshashcode.data.TestClassEntry;

import static java.util.Arrays.asList;


public class PassByValueOfReference {

    public static final int MAX_SIZE = 20;

    public byte[] getObjectBytesArray(Object object) {
        byte[] objectInBytes = SerializationUtils.serialize(object);
        if (objectInBytes.length < MAX_SIZE) {
            byte[] objectWithPaddingBytes = new byte[MAX_SIZE];
            for (int i = 0; i < objectInBytes.length; i++) {
                objectWithPaddingBytes[i] = objectInBytes[i];
            }
            return objectWithPaddingBytes;
        }

        return objectInBytes;
    }

    public Object getOjectFromBytesArray(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }

    public Object getObjectFromBytes(int size, byte... args) {
        if (size > MAX_SIZE) {
            throw new RuntimeException("Your object is very big in bytes");
        }
        byte[] objectBytes = new byte[size];
        System.arraycopy(args, 0, objectBytes, 0, size);
        return getOjectFromBytesArray(objectBytes);
    }

    @SneakyThrows
    public static void main(String[] args) {
        PassByValueOfReference test = new PassByValueOfReference();
        String value1 = "1337";
        TestClassEntry value2 = new TestClassEntry("test");
        asList(value1, value2).forEach(value -> {
            byte[] objectBytes = test.getObjectBytesArray(value);
            Object testObject = test.getObjectFromBytes(objectBytes.length,
                                                        objectBytes[0],
                                                        objectBytes[1],
                                                        objectBytes[2],
                                                        objectBytes[3],
                                                        objectBytes[4],
                                                        objectBytes[5],
                                                        objectBytes[6],
                                                        objectBytes[7],
                                                        objectBytes[8],
                                                        objectBytes[9],
                                                        objectBytes[10],
                                                        objectBytes[11],
                                                        objectBytes[12],
                                                        objectBytes[13],
                                                        objectBytes[14],
                                                        objectBytes[15],
                                                        objectBytes[16],
                                                        objectBytes[17],
                                                        objectBytes[18],
                                                        objectBytes[19]);
            System.out.printf("length: %s, object: %s\n", objectBytes.length, testObject);
        });

    }
}
