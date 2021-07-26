package ru.lmru.mentoring.education.referencevalue;

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

    public Object getObjectFromBytes(int size,
                                     byte arg0,
                                     byte arg1,
                                     byte arg2,
                                     byte arg3,
                                     byte arg4,
                                     byte arg5,
                                     byte arg6,
                                     byte arg7,
                                     byte arg8,
                                     byte arg9,
                                     byte arg10,
                                     byte arg11,
                                     byte arg12,
                                     byte arg13,
                                     byte arg14,
                                     byte arg15,
                                     byte arg16,
                                     byte arg17,
                                     byte arg18,
                                     byte arg19) {
        if (size > MAX_SIZE) {
            throw new RuntimeException("Your object is very big in bytes");
        }
        byte[] objectBytes = new byte[size];
        objectBytes[0] = arg0;
        objectBytes[1] = arg1;
        objectBytes[2] = arg2;
        objectBytes[3] = arg3;
        objectBytes[4] = arg4;
        objectBytes[5] = arg5;
        objectBytes[6] = arg6;
        objectBytes[7] = arg7;
        objectBytes[8] = arg8;
        objectBytes[9] = arg9;
        objectBytes[10] = arg10;
        objectBytes[11] = arg11;
        objectBytes[12] = arg12;
        objectBytes[13] = arg13;
        objectBytes[14] = arg14;
        objectBytes[15] = arg15;
        objectBytes[16] = arg16;
        objectBytes[17] = arg17;
        objectBytes[18] = arg18;
        objectBytes[19] = arg19;

        return getOjectFromBytesArray(objectBytes);
    }

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
            System.out.printf("object: %s, class: %s\n", testObject, testObject.getClass());
        });

    }
}
