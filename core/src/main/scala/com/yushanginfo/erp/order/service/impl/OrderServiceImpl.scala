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
package com.yushanginfo.erp.order.service.impl

import com.yushanginfo.erp.order.model.{OrderStatus, SalesOrder}
import com.yushanginfo.erp.order.service.OrderService
import org.beangle.data.dao.EntityDao

class OrderServiceImpl extends OrderService {

  var entityDao: EntityDao = _

  override def recalcState(order: SalesOrder): Unit = {
    val notComplete =
      order.technicScheme.technics.exists { pt =>
        val passed = order.assesses.exists { assess =>
          assess.technic == pt.technic && assess.passed
        }
        !passed
      }

    //计算计划完工时间
    if (order.materialDate.nonEmpty && !notComplete && order.scheduledOn.isEmpty) {
      order.scheduledOn = order.materialDate
      var scheduledOn = order.materialDate.get
      order.assesses.foreach(assess =>
        scheduledOn = scheduledOn.plusDays(assess.days.toLong)
      )
      order.scheduledOn = Some(scheduledOn)
      if (order.scheduledOn.get.compareTo(order.deadline) > 0) {
        order.status = OrderStatus.Unpassed
      } else {
        order.status = OrderStatus.Passed
      }
    }
    entityDao.saveOrUpdate(order)
  }
}
