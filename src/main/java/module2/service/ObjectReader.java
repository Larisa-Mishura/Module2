package module2.service;

import module2.model.product.*;
import org.apache.commons.lang3.EnumUtils;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class ObjectReader {

    private final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

    private String textFromResourceFile(String filename){
        String result = "";
        try (final InputStream resourceAsStream = contextClassLoader.getResourceAsStream(filename)){
            int i;
            while ((i = resourceAsStream.read()) != -1) {
                result = result + (char) i;
            }
        } catch (Exception e){
            System.out.println("File is not found.");
        }
        return result.replaceAll("(\t)|(\r)|(\s\\B)|(\\B\s)", "");
    }

    private List<String[]> getArrayFields(String text){
        List<String[]> fieldsForObjects =
                Arrays.stream(text.split("\n"))
                .map(s -> s.split(","))
                .collect(Collectors.toCollection(LinkedList::new));
        return fieldsForObjects;
    }

    private Map<String, Integer> getIndexForField(String[] tableHeading){
        final Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < tableHeading.length; i++){
            map.put(tableHeading[i].trim(), i);
        }
        return map;
    }

    private Item create(final String[] fields, Map<String, Integer> keyForField) throws ObjectReaderException{
        Item item = null;
        try {
            final ItemType itemType = EnumUtils.getEnum(ItemType.class, fields[keyForField.get("type")].toUpperCase(Locale.ROOT));
            final String series = fields[keyForField.get("series")];
            final ScreenType screenType = EnumUtils.getEnum(ScreenType.class, fields[keyForField.get("screen type")].toUpperCase(Locale.ROOT));
            int price = Integer.parseInt(fields[keyForField.get("price")]);
            if (itemType == ItemType.TELEPHONE) {
                final String model = fields[keyForField.get("model")];
                item = new Telephone(series, screenType, price, model);
            }
            if (itemType == ItemType.TV) {
                final int diagonal = Integer.parseInt(fields[keyForField.get("diagonal")]);
                final Country country = EnumUtils.getEnum(Country.class, fields[keyForField.get("country")].toUpperCase(Locale.ROOT)); //TODO
                item = new TV(series, screenType, price, diagonal, country);
            }
        } catch (Exception e){
            throw new ObjectReaderException();
        }
        if (item == null) throw new ObjectReaderException();
        return item;
    }

    public List<Item> itemFromFile(String filename) {
        String text = textFromResourceFile(filename);

        List<String[]> fieldsForObjects = new ArrayList<>();
        Map<String, Integer> keyForField = new HashMap<>();
        if (!text.isEmpty()) {
            fieldsForObjects = getArrayFields(text);
            keyForField = getIndexForField(fieldsForObjects.get(0));
        }

        List<Item> itemsFromFileList = new LinkedList<>();

        for (int i = 1; i < fieldsForObjects.size(); i++) {
            try {
                Item item = create(fieldsForObjects.get(i), keyForField);
                itemsFromFileList.add(item);
            } catch (ObjectReaderException e){
                System.out.println("Object is not created because of incorrect data in " + (i+1) + " line.");
            }
        }
        return itemsFromFileList;
    }
}


