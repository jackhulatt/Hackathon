package com.qa.ims.persistence.domain;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {

    @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(Item.class).verify();
    }

}
