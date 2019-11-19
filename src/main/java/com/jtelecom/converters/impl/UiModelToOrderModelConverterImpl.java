package com.jtelecom.converters.impl;

import com.jtelecom.converters.UiModelToOrderModelConverter;
import com.jtelecom.ui.AddsOnUiModel;
import org.springframework.stereotype.Service;

@Service
public class UiModelToOrderModelConverterImpl implements UiModelToOrderModelConverter {
    @Override
    public AddsOnUiModel convert(Integer serviceId, String serviceType) {
        AddsOnUiModel addsOnUiModel = new AddsOnUiModel();
        addsOnUiModel.setServiceId(serviceId);
        addsOnUiModel.setServiceType(serviceType);
        return addsOnUiModel;
    }
}
