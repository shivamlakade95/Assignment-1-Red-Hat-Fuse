package com.mypackage;

import javax.xml.bind.annotation.XmlRegistry;

public class ObjectFactory {

    public PurchaseOrder createPurchaseOrder()
    {
     return new PurchaseOrder();
    }
}
