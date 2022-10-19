package com.comit.core_design_system.icon

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.comit.core_design_system.R

@Immutable
class SimTongIcon private constructor(
    @DrawableRes val drawableId: Int,
    val contentDescription: String? = null,
) {
    companion object {

        @Stable
        val Navi_Home = SimTongIcon(
            drawableId = R.drawable.ic_navi_home,
            contentDescription = "bottom navigation home icon",
        )

        @Stable
        val Navi_Idea = SimTongIcon(
            drawableId = R.drawable.ic_navi_idea,
            contentDescription = "bottom navigation idea icon",
        )

        @Stable
        val Navi_News = SimTongIcon(
            drawableId = R.drawable.ic_navi_news,
            contentDescription = "bottom navigation news icon",
        )

        @Stable
        val Navi_Shopping = SimTongIcon(
            drawableId = R.drawable.ic_navi_shopping,
            contentDescription = "bottom navigation shopping icon",
        )

        @Stable
        val Navi_Vote = SimTongIcon(
            drawableId = R.drawable.ic_navi_vote,
            contentDescription = "bottom navigation vote icon",
        )

        @Stable
        val Alarm_Plan = SimTongIcon(
            drawableId = R.drawable.ic_alarm_plan,
            contentDescription = "plan icon",
        )

        @Stable
        val Alarm_Post = SimTongIcon(
            drawableId = R.drawable.ic_alarm_post,
            contentDescription = "post icon",
        )

        @Stable
        val Check = SimTongIcon(
            drawableId = R.drawable.ic_check,
            contentDescription = "check icon",
        )

        @Stable
        val White_CheckBox = SimTongIcon(
            drawableId = R.drawable.ic_check_white,
            contentDescription = "white check icon",
        )

        @Stable
        val Add = SimTongIcon(
            drawableId = R.drawable.ic_add,
            contentDescription = "add icon",
        )

        @Stable
        val Comment = SimTongIcon(
            drawableId = R.drawable.ic_comment,
            contentDescription = "comment icon",
        )

        @Stable
        val Image = SimTongIcon(
            drawableId = R.drawable.ic_image,
            contentDescription = "image icon",
        )

        @Stable
        val Link = SimTongIcon(
            drawableId = R.drawable.ic_link,
            contentDescription = "link icon",
        )

        @Stable
        val More = SimTongIcon(
            drawableId = R.drawable.ic_more,
            contentDescription = "more icon",
        )

        @Stable
        val MyPage = SimTongIcon(
            drawableId = R.drawable.ic_mypage,
            contentDescription = "people icon",
        )

        @Stable
        val Next = SimTongIcon(
            drawableId = R.drawable.ic_next,
            contentDescription = "next icon",
        )

        @Stable
        val Photo = SimTongIcon(
            drawableId = R.drawable.ic_photo,
            contentDescription = "photo icon",
        )

        @Stable
        val Plus = SimTongIcon(
            drawableId = R.drawable.ic_plus,
            contentDescription = "plus icon",
        )

        @Stable
        val Send = SimTongIcon(
            drawableId = R.drawable.ic_send,
            contentDescription = "send icon",
        )

        @Stable
        val Trash = SimTongIcon(
            drawableId = R.drawable.ic_trash,
            contentDescription = "trash icon",
        )

        @Stable
        val Password_Visible = SimTongIcon(
            drawableId = R.drawable.ic_password_visible,
            contentDescription = "password visible icon",
        )

        @Stable
        val Password_InVisible = SimTongIcon(
            drawableId = R.drawable.ic_password_invisible,
            contentDescription = "password invisible icon",
        )

        @Stable
        val Option_Thin = SimTongIcon(
            drawableId = R.drawable.ic_option_thin,
            contentDescription = "thin option icon",
        )

        @Stable
        val Option_Bold = SimTongIcon(
            drawableId = R.drawable.ic_option_bold,
            contentDescription = "bold option icon",
        )

        @Stable
        val Back_Small = SimTongIcon(
            drawableId = R.drawable.ic_back_small,
            contentDescription = "small back icon",
        )

        @Stable
        val Back_Big = SimTongIcon(
            drawableId = R.drawable.ic_back_big,
            contentDescription = "big back icon",
        )

        @Stable
        val Beil_On = SimTongIcon(
            drawableId = R.drawable.ic_beil_on,
            contentDescription = "beil on icon",
        )

        @Stable
        val Beil_Off = SimTongIcon(
            drawableId = R.drawable.ic_beil_off,
            contentDescription = "beil off icon",
        )

        @Stable
        val Heart_On = SimTongIcon(
            drawableId = R.drawable.ic_heart_on,
            contentDescription = "heart on icon",
        )

        @Stable
        val Heart_Off = SimTongIcon(
            drawableId = R.drawable.ic_heart_off,
            contentDescription = "heart off icon",
        )

        @Stable
        val Gray_Heart_Off = SimTongIcon(
            drawableId = R.drawable.ic_heart_off_gray,
            contentDescription = "gray heart off icon",
        )

        @Stable
        val Gray_Comment = SimTongIcon(
            drawableId = R.drawable.ic_comment_gray,
            contentDescription = "gray comment icon",
        )

        @Stable
        val Close = SimTongIcon(
            drawableId = R.drawable.ic_close,
            contentDescription = "close icon",
        )

        @Stable
        val Logo = SimTongIcon(
            drawableId = R.drawable.ic_logo,
            contentDescription = "logo",
        )
    }
}
