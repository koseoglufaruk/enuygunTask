package com.enuygun.enums;

public class MonthConverterEnum {

    // Enum ile ay isimlerini ve sayısal değerlerini tanımlama
    public enum Month {
        OCAK("01"),
        ŞUBAT("02"),
        MART("03"),
        NİSAN("04"),
        MAYIS("05"),
        HAZİRAN("06"),
        TEMMUZ("07"),
        AĞUSTOS("08"),
        EYLÜL("09"),
        EKİM("10"),
        KASIM("11"),
        ARALIK("12");

        private final String number;

        // Constructor
        Month(String number) {
            this.number = number;
        }

        // Verilen ay ismine göre Enum bulma ve sayısal değeri döndürme
        public static String getMonthNumber(String monthName) {
            try {
                return Month.valueOf(monthName.toUpperCase()).getNumber();
            } catch (IllegalArgumentException e) {
                return "Invalid Month";
            }
        }

        // Sayısal değere göre ay ismini döndüren metod
        public static String getMonthName(String monthNumber) {
            for (Month month : Month.values()) {
                if (month.getNumber().equals(monthNumber)) {
                    return month.name().substring(0, 1).toUpperCase() + month.name().substring(1).toLowerCase(); // Ay ismini düzgün formatla
                }
            }
            return "Invalid Month";
        }

        // Sayısal değeri döndüren metod
        public String getNumber() {
            return number;
        }
    }
}
