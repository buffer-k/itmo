package Models;

/**
 * Перечисляемвй тип транспорта
 */
public enum Transport {
    FEW,
    NONE,
    LITTLE;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var dragonType : values()) {
            nameList.append(dragonType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}