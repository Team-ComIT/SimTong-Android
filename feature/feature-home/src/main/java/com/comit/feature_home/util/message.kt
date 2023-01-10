package com.comit.feature_home.util

/**
 * Home 에서 사용하는 Message 를 관리합니다.
 */
internal object HomeMessage {
    object Alarm {
        const val AlarmNotFound = "도착한 알람이 없습니다."
    }

    object Schedule {
        const val DeleteScheduleNotFound = "삭제할 일정이 존재하지 않습니다."
        const val ScheduleNotFound = "일정이 존재하지 않습니다."
        const val ScheduleBadRequest = "모든 항목에 형식이 일치하게 작성되었는지 확인해주세요."
        const val ChangeScheduleNotFound = "변경할 일정을 확인할 수 없습니다"

        fun DeleteSchedule(name: String) = "\"$name\" 일정을 삭제합니다."
    }

    object Holiday {
        const val HolidayApplyDeny = "현재 휴무표를 작성할 수 있는 기간이 아닙니다."
        const val DateBadRequest = "잘못된 날짜 입력입니다."
        const val AlreadyHoliday = "이미 휴무일입니다."
        const val TooManyHoliday = "휴무일은 일주일에 2번만 가능합니다."
        const val AnnualAlready = "이미 연차입니다."
        const val TooManyAnnual = "연차 개수가 부족합니다."
        const val AlreadyWork = "이미 근무일입니다."
        const val CannotChangeWork = "더 이상 변경할 수 없는 일정입니다."
    }
}
