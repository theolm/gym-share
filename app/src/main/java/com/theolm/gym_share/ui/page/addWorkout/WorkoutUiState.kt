package com.theolm.gym_share.ui.page.addWorkout

data class WorkoutUiState(
    val uid: Int? = null,
    val title: String = "",
    val setList: List<String> = listOf(),
) {
    fun addSet() = copy(
        setList = setList.toMutableList().apply { add("") }
    )

    fun deleteSet(index: Int) = copy(
        setList = setList.toMutableList().apply { removeAt(index) }
    )

    fun editSet(index: Int, title: String) =
        copy(
            setList = setList.toMutableList().apply {
                removeAt(index)
                add(index, title)
            }
        )

}
