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
package com.yushanginfo.erp.order.admin.web.action.base

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import com.yushanginfo.erp.order.admin.web.helper.TechnicImportHelper
import com.yushanginfo.erp.base.model.{Department, Machine, Technic}
import org.beangle.data.dao.OqlBuilder
import org.beangle.data.transfer.excel.ExcelSchema
import org.beangle.data.transfer.importer.ImportSetting
import org.beangle.data.transfer.importer.listener.ForeignerListener
import org.beangle.webmvc.api.annotation.response
import org.beangle.webmvc.api.view.Stream
import org.beangle.webmvc.entity.action.RestfulAction

class TechnicAction extends RestfulAction[Technic] {

  @response
  def downloadTemplate(): Any = {
    val machines = entityDao.search(OqlBuilder.from(classOf[Machine], "p").orderBy("p.code")).map(_.code)
    val departs = entityDao.search(OqlBuilder.from(classOf[Department], "p").orderBy("p.code")).map(_.code)

    val schema = new ExcelSchema()
    val sheet = schema.createScheet("数据模板")
    sheet.title("工艺信息模板")
    sheet.remark("特别说明：\n1、不可改变本表格的行列结构以及批注，否则将会导入失败！\n2、必须按照规格说明的格式填写。\n3、可以多次导入，重复的信息会被新数据更新覆盖。\n4、保存的excel文件名称可以自定。")
    sheet.add("编码", "technic.code").length(15).required().remark("≤10位")
    sheet.add("名称", "technic.name").length(100).required().remark("≤100位")
    sheet.add("说明", "technic.description").length(100).remark("≤100位")
    sheet.add("性质", "technic.internal").required().bool()
    sheet.add("加工中心编号", "technic.machine.code").ref(machines).required()
    sheet.add("部门编号", "technic.department.code").ref(departs).required()
    val code = schema.createScheet("数据字典")
    code.add("加工中心编号").data(machines)
    code.add("部门编号").data(departs)
    val os = new ByteArrayOutputStream()
    schema.generate(os)
    Stream(new ByteArrayInputStream(os.toByteArray), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "工艺模板.xlsx")
  }

  protected override def configImport(setting: ImportSetting): Unit = {
    val fl = new ForeignerListener(entityDao)
    fl.addForeigerKey("code")
    setting.listeners = List(fl, new TechnicImportHelper(entityDao))
  }
}