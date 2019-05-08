package io.sandbox.data.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import io.sandbox.R

enum class CharacterStatus(@StringRes val stringResId: Int, @ColorRes val colorResId: Int) {
    @SerializedName("Alive")
    ALIVE(R.string.character_status_alive, android.R.color.holo_green_dark),
    @SerializedName("Dead")
    DEAD(R.string.character_status_dead, android.R.color.holo_red_dark),
    @SerializedName("unknown")
    UNKNOWN(R.string.character_status_unknown, android.R.color.holo_orange_dark)
}