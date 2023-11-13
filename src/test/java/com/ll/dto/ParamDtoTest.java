package com.ll.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParamDtoTest {

    @Test
    @DisplayName("명령만 넘겼을 때, order에 대입되어 초기화되고, queryString은 비어있다.")
    void orderOnlyTest(){
        String orderString = "등록";
        ParamDto paramDto = new ParamDto(orderString);

        Assertions.assertThat(paramDto.getOrder()).isEqualTo(orderString);
        Assertions.assertThat(paramDto.getQueryString()).isEmpty();
    }

    @Test
    @DisplayName("쿼리 스트링(?key=value)을 넘겼을 때, queryString에 key,value로 삽입된다.")
    void parsingQueryStringTest(){
        String orderString = "삭제?id=1";
        ParamDto paramDto = new ParamDto(orderString);

        Assertions.assertThat(paramDto.getOrder()).isEqualTo("삭제");
        Assertions.assertThat(paramDto.getQueryString().get("id")).isEqualTo("1");
    }

    @Test
    @DisplayName("쿼리 스트링(?key=value&key=value)을 넘겼을 때, queryString에 key,value로 삽입된다.")
    void parsingMultipleQueryStringTest(){
        String orderString = "삭제?id=1&secondKey=두 번째";
        ParamDto paramDto = new ParamDto(orderString);

        Assertions.assertThat(paramDto.getOrder()).isEqualTo("삭제");
        Assertions.assertThat(paramDto.getQueryString().get("id")).isEqualTo("1");
        Assertions.assertThat(paramDto.getQueryString().get("secondKey")).isEqualTo("두 번째");
    }

}