package data;

public enum MainMenuData {

    ADD,
    EDIT,
    LIST,
    EXIT;

    public static MainMenuData allOptions(String string) {
        return switch (string.toLowerCase().trim()) {
            case "add", "создать", "добавить", "сотворить чудо", "1" -> ADD;
            case "edit", "редактировать", "изменить", "2" -> EDIT;
            case "list", "список", "показать", "3" -> LIST;
            case "exit", "выход", "изыди", "4" -> EXIT;
            default -> throw new IllegalStateException();
        };
    }
}
