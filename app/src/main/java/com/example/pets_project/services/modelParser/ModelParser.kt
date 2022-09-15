package com.example.pets_project.services.modelParser

import com.example.pets_project.services.network.models.AdData
import com.example.pets_project.ui.screens.main.addAd.model.AdViewData

interface ModelParser {
    fun adListParser(adList: List<AdData>): List<AdViewData>
}
