package com.jtelecom.converters;

import com.jtelecom.entities.loyalty.Loyalty;
import com.jtelecom.entities.loyalty.UserLoyalty;
import com.jtelecom.ui.AddsOnUiModel;
import com.jtelecom.ui.LoyaltyInfoUi;

import java.util.List;

public interface UiModelToOrderModelConverter {

    AddsOnUiModel convert(Integer serviceId, String serviceType);

    List<LoyaltyInfoUi> convert(Iterable<Loyalty> loyalties, Iterable<UserLoyalty> userLoyalties);
}
