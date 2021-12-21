package gg.scala.plugin.check

import gg.scala.plugin.check.checks.ExampleCheck

object CheckHandler
{

    val CHECKS: List<Check> = listOf(
        ExampleCheck()
    )

}