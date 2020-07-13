package com.shop.GoodsShop.Model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty(message = "Список заказанных предметов не может быть пустым")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private Set<ClientItem> clientItems = new HashSet<>();

    @NotNull(message = "Контактная информация должна быть задана")
    private Contacts contacts;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @NotBlank(message = "Способ оплаты должен быть задан")
    private String paymentMethod;

    private String trackNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn
    private Client manager;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date lastUpdate;


    protected Order() {}

    public Order(Set<ClientItem> clientItems,
                 Contacts contacts,
                 String paymentMethod) {
        this.clientItems = clientItems;
        this.contacts = contacts;
        this.paymentMethod = paymentMethod;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ClientItem> getClientItems() {
        return clientItems;
    }

    public void setClientItems(Set<ClientItem> clientItems) {
        this.clientItems = clientItems;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getManager() {
        return manager;
    }

    public void setManager(Client manager) {
        this.manager = manager;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return clientItems.equals(order.clientItems) &&
                contacts.equals(order.contacts) &&
                paymentMethod.equals(order.paymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientItems, contacts, paymentMethod);
    }
}
