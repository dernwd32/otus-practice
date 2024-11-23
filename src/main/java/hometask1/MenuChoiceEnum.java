package hometask1;

public enum MenuChoiceEnum {
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

    public static MenuChoiceEnum allOptions(String string) {
        return switch (string.toLowerCase().trim()) {
            case "add", "создать", "добавить", "сотворить чудо", "1" -> MenuChoiceEnum.ADD;
            case "list", "список", "показать", "2" -> MenuChoiceEnum.LIST;
            case "exit", "выход", "изыди", "3" -> MenuChoiceEnum.EXIT;
            default -> null;
        };
    }
}
