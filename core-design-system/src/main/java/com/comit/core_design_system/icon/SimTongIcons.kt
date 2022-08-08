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
    val Back = R.drawable.ic_back
    val Comment = R.drawable.ic_comment
    val Image = R.drawable.ic_image
    val Link = R.drawable.ic_link
    val More = R.drawable.ic_more
    val MyPage = R.drawable.ic_mypage
    val Next = R.drawable.ic_next
    val Photo = R.drawable.ic_photo
    val Plus = R.drawable.ic_plus
    val Send = R.drawable.ic_send
    
    val ThinOption = R.drawable.ic_option_thin
    val BoldOption = R.drawable.ic_option_bold

    fun Beil(state: Boolean) =
        if(state) R.drawable.ic_beil_on else R.drawable.ic_beil_off

    fun Heart(state: Boolean) =
        if(state) R.drawable.ic_heart_on else R.drawable.ic_heart_off
}