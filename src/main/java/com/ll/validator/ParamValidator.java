package com.ll.validator;

import com.ll.dto.ParamDto;
import com.ll.storage.Storage;

import java.util.Map;

public class ParamValidator {

    public boolean validateParam(ParamDto paramDto, Storage storage) {
        if (!isContainQueryString(paramDto)) {
            return false;
        }

        Map<String, String> queryString = paramDto.getQueryString();
        int id = Integer.parseInt(queryString.get("id"));
        if (!isIdInStorage(id,storage)){
            return false;
        }
        return true;
    }

    private boolean isContainQueryString(ParamDto paramDto) {
        if (paramDto.getQueryString().isEmpty()) {
            System.out.println(paramDto.getOrder() + "?id={번호}형식으로 입력하세요.");
            return false;
        }
        return true;
    }

    private static boolean isIdInStorage(int id, Storage storage) {
        if (storage.getQuotes().get(id) == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return false;
        }
        return true;
    }
}
