package com.comit.simtong.firebase

import com.comit.domain.exception.UnknownException
import com.google.firebase.messaging.FirebaseMessaging

/**
 * FCM 에서 사용되는 DeviceToken 을 불러오는 함수
 *
 * @return 디바이스 토큰
 * @throws UnknownException DeviceToken 을 불러오지 못한 경우 발생
 */
internal fun getDeviceToken(): String =
    FirebaseMessaging.getInstance().token.result
        ?: throw UnknownException("디바이스 토큰 발급에 실패했습니다.\n잠시 후 다시 시도해주세요.")
