package com.comit.common.extension

import android.content.Context
import android.widget.Toast

fun String.shortToast(context: Context) =
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()

fun String.longToast(context: Context) =
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
