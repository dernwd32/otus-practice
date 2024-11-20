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
        switch (string.toLowerCase().trim()) {
            case "add":
            case "создать":
            case "добавить":
            case "1":
                return MenuChoiceEnum.ADD;
            case "list":
            case "список":
            case "показать":
            case "2":
                return MenuChoiceEnum.LIST;
            case "exit":
            case "выход":
            case "изыди":
            case "3":
                return MenuChoiceEnum.EXIT;
            default:
                return null;

        }
    }
}
