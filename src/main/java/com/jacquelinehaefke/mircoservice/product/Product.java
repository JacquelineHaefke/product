package com.jacquelinehaefke.mircoservice.product;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted=true WHERE id=?")
@Where(clause = "deleted = false")
public class Product {
    @Id
    private long id;

    private String name;

    private double price;

    private Date created;

    private boolean deleted = false;

    private static long generateID() {
        UUID uuid = UUID.randomUUID();
        return Math.abs(uuid.getLeastSignificantBits());
    }

    public Product() {
        this(generateID());
    }

    private Product(long id) {
        super();
        this.id = id;
        created();
    }

    public Product(String name, double price) {
        this(generateID());
        this.name = name;
        this.price = price;
    }

    private void created() {
        created = new Date();
    }

}
