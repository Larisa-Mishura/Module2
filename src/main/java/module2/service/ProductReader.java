package module2.service;

import module2.product.*;
import org.apache.commons.lang3.EnumUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class ProductReader {

    final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

    public String textFromResourceFile(String filename) throws IOException { // TODO  : private Готово!!!
        String result = "";
        try (final InputStream resourceAsStream = contextClassLoader.getResourceAsStream(filename)){
             //BufferedInputStream inputStream = new BufferedInputStream(resourceAsStream);) { TODO
            int i;
            while ((i = resourceAsStream.read()) != -1) {//TODO read lines
                result = result + (char) i;
            }
        } catch (FileNotFoundException e){
            System.out.println("Файл не знайдено");
            e.printStackTrace();
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
        final Product product;
        final String series = fields[keyForField.get("series")];
        final ScreenType screenType = EnumUtils.getEnum(ScreenType.class, fields[keyForField.get("screen type")], ScreenType.OTHERS);
        int price = Integer.parseInt(fields[keyForField.get("price")]);
        if(fields[keyForField.get("type")].equals("Telephone")){
            final String model = fields[keyForField.get("model")];
            product = new Telephone(series, screenType, price, model);
        } else if(fields[keyForField.get("type")].equals("TV")){
            final int diagonal = Integer.parseInt(fields[keyForField.get("diagonal")]);
            final Country country = EnumUtils.getEnum(Country.class, fields[keyForField.get("country")]); //TODO
            product = new TV(series, screenType, price, diagonal, country);
        } else throw new ObjectReaderException("відсутня іформація щодо типу."); //TODO
        return product;
    }


    public List<Product> productsFromFile(String filename) {
        String text = "";
        try {
            text = textFromResourceFile("product.csv");
        } catch (IOException e) {
            System.out.println();   //TODO
        }
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
                System.out.print("Обєкт не створено через некорекні дані в " + i + "-ому рядку - ");
                System.out.println(e.getMessage());
            }
        }
        return productsFromFileList;
    }
}


