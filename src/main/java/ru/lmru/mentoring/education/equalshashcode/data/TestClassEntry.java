package ru.lmru.mentoring.education.equalshashcode.data;

import java.io.Serializable;

public class TestClassEntry  implements Serializable {
    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;
    private String value6;
    private String value7;
    private String value8;
    private String value9;
    private String value10;
    private String value11;
    private String value12;
    private String value13;
    private String value14;
    private String value15;
    private String value16;
    private String value17;
    private String value18;
    private String value19;
    private String value20;

    public TestClassEntry(String value) {
        this.value1 = value;
        this.value2 = value;
        this.value3 = value;
        this.value4 = value;
        this.value5 = value;
        this.value6 = value;
        this.value7 = value;
        this.value8 = value;
        this.value9 = value;
        this.value10 = value;
        this.value11 = value;
        this.value12 = value;
        this.value13 = value;
        this.value14 = value;
        this.value15 = value;
        this.value16 = value;
        this.value17 = value;
        this.value18 = value;
        this.value19 = value;
        this.value20 = value;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestClassEntry)) return false;

        TestClassEntry that = (TestClassEntry) o;

        if (value1 != null ? !value1.equals(that.value1) : that.value1 != null) return false;
        if (value2 != null ? !value2.equals(that.value2) : that.value2 != null) return false;
        if (value3 != null ? !value3.equals(that.value3) : that.value3 != null) return false;
        if (value4 != null ? !value4.equals(that.value4) : that.value4 != null) return false;
        if (value4 != null ? !value4.equals(that.value4) : that.value4 != null) return false;
        if (value5 != null ? !value5.equals(that.value5) : that.value5 != null) return false;
        if (value6 != null ? !value6.equals(that.value6) : that.value6 != null) return false;
        if (value7 != null ? !value7.equals(that.value7) : that.value7 != null) return false;
        if (value8 != null ? !value8.equals(that.value8) : that.value8 != null) return false;
        if (value9 != null ? !value9.equals(that.value9) : that.value9 != null) return false;
        if (value10 != null ? !value10.equals(that.value10) : that.value10 != null) return false;
        if (value11 != null ? !value11.equals(that.value11) : that.value11 != null) return false;
        if (value12 != null ? !value12.equals(that.value12) : that.value12 != null) return false;
        if (value13 != null ? !value13.equals(that.value13) : that.value13 != null) return false;
        if (value14 != null ? !value14.equals(that.value14) : that.value14 != null) return false;
        if (value15 != null ? !value15.equals(that.value15) : that.value15 != null) return false;
        if (value16 != null ? !value16.equals(that.value16) : that.value16 != null) return false;
        if (value17 != null ? !value17.equals(that.value17) : that.value17 != null) return false;
        if (value18 != null ? !value18.equals(that.value18) : that.value18 != null) return false;
        if (value19 != null ? !value19.equals(that.value19) : that.value19 != null) return false;
        return value20 != null ? value20.equals(that.value20) : that.value20 == null;
    }

    @Override
    public int hashCode() {
        int result = value1 != null ? value1.hashCode() : 0;
        result = 31 * result + (value2 != null ? value2.hashCode() : 0);
        result = 31 * result + (value3 != null ? value3.hashCode() : 0);
        result = 31 * result + (value4 != null ? value4.hashCode() : 0);
        result = 31 * result + (value4 != null ? value4.hashCode() : 0);
        result = 31 * result + (value5 != null ? value5.hashCode() : 0);
        result = 31 * result + (value6 != null ? value6.hashCode() : 0);
        result = 31 * result + (value7 != null ? value7.hashCode() : 0);
        result = 31 * result + (value8 != null ? value8.hashCode() : 0);
        result = 31 * result + (value9 != null ? value9.hashCode() : 0);
        result = 31 * result + (value10 != null ? value10.hashCode() : 0);
        result = 31 * result + (value11 != null ? value11.hashCode() : 0);
        result = 31 * result + (value12 != null ? value12.hashCode() : 0);
        result = 31 * result + (value13 != null ? value13.hashCode() : 0);
        result = 31 * result + (value14 != null ? value14.hashCode() : 0);
        result = 31 * result + (value15 != null ? value15.hashCode() : 0);
        result = 31 * result + (value16 != null ? value16.hashCode() : 0);
        result = 31 * result + (value17 != null ? value17.hashCode() : 0);
        result = 31 * result + (value18 != null ? value18.hashCode() : 0);
        result = 31 * result + (value19 != null ? value19.hashCode() : 0);
        result = 31 * result + (value20 != null ? value20.hashCode() : 0);
        return result;
    }
}
