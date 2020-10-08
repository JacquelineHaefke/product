package com.jacquelinehaefke.mircoservice.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRestControllerTest {
    @InjectMocks
    ProductRestController productRestController;

    @Mock
    ProductRepository productRepository;

    @Test
    @DisplayName("Test all() of ProductRestController")
    void all() {
        //Given
        final Product product = mock(Product.class);
        when(productRepository.findAll()).thenReturn(List.of(product));

        //When
        Iterable<Product> products = productRestController.all();

        //Then
        assertThat(products).contains(product);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test newProduct() of ProductRestController")
    void newProduct() {
        //Given
        final Product product = mock(Product.class);
        when(productRepository.save(eq(product))).thenReturn(product);

        //When
        Product productResult = productRestController.newProduct(product);

        //Then
        assertThat(productResult).isEqualTo(product);
        verify(productRepository, times(1)).save(eq(product));
    }

    @Test
    @DisplayName("Test one() of ProductRestController")
    void one() {
        //Given
        final Long id = 2L;
        final Product product = mock(Product.class);
        when(productRepository.findById(eq(id))).thenReturn(Optional.of(product));

        //When
        Product productResult = productRestController.one(id);

        //Then
        assertThat(productResult).isEqualTo(product);
        verify(productRepository, times(1)).findById(eq(id));
    }

    @Test
    @DisplayName("Test one() of ProductRestController and throws Exception")
    void oneWithException() {
        //Given
        final Long id = 2L;
        when(productRepository.findById(eq(id))).thenReturn(Optional.empty());

        //When/Then
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> productRestController.one(id)).withMessageStartingWith("Product not found for this id");
        verify(productRepository, times(1)).findById(eq(id));
    }


    @Test
    @DisplayName("Test replaceProduct() of ProductRestController")
    void replaceProduct() {
        //Given
        final long id = 3;
        final Product product = mock(Product.class);
        when(productRepository.findById(eq(id))).thenReturn(Optional.of(product));
        when(productRepository.save(eq(product))).thenReturn(product);

        //When
        Product productResult = productRestController.replaceProduct(product, id);

        //Then
        assertThat(productResult).isEqualTo(product);
        verify(productRepository, times(1)).findById(eq(id));
        verify(productRepository, times(1)).save(eq(product));
    }

    @Test
    @DisplayName("Test deleteProduct() of ProductRestController")
    void deleteProduct() {
        //Given
        final long id = 3;

        //When
        productRestController.deleteProduct(id);

        //Then
        verify(productRepository, times(1)).deleteById(eq(id));
    }
}
