package com.repsol.core_domain.common.entities

enum class CostCenter(val code: String, val description:String){
    COST_CENTER_01("1","Centro de costo 01"),
    COST_CENTER_02("2","Centro de costo 02"),
    COST_CENTER_03("3","Centro de costo 03");

    companion object {

        fun fromCode(code: String): DocumentType? {
            return DocumentType.entries.find { it.code == code }
        }
    }
}