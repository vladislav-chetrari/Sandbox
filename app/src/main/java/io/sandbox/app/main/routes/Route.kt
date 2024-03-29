package io.sandbox.app.main.routes

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import io.sandbox.R

enum class Route(
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int,
    @ColorRes val colorResId: Int
) {
    PAGINATION(
        R.string.route_pagination,
        R.string.route_pagination_description,
        R.color.route_pagination_bg
    ),
    NAVIGATION(
        R.string.route_navigation,
        R.string.route_navigation_description,
        R.color.route_navigation_bg
    ),
    CAMERA(
        R.string.route_camera,
        R.string.route_camera_description,
        R.color.route_camera_bg
    ),
    SENSORS(
        R.string.route_sensors,
        R.string.route_sensors_description,
        R.color.route_sensors,
    ),
    OTHER(
        R.string.route_other,
        R.string.route_other_description,
        R.color.route_other_bg
    )
}