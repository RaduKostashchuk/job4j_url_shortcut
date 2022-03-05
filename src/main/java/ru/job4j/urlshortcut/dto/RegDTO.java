package ru.job4j.urlshortcut.dto;

public class RegDTO {
    private boolean registration;
    private String login;
    private String password;

    public static RegDTO of(boolean registration, String login, String password) {
        RegDTO dto = new RegDTO();
        dto.registration = registration;
        dto.login = login;
        dto.password = password;
        return dto;
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
