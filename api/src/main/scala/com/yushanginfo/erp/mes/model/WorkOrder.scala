/*
 * Agile Enterprice Resource Planning Solution.
 *
 * Copyright © 2020, The YushangInfo Software.
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
package com.yushanginfo.erp.mes.model

import java.time.{Instant, LocalDate}

import com.yushanginfo.erp.base.model.{Customer, Factory}
import org.beangle.commons.collection.Collections
import org.beangle.data.model.LongId
import org.beangle.data.model.pojo.{Coded, Remark, Updated}

import scala.collection.mutable

/** 工单
 */
class WorkOrder extends LongId with Coded with Updated with Remark {

  /** 生产批号 */
  var batchNum: String = _

  /** 客户信息 */
  var customer: Customer = _

  /** 产品信息 */
  var product: Product = _

  /** 工单工艺 */
  var technicScheme: TechnicScheme = _

  /** 工单类型 */
  var orderType: SalesOrderType = _

  /** 数量 */
  var amount: Int = _

  /** 客户交付日期 */
  var deadline: LocalDate = _

  /** 计划交付日期 */
  var plannedEndOn: LocalDate = _

  /** 评审交付日期 */
  var scheduledOn: Option[LocalDate] = None

  /** 工单状态 */
  var status: OrderStatus.Status = OrderStatus.Original

  /** 到料日期 */
  var materialAssess: Option[MaterialAssess] = None

  /** 部门评审 */
  var assesses: mutable.Buffer[DepartAssess] = Collections.newBuffer[DepartAssess]

  /** 生产工厂 */
  var factory: Factory = _

  /** 创建日期 */
  var createdAt: Instant = _
}