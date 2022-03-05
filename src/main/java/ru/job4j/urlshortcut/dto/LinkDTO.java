package ru.job4j.urlshortcut.dto;

public class LinkDTO {
    private String code;

    public static LinkDTO of(String code) {
        LinkDTO dto = new LinkDTO();
        dto.setCode(code);
        return dto;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
