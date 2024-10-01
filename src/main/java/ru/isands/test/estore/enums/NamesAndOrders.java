package ru.isands.test.estore.enums;

public enum NamesAndOrders {
    ELECTRO_TYPE("ElectroType.csv"), POSITION_TYPE("PositionType.csv"), PURCHASE_TYPE("PurchaseType.csv"), SHOP("Shop.csv"),

    ELECTRO_ITEM("ElectroItem.csv"), EMPLOYEE("Employee.csv"),

    ELECTRO_EMPLOYEE("ElectroEmployee.csv"), ELECTRO_SHOP("ElectroShop.csv"), PURCHASE("Purchase.csv");

    private final String text;

    NamesAndOrders(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
