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
package com.yushanginfo.erp.mes.wo.action

import com.yushanginfo.erp.mes.model.{OrderStatus, WorkOrder}
import com.yushanginfo.erp.order.service.OrderService
import org.beangle.webmvc.api.view.View
import org.beangle.webmvc.entity.action.RestfulAction

class MaterialAction extends RestfulAction[WorkOrder] {
  var orderService: OrderService = _

  override protected def indexSetting(): Unit = {
  }

  override def saveAndRedirect(entity: WorkOrder): View = {
    entity.status = OrderStatus.Submited
    orderService.recalcState(entity)
    super.saveAndRedirect(entity)
  }

}