package data;

public enum MainMenuData {
//    ADD("add"), LIST("list"), EXIT("изыди");
//    private MenuChoiceEnum(String menuOption) {
//        this.menuOption = menuOption;
//    }
//    public String getMenuOption() {
//        return menuOption;
//    }
//    private final String menuOption;

    ADD,
    LIST,
    EXIT;

    public static MainMenuData allOptions(String string) {
        return switch (string.toLowerCase().trim()) {
            case "add", "создать", "добавить", "сотворить чудо", "1" -> MainMenuData.ADD;
            case "list", "список", "показать", "2" -> MainMenuData.LIST;
            case "exit", "выход", "изыди", "3" -> MainMenuData.EXIT;
            default -> throw new IllegalStateException("Unexpected value: " + string.toLowerCase().trim());
        };
    }
}
