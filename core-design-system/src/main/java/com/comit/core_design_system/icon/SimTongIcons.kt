package com.comit.core_design_system.icon

import com.comit.core_design_system.R

object SimTongIcons {

    object Navi {
        val Home = R.drawable.ic_navi_home
        val Idea = R.drawable.ic_navi_idea
        val News = R.drawable.ic_navi_news
        val Shopping = R.drawable.ic_navi_shopping
        val Vote = R.drawable.ic_navi_vote
    }

    object Alarm {
        val Plan = R.drawable.ic_alarm_plan
        val Post = R.drawable.ic_alarm_post
    }

    val Add = R.drawable.ic_add
    val Comment = R.drawable.ic_comment
    val Image = R.drawable.ic_image
    val Link = R.drawable.ic_link
    val More = R.drawable.ic_more
    val MyPage = R.drawable.ic_mypage
    val Next = R.drawable.ic_next
    val Photo = R.drawable.ic_photo
    val Plus = R.drawable.ic_plus
    val Send = R.drawable.ic_send
    val Trash = R.drawable.ic_trash

    fun Password(visible: Boolean) =
        if (visible) R.drawable.ic_password_visible else R.drawable.ic_password_invisible

    fun Option(isThin: Boolean) =
        if (isThin) R.drawable.ic_option_thin else R.drawable.ic_option_bold

    fun Back(isSmail: Boolean) =
        if (isSmail) R.drawable.ic_back_small else R.drawable.ic_back_big

    fun Beil(state: Boolean) =
        if (state) R.drawable.ic_beil_on else R.drawable.ic_beil_off

    fun Heart(state: Boolean) =
        if (state) R.drawable.ic_heart_on else R.drawable.ic_heart_off

    fun Heart(state: Boolean, isGray: Boolean) =
        if (isGray){
            if(state) R.drawable.ic_heart_on else R.drawable.ic_heart_off_gray
        }else{
            if(state) R.drawable.ic_heart_on else R.drawable.ic_heart_off
        }

    fun Comment(isGray: Boolean) =
        if(isGray) R.drawable.ic_comment_gray else R.drawable.ic_comment
}
