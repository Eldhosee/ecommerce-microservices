package com.example.demo.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.example.demo.document.ProductDocument;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.dto.ProductUpdateRequest;


@Mapper(
	    componentModel = "spring",
	    unmappedTargetPolicy = ReportingPolicy.IGNORE
	)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    ProductDocument toDocument(ProductRequest request);

    ProductResponse toResponse(ProductDocument product);

   
    void updateProduct(
            ProductUpdateRequest request,
            @MappingTarget ProductDocument document);
}