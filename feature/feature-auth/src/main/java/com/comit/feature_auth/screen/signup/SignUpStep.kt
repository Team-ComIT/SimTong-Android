package com.comit.feature_auth.screen.signup

private const val FirstStep: Int = 1
private const val SecondStep: Int = 2
private const val ThirdStep: Int = 3
private const val FourthStep: Int = 4

class SignUpStep {

    enum class InputUserInfo(
        val title: String,
        val offsetIdx: Int,
    ) {
        NAME("이름을 입력해주세요.", FirstStep),
        EMPLOYEE_NUMBER("사원번호를 입력해주세요.", SecondStep),
        EMAIL("이메일을 입력해주세요.", ThirdStep),
        AGREED("약관을 동의해주세요.", FourthStep),
    }

    enum class AuthenticatePhone(
        val title: String,
        val offsetIdx: Int,
    )

    enum class InputPassword(
        val title: String,
        val offsetIdx: Int,
    )

    enum class InputNickName(
        val title: String,
        val offsetIdx: Int,
    )
}
