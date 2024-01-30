package com.javase.clientservice.mapper;

import com.javase.clientservice.dto.ContactNumberDto;
import com.javase.clientservice.model.ContactNumber;
import com.javase.clientservice.utility.IdGeneratorUtil;

import java.util.ArrayList;
import java.util.List;

public class NumberMapper {
    //contact to dto
    public static ContactNumberDto mapToNumberDto(ContactNumber number){
        ContactNumberDto numberDto= new ContactNumberDto(
                IdGeneratorUtil.generateUniqueNumberId(),
                number.getNumber()
        );
        return numberDto;
    }

    public static ContactNumber mapToNumber(ContactNumberDto numberDto){
        ContactNumber number= new ContactNumber(
                IdGeneratorUtil.generateUniqueNumberId(),
                numberDto.getNumber()
        );
        return number;
    }

    public static ContactNumber mapToNumber(ContactNumber number, ContactNumberDto numberDto){
         number.setNumber(numberDto.getNumber());
         return number;
    }
    public static List<ContactNumberDto> mapToDtoNumberList(List<ContactNumber> numbers){
        List<ContactNumberDto> dtoNumberList = new ArrayList<>();
        for(ContactNumber number: numbers){
            dtoNumberList.add(mapToNumberDto(number));
        }
        return dtoNumberList;
    }

    public static List<ContactNumber> mapToNumberList(List<ContactNumberDto> numbers){
        List<ContactNumber> numberList = new ArrayList<>();
        for(ContactNumberDto number: numbers){
            numberList.add(mapToNumber(number));
        }
        return numberList;
    }





}
