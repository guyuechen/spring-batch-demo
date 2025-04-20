package com.sb.example.address.processor;

import java.text.Normalizer;

import com.sb.example.address.entity.Address;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class AddressConvertHanToZenProcessor implements ItemProcessor<Address, Address> {

    @Override
    public Address process(Address address) {
        // 半角→全角
        String prefectureKanaZenkaku = Normalizer.normalize(address.getPrefectureKana(), Normalizer.Form.NFKC);
        String cityKanaZenkaku = Normalizer.normalize(address.getCityKana(), Normalizer.Form.NFKC);
        String townKanaZenkaku = Normalizer.normalize(address.getTownKana(), Normalizer.Form.NFKC);

        address.setPrefectureKana(prefectureKanaZenkaku);
        address.setCityKana(cityKanaZenkaku);
        address.setTownKana(townKanaZenkaku);

        return address;
    }

}
