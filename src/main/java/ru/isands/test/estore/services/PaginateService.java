package ru.isands.test.estore.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;


public class PaginateService<TableModel, Repo extends JpaRepository<TableModel, ?>> {
    private final Repo repo;

    public PaginateService(Repo repo) {
        this.repo = repo;
    }

    //нумерация начинается с 0, а на странице с 1
    public Page<TableModel> findAllPaginated(int nPage, int sizePage) {
        Pageable pageable = PageRequest.of(nPage - 1, sizePage);
        return repo.findAll(pageable);
    }
    public void addAtributesToModel(int page, int size, Page<TableModel> itemPgLst, Model model){
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", itemPgLst.getTotalPages());
        model.addAttribute("totalItems", itemPgLst.getTotalElements());
        model.addAttribute("itemLst", itemPgLst.getContent());
    }
}