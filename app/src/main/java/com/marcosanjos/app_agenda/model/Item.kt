package com.marcosanjos.app_agenda.model

/**
 * Represents a basic item in the agenda.
 *
 * @property id Unique identifier for the item.
 */
data class Item(
    val id: String,
    val value: ItemValue
)

/**
 * Detailed information for an agenda entry, including personal details and location.
 *
 * @property id Unique identifier for the item value.
 * @property name First name of the person.
 * @property surname Surname of the person.
 * @property profession Profession or occupation of the person.
 * @property age Age of the person.
 * @property imageUrl URL for the profile image.
 * @property location Geographic location details.
 */
data class ItemValue(
    val id: String,
    val name: String,
    val surname: String,
    val profession: String,
    val age: String,
    val imageUrl: String,
    val location: ItemLocation,
) {
    val fullName : String
        get() = "$name $surname"
}



/**
 * Represents a geographic location with a name and coordinates.
 *
 * @property name Name or description of the location.
 * @property latitude Latitude coordinate.
 * @property longitude Longitude coordinate.
 */
data class ItemLocation(
    val name: String,
    val latitude: String,
    val longitude: String,
)
