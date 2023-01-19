package module2.service;

import module2.model.product.*;
import org.apache.commons.lang3.EnumUtils;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class ProductReader {

    final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

    private String textFromResourceFile(String filename){
        String result = "";
        try (final InputStream resourceAsStream = contextClassLoader.getResourceAsStream(filename)){
            int i;
            while ((i = resourceAsStream.read()) != -1) {//TODO read lines
                result = result + (char) i;
            }
        } catch (Exception e){
            System.out.println("Файл не знайдено");
        }
        return result.replaceAll("(\t)|(\r)|(\s\\B)|(\\B\s)", "");
    }

    public List<String[]> getArrayFields(String text){     //TODO: private
        List<String[]> fieldsForObjects =
                Arrays.stream(text.split("\n"))
                .map(s -> s.split(","))
                .collect(Collectors.toCollection(LinkedList::new));
        return fieldsForObjects;
    }

    public Map<String, Integer> getIndexForField(String[] tableHeading){
        final Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < tableHeading.length; i++){
            map.put(tableHeading[i].trim(), i);
        }
        return map;
    }

    public Product create(final String[] fields, Map<String, Integer> keyForField) throws ObjectReaderException{
        Product product = null;
        try {
            final ProductType productType = EnumUtils.getEnum(ProductType.class, fields[keyForField.get("type")].toUpperCase(Locale.ROOT));
            final String series = fields[keyForField.get("series")];
            final ScreenType screenType = EnumUtils.getEnum(ScreenType.class, fields[keyForField.get("screen type")].toUpperCase(Locale.ROOT));
            int price = Integer.parseInt(fields[keyForField.get("price")]);
            if (productType == ProductType.TELEPHONE) {
                final String model = fields[keyForField.get("model")];
                product = new Telephone(series, screenType, price, model);
            }
            if (productType == ProductType.TV) {
                final int diagonal = Integer.parseInt(fields[keyForField.get("diagonal")]);
                final Country country = EnumUtils.getEnum(Country.class, fields[keyForField.get("country")].toUpperCase(Locale.ROOT)); //TODO
                product = new TV(series, screenType, price, diagonal, country);
            }
        } catch (Exception e){
            throw new ObjectReaderException();
        }
        if (product == null) throw new ObjectReaderException();
        return product;
    }

    public List<Product> productsFromFile(String filename) {
        String text = textFromResourceFile(filename);

        List<String[]> fieldsForObjects = new ArrayList<>();
        Map<String, Integer> keyForField = new HashMap<>();
        if (!text.isEmpty()) {
            fieldsForObjects = getArrayFields(text);
            keyForField = getIndexForField(fieldsForObjects.get(0));
        }

        List<Product> productsFromFileList = new LinkedList<>();

        for (int i = 1; i < fieldsForObjects.size(); i++) {
            try {
                Product product = create(fieldsForObjects.get(i), keyForField);
                productsFromFileList.add(product);
            } catch (ObjectReaderException e){
                System.out.println("Обєкт не створено через невірні дані в " + i + "-ому рядку."); //рядок не враховує заголовок таблиці
            }
        }
        return productsFromFileList;
    }
}


