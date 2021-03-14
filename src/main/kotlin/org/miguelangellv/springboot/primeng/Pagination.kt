package org.miguelangellv.springboot.primeng

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.NumberPath
import com.querydsl.core.types.dsl.StringPath
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

public class Pagination(

    public val page: Int,
    public val size: Int,
    public val direction: Int,
    public val columns: String?,
    public val filters: List<Filter>,
) {

    public val pagination: PageRequest get()  {

        if (columns == null)
            return PageRequest.of(page, size)

        val c = columns.split(",")

        val sort = if (direction == 1)
            Sort.by(Sort.Direction.ASC, *c.toTypedArray())
        else
            Sort.by(Sort.Direction.DESC, *c.toTypedArray())


        return PageRequest.of(page, size, sort)
    }



    public fun filter(path: NumberPath<Int>, query: BooleanBuilder) {
        val field = path.root.metadata.name

        filters
            .filter { it.field == field }
            .mapNotNull { it.filter(path) }
            .forEach {
                query.and(it)
            }

    }


    public fun filter(path: StringPath, query: BooleanBuilder) {
        val field = "$path".substringAfter(".")

        filters
            .filter { it.field == field }
            .mapNotNull { it.filter(path) }
            .forEach {
                query.and(it)
            }

    }

}
