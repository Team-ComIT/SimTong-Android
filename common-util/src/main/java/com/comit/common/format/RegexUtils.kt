package com.comit.common.format

object RegexUtils {

    /**
     * first word & Last word - space X
     *
     * space , _ , . , a ~ z , A ~ Z , 가 ~ 힣 , 0 ~ 9
     *
     * length - 1 ~ 20
     **/
    const val NICKNAME_PATTERN = """(?=^\S)(?=^[\w\s가-힣.]{1,20}$).*(?=\S$)."""

    /**
     * a ~ z or A ~ Z & 0 ~ 9 - must more than once
     *
     * non-space, $ , + , - , _ , a ~ z , A ~ Z , 0 ~ 9
     *
     * length - 8 ~ 20
     **/
    const val SECRET_PATTERN = """(?=.*[a-zA-Z])(?=.*\d)(?=^[\w+\-$]{8,20}$).*"""
}
