package com.sb.example.address.mapper;

import com.sb.example.address.entity.Address;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class AddressFieldSetMapper implements FieldSetMapper<Address> {

    public enum AddressField {
        Jis,
        ZipcodeOld,
        Zipcode,
        PrefectureKana,
        CityKana,
        TownKana,
        Prefecture,
        City,
        Town,
        DuplicateTown,
        Koaza,
        HasTyome,
        DuplicateZipcode,
        Update,
        ChangeReason
    }

    @Override
    public Address mapFieldSet(FieldSet fieldSet) throws BindException {
        Address address = new Address();
        address.setJis(fieldSet.readString(AddressField.Jis.ordinal()));
        address.setZipcodeOld(fieldSet.readString(AddressField.ZipcodeOld.ordinal()));
        address.setZipcode(fieldSet.readString(AddressField.Zipcode.ordinal()));
        address.setPrefectureKana(fieldSet.readString(AddressField.PrefectureKana.ordinal()));
        address.setCityKana(fieldSet.readString(AddressField.CityKana.ordinal()));
        address.setTownKana(fieldSet.readString(AddressField.TownKana.ordinal()));
        address.setPrefecture(fieldSet.readString(AddressField.Prefecture.ordinal()));
        address.setCity(fieldSet.readString(AddressField.City.ordinal()));
        address.setTown(fieldSet.readString(AddressField.Town.ordinal()));
        address.setDuplicateTown(fieldSet.readString(AddressField.DuplicateTown.ordinal()));
        address.setKoaza(fieldSet.readString(AddressField.Koaza.ordinal()));
        address.setHasTyome(fieldSet.readString(AddressField.HasTyome.ordinal()));
        address.setDuplicateZipcode(fieldSet.readString(AddressField.DuplicateZipcode.ordinal()));
        address.setUpdate(fieldSet.readString(AddressField.Update.ordinal()));
        address.setChangeReason(fieldSet.readString(AddressField.ChangeReason.ordinal()));
        return address;
    }
}
