package com.theolm.gym_share.data.repositories

import com.theolm.gym_share.domain.WorkoutPlan
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import kotlin.random.Random


class WorkoutPlanRepoInMemory @Inject constructor() : WorkoutPlanRepo {
    private val list = arrayListOf<WorkoutPlan>()
    private val channel = Channel<List<WorkoutPlan>>(Channel.CONFLATED)

    override suspend fun get(id: Int): WorkoutPlan {
        list.forEach {
            if (it.id == id) return it
        }

        throw Exception("WorkoutPlan don't exist")
    }

    override suspend fun save(workoutPlan: WorkoutPlan) {
        val updateIndex = list.indexOfFirst { it.id == workoutPlan.id }

        if (updateIndex >= 0) {
            /* Update */
            list.removeAt(updateIndex)
            list.add(updateIndex, workoutPlan)
        } else {
            /* Create new */
            val memWorkout = workoutPlan.copy(id = Random.nextInt())
            list.add(memWorkout)
        }

        channel.send(ArrayList(list))
    }

    override suspend fun delete(workoutPlan: WorkoutPlan) {
        list.remove(workoutPlan)
        channel.send(ArrayList(list))
    }

    override fun getAll(): Flow<List<WorkoutPlan>> = channel.receiveAsFlow()

}
