package data;

public enum MainMenuData {

    ADD,
    EDIT,
    LIST,
    DELETE,
    EXIT;

    public static MainMenuData allOptions(String string) {
        return switch (string.toLowerCase().trim()) {
            case "add", "создать", "добавить", "сотворить чудо", "1" -> ADD;
            case "edit", "редактировать", "изменить", "2" -> EDIT;
            case "list", "список", "показать", "3" -> LIST;
            case "delete", "удалить", "убрать", "4" -> DELETE;
            case "exit", "выход", "изыди", "5" -> EXIT;
            default -> throw new IllegalStateException();
        };
    }
}
