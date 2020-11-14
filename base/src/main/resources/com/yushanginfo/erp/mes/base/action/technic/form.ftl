[#ftl]
[@b.head/]
[@b.toolbar title="工艺基本信息"]bar.addBack();[/@]
  [@b.form action=b.rest.save(technic) theme="list"]
    [@b.textfield name="technic.code" label="代码" value="${technic.code!}" required="true" maxlength="10"/]
    [@b.textfield name="technic.name" label="名称" value="${technic.name!}" required="true" maxlength="80"/]
    [@b.radios name="technic.internal" label="性质" value=technic.internal?string("1","0") items="1:厂内,0:委外" comment="厂内需要选择加工中心"/]
    [@b.select name="technic.machine.id" label="加工中心" items=machines value=technic.machine!  option=r"${item.code} ${item.name}" required="false"/]
    [@b.textfield name="technic.description" label="说明" value=technic.description! required="false" maxlength="100"/]
    [@b.select name="technic.depart.id" label="负责部门" items=departs value=technic.depart! required="true"/]
    [@b.textfield name="technic.remark" label="备注" value="${technic.remark!}" maxlength="190"/]
    [@b.formfoot]
      [@b.reset/]&nbsp;&nbsp;[@b.submit value="action.submit"/]
    [/@]
  [/@]
[@b.foot/]