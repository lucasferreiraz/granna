package io.lucasprojects.granna.domain.Enum;

public enum TitleType {
    RECEIVABLE("Receivable"),
    PAYABLE("Payable");

    private String value;

    private TitleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
