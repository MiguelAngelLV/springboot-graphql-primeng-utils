package org.miguelangellv.springboot.graphql

import graphql.language.StringValue
import graphql.schema.*
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

public object Scalars {


    public fun getDate(): GraphQLScalarType {

        val coercing = object : Coercing<Date, String> {

            override fun serialize(input: Any?): String {
                if (input is Date)
                    return dateFormatter.format(input)

                if (input is Long)
                    return dateFormatter.format(Date(input))

                if (input is String)
                    return input

                throw CoercingSerializeException("Get $input")
            }


            override fun parseValue(input: Any?): Date {

                if (input is Date)
                    return input

                if (input is String)
                    return dateFormatter.parse(input)

                if (input is Long)
                    return Date(input)


                throw CoercingParseValueException("Get $input")

            }

            override fun parseLiteral(input: Any?): Date {

                if (input !is StringValue)
                    throw CoercingParseLiteralException("Expected AST type 'StringValue'. Get $input ")


                return parseValue(input.value)
            }

        }
        return GraphQLScalarType
            .newScalar()
            .name("Date")
            .description("An RFC-3339 compliant Full Date Scalar")
            .coercing(coercing)
            .build()

    }


    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")


}
