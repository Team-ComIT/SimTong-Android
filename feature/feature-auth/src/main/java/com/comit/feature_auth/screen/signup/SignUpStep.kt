package com.comit.feature_auth.screen.signup

class SignUpStep {

    enum class InputUserInfo(
        val title: String,
        val offsetIdx: Int,
    ) {
        NAME("이름을 입력해주세요.", 1),
        EMPLOYEE_NUMBER("사원번호를 입력해주세요.", 2),
        EMAIL("이메일을 입력해주세요", 3),

    }

    enum class AuthenticatePhone(
        val title: String,
        val offsetIdx: Int,
    ) {

    }

    enum class InputPassword(
        val title: String,
        val offsetIdx: Int,
    )

    enum class InputNickName(
        val title: String,
        val offsetIdx: Int,
    )
}