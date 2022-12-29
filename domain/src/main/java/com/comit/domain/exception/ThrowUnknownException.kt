package com.comit.domain.exception

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase

/**
 * 심통에서 예상 밖에 에외를 처리할 때 사용되는 함수
 *
 * NoInterNetException, NoConnectivityException 등 모든 경우에 사용되는 Exception은 별도로 처리
 * Exception이 발생할 경우 Firebase Crashlytics에 제보합니다.
 */
fun throwUnknownException(
    e: Throwable,
) {
    // TODO(limsaehyun): 인터넷 에러가 UnknwonException으로 감지됨 해결 필요
    // TODO(limsaehyun): 임시로 message text 로 구분
    if (e.message?.contains("NoInternetException") == true) throw NoInternetException()
    if (e.message?.contains("NoConnectivityException") == true) throw NoInternetException()

    when (e) {
        is NoInternetException -> throw NoInternetException()
        is NoConnectivityException -> throw NoInternetException()
        else -> {
            Firebase.crashlytics.recordException(e)
            throw UnknownException(e.message)
        }
    }
}

/**
 * 심통에서 예상 밖에 예외를 toast 방식으로 처리할 때 사용하는 함수
 *
 * NoInterNetException, NoConnectivityException 등 모든 경우에 사용되는 Exception은 별도로 처리
 * Exception이 발생할 경우 Firebase Crashlytics에 제보합니다.
 */
fun throwUnknownExceptionForToast(
    e: Throwable,
    throwErrorMessage: (String) -> Unit,
) {
    // TODO(limsaehyun): 인터넷 에러가 UnknwonException으로 감지됨 해결 필요
    // TODO(limsaehyun): 임시로 message text 로 구분
    if (e.message?.contains("NoInternetException") == true) throw NoInternetException()
    if (e.message?.contains("NoConnectivityException") == true) throw NoInternetException()

    when (e) {
        is NoInternetException -> throw NoInternetException()
        is NoConnectivityException -> throw NoInternetException()
        else -> {
            Firebase.crashlytics.recordException(e)
            throwErrorMessage(e.message ?: "알 수 없는 에러가 발생했습니다!")
        }
    }
}
