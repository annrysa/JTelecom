package com.jtelecom.utils;

import com.jtelecom.ui.OrderAction;
import com.jtelecom.ui.OrderType;

public class OrderRecordUtil {

    private static final String EMPTY_STRING_CONCAT = " ";

    public OrderRecordUtil() {
    }

    public static String setOrderAction(OrderAction orderAction, OrderType orderType, String itemName) {
        return new StringBuilder().append(orderAction.getAction()).append(EMPTY_STRING_CONCAT).append(orderType.getType())
                .append(EMPTY_STRING_CONCAT).append(itemName).toString();
    }
}
