package dev.lucas.desafiocdc.purchases.controllers.responses;

import dev.lucas.desafiocdc.purchases.domain.Address;
import dev.lucas.desafiocdc.purchases.domain.Order;
import dev.lucas.desafiocdc.purchases.domain.OrderItem;
import dev.lucas.desafiocdc.purchases.domain.Purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.stream.Collectors;

public class PurchaseResponse {

    private final Long id;
    private final String email;
    private final String name;
    private final String lastName;
    private final String document;
    private final String telephone;
    private final AddressResponse address;
    private final OrderResponse order;
    private boolean coupon;
    private BigDecimal totalDiscount;

    public PurchaseResponse(
            Long id,
            String email,
            String name,
            String lastName,
            String document,
            String telephone,
            AddressResponse address,
            OrderResponse order
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.telephone = telephone;
        this.address = address;
        this.order = order;
    }

    public static PurchaseResponse fromPurchase(Purchase purchase) {
        var address = AddressResponse.fromAddress(purchase.getAddress());

        var order = OrderResponse.fromOrder(purchase.getOrder());

        var totalPurchase = order.orderItems().stream().map(OrderResponse.OrderItemResponse::price).reduce(BigDecimal.ZERO, BigDecimal::add);

        PurchaseResponse purchaseResponse = new PurchaseResponse(purchase.getId(),
                purchase.getEmail(),
                purchase.getName(),
                purchase.getLastName(),
                purchase.getDocument(),
                purchase.getTelephone(),
                address,
                order
        );

        if (purchase.getApplyedCoupon() != null) {
            var porcentagem = BigDecimal.valueOf(purchase.getApplyedCoupon().getPercentage())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            var totalDiscount = porcentagem
                    .multiply(purchase.getOrder().getOrderItems().stream().map(OrderItem::getTotal)
                            .reduce(BigDecimal.ZERO, BigDecimal::add))
                    .setScale(2, RoundingMode.HALF_UP);

            purchaseResponse.hasCoupon(true);
            purchaseResponse.setTotalDiscount(totalDiscount);
        }

        return purchaseResponse;

    }

    private void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    private void hasCoupon(boolean hasCoupon) {
        this.coupon = hasCoupon;
    }

    public record AddressResponse(String street,
                                  String adjunct,
                                  String city,
                                  String country,
                                  String state,
                                  String zipCode) {

        public static AddressResponse fromAddress(Address address) {
            return new AddressResponse(address.getStreet(),
                    address.getAdjunct(),
                    address.getCity(),
                    address.getCountry().getName(),
                    address.getState().getName(),
                    address.getZipCode());
        }
    }

    public record OrderResponse(Set<OrderItemResponse> orderItems) {
        public static OrderResponse fromOrder(Order order) {

            var itens = order.getOrderItems().stream().map(OrderItemResponse::fromOrderItem).collect(Collectors.toSet());

            return new OrderResponse(itens);
        }

        public record OrderItemResponse(String book, int quantity, BigDecimal price) {

            public static OrderItemResponse fromOrderItem(OrderItem orderItem) {
                return new OrderItemResponse(orderItem.getBook().getTitle(), orderItem.getQuantity(), orderItem.getTotal());
            }
        }
    }

    @Override
    public String toString() {
        return "PurchaseResponse{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", document='" + document + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address=" + address +
                ", order=" + order +
                ", coupon=" + coupon +
                ", totalDiscount=" + totalDiscount +
                '}';
    }
}


