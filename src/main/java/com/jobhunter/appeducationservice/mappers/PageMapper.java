package com.jobhunter.appeducationservice.mappers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PageMapper {

    public <D, T> Page<D> mapEntityPageToDtoPage(Page<T> entityPage, List<D> dtoList) {
        return new PageImpl<>(dtoList, PageRequest.of(entityPage.getNumber(), entityPage.getSize()), entityPage.getTotalElements());
    }

    public <D, T> Page<D> mapEntityPageToDtoPage(Page<T> entityPage, Function<T, D> converter) {
        List<D> dtoList = entityPage.stream()
                .map(converter)
                .collect(Collectors.toList());
        return new PageImpl<>(dtoList, PageRequest.of(entityPage.getNumber(), entityPage.getSize()), entityPage.getTotalElements());
    }
}
