package org.miguelangellv.springboot.primeng

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.NumberPath
import com.querydsl.core.types.dsl.StringPath

public class Filter(
    public val field: String,
    public val value: String?,
    public val matchMode: String?,
    public val operator: String?) {



    public fun filter(path: StringPath): BooleanExpression? {

       return when (matchMode) {
            "startsWith" -> path.startsWithIgnoreCase(value)
            "endsWith" -> path.endsWithIgnoreCase(value)
            "contains" -> path.containsIgnoreCase(value)
            "equals" -> path.equalsIgnoreCase(value)
            "notStartWith" -> path.startsWithIgnoreCase(value).not()
            "notEndWitch" -> path.endsWithIgnoreCase(value).not()
            "notContains" -> path.containsIgnoreCase(value).not()
            "notEquals" -> path.equalsIgnoreCase(value).not()
           else -> null
        }
    }


    public fun filter(path: NumberPath<Int>): BooleanExpression? {

        val value = value?.toIntOrNull() ?: return null

        return when (matchMode) {
            "gt" -> path.gt(value)
            "gte" -> path.goe(value)

            "lt" -> path.lt(value)
            "lte" -> path.loe(value)

            "equals" -> path.eq(value)
            "notEquals" -> path.ne(value)
            else -> null
        }

    }

}
