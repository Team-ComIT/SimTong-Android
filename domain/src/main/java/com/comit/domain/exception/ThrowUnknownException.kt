package com.comit.domain.exception

/**
 * 심통에서 예상 밖에 에외를 처리할 때 사용되는 함수
 *
 * NoInterNetException, NoConnectivityException 등 모든 경우에 사용되는 Exception은 별도로 처리
 */
fun throwUnknownException(e: Throwable) {
    // TODO(limsaehyun): 인터넷 에러가 UnknwonException으로 감지됨 해결 필요
    // TODO(limsaehyun): 임시로 message text 로 구분
    if (e.message?.contains("NoInternetException") == true) throw NoInternetException()
    if (e.message?.contains("NoConnectivityException") == true) throw NoInternetException()

    when (e) {
        is NoInternetException -> throw NoInternetException()
        is NoConnectivityException -> throw NoInternetException()
        else -> throw UnknownException(e.message)
    }
}