package pages.autostore.page_objects.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

    @Getter
    @RequiredArgsConstructor
    public enum Region {


        ALABAMA("Alabama", "12345"),
        ALASKA("Alaska", "12000"),
        NEW_YORK("New York", "10000");

        private final String regionName;
        private final String regionZip;
    }

