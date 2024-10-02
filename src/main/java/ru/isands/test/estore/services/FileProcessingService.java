package ru.isands.test.estore.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isands.test.estore.enums.NamesAndOrders;
import ru.isands.test.estore.repositories.*;

import java.util.List;
import java.util.stream.Collectors;

//Выглядит чудовищно
@Service
public class FileProcessingService {

            /*
        Это не будет работать
        Map<String, Function<String, ? extends _Model>> mapMappers = new HashMap<>(Map.of("ElectroEmployee.csv", mapperSevice::mapperElectroEmployeeCSV));
        Map<String, JpaRepository<? extends _Model, ?>> mapRepo = new HashMap<>(Map.of("ElectroEmployee.csv", electroEmployeeRepository));
        var list = fileList.stream().skip(1).map(mapMappers.get(name)).collect(Collectors.toList());
        mapRepo.get(name).saveAll(list);

         */

    private final ElectroEmployeeRepository electroEmployeeRepository;
    private final ElectroItemRepository electroItemRepository;
    private final ElectroShopRepository electroShopRepository;
    private final ElectroTypeRepository electroTypeRepository;
    private final EmployeeRepository employeeRepository;
    private final PositionTypeRepository positionTypeRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseTypeRepository purchaseTypeRepository;
    private final ShopRepository shopRepository;
    private final MapperSevice mapperSevice;


    public FileProcessingService(ElectroEmployeeRepository electroEmployeeRepository,
                                 ElectroItemRepository electroItemRepository,
                                 ElectroShopRepository electroShopRepository,
                                 ElectroTypeRepository electroTypeRepository,
                                 EmployeeRepository employeeRepository,
                                 PositionTypeRepository positionTypeRepository,
                                 PurchaseRepository purchaseRepository,
                                 PurchaseTypeRepository purchaseTypeRepository,
                                 ShopRepository shopRepository,
                                 MapperSevice mapperSevice) {
        this.electroEmployeeRepository = electroEmployeeRepository;
        this.electroItemRepository = electroItemRepository;
        this.electroShopRepository = electroShopRepository;
        this.electroTypeRepository = electroTypeRepository;
        this.employeeRepository = employeeRepository;
        this.positionTypeRepository = positionTypeRepository;
        this.purchaseRepository = purchaseRepository;
        this.purchaseTypeRepository = purchaseTypeRepository;
        this.shopRepository = shopRepository;
        this.mapperSevice = mapperSevice;
    }

    @Transactional//если ошибка в какой-либо таблице, она не добавится???
    public void processingFile(List<String> fileList, NamesAndOrders enumName){
        switch (enumName) {
            case ELECTRO_TYPE:
                var lst1 = fileList.stream().skip(1).map(mapperSevice::mapperElectroTypeCSV).collect(Collectors.toList());
                electroTypeRepository.saveAll(lst1);
                break;
            case POSITION_TYPE:
                var lst2 = fileList.stream().skip(1).map(mapperSevice::mapperPositionTypeCSV).collect(Collectors.toList());
                positionTypeRepository.saveAll(lst2);
                break;
            case PURCHASE_TYPE:
                var lst3 = fileList.stream().skip(1).map(mapperSevice::mapperPurchaseTypeCSV).collect(Collectors.toList());
                purchaseTypeRepository.saveAll(lst3);
                break;
            case SHOP:
                var lst4 = fileList.stream().skip(1).map(mapperSevice::mapperShopCSV).collect(Collectors.toList());
                shopRepository.saveAll(lst4);
                break;
            case ELECTRO_ITEM:
                var lst5 = fileList.stream().skip(1).map(mapperSevice::mapperElectroItemCSV).collect(Collectors.toList());
                electroItemRepository.saveAll(lst5);
                break;
            case EMPLOYEE:
                var lst6 = fileList.stream().skip(1).map(mapperSevice::mapperEmployeeCSV).collect(Collectors.toList());
                employeeRepository.saveAll(lst6);
                break;
            case ELECTRO_EMPLOYEE:
                var lst7 = fileList.stream().skip(1).map(mapperSevice::mapperElectroEmployeeCSV).collect(Collectors.toList());
                electroEmployeeRepository.saveAll(lst7);
                break;
            case ELECTRO_SHOP:
                var lst8 = fileList.stream().skip(1).map(mapperSevice::mapperElectroShopCSV).collect(Collectors.toList());
                electroShopRepository.saveAll(lst8);
                break;
            case PURCHASE:
                var lst9 = fileList.stream().skip(1).map(mapperSevice::mapperPurchaseCSV).collect(Collectors.toList());
                purchaseRepository.saveAll(lst9);
                break;
        }
    }
}

    /*  file.getInputStream()

    * public void processingFile(InputStream is, String name) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        * reader.lines().skip(1).map(MAPPER).collect(Collectors.toList());
        * */

