package com.cta.models;

public record AddressForm(
        String address1,
        String city,
        String id_state,
        String postcode,
        String phone,
        String phone_mobile
) {
}