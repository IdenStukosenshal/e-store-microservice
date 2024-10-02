package ru.isands.test.estore.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public class PaginateService<Model, Repo extends JpaRepository<Model, ?>> {
    private final Repo repo;

    public PaginateService(Repo repo) {
        this.repo = repo;
    }
    /*
    Объект PageRequest является реализацией интерфейса Pageable, нумерация начинается с 0 */
    public Page<Model> findAllPaginated(int nPage, int sizePage) {
        Pageable pageable = PageRequest.of(nPage - 1, sizePage);
        return repo.findAll(pageable);
    }
}