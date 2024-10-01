package ru.isands.test.estore.services;

import org.springframework.stereotype.Service;
import ru.isands.test.estore.models.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MapperSevice {

    //private final Function<String, ElectroType> mapperElectroTypeCSV = (line) -> {}
    public ElectroType mapperElectroTypeCSV(String line){
        String[] fields = line.split(";");
        if (fields.length != 2) {
            //2 - количество полей модели
            return null;
        }
        ElectroType etp = new ElectroType();
        etp.setId(Long.parseLong(fields[0]));
        etp.setName(fields[1]);
        return etp;
    }

    public ElectroItem mapperElectroItemCSV(String line){
        String[] fields = line.split(";");
        if (fields.length != 7) {
            return null;
        }
        ElectroItem etm = new ElectroItem();
        etm.setId(Long.parseLong(fields[0]));
        etm.setName(fields[1]);
        etm.setETypeId(Long.parseLong(fields[2]));
        etm.setPrice(Long.parseLong(fields[3]));
        etm.setCount(Integer.parseInt(fields[4]));
        etm.setArchive(Boolean.parseBoolean(fields[5]));
        etm.setDescription(fields[6]);
        return etm;
    }


    public ElectroEmployee mapperElectroEmployeeCSV(String line){
        String[] fields = line.split(";");
        if (fields.length != 2) {
            return null;
        }
        ElectroEmployee ee = new ElectroEmployee();
        ee.setEmployeeId(Long.parseLong(fields[0]));
        ee.setElectroTypeId(Long.parseLong(fields[1]));
        return ee;
    };
    public ElectroShop mapperElectroShopCSV(String line){
        String[] fields = line.split(";");
        if (fields.length != 3) {
            return null;
        }
        ElectroShop es = new ElectroShop();
        es.setShopId(Long.parseLong(fields[0]));
        es.setElectroItemId(Long.parseLong(fields[1]));
        es.setCount(Integer.parseInt(fields[2]));
        return es;
    }
    public Employee mapperEmployeeCSV(String line){
        String[] fields = line.split(";");
        if (fields.length != 8) {
            return null;
        }
        Employee e = new Employee();
        e.setId(Long.parseLong(fields[0]));
        e.setLastName(fields[1]);
        e.setFirstName(fields[2]);
        e.setPatronymic(fields[3]);
        try{
            e.setBirthDate(dateParser(fields[4]));
        }catch (ParseException excc ){
            System.out.println("Проблема парсинга даты");
            return null;
        }
        e.setPositionId(Long.parseLong(fields[5]));
        e.setShopId(Long.parseLong(fields[6]));
        e.setGender(Boolean.parseBoolean(fields[7]));
        return e;
    };
    public PositionType mapperPositionTypeCSV(String line){
        String[] fields = line.split(";");
        if (fields.length != 2) {
            return null;
        }
        PositionType pt = new PositionType();
        pt.setId(Long.parseLong(fields[0]));
        pt.setName(fields[1]);
        return pt;
    }
    public Purchase mapperPurchaseCSV(String line){
        String[] fields = line.split(";");
        if (fields.length != 6) {
            return null;
        }
        Purchase p = new Purchase();
        p.setId(Long.parseLong(fields[0]));
        p.setElectroId(Long.parseLong(fields[1]));
        p.setEmployeeId(Long.parseLong(fields[2]));
        try{
            p.setPurchaseDate(dateParser(fields[3]));
        }catch (ParseException excc ){
            System.out.println("Проблема парсинга даты");
            return null;
        }
        p.setType(Integer.parseInt(fields[4]));
        p.setShopId(Long.parseLong(fields[5]));
        return p;
    }
    public PurchaseType mapperPurchaseTypeCSV(String line){
        String[] fields = line.split(";");
        if (fields.length != 2) {
            return null;
        }
        PurchaseType pt = new PurchaseType();
        pt.setId(Long.parseLong(fields[0]));
        pt.setName(fields[1]);
        return pt;
    }
    public Shop mapperShopCSV(String line){
        String[] fields = line.split(";");
        if (fields.length != 3) {
            return null;
        }
        Shop s = new Shop();
        s.setId(Long.parseLong(fields[0]));
        s.setName(fields[1]);
        s.setAddress(fields[2]);
        return s;
    }

    private Date dateParser(String strk) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(strk);
        return sdf.parse(strk);
    }
}
