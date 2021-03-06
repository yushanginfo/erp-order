/*
 * OpenURP, Agile University Resource Planning Solution.
 *
 * Copyright © 2014, The OpenURP Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.yushanginfo.erp.order.model

object OrderStatus extends Enumeration(0) {

  class Status(val name: String) extends super.Val {
  }

  val Original = new Status("初始")
  val Submited = new Status("评审中")
  val Unpassed = new Status("待复审")
  val Review = new Status("复审中")
  val Passed = new Status("通过")
  val Cancel = new Status("取消")

}
