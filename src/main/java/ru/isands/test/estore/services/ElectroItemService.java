package ru.isands.test.estore.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.isands.test.estore.models.ElectroItem;
import ru.isands.test.estore.repositories.ElectroItemRepository;

@Service
public class ElectroItemService {

    private final ElectroItemRepository electroItemRepository;

    public ElectroItemService(ElectroItemRepository electroItemRepository) {
        this.electroItemRepository = electroItemRepository;
    }

    /*
    Объект PageRequest является реализацией интерфейса Pageable, нумерация начинается с 0 */
    public Page<ElectroItem> findAllPaginated(int nPage, int sizePage){
        Pageable pageable = PageRequest.of(nPage-1, sizePage);
        return electroItemRepository.findAll(pageable);
    }
}