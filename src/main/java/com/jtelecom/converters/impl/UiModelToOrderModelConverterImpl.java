package com.jtelecom.converters.impl;

import com.jtelecom.converters.UiModelToOrderModelConverter;
import com.jtelecom.entities.loyalty.Loyalty;
import com.jtelecom.entities.loyalty.UserLoyalty;
import com.jtelecom.ui.AddsOnUiModel;
import com.jtelecom.ui.LoyaltyInfoUi;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UiModelToOrderModelConverterImpl implements UiModelToOrderModelConverter {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.ENGLISH);

    @Override
    public AddsOnUiModel convert(Integer serviceId, String serviceType) {
        AddsOnUiModel addsOnUiModel = new AddsOnUiModel();
        addsOnUiModel.setServiceId(serviceId);
        addsOnUiModel.setServiceType(serviceType);
        return addsOnUiModel;
    }

    @Override
    public List<LoyaltyInfoUi> convert(Iterable<Loyalty> loyaltiesIter, Iterable<UserLoyalty> userLoyaltiesIter) {
        List<Loyalty> loyalties = new ArrayList<>();
        List<UserLoyalty> userLoyalties = new ArrayList<>();
        loyaltiesIter.forEach(loyalties::add);
        userLoyaltiesIter.forEach(userLoyalties::add);
        List<LoyaltyInfoUi> result = new ArrayList<>();
        userLoyalties.forEach(userLoyalty -> {
            loyalties.stream().filter(loyalty -> userLoyalty.getLoyaltyId() == loyalty.getLoyaltyId()).forEach(loyalty -> {
                LoyaltyInfoUi ui = new LoyaltyInfoUi();
                ui.setId(loyalty.getLoyaltyId());
                ui.setName(loyalty.getName());
                ui.setDescription(loyalty.getDescription());
                ui.setActivationCode(userLoyalty.getLoyaltyCode());
                ui.setDueDate(userLoyalty.getDueDateActive());
                ui.setStatus(userLoyalty.getIsActive());
                result.add(ui);
            });
        });
        result.sort((l1, l2) -> {
            boolean sortResult;
            try {
                sortResult = formatter.parse(l1.getDueDate()).after(formatter.parse(l2.getDueDate()));
            } catch (ParseException e) {
                return 0;
            }
            return sortResult ? -1 : 1;
        });
        return result;
    }
}
