package com.example.mtapp.data

import com.example.mtapp.Models.Show

interface StageSyncRepository {
    val shows: List<Show>
}