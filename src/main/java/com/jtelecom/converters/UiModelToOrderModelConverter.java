package com.jtelecom.converters;

import com.jtelecom.ui.AddsOnUiModel;

public interface UiModelToOrderModelConverter {

    AddsOnUiModel convert(Integer serviceId, String serviceType);
}
