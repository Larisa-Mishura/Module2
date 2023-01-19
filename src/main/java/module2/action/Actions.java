package module2.action;

import lombok.Getter;

@Getter
public enum Actions {

    READ_FILE ("Read the file and create items.", new ReadFileAction()),
    CREATE_INVOICE("Create invoices.", new CreateInvoiceAction()),

    COUNT_BY_TYPE ("Show total amount for every type.", new CountByTypeAction()), //Кількість проданих товарів за категоріями (Telephone/Television)
    SHOW_MIN_INVOICE("Show customer of the least invoice.", new MinInvoiceAction()),//Суму найменшого чека та інформацію про покупця
    TOTAL_AMOUNT ("Show the total sum of invoices.", new TotulSumAction()),//Суму всіх покупок
    RETAIL_COUNT ("Show the count of retail invoices.",  new RetailCountAction()),//Кількість чеків з категорією retail
    INVOICES_WITH_ONE_TYPE ("Show invoices with one type of items.", new InvoicesWithOneType()),//Чеки, які містять тільки один тип товару
    FIRST_THREE_INVOICES ("Show the first three invoices.", new FirstThreeInvoicesAction()),//Перші три чеки зроблені покупцями
    YOUNG_CUSTOMERS ("Show invoices of customers under 18 years.", new YoungCustomersAction()),//Інформацію по чеках куплених користувачем молодше 18 років, при цьому змінити тип чека на low_age
    SORTIN_INVOICES("Sort invoices by age of customers, then by count of items, then by total sum.", new SortInvoicesAction()),//Написати метод, який сортує (будь-яким відомим способом) усі замовлення в такому порядку:за віком покупця, за кількістю куплених предметів, за загальною сумою куплених предметів

    SHOW_ALL_PRODUCTS("Show all products", new ShowAllAction()),
    SHOW_ALL_INVOICES("Show all invoices", new ShowAllInvoicesAction()),
    EXIT("Finish program", new ExitAction());

    private final String name;
    private final Action action;

    Actions(final String name, final Action action){
        this.name = name;
        this.action = action;
    }

    public void execute() {
        action.execute();
    }

    private static String[] mapActionsToNames(final Actions[] values) {
        String[] names = new String[values.length];
        //System.out.println("What do you want to do?");
        for (int i = 0; i < values.length; i++) {
            //System.out.println(i + "  " + values[i].getName());
            names[i] = values[i].getName();
        }
        return names;
    }
}
