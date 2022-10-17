package com.comit.core_design_system.icon

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.comit.core_design_system.R

@Immutable
@JvmInline
value class SimTongIcon private constructor(
    @DrawableRes val drawableId: Int,
) {
    companion object {

        @Stable
        val Navi_Home = SimTongIcon(
            drawableId = R.drawable.ic_navi_home,
        )

        @Stable
        val Navi_Idea = SimTongIcon(
            drawableId = R.drawable.ic_navi_idea,
        )

        @Stable
        val Navi_News = SimTongIcon(
            drawableId = R.drawable.ic_navi_news,
        )

        @Stable
        val Navi_Shopping = SimTongIcon(
            drawableId = R.drawable.ic_navi_shopping,
        )

        @Stable
        val Navi_Vote = SimTongIcon(
            drawableId = R.drawable.ic_navi_vote,
        )

        @Stable
        val Alarm_Plan = SimTongIcon(
            drawableId = R.drawable.ic_alarm_plan,
        )

        @Stable
        val Alarm_Post = SimTongIcon(
            drawableId = R.drawable.ic_alarm_post,
        )

        @Stable
        val Check = SimTongIcon(
            drawableId = R.drawable.ic_check,
        )

        @Stable
        val Add = SimTongIcon(
            drawableId = R.drawable.ic_add,
        )

        @Stable
        val Comment = SimTongIcon(
            drawableId = R.drawable.ic_comment,
        )

        @Stable
        val Image = SimTongIcon(
            drawableId = R.drawable.ic_image,
        )

        @Stable
        val Link = SimTongIcon(
            drawableId = R.drawable.ic_link,
        )

        @Stable
        val More = SimTongIcon(
            drawableId = R.drawable.ic_more,
        )

        @Stable
        val MyPage = SimTongIcon(
            drawableId = R.drawable.ic_mypage,
        )

        @Stable
        val Next = SimTongIcon(
            drawableId = R.drawable.ic_next,
        )

        @Stable
        val Photo = SimTongIcon(
            drawableId = R.drawable.ic_photo,
        )

        @Stable
        val Plus = SimTongIcon(
            drawableId = R.drawable.ic_plus,
        )

        @Stable
        val Send = SimTongIcon(
            drawableId = R.drawable.ic_send,
        )

        @Stable
        val Trash = SimTongIcon(
            drawableId = R.drawable.ic_trash,
        )

        @Stable
        val Password_Visible = SimTongIcon(
            drawableId = R.drawable.ic_password_visible,
        )

        @Stable
        val Password_InVisible = SimTongIcon(
            drawableId = R.drawable.ic_password_invisible,
        )

        @Stable
        val Option_Thin = SimTongIcon(
            drawableId = R.drawable.ic_option_thin,
        )

        @Stable
        val Option_Bold = SimTongIcon(
            drawableId = R.drawable.ic_option_bold,
        )

        @Stable
        val Back_Small = SimTongIcon(
            drawableId = R.drawable.ic_back_small,
        )

        @Stable
        val Back_Big = SimTongIcon(
            drawableId = R.drawable.ic_back_big,
        )

        @Stable
        val Beil_On = SimTongIcon(
            drawableId = R.drawable.ic_beil_on,
        )

        @Stable
        val Beil_Off = SimTongIcon(
            drawableId = R.drawable.ic_beil_off,
        )

        @Stable
        val Heart_On = SimTongIcon(
            drawableId = R.drawable.ic_heart_on
        )

        @Stable
        val Heart_Off = SimTongIcon(
            drawableId = R.drawable.ic_heart_off,
        )

        @Stable
        val Gray_Heart_Off = SimTongIcon(
            drawableId = R.drawable.ic_heart_off_gray,
        )

        @Stable
        val Gray_Comment = SimTongIcon(
            drawableId = R.drawable.ic_comment_gray,
        )
    }
}
